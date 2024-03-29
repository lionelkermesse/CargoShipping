/** 
 *   ShippingProcess Application.
 *
 *   Copyright 2011 Harmonic-Pharma
 *
 *   Licensed under the Apache License, Version 2.0 (the "License");
 *   you may not use this file except in compliance with the License.
 *   You may obtain a copy of the License at
 *
 *       http://www.apache.org/licenses/LICENSE-2.0
 *
 *   Unless required by applicable law or agreed to in writing, software
 *   distributed under the License is distributed on an "AS IS" BASIS,
 *   WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *   See the License for the specific language governing permissions and
 *   limitations under the License.
 *
 *   See http://www.harmonic-pharma.com/ for more information 
 *   about this app.
 */
package com.linkcorp.dddcqrs.commands;

//import javax.inject.Inject;

import com.linkcorp.dddcqrs.annotations.CommandHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.IDomainRepository;
import com.linkcorp.dddcqrs.domain.Ship;
import com.linkcorp.dddcqrs.domain.ShipRepository;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;


/**
 * @author Nelson Lionel KEMINSE
 * 5 ao�t 2011 12:28:48
 */

@CommandHandlerAnnotation
public class NotifyShipDepartureCommandHandler implements ICommandHandler<NotifyShipDepartureCommand>{
//	@Inject
	private IDomainRepository<Ship> domainRepository;
	
	public NotifyShipDepartureCommandHandler(ShipRepository repository) {
		this.domainRepository = repository;
	}

	public void handles(NotifyShipDepartureCommand command) throws AggregateNotFoundException {
		Ship ship = domainRepository.getById(command.getId());
		ship.updateShipDeparture(command.getOccured(), command.getToPort());
	}
}
