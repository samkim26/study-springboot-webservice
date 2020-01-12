package com.samkim.study.springboot.web;

import com.samkim.study.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // 컨트롤러를 JSON을 반환하는 컨트롤러로 만들어 줌 (예전에는 각 메소드마다 @ResponseBody를 선언함)
public class HelloController {

    @GetMapping("/hello") // HTTP Method인 Get의 요청을 받을 수 있는 API를 만들어 줌(예전에는 @RequestMapping(method=RequestMethod.Get)으로 사용됨)
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, @RequestParam("amount") int amount) {
        // @RequestParam 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        // 외부에서 name(@RequestParam("name")) 이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장하게 됨
        return new HelloResponseDto(name, amount);
    }
}
