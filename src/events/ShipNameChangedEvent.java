package events;

import java.util.UUID;

@SuppressWarnings("serial")
public class ShipNameChangedEvent extends Event {
	public final String newName;
	
	public ShipNameChangedEvent(UUID id, String newName) {
		super(id);
		this.newName = newName;
	}
}
