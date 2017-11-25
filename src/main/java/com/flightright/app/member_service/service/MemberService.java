package com.flightright.app.member_service.service;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.exception.MemberNotFoundException;
import com.flightright.app.member_service.exception.MemberValidationException;
import com.flightright.app.member_service.reposiory.MemberRepository;
import com.flightright.app.member_service.util.Util;

@Service
public class MemberService {
	private static final Logger logger = LoggerFactory.getLogger(MemberService.class);
	
	@Autowired
	MemberRepository memberRepository;

	public void createMember(Member member) {
		if(Util.isFutureDate(member.getDateOfBirth())){
			logger.debug("Member has future date.");
			throw new MemberValidationException("Future Date not valid");
		}
		memberRepository.save(member);
	}

	public Member findById(long id) {
		Member member  = memberRepository.findById(id);
		if(member == null){
			logger.debug("Member id : {}, does not found in database.", id);
			throw new MemberNotFoundException("Member does not exits");
		}
		return member;
	}

	public List<Member> findAllMembers() {
		List<Member> members = memberRepository.findAllMembers();
		if(members == null || members.isEmpty()){
			logger.debug("Table is Empty. No member found.");
			throw new MemberNotFoundException("Table is Empty. No member found.");
		}
		return members;
	}
}
