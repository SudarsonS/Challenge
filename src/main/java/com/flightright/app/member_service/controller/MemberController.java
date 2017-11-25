package com.flightright.app.member_service.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.service.MemberService;

@RestController
public class MemberController {

	MemberService memberService;
	
	@Autowired
	MemberController(MemberService memberService){
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "/member", method = RequestMethod.POST, headers="Accept=application/json")
    public ResponseEntity<Void> createUser(@Valid @RequestBody Member member){
		memberService.createMember(member);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
}
