package com.flightright.app.member_service.reposiory;

import org.springframework.data.jpa.repository.JpaRepository;

import com.flightright.app.member_service.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{
}
