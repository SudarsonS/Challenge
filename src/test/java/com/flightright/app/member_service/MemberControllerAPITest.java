package com.flightright.app.member_service;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.hamcrest.Matchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;
import java.util.HashMap;
import java.util.Map;

import static com.flightright.app.member_service.util.MultiLineString.multiLineString;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;


@RunWith(SpringRunner.class)
@SpringBootTest
@AutoConfigureMockMvc
public class MemberControllerAPITest {
	
	 @Autowired
	    private MockMvc mvc;
	 
	 @Autowired
	    private ObjectMapper objectMapper;
	 
	 @Test
	 public void shouldReturnCreatedStatusIfTheMemberIsPassed() throws Exception{
		 mvc.perform(post("/member/")
	                .contentType("application/json")
	                .content(multiLineString(/*
	                    {
	                        "firstName": "John",
	                        "lastName": "Mathew",
	                        "dateOfBirth":"2017-04-23",
	                        "postalCode":12345
	                    }
	                */)
	                ))
	                .andExpect(status().is(201));
	 }
	 
	 @Test
	 public void shouldReturn400BadRequestIfFirstLameIsNull() throws Exception{
		 mvc.perform(post("/member/")
	                .contentType("application/json")
	                .content(multiLineString(/*
	                    {
	                        "firstName": "",
	                        "lastName": "Mathew",
	                        "dateOfBirth":"2017-04-23",
	                        "postalCode":12345
	                    }
	                */)
	                ))
	                .andExpect(status().is(400));
	 }
	 
	 @Test
	 public void shouldReturn400BadRequestIfLastLameIsNull() throws Exception{
		 mvc.perform(post("/member/")
	                .contentType("application/json")
	                .content(multiLineString(/*
	                    {
	                        "firstName": "John",
	                        "lastName": "",
	                        "dateOfBirth":"2017-04-23",
	                        "postalCode":12345
	                    }
	                */)
	                ))
	                .andExpect(status().is(400));
	 }
	 
	 @Test
	 public void shouldReturn400BadRequestIfItIsFutureDate() throws Exception{
		 LocalDate date = LocalDate.now().plusDays(3);
		 Map<String, Object> map = new HashMap<>();
	        map.put("firstName", "John");
	        map.put("lastName", "Chris");
	        map.put("dateOfBirth", date);
	        map.put("postalCode", 12345);
		 mvc.perform(post("/member/")
	                .contentType("application/json")
	                .content(asJson(map)))
	                .andExpect(status().is(400));
	 }
	 
	 @Test
	 public void shouldReturn404BadRequestIfGetMemberByIdIsNotPresent() throws Exception{
		 mvc.perform(get("/member/10987632467"))
         .andExpect(status().is(404));
	 }
	 
	 @Test
	 public void shouldReturnMemberForGivenId() throws Exception{
		 mvc.perform(get("/member/1"))
         .andExpect(status().is(200))
         .andExpect(jsonPath("$.firstName", is("John")))
         .andExpect(jsonPath("$.lastName", is("Mathew")))
         .andExpect(jsonPath("$.dateOfBirth", is("2017-04-23")))
         .andExpect(jsonPath("$.postalCode", is(12345)));
	 }
	 
//	 @Test
//	 public void shouldReturnAllTheMembers() throws Exception{
//		 
//		 mvc.perform(post("/member/")
//	                .contentType("application/json")
//	                .content(multiLineString(/*
//	                    {
//	                        "firstName": "John1",
//	                        "lastName": "Mathew1",
//	                        "dateOfBirth":"2017-04-23",
//	                        "postalCode":45678
//	                    }
//	                */)
//	                ))
//	                .andExpect(status().is(201));
//		 
//		 mvc.perform(get("/member/"))
//         .andExpect(status().is(200))
//         .andExpect(jsonPath("$", hasSize(2)))
//         .andExpect(jsonPath("$[0].firstName", is("John")))
//         .andExpect(jsonPath("$[1].firstName", is("John1")))
//         .andExpect(jsonPath("$[0].lastName", is("Mathew")))
//         .andExpect(jsonPath("$[1].lastName", is("Mathew1")))
//         .andExpect(jsonPath("$[0].dateOfBirth", is("2017-04-23")))
//         .andExpect(jsonPath("$[1].dateOfBirth", is("2017-04-23")))
//         .andExpect(jsonPath("$[0].postalCode", is(12345)))
//         .andExpect(jsonPath("$[1].postalCode", is(45678)));
//	 }
	 
	 @Test
	 public void shouldReturn404BadRequestIfNoMembersToGetFromDatabase() throws Exception{
		 mvc.perform(delete("/member/"))
         .andExpect(status().is(200));
		 
		 mvc.perform(get("/member/"))
         .andExpect(status().is(404));
	 }
	 
	 @Test
	 public void shouldReturn200ForUpdatingTheMember() throws Exception{
		 mvc.perform(put("/member/")
	                .contentType("application/json")
	                .content(multiLineString(/*
	                    {
	                    	"memberId":1,
	                        "firstName": "Chris",
	                        "lastName": "Mathew",
	                        "dateOfBirth":"2017-04-23",
	                        "postalCode":12345
	                    }
	                */)
	                ))
	                .andExpect(status().is(200));
	 }
	 
//	 @Test
//	 public void shouldReturn200ForDeletingTheMemberForGivenId() throws Exception{
//		 mvc.perform(delete("/member/1"))
//         .andExpect(status().is(200));
//	 }
	 
	 @Test
	 public void shouldReturn200ForDeletingAllTheData() throws Exception{
		 mvc.perform(delete("/member/"))
         .andExpect(status().is(200));
	 }
	 
	 private String asJson(Map<String, Object> map) {
	        String json = null;
	        try {
	            json = objectMapper.writeValueAsString(map);
	        } catch (Exception e) {
	            e.printStackTrace();
	        }
	        return json;
	    }
	 
	 
}
