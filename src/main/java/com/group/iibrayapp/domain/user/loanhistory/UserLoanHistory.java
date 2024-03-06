package com.group.iibrayapp.domain.user.loanhistory;

import com.group.iibrayapp.domain.user.User;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

/**
 *  @ ManyToOne
 * //대출기록은 여러개 대출기록을 소유하고있는 유저는 하나
 * 주인 테이블
 */
@NoArgsConstructor
@Entity
public class UserLoanHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id = null;

    @ManyToOne
    private User user;


    @Getter
    private String bookName;

    private boolean isReturn;

    public UserLoanHistory(User user, String bookName) {
        this.user = user;
        this.bookName = bookName;
        this.isReturn = false;
    }

    public void doReturn(){
        this.isReturn = true;
    }

}
