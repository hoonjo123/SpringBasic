package com.encore.basic.repository;

import com.encore.basic.domain.Member;
import java.util.List;

public interface MemberRepository {
    List<Member> members();
    void memberCreate(Member member);
    Member findById(int id);
}
