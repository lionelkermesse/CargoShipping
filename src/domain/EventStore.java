package domain;

import infrastructure.AggregateNotFoundException;
import infrastructure.ConcurrencyException;
import infrastructure.MessageHandlerNotFoundException;

import java.util.ArrayList;
import java.util.Hashtable;
import java.util.List;
import java.util.UUID;

import events.Event;
import events.EventBus;

public class EventStore implements IEventStore, IEventStoreUnitOfWork{
	private EventBus eventBus;
	private Hashtable<UUID, List<Event>> events = new Hashtable<UUID, List<Event>>();
	private List<Event> eventsProvider = new ArrayList<Event>();
	
	public EventStore(EventBus eventBus){
		this.eventBus = eventBus;
	}

	
	/*DomainEventStore Implementations*/
	@Override
	public void addEvents(UUID aggregateId, List<Event> aggregateEvents) throws ConcurrencyException {
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

	@Override
	public List<Event> getEventsForAggregate(UUID aggregateId) throws AggregateNotFoundException {
		List<Event> eventsList = this.events.get(aggregateId);
		if(eventsList == null)
			throw (new AggregateNotFoundException());
		
		return eventsList;
	}

	/*EventStoreUnitOfWork Implementations*/
	
	@Override
	public void commit() {
		try {
			for(Event event : this.eventsProvider){
				this.eventBus.publish(event);
			}	
			this.eventsProvider.clear();
		} catch (MessageHandlerNotFoundException e) {e.printStackTrace();}
	}

	@Override
	public void rollback() {
		// TODO Auto-generated method stub
		
	}

}
