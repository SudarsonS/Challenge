package com.flightright.app.member_service.util;

import java.time.Instant;
import java.time.LocalDate;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Date;

public class Util {

	public static boolean isFutureDate(Date dateOfBirth) {
		Instant instant = dateOfBirth.toInstant();
		ZonedDateTime zdt = instant.atZone(ZoneId.systemDefault());
		LocalDate dob = zdt.toLocalDate();
		LocalDate today = LocalDate.now();
		if(dob.isAfter(today)){
		    return true;
		}
		return false;
	}
   
}
