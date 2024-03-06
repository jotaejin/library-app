package com.group.iibrayapp.service.fruit;

import com.group.iibrayapp.repository.fruit.FruitReposiotry;
import com.group.iibrayapp.request.FruitRequest;
import com.group.iibrayapp.request.IdCheckRequest;
import com.group.iibrayapp.response.FruitSumResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Service
public class FruitService {
    private final FruitReposiotry fruitReposiotry;
    public int fruit(FruitRequest fruitRequest){
       return fruitReposiotry.fruit(fruitRequest);
    }

    public FruitSumResponse fruitSumResponses(String name){
        return fruitReposiotry.fruitSumResponses(name);
    }

    public int sellFruit(IdCheckRequest request) {
        return fruitReposiotry.sellFruit(request);
    }


}
