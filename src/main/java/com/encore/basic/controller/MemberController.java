package com.encore.basic.controller;

import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.service.MemberService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class MemberController {

    private final MemberService memberService;
    MemberController(){
        memberService = new MemberService();
    }



    @GetMapping("members/create")
    public String memberCreateScreen(){
        return "member/member-create-screen";
    }


    @GetMapping("members")
    public String members(Model model){
        model.addAttribute("members", memberService.members());
        return "member/member-list";
    }



    @PostMapping("members/create")

    public String memberCreate(MemberRequestDto memberRequestDto) {
        memberService.memberCreate(memberRequestDto);
        return "redirect:/members";
    }


    //home생성
    @GetMapping("/")
    public String home() {
        return "member/header";
    }
}