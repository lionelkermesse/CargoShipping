package domain;

import infrastructure.UnregisteredEventException;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

import events.Event;

public abstract class AggregateRoot implements IAggregateRoot {
	protected UUID id;
	protected int version;
	protected List<Event> appliedChanges;
	private HashMap<Class<?>, Method> registeredEvents; //Use to register All Event handlers for the aggregate
	private int eventVersion;
	
	public AggregateRoot(){
		appliedChanges = new ArrayList<Event>();
		registeredEvents = new HashMap<Class<?>, Method>();
	}
	
	public AggregateRoot(UUID id){
		this();
		this.id = id;
	}
	
	public int getVersion(){ return this.version;}
	public void setVersion(int version){this.version = version;}
	
	protected void registerEvent(Class<?> eventClass, Method eventHandler){
		registeredEvents.put(eventClass, eventHandler);
	}
	
	@Override
	public void applyChange(Event event){
		event._Id = id;
		event.version = getNewEventVersion();
		apply(event);
		appliedChanges.add(event);
		
	}
	
	@SuppressWarnings("rawtypes")
	private void apply(Event event){
		try {
			Method handler = registeredEvents.get(event.getClass());
			if(handler == null){
				throw new UnregisteredEventException("The requested domain event "+event.getClass().getName()+" is not registered !");
			}
			Class _class = handler.getDeclaringClass();
			Event[] args = {event};
			handler.invoke(_class.newInstance(), args);
		}
		catch (IllegalAccessException e) {e.printStackTrace();}
		catch (IllegalArgumentException e) {e.printStackTrace();}
		catch (InvocationTargetException e) {	e.printStackTrace();}
		catch (UnregisteredEventException e) {	e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();}
	}

	@Override
	public List<Event> getUncommitedChanges() {
		return appliedChanges;
	}

	@Override
	public void markChangesAsCommited() {
		appliedChanges.clear();
	}

	@Override
	public void loadsFromHistory(List<Event> history) {
		for (Event event : history) {
			applyChange(event);
		}
	}
	
	private int getNewEventVersion(){
		return ++eventVersion;
	}
	
	public void updateVersion(int version){
		this.version = version;
	}
	
	protected abstract void registerEvents() throws NoSuchMethodException, SecurityException;
	public abstract UUID getId();

}
