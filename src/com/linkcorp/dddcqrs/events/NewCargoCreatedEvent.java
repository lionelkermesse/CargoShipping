package com.linkcorp.dddcqrs.events;

import java.util.UUID;

import com.linkcorp.dddcqrs.annotations.EventAnnotation;

@SuppressWarnings("serial")
@EventAnnotation
public class NewCargoCreatedEvent extends Event {
	private String name;
	
	public NewCargoCreatedEvent(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
