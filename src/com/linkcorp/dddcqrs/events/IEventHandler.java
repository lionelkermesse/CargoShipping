package com.linkcorp.dddcqrs.events;

import com.linkcorp.dddcqrs.infrastructure.IHandler;

public interface IEventHandler<T,E> extends IHandler<Message>{
	public void handles(T event);
}
