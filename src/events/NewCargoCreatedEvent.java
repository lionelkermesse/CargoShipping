package events;

import java.util.UUID;

@SuppressWarnings("serial")
public class NewCargoCreatedEvent extends Event {
	private String name;
	
	public NewCargoCreatedEvent(UUID id, String name) {
		super(id);
		this.name = name;
	}

	public String getName() {
		return name;
	}
}
