package domain;

import java.util.ArrayList;
import java.util.List;

import dto.CargoReport;

public class CargoReportingRepository implements IReportingRepository<CargoReport> {
	private List<CargoReport> db;
	
	public CargoReportingRepository(){
		this.db = new ArrayList<CargoReport>();
	}
	
	@Override
	public void save(CargoReport aggregateDTO) {
		this.db.add(aggregateDTO);		
	}

	@Override
	public void update(CargoReport aggreatedDTO) {
		delete(aggreatedDTO.getId());
		db.add(aggreatedDTO);
	}

	@Override
	public void delete(String aggregateDTOId) {
		CargoReport toUpdate = null;
		for (CargoReport cargo : this.db) {
			if(cargo.getId().equals(aggregateDTOId)){
				toUpdate = cargo;
				break;
			}
		}
		db.remove(toUpdate);
	}

	@Override
	public List<CargoReport> getBy(Object type) {
		// TODO Auto-generated method stub
		return null;
	}

}
