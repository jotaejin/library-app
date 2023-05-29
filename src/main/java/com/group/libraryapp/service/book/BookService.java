package com.group.libraryapp.service.book;

import com.group.libraryapp.domain.book.Book;
import com.group.libraryapp.domain.book.BookRepository;
import com.group.libraryapp.domain.user.User;
import com.group.libraryapp.domain.user.UserRepository;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;
import com.group.libraryapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.libraryapp.dto.request.BookCreateRequest;
import com.group.libraryapp.dto.request.BookLoanRequest;
import com.group.libraryapp.dto.request.BookReturnRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class BookService {
    private final BookRepository bookRepository;//책 정보 저장소
    private final UserLoanHistoryRepository userLoanHistoryRepository;//유저 대출 기록 저장소
    private final UserRepository userRepository;//유저 저장소

    public BookService(BookRepository bookRepository,UserLoanHistoryRepository userLoanHistoryRepository, UserRepository userRepository) {
        this.bookRepository = bookRepository;
        this.userLoanHistoryRepository = userLoanHistoryRepository;
        this.userRepository = userRepository;
    }

    @Transactional
    public void saveBook(BookCreateRequest request) {//책 등록
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request){//책 대출
        //1. 책 정보를 가져온다
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);//책이름 가져오기

        //2. 대출기록 정보를 확인해서 대출중인지 확인
        //3. 만약 확인했는데 대출 중이면 예외
        if(userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(),false)){//false면 대여중인 책이 없는 것
            throw new IllegalArgumentException("진작 대출되어 있는 책");//대출되어 있는경우
        }
            //4. 대출 되어있지 않다면 유저 정보 가져온다
            User user = userRepository.findByName(request.getUserName())
                    .orElseThrow(IllegalArgumentException::new);
            user.loanBook(book.getName());

    }

    @Transactional
    public void returnBook(BookReturnRequest request) {//책 반납
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.returnBook(request.getBookName());
    }
}

