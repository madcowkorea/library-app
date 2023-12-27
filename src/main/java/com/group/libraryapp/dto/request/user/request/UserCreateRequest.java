package com.group.libraryapp.dto.request.user.request;

public class UserCreateRequest {
    private String name;
    private Integer age; // int null을 표현할수 없는 자료형 , Integer null 표현 가능 자료형

    // 기본 생성자 추가
    public UserCreateRequest() {}

    public UserCreateRequest(String name, Integer age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }
}
