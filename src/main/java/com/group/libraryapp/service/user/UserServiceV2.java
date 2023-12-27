package com.group.libraryapp.service.user;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.repository.user.UserRepository;
import com.group.libraryapp.domain.response.UserResponse;
import com.group.libraryapp.dto.request.user.request.UserCreateRequest;
import com.group.libraryapp.dto.request.user.request.UserUpdateRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

/*  @Transactional
* 하나라도 실패하면 모두실패
* 전부 성공하면 commit
* (readOnly = true)*/

@Service
public class UserServiceV2 {
    private final UserRepository repository;

    public UserServiceV2(UserRepository repository) {
        this.repository = repository;
    }

    //아래 있는 함수가 시작될때 start Transaction 을 해준다 트랜잭션 시작
    //함수가 예외없이 잘끝나면 commit
    //문제가 있으면 rollback
    @Transactional
    public void saveUser(UserCreateRequest request) throws IllegalAccessException {
        repository.save(new User(request.getName() , request.getAge()));
    }

    @Transactional(readOnly = true)
    public List<UserResponse> getUsers(){
       /*return repository.findAll().stream()
               .map(user -> new UserResponse(user.getId(), user.getName() , user.getAge()))
               .collect(Collectors.toList());*/
        //코드 깔끔하게
        return repository.findAll().stream()
                .map(UserResponse::new)
                .collect(Collectors.toList());
    }


    @Transactional
    public void updateUser(UserUpdateRequest request) throws IllegalAccessException {
        //유저가 없으면 예외 있으면 결과
        // @Transactional 영속성 콘텍스트 가 감지되어 save 해주지 않아도 트랜잭션이 끝날때 자동으로 변경을 감지해서 업데이트 해줌
        User user = repository.findById(request.getId())
                .orElseThrow(IllegalAccessException::new);
        user.updateName(request.getName());
        //repository.save(user);
    }

    @Transactional
    public void deleteUser(String name) throws IllegalAccessException {
        User user = repository.findByName(name)
                .orElseThrow(IllegalAccessException::new);
        repository.delete(user);
    }
}
