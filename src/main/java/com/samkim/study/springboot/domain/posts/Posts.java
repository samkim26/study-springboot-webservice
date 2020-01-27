package com.samkim.study.springboot.domain.posts;

import com.samkim.study.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter // 롬복 어노테이션
@NoArgsConstructor // 롬복 어노테이션
@Entity // JPA의 어노테이션 : 테이블과 링크될 클래스임을 나타냄, 기본값으로 카멜케이스 이름을 언더스코어 네이밍(_)으로 테이블 이름을 매칭함 (ex SalesManager.java -> sales_manager table)
public class Posts extends BaseTimeEntity {

    @Id // JPA 어노테이션 : 해당 테이블의 PK 필드를 나타냄
    @GeneratedValue(strategy = GenerationType.IDENTITY) // PK의 생성 규칙을 나타냄. 스프링 부트 2.0 에서는 IDENTITY 옵션을 추가해야만 auto_increment가 됨
    private Long id;

    @Column(length = 500, nullable = false) // 테이블의 칼럼을 나타냄. 굳이 선언하지 않더라도 해당 클래스의 필드는 모두 칼럼이 됨, 사용하는 이유는 기본값 외에 추가로 변경이 필요한 옵션 있는 경우
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder // 해당 클래스의 빌더 패턴 클래스를 생성. 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
