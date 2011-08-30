package com.linkcorp.dddcqrs.commands;

import com.linkcorp.dddcqrs.infrastructure.IBus;
import com.linkcorp.dddcqrs.infrastructure.MessageHandlerNotFoundException;


public interface CommandSender extends IBus {
	void send(ICommand command) throws MessageHandlerNotFoundException;
}
