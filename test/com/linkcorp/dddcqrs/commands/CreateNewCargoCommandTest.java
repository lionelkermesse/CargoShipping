package com.linkcorp.dddcqrs.commands;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.Test;

import com.linkcorp.dddcqrs.commands.CreateNewCargoCommand;
import com.linkcorp.dddcqrs.domain.CargoReportingRepository;
import com.linkcorp.dddcqrs.events.NewCargoCreatedEvent;
import com.linkcorp.dddcqrs.infrastructure.FakeBus;

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration("classpath:/CargoContext.xml")
public class CreateNewCargoCommandTest{
	CreateNewCargoCommand command;
	FakeBus bus = FakeBus.get();
	
	@Before
	public void setUp(){
		command = new CreateNewCargoCommand("Alibaba");
	}
	
	@Test
	public void testCreateNewCargoCommand() throws ClassNotFoundException{
		bus.RegisterHandler(CreateNewCargoCommand.class, "com.linkcorp.dddcqrs.commands.CreateNewCargoCommandHandler");
		bus.RegisterHandler(NewCargoCreatedEvent.class, "com.linkcorp.dddcqrs.events.NewCargoCreatedEventHandler");
		
		bus.send(command);
		
		assertEquals(1, CargoReportingRepository.get().getReportingDatabase().size());
		assertEquals("Alibaba", CargoReportingRepository.get().getReportingDatabase().get(0).getName());
	}
	
}
