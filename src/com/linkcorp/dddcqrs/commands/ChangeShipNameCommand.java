package com.linkcorp.dddcqrs.commands;

import java.util.UUID;

import com.linkcorp.dddcqrs.annotations.CommandAnnotation;


@SuppressWarnings("serial")
@CommandAnnotation 
public class ChangeShipNameCommand implements ICommand {
	UUID shipId;
	String newName;
	public ChangeShipNameCommand(UUID id, String name){
		this.shipId = id;
		this.newName = name;
	}
	
	public UUID getShipId() {	return shipId;	}
	public String getNewName() {return newName;}
	
	
}
