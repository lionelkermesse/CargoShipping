package com.linkcorp.dddcqrs.commands;

//import javax.inject.Inject;

import com.linkcorp.dddcqrs.annotations.CommandHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.IDomainRepository;
import com.linkcorp.dddcqrs.domain.Ship;
import com.linkcorp.dddcqrs.domain.ShipRepository;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;

@CommandHandlerAnnotation
public class ChangeShipNameCommandHandler implements ICommandHandler<ChangeShipNameCommand>{
//	@Inject
	private IDomainRepository<Ship> domainRepository;
	
	public ChangeShipNameCommandHandler(){
		this.domainRepository = new ShipRepository();
	}
	
	public ChangeShipNameCommandHandler(IDomainRepository<Ship> domainRepository){
		this.domainRepository = domainRepository;
	}
	
	public void setDomainRepository(IDomainRepository<Ship> domainRepository) {
		this.domainRepository = domainRepository;
	}

	public void handles(ChangeShipNameCommand command) throws AggregateNotFoundException {
		Ship ship = domainRepository.getById(command.getShipId());
		ship.changeName(command.getNewName());
	}
}
