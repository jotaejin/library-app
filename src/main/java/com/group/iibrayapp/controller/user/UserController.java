package com.group.iibrayapp.controller.user;

import com.group.iibrayapp.dto.user.request.UserCreateRequest;
import com.group.iibrayapp.dto.user.request.UserUpdateRequest;
import com.group.iibrayapp.dto.user.response.UserResponse;
import com.group.iibrayapp.service.User.UserServiceV1;
import com.group.iibrayapp.service.User.UserServiceV2;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class UserController {

    private final UserServiceV2 userService;

    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest req){
        userService.saveUser(req);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userService.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request){
        userService.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam("name") String name){//delete는 api스텍상 query를 쓰므로 @RequestParam을 사용
        userService.deleteUser(name);
    }

    @GetMapping("/user/error-test")
    public void errorTest(){
        throw new IllegalArgumentException();
    }
}
