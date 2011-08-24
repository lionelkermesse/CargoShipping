package domain;

import infrastructure.AggregateNotFoundException;
import infrastructure.ConcurrencyException;

import java.util.List;
import java.util.UUID;

import events.Event;

public class CargoDomainRepository implements IDomainRepository<Cargo>{
	IEventStore eventStore;
	
	public CargoDomainRepository(IEventStore eventStore){
		this.eventStore = eventStore;
	}
	
	@Override
	public void save(Cargo aggregate) throws ConcurrencyException {
		eventStore.addEvents(aggregate.getId(), aggregate.getUncommitedChanges());
	}

	@Override
	public Cargo getById(UUID aggregateId) throws AggregateNotFoundException {
		Cargo cargo = new Cargo(aggregateId);
		List<Event> events = eventStore.getEventsForAggregate(aggregateId);
		cargo.loadsFromHistory(events); //Here we fetch all events reflecting the last state for the current aggregate. 
		
		return cargo;
	}
	 
}
