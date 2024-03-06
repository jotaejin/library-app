package com.group.iibrayapp.service.User;

import com.group.iibrayapp.domain.user.UserRepository;
import com.group.iibrayapp.domain.user.User;
import com.group.iibrayapp.dto.user.request.UserCreateRequest;
import com.group.iibrayapp.dto.user.request.UserUpdateRequest;
import com.group.iibrayapp.dto.user.response.UserResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
public class UserServiceV2 {

    private final UserRepository userRepository;

    @Transactional
    public void saveUser(UserCreateRequest request){

        User user = userRepository.save(new User(request.getName(), request.getAge()));

    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
        log.info(String.valueOf("객체"+userRepository.getClass()));
        return userRepository.findAll().stream()
                .map(user -> new UserResponse(user.getId(),user.getName(),user.getAge()))
                .collect(Collectors.toList());
    }

    @Transactional
    public void updateUser(UserUpdateRequest request){
        User user = userRepository.findById(request.getId())
                .orElseThrow(IllegalArgumentException::new);

        user.updateName(request.getName());
    }

    @Transactional
    public void deleteUser(String name) {
        User user = userRepository.findByName(name)
                .orElseThrow(IllegalArgumentException::new);
        userRepository.delete(user);
    }
}
