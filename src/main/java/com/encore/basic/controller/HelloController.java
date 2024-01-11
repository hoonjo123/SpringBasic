package com.encore.basic.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller //http 통신을 원활하게!
//RestController가 있으면 프론트가 따로 있나보다~ 라고 생각하기
//모든 요청에 ResponseBody를 붙이고 싶으면, RestController사용

//클래스차원에서 url경로를 지정하고 싶다면 @RequestMapping을 클래스 위에 선언하면서 경로지정.
//사용자 요청에 대해 분기처리가가능한 기능
//@responseBody가 없고, return타입이 String이면 templates 밑에 html파일 리턴
// data만을 return할 때에는 @ResponseBody를 붙인다.

@RequestMapping("hello")
public class HelloController {
    @GetMapping("screen")
    public String helloScreen() {
        return "screen"; //hello_String.html 파일을 찾으러 templates로 여행을 떠나지만 아무것도 없어서 오류남. -> @ResponseBody를 붙여보자
        // template might not exist or might not be accessible b ...어쩌고 저쩌고..
    }
    @GetMapping("screen-model-param")
    //?name=honggildong의 파라미터방식으로 호출방식
    public String helloScreenModelParam(@RequestParam(value = "name")String inputName, Model model){
        //화면에 데이터를 넘기고 싶을때는 model객체를 사용
        //모델의 key:value형식으로 전달
        model.addAttribute("mydata",inputName); //jsp,thyme leaf와 같은
        // 템플릿 엔진을 통해 화면에 자바문법을 동적으로 구현가능
        return "screen";

        //parameter 방식 : ?키 =밸류 방식 localhost:8080/hello/screen-model?name="honggildong"
        //pathvariable 방식 localhost:8080/hello/screen-model/1..2....3......특정한아이디값


//        localhost:8080/member?id=1
//        localhost:8080/member/1
    }


    //path variable 방식은 url을 통해 자원의 구조를 명확하게 표현할 수 있어 좀 더 RestFulAPI디자인에 적합하다.
    //RestFul이란? 현대적인 아키텍쳐에 적합한....음?
    @GetMapping("screen-model-path/{id}")
    public String helloScreenModelPath(@PathVariable int id, Model model){
        model.addAttribute("mydata",id);
        return "screen";



    }
}
