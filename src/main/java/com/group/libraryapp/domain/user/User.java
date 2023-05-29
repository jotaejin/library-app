package com.group.libraryapp.domain.user;

import com.group.libraryapp.domain.user.loanhistory.UserLoanHistory;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity//User객체와 User테이블을 같은 것으로 본다
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)//primary key를 자동 생성
    private Long id = null;

    @Column(nullable = false, length = 20)// null이 못들어감 name varchar(20)
    private String name;
    private Integer age;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL,orphanRemoval = true)//한명의 유저는 여러가지의 데이터를 가질수있다
    //User 객체에서 포함된 모든 UserLoanHistory를 조회하려면 List<UserLoanHistory> 타입의 필드를 가져야 한다
    private List<UserLoanHistory> userLoanHistories = new ArrayList<>();

    protected User(){}

    public User(String name, Integer age) {
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException(String.format("잘못된 name(%s)이 들어 왔습니다.", name));
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

    public Long getId(){
        return id;
    }

    public void updateName(String name){//변경되는 이름
        this.name = name;
    }

    public void loanBook(String bookBame){//유저 대출기록에 유저 데이터 추가
        this.userLoanHistories.add(new UserLoanHistory(this,bookBame));
    }

    public void returnBook(String bookName){
        UserLoanHistory targetHistory = this.userLoanHistories.stream()//함수형 메소드 시작
                .filter(history -> history.getBookName().equals(bookName))//userLoanHistories 중에서 책 이름이 매개변수 bookName과 같은것만 남게 필터링
                .findFirst()//첫번째로 찾은 userLoanHistories를 찾는다
                .orElseThrow(IllegalArgumentException::new);
        targetHistory.doReturn();//그렇게 찾은 userLoanHistories 를 이 안에서 반납처리
    }



}
