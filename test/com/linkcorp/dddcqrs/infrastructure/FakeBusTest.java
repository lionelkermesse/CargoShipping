package com.linkcorp.dddcqrs.infrastructure;

import static org.junit.Assert.*;

import java.util.List;
import java.util.UUID;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import com.linkcorp.dddcqrs.commands.ChangeShipNameCommand;
import com.linkcorp.dddcqrs.commands.CreateNewCargoCommand;
import com.linkcorp.dddcqrs.commands.ICommand;
import com.linkcorp.dddcqrs.infrastructure.FakeBus;
import com.linkcorp.dddcqrs.infrastructure.MessageHandlerNotFoundException;

public class FakeBusTest {
	private FakeBus bus;
	
	@Before
	public void setUp(){
		bus = FakeBus.get();
	}
	
	@Ignore
	public void testCommandHandlerExistFor() throws MessageHandlerNotFoundException {
		ICommand command = new CreateNewCargoCommand("CargoTest");
		
		bus.RegisterHandler(CreateNewCargoCommand.class, "CreateNewCargoCommandHandler");
		
		assertNotNull(bus.getRoutes());
		assertEquals(1, bus.getRoutes().size());
		
		assertEquals(CreateNewCargoCommand.class, bus.getRoutes().keySet().toArray()[0]);
		
		List<String> handlers = bus.getRoutes().get(CreateNewCargoCommand.class);
		
		assertNotNull(handlers);
		assertEquals(1, handlers.size());
		
		assertNotNull(bus.getMessageRouters(command));
		assertEquals(1, bus.getMessageRouters(command).size());
	}
	
	
	@Test //(expected = MessageHandlerNotFoundException.class)
	public void testCommandHandlerNotExistFor() {
		ICommand command = new ChangeShipNameCommand(UUID.randomUUID(),"NewShipName");
		bus.RegisterHandler(CreateNewCargoCommand.class, "CreateNewCargoCommandHandler");
		assertNull(bus.getMessageRouters(command));
	}
}
