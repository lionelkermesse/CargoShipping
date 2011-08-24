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
package commands;

import infrastructure.AggregateNotFoundException;
import domain.IDomainRepository;
import domain.Ship;
import domain.ShipRepository;

/**
 * @author Nelson Lionel KEMINSE
 * 5 ao�t 2011 12:28:48
 */
public class NotifyShipDepartureCommandHandler{
	private IDomainRepository<Ship> domainRepository;
	
	public NotifyShipDepartureCommandHandler(ShipRepository repository) {
		this.domainRepository = repository;
	}

	public void handles(NotifyShipDepartureCommand command) throws AggregateNotFoundException {
		Ship ship = domainRepository.getById(command.getId());
		ship.setPort(command.getToPort());
		//repository.save(ship);
	}
}
