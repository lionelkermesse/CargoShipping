package com.linkcorp.dddcqrs.dto;

@SuppressWarnings("serial")
public class CargoReport implements DTO {
	private String Id;
	private String Name;
	
	public CargoReport(String Id, String Name){
		this.Id = Id;
		this.Name = Name;
	}

	public String getId() {return Id;}
	public String getName() {return Name;}

	public void setId(String Id) {this.Id = Id;}
	public void setName(String name) {Name = name;}
	
	
}
