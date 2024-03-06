package com.group.iibrayapp.repository.fruit;

import com.group.iibrayapp.request.FruitRequest;
import com.group.iibrayapp.request.IdCheckRequest;
import com.group.iibrayapp.response.FruitSumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

@Repository
@RequiredArgsConstructor
public class FruitReposiotry {
    private final JdbcTemplate jdbcTemplate;

    public int fruit(FruitRequest fruitRequest){
        String sql =  "insert into fruit (name, warehousingDate, price) values (?, ?, ?)";
        return jdbcTemplate.update(sql,fruitRequest.getName(),fruitRequest.getWarehousingDate(),fruitRequest.getPrice());
    }

    public FruitSumResponse fruitSumResponses(String name){
        String SalesSql = "select sum(price) from fruit where name = ? and check_id = 1";
        String NotSalesSql = "select sum(price) from fruit where name = ? and check_id = 0";

        Long SalesResult = jdbcTemplate.queryForObject(SalesSql, new Object[]{name}, Long.class);
        Long NotSalesResult = jdbcTemplate.queryForObject(NotSalesSql, new Object[]{name}, Long.class);

        return new FruitSumResponse(NotSalesResult,SalesResult);
    }

    public int sellFruit(IdCheckRequest request) {
        String sql = "SELECT * FROM fruit WHERE id = ?";
        boolean checkId = jdbcTemplate.query(sql, (rs, rowNum) -> 0, request.getId()).isEmpty();

        if (checkId) {
            throw new IllegalArgumentException();
        }

        String sql2 = "UPDATE fruit SET check_id = 1 WHERE id = ?";
        int id = jdbcTemplate.update(sql2, request.getId());
        return id;
    }
}
