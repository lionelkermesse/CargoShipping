package commands;

import infrastructure.IBus;

public interface CommandBus extends IBus {
	<T> void send(T command);
}
