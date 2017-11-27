package com.flightright.app.member_service.repository;

import com.flightright.app.member_service.Application;
import com.flightright.app.member_service.entity.Member;
import com.flightright.app.member_service.repository.MemberRepository;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;

import static org.assertj.core.api.Java6Assertions.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest(classes = Application.class)
@Transactional
@Rollback
public class MemberRepositoryIntegrationTest {

    @Autowired
    private MemberRepository repository;
    

    @Test
    public void shouldCreateNewMemberInTheDatabase() throws Exception{
    	 Member member = new Member(null, "John1", "Chris",  LocalDate.now() , 123455); 
    	 
    	 Member savedMember = repository.save(member);	 
    	 
    	 assertThat(savedMember).isEqualTo(member);
    }
    
    @Test
    public void shouldReturnTheMemberForTheGivenIdInTheDatabase() throws Exception{
    	 Member member = new Member(null, "Chris2", "Mathew",  LocalDate.now(), 12345);
        Member savedMember = repository.save(member);

        Member actualMember = repository.findOne(savedMember.getMemberId());
    	 
    	 assertThat(actualMember).isEqualTo(member);
    }
    
    @Test
    public void shouldReturnAllTheMembersWhichExistsInTheDatabase() throws Exception {
        Member firstMember = new Member(null, "John3", "Chris",  LocalDate.now() , 12345);
        Member secondMember = new Member(null, "Chris4", "Mathew",  LocalDate.now(), 12345);
        Member thirdMember = new Member(null, "Sara5", "Williams",  LocalDate.now(), 12345);
        repository.deleteAll();
        repository.save(firstMember);
        repository.save(secondMember);
        repository.save(thirdMember);

        List<Member> actualMembers = repository.findAll();
        assertThat(actualMembers).containsExactlyInAnyOrder(firstMember, secondMember, thirdMember);
    }

    @Test
    public void shouldUpdateTheMemberInTheDatabase() throws Exception{
    	Member member = new Member(null, "John", "Chris",  LocalDate.now() , 12345);
    	repository.save(member);
    	member.setFirstName("Abar");
    	
    	repository.save(member);
    	
    	Member actualMember = repository.findOne(member.getMemberId());
    	assertThat(actualMember).isEqualTo(member);
    }
    
    @Test
    public void shouldDeleteTheMemberForTheGivenIdInTheDatabase() throws Exception{
    	Member member = new Member(null, "John", "Chris",  LocalDate.now() , 12345);
    	repository.save(member);
    	
    	repository.delete(member.getMemberId());
    	
    	Member actualMember = repository.findOne(member.getMemberId());
    	assertThat(actualMember).isNull();
    }
    
    @Test
    public void shouldDeleteAllTheMemberInTheDatabase() throws Exception{
    	Member firstMember = new Member(null, "John3", "Chris",  LocalDate.now() , 12345);
        Member secondMember = new Member(null, "Chris4", "Mathew",  LocalDate.now(), 12345);
    	repository.save(firstMember);
    	repository.save(secondMember);
    	
    	repository.deleteAll();
    	
    	List<Member> members = repository.findAll();
    	assertThat(members).isEmpty();
    }
}