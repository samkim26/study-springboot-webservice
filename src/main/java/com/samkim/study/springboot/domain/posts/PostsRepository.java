package com.samkim.study.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

/*
* 인터페이스
* 단순히 인터페이스를 생성 후 JpaRepostory<Entity 클래스, PK 타입>을 상속하면 기본적인 CRUD 메소드가 자동으로 생성됨
* @Repository 를 추가할 필요도 없음
* Entity 클래스와 Entity Repository는 함께 위치 (도메인 패키지에서 함께 관리)
* */
public interface PostsRepository extends JpaRepository<Posts, Long> {

}
