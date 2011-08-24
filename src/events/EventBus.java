package events;

import infrastructure.IBus;

public interface EventBus extends IBus{
	<T> void publish(T event);
}
