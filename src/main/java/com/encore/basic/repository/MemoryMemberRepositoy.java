package com.encore.basic.repository;

import com.encore.basic.domain.Member;

import java.util.ArrayList;
import java.util.List;

public class MemoryMemberRepositoy {
    private List<Member> memberDB;
    void MemoryMemberRepository(){
        memberDB = new ArrayList<Member>();
    }

    public List<Member> members(){
        return memberDB;
    }

    public void memberCreate(Member member){
        memberDB.add(member);
    }
}
