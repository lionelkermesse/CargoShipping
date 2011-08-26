package infrastructure;

import static org.junit.Assert.*;

import java.lang.reflect.Method;
import java.util.List;

import org.junit.Before;
import org.junit.Test;

import commands.ChangeShipNameCommand;
import commands.CreateNewCargoCommand;
import commands.CreateNewCargoCommandHandler;
import commands.ICommand;

public class FakeBusTest {
	private FakeBus bus;
	
	@Before
	public void setUp(){
		bus = new FakeBus();
	}
	
	@Test
	public void testCommandHandlerExistFor() throws MessageHandlerNotFoundException {
		ICommand command = new CreateNewCargoCommand("CargoTest");
		
		Method handler = PrivateAccessor.getPrivateMethod(new CreateNewCargoCommandHandler(null), "handles");
		assertNotNull(handler);
		
		bus.RegisterHandler(CreateNewCargoCommand.class, handler);
		
		assertNotNull(bus.getRoutes());
		assertEquals(1, bus.getRoutes().size());
		
		assertEquals(CreateNewCargoCommand.class, bus.getRoutes().keySet().toArray()[0]);
		
		List<Method> handlers = bus.getRoutes().get(CreateNewCargoCommand.class);
		
		assertNotNull(handlers);
		assertEquals(1, handlers.size());
		
		assertNotNull(bus.getMessageRouters(command));
		assertEquals(1, bus.getMessageRouters(command).size());
	}
	
	
	@Test //(expected = MessageHandlerNotFoundException.class)
	public void testCommandHandlerNotExistFor() {
		ICommand command = new ChangeShipNameCommand(null,"NewShipName");
		bus.RegisterHandler(CreateNewCargoCommand.class, PrivateAccessor.getPrivateMethod(new CreateNewCargoCommandHandler(null), "handles"));
		assertNull(bus.getMessageRouters(command));
	}
}
