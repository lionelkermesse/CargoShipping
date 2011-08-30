package com.linkcorp.dddcqrs.dto;

@SuppressWarnings("serial")
public class ShipReport implements DTO {
		private String id;
		private String name;
		private String cargoName;
		private String portCity;
		private String portCountry;
		
		public ShipReport(){}
		
		public ShipReport(String id, String name, String cargoName, String portCity, String portCountry) {
			this.id = id;
			this.name = name;
			this.cargoName = cargoName;
			this.portCity = portCity;
			this.portCountry = portCountry;
		}

		public String getId() {return id;}

		public void setId(String id) {
			this.id = id;
		}

		public String getName() {
			return name;
		}

		public void setName(String name) {
			this.name = name;
		}

		public String getCargoName() {
			return cargoName;
		}

		public void setCargoName(String cargoName) {
			this.cargoName = cargoName;
		}

		public String getPortCity() {
			return portCity;
		}

		public void setPortCity(String portCity) {
			this.portCity = portCity;
		}

		public String getPortCountry() {
			return portCountry;
		}

		public void setPortCountry(String portCountry) {
			this.portCountry = portCountry;
		}
		
}