package com.linkcorp.dddcqrs.events;

import java.util.UUID;

import com.linkcorp.dddcqrs.annotations.EventAnnotation;

@SuppressWarnings("serial")
@EventAnnotation
public class ShipNameChangedEvent extends Event {
	public final String newName;
	
	public ShipNameChangedEvent(UUID id, String newName) {
		super(id);
		this.newName = newName;
	}
}
