package com.group.libraryapp.repository.user;

import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
//SQL을 사용해 실제 DB와의 통신을 담당한다.
@Repository
public class UserJdbcRepositroy {

    private final JdbcTemplate jdbcTemplate;

    public UserJdbcRepositroy(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public void saveUser(String name, Integer age){
        String sql = "INSERT INTO user (name, age) VALUES (?,?)";
        jdbcTemplate.update(sql, name, age);
    }

    public List<UserResponse> getUsers(){
        String sql = "SELECT * FROM user";//모든 유저 정보를 조회
        return jdbcTemplate.query(sql,(rs , rowNum) ->{
            long id = rs.getLong("id");
            String name = rs.getString("name");
            int age= rs.getInt("age");
            return new UserResponse(id,name,age);
        } );
    }

    public void updateUserName(String name, long id){
        String sql = "UPDATE user SET name = ? WHERE id = ?";//해당하는id를 새로운 네임으로 바꿔
        jdbcTemplate.update(sql, name, id);
    }

    public boolean isUserNoExist(long id){
        String readSql = "SELECT * FROM user WHERE id = ?";
       return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, id).isEmpty();//readsql의 결과가 있으면 0으로 바꿔치기한다
    }

    public boolean isUserNotExist(String name){
        String readSql = "SELECT * FROM user WHERE name = ?";
        return jdbcTemplate.query(readSql, (rs, rowNum) -> 0, name).isEmpty();//readsql의 결과가 있으면 0으로 바꿔치기한다
    }

    public void deleteUser(String name){
        String delsql = "DELETE FROM user WHERE name = ?";
        jdbcTemplate.update(delsql, name);
    }
}
