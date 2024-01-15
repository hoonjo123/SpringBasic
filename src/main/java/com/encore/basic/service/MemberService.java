package com.encore.basic.service;

import com.encore.basic.domain.Member;
import com.encore.basic.domain.MemberRequestDto;
import com.encore.basic.domain.MemberResponseDto;
import com.encore.basic.repository.MemoryMemberRepository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class MemberService {
    private final MemoryMemberRepository memoryMemberRepository;
    static int totalId = 0;
    public MemberService(){
        memoryMemberRepository = new MemoryMemberRepository();
    }

    public List<MemberResponseDto> members(){
        List<Member> members = memoryMemberRepository.members();
        List<MemberResponseDto> memberResponseDtos = new ArrayList<>();
        for(Member member : members){
            MemberResponseDto memberResponseDto = new MemberResponseDto();
            memberResponseDto.setName(member.getName());
            memberResponseDto.setEmail(member.getEmail());
            memberResponseDto.setPassword(member.getPassword());
            memberResponseDtos.add(memberResponseDto);
        }
        return memberResponseDtos;
    }

    public void memberCreate(MemberRequestDto memberRequestDto){
        LocalDateTime now = LocalDateTime.now();
        totalId += 1;
        Member member = new Member(
                totalId,
                memberRequestDto.getName(),
                memberRequestDto.getEmail(),
                memberRequestDto.getPassword(),
                now);
        memoryMemberRepository.memberCreate(member);
    }
}