package com.linkcorp.dddcqrs.events;

import com.linkcorp.dddcqrs.infrastructure.IBus;

public interface EventPublisher extends IBus{
	public void publish(IEvent event);
}
