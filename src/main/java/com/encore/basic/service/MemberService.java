package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.*;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.NoSuchElementException;

@Service // 싱글톤 컴포넌트로 생성 -> 내부 @Component를 통해 "스프링 빈"으로 등록
// 스프링 빈이랑, 스프링이 생성하고 관리하는 객체를 의미
// 제어의 역전(Inversion of Control)
// IoC 컨테이너가 스프링 빈을 관리한다.(빈 생성, 의존성 주입)
public class MemberService {
    private final MemberRepository memberRepository;
    @Autowired
    public MemberService(MybatisMemberRepository mybatisMemberRepository) {
        this.memberRepository = mybatisMemberRepository;
    }
    public List<MemberResponseDto> members(){

        List<Member> members = memberRepository.findAll();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto(member.getId(), member.getName(), member.getEmail(), member.getPassword(), member.getCreate_time());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    // 사용자의 입력 값이 담긴 DTO를 통해, 실제 시스템에서 사용되는 정보를 조합해 Member 객체로 변환 후 저장
    public void memberCreate(MemberRequestDto memberRequestDto){
        Member member = new Member(memberRequestDto.getName(), memberRequestDto.getEmail(), memberRequestDto.getPassword());
        memberRepository.save(member);
    }

    public MemberResponseDto findById(int id) throws NoSuchElementException{
        //Member 객체를 MemberRequestDto로 변환
        Member member = memberRepository.findById(id).orElseThrow(NoSuchElementException::new);
        MemberResponseDto memberResponseDto = new MemberResponseDto(
                member.getId(),
                member.getName(),
                member.getEmail(),
                member.getPassword(),
                member.getCreate_time());
        return memberResponseDto;
    }
}