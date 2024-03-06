package com.group.iibrayapp.service.book;

import com.group.iibrayapp.domain.book.Book;
import com.group.iibrayapp.domain.user.User;
import com.group.iibrayapp.domain.user.UserRepository;
import com.group.iibrayapp.domain.user.loanhistory.UserLoanHistory;
import com.group.iibrayapp.domain.user.loanhistory.UserLoanHistoryRepository;
import com.group.iibrayapp.dto.book.BookRepository;
import com.group.iibrayapp.dto.book.request.BookCreateRequest;
import com.group.iibrayapp.dto.book.request.BookLoanRequest;
import com.group.iibrayapp.dto.book.request.BookReturnRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository bookRepository;
    private final UserLoanHistoryRepository userLoanHistoryRepository;
    private final UserRepository userRepository;

    @Transactional
    public void saveBook(BookCreateRequest request) {
        bookRepository.save(new Book(request.getName()));
    }

    @Transactional
    public void loanBook(BookLoanRequest request) {
        // 1. 책 정보를 가져온다.
        Book book = bookRepository.findByName(request.getBookName())
                .orElseThrow(IllegalArgumentException::new);

        // 2. 대출기록 정보를 확인해서 대출중인지 확인
        // 3. 만약에 확인했는데 대출 중이라면 예외를 발생
        if (userLoanHistoryRepository.existsByBookNameAndIsReturn(book.getName(), false)) {
            throw new IllegalArgumentException("진작 대출되어 있는 책입니다");
        }

        // 4. 유저 정보를 가져온다.
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

        user.loanBook(book.getName());
    }

    @Transactional
    public void returnBook(BookReturnRequest request){
        User user = userRepository.findByName(request.getUserName())
                .orElseThrow(IllegalArgumentException::new);

       user.returnBook(request.getBookName());

    }

}
