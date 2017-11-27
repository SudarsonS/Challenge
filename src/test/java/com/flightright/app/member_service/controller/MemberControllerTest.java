package com.flightright.app.member_service.controller;

import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.service.MemberService;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

public class MemberControllerTest {

    @InjectMocks
    private MemberController controller;

    @Mock
    private MemberService memberService;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldCreateMemberByInvokingMemberService() throws Exception {
        Member member = new Member();

        ResponseEntity<Void> user = controller.createMember(member);

        verify(memberService).createMember(member);
        assertThat(user.getStatusCode()).isEqualTo(HttpStatus.CREATED);
    }

    @Test
    public void shouldGetAMemberByIdByInvokingMemberService() throws Exception {
        Member member = new Member();
        member.setMemberId(1L);
        when(memberService.findById(any(Long.class))).thenReturn(member);

        ResponseEntity<Member> actualMember = controller.getMember(1L);

        verify(memberService).findById(1L);
        assertThat(actualMember.getBody()).isEqualTo(member);
    }

    @Test
    public void shouldListAllTheMemberByInvokingFindAllInServer() throws Exception {
        Member firstMember = new Member();
        Member secondMember = new Member();
        List<Member> members = Arrays.asList(firstMember, secondMember);
        when(memberService.findAllMembers()).thenReturn(members);

        ResponseEntity<List<Member>> response = controller.listAllMembers();

        verify(memberService).findAllMembers();
        assertThat(response.getBody()).isEqualTo(members);
    }

}