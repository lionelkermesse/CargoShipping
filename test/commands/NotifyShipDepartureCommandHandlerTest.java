package commands;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import commands.NotifyShipDepartureCommand;
import commands.NotifyShipDepartureCommandHandler;

import domain.Cargo;
import domain.IEventStore;
import domain.Port;
import domain.Ship;
import domain.ShipRepository;
import events.EventHandler;

public class NotifyShipDepartureCommandHandlerTest {

	NotifyShipDepartureCommand command;
	NotifyShipDepartureCommandHandler commandHandler;
	EventHandler eventHandler;
	Ship ship;
	
	@Before
	public void setUp() throws Exception {
		Port port = new Port("Douala", "Cameroon");
		Cargo cargo = new Cargo("Bindi");
		ship = new Ship("Baba", cargo, port);
		command = new NotifyShipDepartureCommand("ID2011", new Date(), "Havre", "France");
		eventHandler = new EventHandler();
	}

	@Test
	public final void testSetDepartureCommand() {
		
		IEventStore shipStore = Mockito.mock(IEventStore.class);
		
		ShipRepository shipRepository = new ShipRepository(eventHandler, shipStore);
		
		shipRepository.setShipEventHandler(eventHandler);
		
		commandHandler = new NotifyShipDepartureCommandHandler(command, shipRepository);
		
		//Scenario de test
		Mockito.when(shipStore.get("ID2011")).thenReturn(ship);

		assertEquals("initial port city", "Douala", ship.getPort().getCity());
		assertEquals("initial port country", "Cameroon", ship.getPort().getCountry());
		
		//Command execution test
		commandHandler.execute();
		
		assertEquals("the event has been store", 1, eventHandler.getLog().size());
		
		assertEquals("The departure command update ship's port city", "Havre", ship.getPort().getCity());
		assertEquals("The departure command update ship's port country", "France", ship.getPort().getCountry());
		
		Mockito.verify(shipStore);
		
	}
}
