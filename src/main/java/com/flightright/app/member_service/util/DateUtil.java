package com.flightright.app.member_service.util;

import java.time.LocalDate;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.flightright.app.member_service.exception.MemberValidationException;


public class DateUtil {
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);
	public static boolean isFutureDate(LocalDate dateOfBirth) {
		if(dateOfBirth == null){
			throw new MemberValidationException("Date of birth is null");
		}
		
		LocalDate today = LocalDate.now();
		logger.debug("Date of Birth converted to local date = {}, today's date = {}",dateOfBirth, today);
		if(dateOfBirth.isAfter(today)){
		    return true;
		}
		return false;
	}
   
}
