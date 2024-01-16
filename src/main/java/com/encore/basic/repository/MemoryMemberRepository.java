package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Repository // 내부 @Component를 통해 "스프링 빈"으로 등록
public class MemoryMemberRepository implements MemberRepository{

    static int total_id;
    private final List<Member> memberDB;
    public MemoryMemberRepository(){
        memberDB = new ArrayList<>();
    }

    @Override
    public List<Member> findAll(){
        return memberDB;
    }

    @Override
    public Member save(Member member){
        ++total_id;
        LocalDateTime now = LocalDateTime.now();
        memberDB.add(member);
        member.setId(total_id);
        member.setCreate_time(now);
        return member;
    }

    @Override
    public Optional<Member> findById(int id) {
        for(Member member : memberDB){
            if(member.getId() == id){
                return Optional.of(member);
            }
        }
        return Optional.empty();
    }
}