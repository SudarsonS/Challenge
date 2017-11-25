package com.flightright.app.member_service.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.exception.MemberValidationException;
import com.flightright.app.member_service.reposiory.MemberRepository;
import com.flightright.app.member_service.util.Util;

@Service
public class MemberService {

	@Autowired
	MemberRepository memberRepository;

	public void createMember(Member member) {
		if(Util.isFutureDate(member.getDateOfBirth())){
			throw new MemberValidationException("Future Date not valid");
		}
		memberRepository.save(member);
	}
}
