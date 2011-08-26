package commands;

import domain.Cargo;
import domain.IDomainRepository;
import infrastructure.AggregateNotFoundException;
import infrastructure.ConcurrencyException;

public class CreateNewCargoCommandHandler implements ICommandHandler<CreateNewCargoCommand> {
	private IDomainRepository<Cargo> domainRepository;
	
	public CreateNewCargoCommandHandler(IDomainRepository<Cargo> domainRepository){
		this.domainRepository = domainRepository;
	}
	
	@Override
	public void handles(CreateNewCargoCommand command) throws AggregateNotFoundException {
		Cargo  cargo = new Cargo(command.getCargoName());
		try {
			domainRepository.save(cargo);
		} catch (ConcurrencyException e) {
			e.printStackTrace();
		}
	}
}
