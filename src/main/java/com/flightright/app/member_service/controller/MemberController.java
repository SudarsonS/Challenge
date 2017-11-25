package com.flightright.app.member_service.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
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
	
	@RequestMapping(value = "/member/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Void> createUser(@Valid @RequestBody Member member){
		memberService.createMember(member);
		return new ResponseEntity<Void>(HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/member/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable("id") long id) {
		Member member = memberService.findById(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/members/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.findAllMembers();
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }
 
}
