package commands;

import java.util.UUID;

@SuppressWarnings("serial")
public class ChangeShipNameCommand implements ICommand {
	UUID id;
	String name;
	public ChangeShipNameCommand(UUID id, String name){
		this.id = id;
		this.name = name;
	}
	
	public UUID getId() {	return id;	}
	public String getName() {return name;}
	
	
}
