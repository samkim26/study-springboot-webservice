package com.samkim.study.springboot.web;

import com.samkim.study.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@RunWith(SpringRunner.class) // 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함
@WebMvcTest(controllers = HelloController.class,
        excludeFilters = {
        @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
) // 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션, @Controller, @ControllerAdvice 등을 사용할 수 있음, @Service, @Component, @Repository 등은 사용할 수 없음
public class HelloControllerTest {

    @Autowired // 스프링이 관리하는 빈(Bean)을 주입 받음
    private MockMvc mvc; // 웹 API 테스트 할 때 사용, 스프링 MVC 테스트의 시작점, HTTP GET, POST 등에 대한 API 테스트 가능

    @WithMockUser(roles = "USER")
    @Test
    public void hello가_리턴된다() throws Exception {
        String hello = "hello";

        mvc.perform(get("/hello")) // MockMvc를 통해 /hello 주소로 HTTP GET 요청
                .andExpect(status().isOk()) // mvc.perform의 결과 검증, HTTP Header의 Status 검증, 200/404/500 등의 상태 검증, 여기선 OK 즉, 200인지 아닌지 검증
                .andExpect(content().string(hello)); // mvc.perfom의 결과 검증, 응답 본문의 내용을 검증, Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증
    }

    @WithMockUser(roles = "USER")
    @Test
    public void helloDto가_리턴된다() throws Exception {
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto")
                        .param("name", name)
                        .param("amount", String.valueOf(amount))) // API 테스트할 때 사용될 요청 파라미터를 설정함, 값은 String만 허용됨, 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야함
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name)))
                .andExpect(jsonPath("$.amount", is(amount)));
                /* jsonPath
                is에서 import 이슈!! , JSON 응답값을 필드별로 검증할 수 있는 메소드
                $를 기준으로 필드명을 명시
                */
    }
}
