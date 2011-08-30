package com.linkcorp.dddcqrs.domain;

import java.util.List;

import com.linkcorp.dddcqrs.events.Event;


public interface IAggregateRoot {
	public void apply(Event event);
	public List<Event> getUncommitedChanges();
	public void markChangesAsCommited();
	public void loadsFromHistory(List<Event> history);
}
