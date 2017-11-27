package com.flightright.app.member_service.entity;

import static org.junit.Assert.*;
import static org.hamcrest.CoreMatchers.containsString;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Set;
import java.util.TimeZone;

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
    	  Member member = new Member(null, null, "chris", new Date(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 1, constraintViolations.size() );
    	  assertEquals("First name can not be null", constraintViolations.iterator().next().getMessage());
      }
      
      @Test
      public void lastNameIsNull(){
    	  Member member = new Member(null, "jack", null, new Date(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 1, constraintViolations.size() );
    	  assertEquals("Last name can not be null", constraintViolations.iterator().next().getMessage());
      }
      
      @Test
      public void memberisValid(){
    	  Member member = new Member(null, "jack", "chris", new Date(), 12345);
    	  Set<ConstraintViolation<Member>> constraintViolations = validator.validate( member );
    	  assertEquals( 0, constraintViolations.size() );
      }
      
      @Test
      public void whenSerializingUsingJsonFormat_thenCorrect() throws JsonProcessingException, ParseException {
          SimpleDateFormat df = new SimpleDateFormat("dd-MM-yyyy");
          df.setTimeZone(TimeZone.getTimeZone("UTC"));
       
          String toParse = "20-02-2015";
          Date date = df.parse(toParse);
          Member member = new Member(null, "jack", "chris", date, 12345);
          String result = new ObjectMapper().writeValueAsString(member);
           
          assertThat(result, containsString(toParse));
      }

}