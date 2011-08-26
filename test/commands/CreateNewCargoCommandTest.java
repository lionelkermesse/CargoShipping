package commands;

import static org.junit.Assert.*;

import infrastructure.FakeBus;
import infrastructure.MessageHandlerNotFoundException;
import infrastructure.PrivateAccessor;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import domain.CargoDomainRepository;
import domain.CargoReportingRepository;
import domain.EventStore;
import domain.IEventStore;
import events.NewCargoCreatedEvent;
import events.NewCargoCreatedEventHandler;


public class CreateNewCargoCommandTest {
	CreateNewCargoCommand command;
	FakeBus bus;
	IEventStore eventStore;
	CargoDomainRepository cargoDomainRepository;
	CargoReportingRepository cargoReportingRepository;
	
	@Before
	public void setUp(){
		command = new CreateNewCargoCommand("Alibaba");
		bus = new FakeBus();
		eventStore = new EventStore(bus);
		cargoReportingRepository = new CargoReportingRepository();
		cargoDomainRepository = new CargoDomainRepository(eventStore);
	}
	
	@Test
	public void testCreateNewCargoCommand() throws MessageHandlerNotFoundException {
		bus.RegisterHandler(CreateNewCargoCommand.class, PrivateAccessor.getPrivateMethod(new CreateNewCargoCommandHandler(cargoDomainRepository), "handles"));
		bus.RegisterHandler(NewCargoCreatedEvent.class, PrivateAccessor.getPrivateMethod(new NewCargoCreatedEventHandler(cargoReportingRepository), "handles"));
		
		bus.send(command);
		
		assertEquals(1, cargoReportingRepository.getReportingDatabase().size());
	}

}
