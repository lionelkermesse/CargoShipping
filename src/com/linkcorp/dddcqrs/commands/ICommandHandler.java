package com.linkcorp.dddcqrs.commands;

import com.linkcorp.dddcqrs.events.Message;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.IHandler;

public interface ICommandHandler<T> extends IHandler<Message> {
	public void handles(T command) throws AggregateNotFoundException;
}
