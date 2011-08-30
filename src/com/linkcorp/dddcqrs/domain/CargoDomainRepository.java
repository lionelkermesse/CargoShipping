package com.linkcorp.dddcqrs.domain;


import java.util.List;
import java.util.UUID;

//import javax.inject.Inject;
//import javax.inject.Named;

import com.linkcorp.dddcqrs.annotations.DomainRepositoryAnnotation;
import com.linkcorp.dddcqrs.events.Event;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.ConcurrencyException;

//@Named
@DomainRepositoryAnnotation
public class CargoDomainRepository implements IDomainRepository<Cargo>{
//	@Inject
//	@Named("EventStore")
	EventStore eventStore;
	
	public CargoDomainRepository(){
		this.eventStore = new EventStore();
	}
	
	public CargoDomainRepository(EventStore eventStore){
		this.eventStore = eventStore;
	}

	public void setEventStore(EventStore eventStore) {
		this.eventStore = eventStore;
	}
	
	public void save(Cargo aggregate) throws ConcurrencyException {
		eventStore.addEvents(aggregate.getId(), aggregate.getUncommitedChanges());
	}

	public Cargo getById(UUID aggregateId) throws AggregateNotFoundException {
		Cargo cargo = new Cargo(aggregateId);
		List<Event> events = eventStore.getEventsForAggregate(aggregateId);
		cargo.loadsFromHistory(events); //Here we fetch all events reflecting the last state for the current aggregate. 
		
		return cargo;
	}
	 
}
