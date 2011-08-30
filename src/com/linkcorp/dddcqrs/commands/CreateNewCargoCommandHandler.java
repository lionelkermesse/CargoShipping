package com.linkcorp.dddcqrs.commands;

//import javax.inject.Inject;
//import javax.inject.Named;

import com.linkcorp.dddcqrs.annotations.CommandHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.Cargo;
import com.linkcorp.dddcqrs.domain.CargoDomainRepository;
import com.linkcorp.dddcqrs.domain.IDomainRepository;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.ConcurrencyException;

@CommandHandlerAnnotation
public class CreateNewCargoCommandHandler implements ICommandHandler<CreateNewCargoCommand> {
//	@Inject
//	@Named
	private IDomainRepository<Cargo> domainRepository;
	
	public CreateNewCargoCommandHandler(){
		this.domainRepository = new CargoDomainRepository();
	}
	
	public CreateNewCargoCommandHandler(CargoDomainRepository domainRepository){
		this.domainRepository = domainRepository;
	}
	
	public void setCargoDomainRepository(CargoDomainRepository domainRepository){
		this.domainRepository = domainRepository;
	}
	
	public void handles(CreateNewCargoCommand command) throws AggregateNotFoundException {
		Cargo  cargo = new Cargo(command.getCargoName());
		try {
			domainRepository.save(cargo);
		} catch (ConcurrencyException e) {
			e.printStackTrace();
		}
	}
}
