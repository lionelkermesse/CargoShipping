package com.linkcorp.dddcqrs.events;

//import javax.inject.Inject;
//import javax.inject.Named;

import com.linkcorp.dddcqrs.annotations.EventHandlerAnnotation;
import com.linkcorp.dddcqrs.domain.CargoReportingRepository;
import com.linkcorp.dddcqrs.dto.CargoReport;

@EventHandlerAnnotation
public class NewCargoCreatedEventHandler implements IEventHandler<NewCargoCreatedEvent, CargoReport> {
//	@Inject
//	@Named
	private CargoReportingRepository reportingRepository;
	
	public NewCargoCreatedEventHandler(){
		this.reportingRepository = CargoReportingRepository.get();
	}

	public NewCargoCreatedEventHandler(CargoReportingRepository reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	public void setCargoReportingRepository(CargoReportingRepository reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	public void handles(NewCargoCreatedEvent event) {
		CargoReport cargo = new CargoReport(event.getAggregateId().toString(), event.getName());
		reportingRepository.save(cargo);
	}
}
