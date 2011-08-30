package com.linkcorp.dddcqrs.infrastructure;

@SuppressWarnings("serial")
public class MessageHandlerNotFoundException extends Exception {

	public MessageHandlerNotFoundException(String message) {
		super(message);
	}

}
