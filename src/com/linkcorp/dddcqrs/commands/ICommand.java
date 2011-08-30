package com.linkcorp.dddcqrs.commands;

import java.io.Serializable;

import com.linkcorp.dddcqrs.events.Message;

public interface ICommand extends Serializable, Message{

}
