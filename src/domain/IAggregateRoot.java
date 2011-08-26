package domain;

import java.util.List;

import events.Event;

public interface IAggregateRoot {
	public void apply(Event event);
	public List<Event> getUncommitedChanges();
	public void markChangesAsCommited();
	public void loadsFromHistory(List<Event> history);
}
