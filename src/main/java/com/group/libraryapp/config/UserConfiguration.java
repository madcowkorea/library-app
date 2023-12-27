package com.group.libraryapp.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;

//@Configuration
public class UserConfiguration {

    /*
    외부 라이브러리, 프레임워크에서 만든 클래스를 등록할때 사용
    ex:JdbTemplate
    */
   /* @Bean
    public UserRepository userRepository(JdbcTemplate jdbcTemplate){
        return new UserRepository(jdbcTemplate);
    }*/
}
