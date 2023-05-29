package com.group.libraryapp.dto.request;

public class BookLoanRequest {//책 대출 요청

    private String userName;
    private String bookName;

    public String getUserName() {
        return userName;
    }

    public String getBookName() {
        return bookName;
    }
}
