package com.flightright.app.member_service.entity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.text.ParseException;
import java.time.LocalDate;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import javax.validation.ValidatorFactory;

import org.junit.BeforeClass;
import org.junit.Test;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class MemberValidatorTest {
	
	private static Validator validator;

	   @BeforeClass
	   public static void setUp() {
	      ValidatorFactory factory = Validation.buildDefaultValidatorFactory();
	      validator = factory.getValidator();
	   }

      @Test
      public void firstNameIsNull(){
    	  Member member = new Member(null, null, "chris",  LocalDate.now(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 1, constraintViolations.size() );
    	  assertEquals("First name can not be null", constraintViolations.iterator().next().getMessage());
      }
      
      @Test
      public void lastNameIsNull(){
    	  Member member = new Member(null, "jack", null, LocalDate.now(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 1, constraintViolations.size() );
    	  assertEquals("Last name can not be null", constraintViolations.iterator().next().getMessage());
      }
      
      @Test
      public void whenSerializingUsingJsonFormat_thenCorrect() throws JsonProcessingException, ParseException {
         
       
          String toParse = "2015-02-20";
          LocalDate date = LocalDate.of(2015, 02, 20);
          Member member = new Member(null, "jack", "chris", date, 12345);
          String result = new ObjectMapper().writeValueAsString(member);
           
          assertThat(result, containsString(toParse));
      }
      
      @Test
      public void memberisValid(){
    	  Member member = new Member(null, "jack", "chris", LocalDate.now(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 0, constraintViolations.size() );
      }

}
