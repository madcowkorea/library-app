package com.group.libraryapp.domain.book;

import javax.annotation.processing.Generated;
import javax.persistence.*;


@Entity
public class Book {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null ;

    @Column(nullable = false)
    private String name;

    protected Book(){
    }

    public Book(String name) throws IllegalAccessException {
        if(name == null || name.isBlank()){
            throw new IllegalAccessException(String.format("잘못된 name(%s)이 들어왔습니다" , name));
        }
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}
