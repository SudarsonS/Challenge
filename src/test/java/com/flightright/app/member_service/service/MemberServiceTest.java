package com.flightright.app.member_service.service;

import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.exception.MemberNotFoundException;
import com.flightright.app.member_service.exception.MemberValidationException;
import com.flightright.app.member_service.reposiory.MemberRepository;
import org.assertj.core.util.DateUtil;
import org.junit.Before;
import org.junit.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;
import static org.junit.Assert.fail;
import static org.mockito.Mockito.*;
import static org.mockito.MockitoAnnotations.initMocks;

public class MemberServiceTest {

    @InjectMocks
    private MemberService service;

    @Mock
    private MemberRepository repository;

    @Before
    public void setUp() throws Exception {
        initMocks(this);
    }

    @Test
    public void shouldCreateANewMemberByInvokingRepository() throws Exception {
        Member member = new Member();
        member.setDateOfBirth(LocalDate.now());

        service.createMember(member);

        verify(repository).save(member);
    }

    @Test(expected = MemberValidationException.class)
    public void shouldNotSaveAMemberWhoseDateOfBirthIsInTheFuture() throws Exception {
        Member member = new Member();
        member.setDateOfBirth( LocalDate.now().plusDays(1));

        service.createMember(member);

        verifyZeroInteractions(repository);
    }

    @Test
    public void shouldUpdateTheMemberByInvokingTheRepository() throws Exception {
        Member member = new Member();
        member.setMemberId(1L);
        when(repository.exists(any(Long.class))).thenReturn(true);

        service.updateMember(member);

        verify(repository).save(member);
    }

    @Test(expected = MemberNotFoundException.class)
    public void shouldNotUpdateTheMemberIfTheMemberDoesNotExistsInTheRepository() throws Exception {
        Member member = new Member();
        member.setMemberId(1L);
        when(repository.exists(any(Long.class))).thenReturn(false);

        service.updateMember(member);

        verify(repository).exists(1L);
    }

    @Test
    public void shouldUpdateTheMemberIfTheMemberDoesExistsInTheRepository() throws Exception {
        Member member = new Member();
        member.setMemberId(1L);
        when(repository.exists(any(Long.class))).thenReturn(true);
        Member updatedMember = new Member();
        when(repository.save(any(Member.class))).thenReturn(updatedMember);

        Member actualMember = service.updateMember(member);

        verify(repository).exists(1L);
        verify(repository).save(member);
        assertThat(actualMember).isEqualTo(updatedMember);
    }
    
    @Test
    public void shouldDeleteTheMemberByInvokingTheRepository() throws Exception{
    	Long id = 1L;
    	when(repository.exists(any(Long.class))).thenReturn(true);
    	
    	service.deleteMember(id);
    	
    	verify(repository).delete(id);
    }
    
    @Test(expected = MemberNotFoundException.class)
    public void shouldNotDeleteTheMemberIfTheMemberDoesNotExistsInTheRepository() throws Exception {
    	Long id = 1L;
        when(repository.exists(any(Long.class))).thenReturn(false);

        service.deleteMember(id);

        verify(repository).exists(1L);
    }
    
    @Test
    public void shouldDeleteTheMemberIfTheMemberDoesExistsInTheRepository() throws Exception {
    	Long id = 1L;
    	when(repository.exists(any(Long.class))).thenReturn(true);
    	
    	try{
    		service.deleteMember(id);
    	
    		verify(repository).exists(id);
    		verify(repository).delete(id);
    	}catch(Exception e){
    		fail("should not have thrown any error");
    	}
    }
    
    @Test
    public void shouldDeleteAllTheMembersByInvokingTheRepository() throws Exception{
    	
    	service.deleteAllMembers();

    	try{
    		verify(repository).deleteAll();
    	}catch(Exception e){
    		fail("should not have thrown any error");
    	}
    }
    
    @Test
    public void shouldGetTheMemberByInvokingTheRepository() throws Exception{
    	Long id = 1L;
    	Member member = new Member();
    	when(repository.findOne(id)).thenReturn(member);
    	
    	service.findById(id);
    	
    	verify(repository).findOne(id);
    }
    
    @Test(expected = MemberNotFoundException.class)
    public void shouldNotReturnTheMemberIfTheMemberIsNull() throws Exception{
    	Long id = 1L;
    	when(repository.findOne(id)).thenReturn(null);
    	
    	service.findById(id);
    	
    	verify(repository).findOne(id);
    }
    
    @Test
    public void shouldGetTheMemberIfItExistsInRepository() throws Exception{
    	Long id = 1L;
    	Member member = new Member();
    	when(repository.findOne(id)).thenReturn(member);
    	
    	Member actualMember = service.findById(id);
    	
    	verify(repository).findOne(id);
    	assertThat(actualMember).isEqualTo(member);
    }
    
    @Test
    public void shouldGetAllTheMembersByInvokingTheRepository() throws Exception{
    	List<Member> members = new ArrayList<Member>();
    	Member member1 = new Member();
    	Member member2 = new Member();
    	members.add(member1);
    	members.add(member2);
    	when(repository.findAll()).thenReturn(members);
    	
    	service.findAllMembers();
    	
    	verify(repository).findAll();
    }
    
    @Test(expected = MemberNotFoundException.class)
    public void shouldNotReturnTheMembersIfTheMemberListIsNull() throws Exception{
    	when(repository.findAll()).thenReturn(null);
    	
    	service.findAllMembers();
    	
    	verify(repository).findAll();
    }
    
    @Test
    public void shouldGetAllTheMembersIfItExistsInRepository() throws Exception{
    	List<Member> members = new ArrayList<Member>();
    	Member member1 = new Member();
    	Member member2 = new Member();
    	members.add(member1);
    	members.add(member2);
    	when(repository.findAll()).thenReturn(members);
    	
    	List<Member> actualMemberList = service.findAllMembers();
    	
    	verify(repository).findAll();
    	assertThat(actualMemberList).isEqualTo(members);
    }

}