package events;

import domain.IReportingRepository;
import dto.ShipReport;

public class ShipArrivalNotifiedEventHandler implements IEventHandler<ShipArrivalNotifiedEvent, ShipReport> {
	private IReportingRepository<ShipReport> reportingRepository;
	
	public ShipArrivalNotifiedEventHandler(IReportingRepository<ShipReport> reportingRepository){
		this.reportingRepository = reportingRepository;
	}
	
	@Override
	public void handles(ShipArrivalNotifiedEvent event) {
		reportingRepository.update(new ShipReport(event.aggregateId.toString(), event._ShipName, event._Cargo.getName(), event._FromPort.getCity(), event._FromPort.getCountry()));
	}
}
