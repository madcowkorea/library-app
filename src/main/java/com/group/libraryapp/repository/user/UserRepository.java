package com.group.libraryapp.repository.user;

import com.group.libraryapp.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {

    //반환 타입은 User이고 findByName 가 중요함
    /*find 는 1개만 날라감
    * findAll 은 List<타입> 반환
    * */
    Optional<User> findByName(String name);

    
}
