package com.linkcorp.dddcqrs.domain;

import java.util.ArrayList;
import java.util.List;

//import javax.inject.Named;

import com.linkcorp.dddcqrs.annotations.ReportingRepositoryAnnotation;
import com.linkcorp.dddcqrs.dto.CargoReport;

//@Named
@ReportingRepositoryAnnotation
public class CargoReportingRepository implements IReportingRepository<CargoReport> {
	private List<CargoReport> db;
	private static CargoReportingRepository singleton = new CargoReportingRepository();
	
	private CargoReportingRepository(){
		this.db = new ArrayList<CargoReport>();
	}
	
	public static CargoReportingRepository get(){
		return singleton;
	}
	
	public void save(CargoReport aggregateDTO) {
		this.db.add(aggregateDTO);		
	}

	public void update(CargoReport aggreatedDTO) {
		delete(aggreatedDTO.getId());
		db.add(aggreatedDTO);
	}

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

	public List<CargoReport> getBy(Object type) {
		// TODO Auto-generated method stub
		return null;
	}

	public List<CargoReport> getReportingDatabase() {
		return db;
	}

}
