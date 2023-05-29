package com.group.libraryapp.service.user;

import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import com.group.libraryapp.repository.user.UserJdbcRepositroy;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepositroy userRepository;

    public UserServiceV1(UserJdbcRepositroy userRepository) {
        this.userRepository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        userRepository.saveUser(request.getName(),request.getAge());
    }

    public List<UserResponse> getUsers(){
        return userRepository.getUsers();
    }

    //현재 유저가 있는지, 없는지 등을 확인하고 예외 처리를 해준다.
    public void updateUser(UserUpdateRequest request){

        if(userRepository.isUserNoExist(request.getId())){
            throw new IllegalArgumentException();
        }

        userRepository.updateUserName(request.getName(),request.getId());
    }

    public void deleteUser(String name){
        if(userRepository.isUserNotExist(name)){
            throw new IllegalArgumentException();
        }

      userRepository.deleteUser(name);
    }


}
