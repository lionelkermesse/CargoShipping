package commands;

import domain.IDomainRepository;
import domain.Ship;
import infrastructure.AggregateNotFoundException;

public class ChangeShipNameCommandHandler implements ICommandHandler<ChangeShipNameCommand>{
	private IDomainRepository<Ship> domainRepository;
	
	public ChangeShipNameCommandHandler(IDomainRepository<Ship> domainRepository){
		this.domainRepository = domainRepository;
	}
	
	@Override
	public void handles(ChangeShipNameCommand command) throws AggregateNotFoundException {
		Ship ship = domainRepository.getById(command.getShipId());
		ship.changeName(command.getNewName());
	}
}
