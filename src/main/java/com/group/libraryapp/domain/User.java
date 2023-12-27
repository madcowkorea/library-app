package com.group.libraryapp.domain;

import javax.persistence.*;

@Entity
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY) // 자동으로 id 생성
    private Long id = null;
    @Column(nullable = false , length = 20)
    //@Column(nullable = false , length = 20 , name = "name") // name varchar(20) name 이름이 변수명이랑 같으면 생략가능
    private String name;
    private Integer age;

    /*jpa 사용하기 위해선 기본 생성자가 꼭 필요하다.*/
    protected User(){}

    public User(String name, Integer age) throws IllegalAccessException {
        if(name == null || name.isBlank()){
            throw new IllegalAccessException(String.format("잘못된 name(%s)이 들어왔습니다" , name));
        }
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public Long getId() {
        return id;
    }

    public void updateName(String name){
        this.name = name;
    }
}
