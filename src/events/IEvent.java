package events;

import java.io.Serializable;
import java.util.UUID;

public interface IEvent extends Serializable, Message{
	public UUID getId();
	public UUID getAggregateId();
	public void setAggregateId(UUID aggregateId);
	public int getVersion();
	public void setVersion(int version);
}
