package com.group.libraryapp.domain.loanhistory;

import javax.persistence.GenerationType;
import javax.persistence.*;
@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    private long userId;
    private String bookName;
    private boolean isReturn;

    public UserLoanHistory(){}
    public UserLoanHistory(long userId, String bookName) {
        this.userId = userId;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }
}
