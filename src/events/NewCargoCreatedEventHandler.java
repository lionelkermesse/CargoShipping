package events;

import domain.IReportingRepository;
import dto.CargoReport;

public class NewCargoCreatedEventHandler implements IEventHandler<NewCargoCreatedEvent, CargoReport> {
	private IReportingRepository<CargoReport> reportingRepository;
	
	public NewCargoCreatedEventHandler(IReportingRepository<CargoReport> reportingRepository){
		this.reportingRepository = reportingRepository;
	}

	@Override
	public void handles(NewCargoCreatedEvent event) {
		CargoReport cargo = new CargoReport(event.getAggregateId().toString(), event.getName());
		reportingRepository.save(cargo);
	}

}
