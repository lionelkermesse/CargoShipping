package com.linkcorp.dddcqrs.events;

import java.util.UUID;

public class EventDescriptor {
	public final UUID id;
	public final int version;
	public final Event event;
	
	public EventDescriptor(UUID id, int version, Event event) {
		this.id = id;
		this.version = version;
		this.event = event;
	}
	
	
}
