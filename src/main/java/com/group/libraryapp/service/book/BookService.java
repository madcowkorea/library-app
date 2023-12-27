package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.User;
import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.loanhistory.UserLoanHistory;
import com.group.libraryapp.dto.book.BookCreateRequest;
import com.group.libraryapp.dto.book.BookLoanRequest;
import com.group.libraryapp.dto.book.BookReturnRequest;
import com.group.libraryapp.repository.user.UserLoanHistoryRepository;
import com.group.libraryapp.repository.user.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
        private final BookRepository bookRepository;
        private final UserLoanHistoryRepository userLoanHistoryRepository;
        private final UserRepository userRepository;

    public BookService(BookRepository bookRepository , UserLoanHistoryRepository userLoanHistoryRepository , UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository  = userRepository;

    }

    @Transactional
    public void saveBook(BookCreateRequest request) throws IllegalAccessException {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) throws IllegalAccessException {
        // 1. 책 정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalAccessException::new);
        // 2. 대출 기록 정보를 확인해서 대출중인지 확인한다.
        // 3. 확인후 대출중이라면 예외를 발생시킴
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName() , false)){
            throw new IllegalAccessException("진작 대출되어 있는 책입니다.");
        }
        //4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalAccessException::new);
        //5. 유저 정보와 책  정보 기반으로 UserLoanHistory를 저장
        userLoanHistoryRepository.save(new UserLoanHistory(user.getId() , book.getName()));
    }

    @Transactional
    public void returnBook(BookReturnRequest request) throws IllegalAccessException {
        //4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalAccessException::new);

       UserLoanHistory history = userLoanHistoryRepository.findByUserIdAndBookName(user.getId() ,request.getBookName())
                .orElseThrow(IllegalAccessException::new);
       //반납처리
       history.doReturn();
        // 영속성 컨텍스트가 메서드 내부에 있어서 userLoanHistoryRepository.save() 해주지 않아도 변경을 감지함
    }


}
