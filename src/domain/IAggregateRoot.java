package domain;

import java.util.List;

import events.Event;

public interface IAggregateRoot {
	public void applyChange(Event event);
	public List<Event> getUncommitedChanges();
	public void markChangesAsCommited();
	public void loadsFromHistory(List<Event> history);
}
