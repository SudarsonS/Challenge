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
@RequestMapping(value = "/member")
public class MemberController {

	private MemberService memberService;
	
	@Autowired
	MemberController(MemberService memberService){
		this.memberService = memberService;
	}
	
	@RequestMapping(value = "/", method = RequestMethod.POST, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> createMember(@Valid @RequestBody Member member){
		Member savedMember = memberService.createMember(member);
		return new ResponseEntity<>(savedMember, HttpStatus.CREATED);
	}
	
	@RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<Member> getMember(@PathVariable("id") long id) {
		Member member = memberService.findById(id);
		return new ResponseEntity<Member>(member, HttpStatus.OK);
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<List<Member>> listAllMembers() {
        List<Member> members = memberService.findAllMembers();
        return new ResponseEntity<List<Member>>(members, HttpStatus.OK);
    }
	
	 @RequestMapping(value = "/", method = RequestMethod.PUT)
	 public ResponseEntity<Member> updateMember(@RequestBody Member newMember) {
		Member member = memberService.updateMember(newMember);
		return new ResponseEntity<Member>(member, HttpStatus.OK);	 
	 }
	 
	 @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
	 public ResponseEntity<Void> deleteMember(@PathVariable("id") long id) {
		memberService.deleteMember(id);
		return new ResponseEntity<Void>(HttpStatus.OK);	
	 }
	 
	 @RequestMapping(value = "/", method = RequestMethod.DELETE)
	 public ResponseEntity<Void> deleteAllMembers() {
		memberService.deleteAllMembers();
		return new ResponseEntity<Void>(HttpStatus.OK);	
	 }
}
