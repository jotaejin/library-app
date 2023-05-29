package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.dto.user.request.UserCreateRequest;
import com.group.libraryapp.dto.user.request.UserUpdateRequest;
import com.group.libraryapp.dto.user.response.UserResponse;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceV2 {

    private final UserRepository userRepository;

    public UserServiceV2(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    // 아래 있는 함수 시작될 때 start transaction;을 해준다
    //함수가 예외 없이 잘 끝났다면 commit
    //문제 있다면 rollback
    @Transactional //함수가 한몸이된다 없다면 각자 따로논다(에러가 됐디만 SAVE가된다)
    public void saveUser(UserCreateRequest request){
        userRepository.save(new User(request.getName(), request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        return userRepository.findAll().stream()
                .map(UserResponse::new)//user를 UserResponse로 변경
                .collect(Collectors.toList());//리스트로 변경
    }
    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())//id로 1개의 데이터를 가져올 수 있다
                .orElseThrow(IllegalAccessError::new);

        user.updateName(request.getName());//api를 통해 들어온 이름
        //userRepository.save(user); save를 알아서 해줌 영속성이라서
    }

    @Transactional
    public void deleteUser(String name){
        User user = userRepository.findByName(name).orElseThrow(IllegalArgumentException::new);
        if(user == null){
            throw new IllegalArgumentException();
        }

        userRepository.delete(user);
    }
}
