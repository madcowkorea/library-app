package com.group.libraryapp.dto.book;

public class BookCreateRequest {
    private String name ;

    public BookCreateRequest(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
