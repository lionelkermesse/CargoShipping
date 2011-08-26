package events;

import infrastructure.IBus;
import infrastructure.MessageHandlerNotFoundException;

public interface EventBus extends IBus{
	public void publish(IEvent event) throws MessageHandlerNotFoundException;
}
