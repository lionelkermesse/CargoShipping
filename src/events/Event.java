/**
 * events/DomainEvent.java
 *
 * File generated from the DomainEvent uml Class
 * Generated by the Acceleo UML 2.1 to Java generator module (Obeo)
 * $ Date : 04/08/11 20:26:35 (4 août 2011) $
 */
package events;

import java.util.UUID;

@SuppressWarnings("serial")
public class Event implements IEvent, Message{
		public UUID _Id;
		public UUID aggregateId;
		public int version;
 
		public Event(UUID aggregateId) {
			this._Id = UUID.randomUUID();
			this.aggregateId = aggregateId;
		}

		@Override
		public UUID getId() {return this._Id;}

		@Override
		public UUID getAggregateId() {return this.aggregateId;}
		@Override
		public void setAggregateId(UUID aggregateId) {this.aggregateId = aggregateId;}

		@Override
		public int getVersion() {return this.version;}
		@Override
		public void setVersion(int version) {this.version = version;}
}