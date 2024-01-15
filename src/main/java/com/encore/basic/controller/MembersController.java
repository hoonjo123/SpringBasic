package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MembersController {

    private MemberService memberService;
    void MemberController(){
        memberService = new MemberService();
    }



    @GetMapping("members/create")
    public String memberCreateScreen(){
        return "member/member-create-screen";
    }


    @GetMapping("members")
    public String memberListScreen(){
        return "member/member-list";
    }



    @PostMapping("members/create")
    @ResponseBody
    public String memberCreate(MemberRequestDto memberRequestDto){
        System.out.println("name : " + memberRequestDto.getName());
        System.out.println("email : " +memberRequestDto.getEmail());
        System.out.println("password : " +memberRequestDto.getPassword());
        return "ok";
    }

}
