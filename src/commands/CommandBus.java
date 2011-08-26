package commands;

import infrastructure.IBus;
import infrastructure.MessageHandlerNotFoundException;

public interface CommandBus extends IBus {
	void send(ICommand command) throws MessageHandlerNotFoundException;
}
