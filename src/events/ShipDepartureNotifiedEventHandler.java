package events;

import domain.IReportingRepository;
import dto.ShipReport;

public class ShipDepartureNotifiedEventHandler implements IEventHandler<ShipDepartureNotifiedEvent, ShipReport> {
	private IReportingRepository<ShipReport> reportingRepository;
	
	public ShipDepartureNotifiedEventHandler(IReportingRepository<ShipReport> reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	@Override
	public void handles(ShipDepartureNotifiedEvent event) {
		reportingRepository.update(new ShipReport(event.aggregateId.toString(), event._ShipName, event._Cargo.getName(), event._ToPort.getCity(), event._ToPort.getCountry()));
	}

}
