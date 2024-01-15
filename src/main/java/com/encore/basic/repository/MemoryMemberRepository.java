package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepository {
    private final List<Member> memberDB;

    public MemoryMemberRepository() { // 생성자 수정
        memberDB = new ArrayList<>();
    }


    public List<Member> members(){
        return memberDB;
    }

    public void memberCreate(Member member){
        memberDB.add(member);
    }
}