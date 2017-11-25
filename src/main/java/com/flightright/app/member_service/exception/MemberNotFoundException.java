package com.flightright.app.member_service.exception;

public class MemberNotFoundException extends RuntimeException{
	private static final long serialVersionUID = 1L;

	public MemberNotFoundException(){
	}

	public MemberNotFoundException(String message){
		super(message);
	}
}
