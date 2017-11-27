package com.flightright.app.member_service.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.flightright.app.member_service.entity.Member;

@Repository
public interface MemberRepository extends JpaRepository<Member, Long>{
}
