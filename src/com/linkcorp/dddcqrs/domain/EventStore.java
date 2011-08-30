package com.linkcorp.dddcqrs.domain;


import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

//import javax.inject.Inject;
//import javax.inject.Named;

import com.linkcorp.dddcqrs.annotations.EventStoreAnnotation;
import com.linkcorp.dddcqrs.events.Event;
import com.linkcorp.dddcqrs.events.EventPublisher;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.FakeBus;

//@Named
@EventStoreAnnotation
public class EventStore implements IEventStore, IEventStoreUnitOfWork{
//	@Inject
	private EventPublisher eventBus;
	
	private Hashtable<UUID, List<Event>> events = new Hashtable<UUID, List<Event>>();
	private List<Event> eventsProvider = new ArrayList<Event>();
	
	public EventStore(){
		this.eventBus = FakeBus.get();
	}

	public EventStore(EventPublisher eventBus){
		this.eventBus = eventBus;
	}
	
	public void setEventBus(EventPublisher eventBus){
		this.eventBus = eventBus;
	}
	
	/*DomainEventStore Implementations*/
	public void addEvents(UUID aggregateId, List<Event> aggregateEvents){
		List<Event> eventList = this.events.get(aggregateId);
		if(eventList == null){
			eventList = new ArrayList<Event>();
			this.events.put(aggregateId, eventList);
		}
		
		for(Event event : aggregateEvents){
			this.events.get(aggregateId).add(event);
			this.eventsProvider.add(event);
		}
		
		//Then We can commit allChanges throught out the bus
		commit();
	}

	public List<Event> getEventsForAggregate(UUID aggregateId) throws AggregateNotFoundException {
		List<Event> eventsList = this.events.get(aggregateId);
		if(eventsList == null)
			throw (new AggregateNotFoundException());
		
		return eventsList;
	}

	/*EventStoreUnitOfWork Implementations*/
	
	public void commit(){
		for(Event event : this.eventsProvider){
			this.eventBus.publish(event);
		}	
		this.eventsProvider.clear();
	}

	public void rollback() {
		// TODO Auto-generated method stub
		
	}

}
