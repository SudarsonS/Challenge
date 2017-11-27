//package com.flightright.app.member_service.repository;
//
//import com.flightright.app.member_service.Application;
//import com.flightright.app.member_service.entity.Member;
//import com.flightright.app.member_service.reposiory.MemberRepository;
//
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//
//import java.time.LocalDate;
//import java.util.List;
//
//import static org.assertj.core.api.Java6Assertions.assertThat;
//
//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringBootTest(classes = Application.class)
//public class MemberRepositoryIntegrationTest {
//
//    @Autowired
//    private MemberRepository repository;
//    
//
//    @Test
//    public void shouldReturnAllTheMembersWhichExistsInTheDatabase() throws Exception {
//        Member firstMember = new Member(1L, "John", "Chris",  LocalDate.now() , 12345);
//        Member secondMember = new Member(2L, "Chris", "Mathew",  LocalDate.now(), 12345);
//        Member thirdMember = new Member(3L, "Sara", "Williams",  LocalDate.now(), 12345);
//        repository.save(firstMember);
//        repository.save(secondMember);
//        repository.save(thirdMember);
//
//        List<Member> actualMembers = repository.findAll();
//        System.out.println("ACTUAL MEMBERS >>>>>>>>>>>>>"+actualMembers);
//        System.out.println("MEMBERS >>>>>>>>>>>>>"+firstMember+" ,"+secondMember);
//        assertThat(actualMembers).containsExactlyInAnyOrder(firstMember, secondMember, thirdMember);
//    }
//
//
//}