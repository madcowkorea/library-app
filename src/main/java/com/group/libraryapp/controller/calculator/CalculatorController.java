package com.group.libraryapp.controller.calculator;

import com.group.libraryapp.dto.book.BookLoanRequest;
import com.group.libraryapp.dto.request.CalculatorAddRequest;
import com.group.libraryapp.dto.request.CalculatorMultiply;
import org.springframework.web.bind.annotation.*;

@RestController //RestController 역할은 API 의 진입지점으로 만들어줌
public class CalculatorController {

    //변수를 이용한 request
    @GetMapping("/add") //HTTP Method GET /add
    public int addToNumbers( //쿼리를 통해서 넘어온 데이터를 함수에 연결해줄때 앞에 RequestParam 이라고 적어줘야함
            @RequestParam int number1,
            @RequestParam int number2
    ){
        return number1 + number2;
    }

    //객체를 이용한 request
    @GetMapping("/twoAdd") //HTTP Method GET /add
    public int addToNumbers(
            CalculatorAddRequest request //객체로도 가능함
    ){
        return request.getNumber1() + request.getNumber2();
    }

    @PostMapping("/multiply") // POST @RequestBody 어노테이션이 있어야 post api 에서 정상적으로 http body 안에 담긴 json 을 객체로 변활 할수있음
    public int multiplyTwoNumbers(@RequestBody CalculatorMultiply request){
        return request.getNumber1() * request.getNumber2();
    }

}
