package com.group.iibrayapp.service.User;

import com.group.iibrayapp.domain.user.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class UserServiceV2Test {

    @BeforeEach
    void before()\
        UserRepository userRepository;
    }



    @Test
    void test(){
        System.out.println(userRepository.getClass());
    }
}