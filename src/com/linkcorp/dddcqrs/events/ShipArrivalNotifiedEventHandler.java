package com.linkcorp.dddcqrs.events;

import com.linkcorp.dddcqrs.annotations.EventHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.IReportingRepository;
import com.linkcorp.dddcqrs.dto.ShipReport;

@EventHandlerAnnotation
public class ShipArrivalNotifiedEventHandler implements IEventHandler<ShipArrivalNotifiedEvent, ShipReport> {
	private IReportingRepository<ShipReport> reportingRepository;
	
	public ShipArrivalNotifiedEventHandler(IReportingRepository<ShipReport> reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	public void handles(ShipArrivalNotifiedEvent event) {
		reportingRepository.update(new ShipReport(event.aggregateId.toString(), event._ShipName, event._Cargo.getName(), event._FromPort.getCity(), event._FromPort.getCountry()));
	}
}
