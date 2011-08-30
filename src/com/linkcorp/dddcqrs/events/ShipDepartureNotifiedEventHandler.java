package com.linkcorp.dddcqrs.events;

import com.linkcorp.dddcqrs.annotations.EventHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.IReportingRepository;
import com.linkcorp.dddcqrs.dto.ShipReport;

@EventHandlerAnnotation
public class ShipDepartureNotifiedEventHandler implements IEventHandler<ShipDepartureNotifiedEvent, ShipReport> {
	private IReportingRepository<ShipReport> reportingRepository;
	
	public ShipDepartureNotifiedEventHandler(IReportingRepository<ShipReport> reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	public void handles(ShipDepartureNotifiedEvent event) {
		reportingRepository.update(new ShipReport(event.aggregateId.toString(), event._ShipName, event._Cargo.getName(), event._ToPort.getCity(), event._ToPort.getCountry()));
	}

}
