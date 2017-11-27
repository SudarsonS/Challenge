package com.flightright.app.member_service.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flightright.app.member_service.exception.MemberValidationException;


public class Util {
	private static final Logger logger = LoggerFactory.getLogger(Util.class);
	public static boolean isFutureDate(Date dateOfBirth) {
		if(dateOfBirth == null){
			throw new MemberValidationException("Date of birth is null");
		}
		Instant instant = dateOfBirth.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate dob = zdt.toLocalDate();
		LocalDate today = LocalDate.now();
		logger.debug("Date of Birth converted to local date = {}, today's date = {}",dob, today);
		if(dob.isAfter(today)){
		    return true;
		}
		return false;
	}
   
}
