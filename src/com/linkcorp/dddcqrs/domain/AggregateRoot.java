package com.linkcorp.dddcqrs.domain;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;


import com.linkcorp.dddcqrs.events.Event;

public abstract class AggregateRoot implements IAggregateRoot {
	protected UUID id;
	protected int version;
	protected List<Event> appliedChanges;
	
//	@Inject
//	private EventPublisher publisher;
	
	private int eventVersion;
	
	public AggregateRoot(){
		appliedChanges = new ArrayList<Event>();
//		this.publisher = FakeBus.get();
	}
	
	public AggregateRoot(UUID id){
		this();
		this.id = id;
	}
	
	public int getVersion(){ return this.version;}
	
	public void setVersion(int version){this.version = version;}
	
//	public void setEventPublisher(EventPublisher publisher){ this.publisher = publisher;}
	
	public void apply(Event event){
//		try {
			event._Id = id;
			event.version = getNewEventVersion();
//			applyChange(event);
			appliedChanges.add(event);
//		} catch (MessageHandlerNotFoundException e) {e.printStackTrace();}
	}
	
//	private void applyChange(Event event) throws MessageHandlerNotFoundException{
//		publisher.publish(event);
//	}

	public List<Event> getUncommitedChanges() {
		return appliedChanges;
	}

	public void markChangesAsCommited() {
		appliedChanges.clear();
	}

	public void loadsFromHistory(List<Event> history) {
		for (Event event : history) {
			apply(event);
		}
	}
	
	private int getNewEventVersion(){
		return ++eventVersion;
	}
	
	public void updateVersion(int version){
		this.version = version;
	}
	
	public abstract UUID getId();

}
