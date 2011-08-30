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
package com.linkcorp.dddcqrs.domain;


import java.util.List;
import java.util.UUID;

import com.linkcorp.dddcqrs.events.Event;
import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.ConcurrencyException;

/**
 * @author Nelson Lionel KEMINSE
 * 5 ao�t 2011 15:05:09
 */
public interface IEventStore {
	public void addEvents(UUID aggregateId, List<Event> events) throws ConcurrencyException;
	public List<Event> getEventsForAggregate(UUID aggregateId) throws AggregateNotFoundException;
	
}
