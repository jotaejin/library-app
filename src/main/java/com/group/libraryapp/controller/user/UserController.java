package com.group.libraryapp.controller.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;
//API의 진입 지점으로써 HTTP Body를 객체로 변환
@RestController
public class UserController {

    private final UserServiceV2 userservice;

    //UserService 클래스 생성 외부로부터 객체를 받아 사용하는 것
    public UserController(UserServiceV2 userservice) {
        this.userservice = userservice;
    }

    @PostMapping("/user") //post /user 사용자 등록
    public void saveUser(@RequestBody UserCreateRequest request) {
        userservice.saveUser(request);
    }

    @GetMapping("/user")//목록 확인
    public List<UserResponse> getUsers() {
        return userservice.getUsers();
    }


    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) {
       userservice.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) {
        userservice.deleteUser(name);
    }
}


