package commands;

import infrastructure.AggregateNotFoundException;

public interface ICommandHandler<T> {
	public void handles(T command) throws AggregateNotFoundException;
}
