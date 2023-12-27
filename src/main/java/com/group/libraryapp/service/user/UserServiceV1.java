package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.response.UserResponse;
import com.group.libraryapp.dto.request.user.request.UserCreateRequest;
import com.group.libraryapp.dto.request.user.request.UserUpdateRequest;
import com.group.libraryapp.repository.user.UserJdbcRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceV1 {

    private final UserJdbcRepository repository;

    public UserServiceV1(UserJdbcRepository userRepository) {
        repository = userRepository;
    }

    public void saveUser(UserCreateRequest request){
        repository.saveUser(request.getName() , request.getAge());
    }

    public List<UserResponse> getUsers(){
        return repository.getUsers();
    }

    public void updateUser(UserUpdateRequest request) throws IllegalAccessException {
        /*존재 여부 확인후 예외처리*/
        if(repository.isUserNotExist(request.getId())){
            throw new IllegalAccessException();
        }
        repository.updateUserName(request.getName() , request.getId());
    }

    public void deleteUser(String name) throws IllegalAccessException {
        /*존재 여부 확인후 예외처리*/
        if(repository.isUserNotExist(name)){
            throw new IllegalAccessException();
        }
        repository.deleteUser(name);
    }
}
