package com.linkcorp.dddcqrs.events;

import com.linkcorp.dddcqrs.infrastructure.IBus;
import com.linkcorp.dddcqrs.infrastructure.MessageHandlerNotFoundException;

public interface EventBus extends IBus{
	public void publish(IEvent event) throws MessageHandlerNotFoundException;
}
