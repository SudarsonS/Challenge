package com.flightright.app.member_service.reposiory;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.flightright.app.member_service.entity.Member;

public interface MemberRepository extends JpaRepository<Member, Long>{

	@Query("select m from member m where m.memberId = ?1")
	public Member findById(long id);
	
	@Query("select m from member m")
	public List<Member> findAllMembers();
}
