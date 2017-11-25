package com.flightright.app.member_service.exception;

public class MemberValidationException extends RuntimeException {
	private static final long serialVersionUID = 1L;
	
	public MemberValidationException() {
	}
	
	public MemberValidationException(String message){
		super(message);
	}

}
