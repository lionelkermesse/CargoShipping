/**
 * command/ShipDepartureCommand.java
 *
 * File generated from the ShipDepartureCommand uml Class
 * Generated by the Acceleo UML 2.1 to Java generator module (Obeo)
 * $ Date : 04/08/11 20:26:34 (4 août 2011) $
 */
package com.linkcorp.dddcqrs.commands;

import java.util.Date;
import java.util.UUID;

import com.linkcorp.dddcqrs.annotations.CommandAnnotation;
import com.linkcorp.dddcqrs.domain.Port;

/**
 * Description of the class ShipDepartureCommand.
 *
 */

@SuppressWarnings("serial")
@CommandAnnotation
public class NotifyShipDepartureCommand implements ICommand {
	private UUID id;
	private Date occured;
	private Port toPort;
		
	public NotifyShipDepartureCommand(UUID id, Date occured, String toPortCity, String toPortCountry) {
		this.id = id;
		this.occured = occured;
		this.toPort = new Port(toPortCity, toPortCountry);
	}
		
	public UUID getId() {	return id;}
	public Date getOccured() {	return occured;}
	public Port getToPort() { return toPort;}
		
}