package com.group.libraryapp.controller.user;

import com.group.libraryapp.domain.response.UserResponse;
import com.group.libraryapp.dto.request.user.request.UserCreateRequest;
import com.group.libraryapp.dto.request.user.request.UserUpdateRequest;
import com.group.libraryapp.service.user.UserServiceV1;
import com.group.libraryapp.service.user.UserServiceV2;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceV2 userServiceV2;

    /*인스턴스화를 생성하지 않아도 RestController 어노테이션 이 스프링 빈으로 등록시킨다*/
    public UserController(UserServiceV2 userServiceV2){
        this.userServiceV2 = userServiceV2;
    }

    /*VALUES (?,?) ? 안에 request.getName() , request.getAge() 들어감 유동적으로 값을 넣을수 있음 */
    @PostMapping("/user")
    public void saveUser(@RequestBody UserCreateRequest request) throws IllegalAccessException {
        userServiceV2.saveUser(request);
    }

    @GetMapping("/user")
    public List<UserResponse> getUsers(){
        return userServiceV2.getUsers();
    }

    @PutMapping("/user")
    public void updateUser(@RequestBody UserUpdateRequest request) throws IllegalAccessException {
        userServiceV2.updateUser(request);
    }

    @DeleteMapping("/user")
    public void deleteUser(@RequestParam String name) throws IllegalAccessException {
        userServiceV2.deleteUser(name);
    }

}
