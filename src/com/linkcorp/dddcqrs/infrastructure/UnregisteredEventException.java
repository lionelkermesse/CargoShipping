package com.linkcorp.dddcqrs.infrastructure;

@SuppressWarnings("serial")
public class UnregisteredEventException extends Exception {
	
	public UnregisteredEventException(String message){
		super(message);
	}

}
