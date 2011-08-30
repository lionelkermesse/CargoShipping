package com.linkcorp.dddcqrs.commands;

import com.linkcorp.dddcqrs.annotations.CommandAnnotation;

@SuppressWarnings("serial")
@CommandAnnotation
public class CreateNewCargoCommand implements ICommand {
	private String cargoName;
	
	public CreateNewCargoCommand(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoName() {
		return cargoName;
	}
}
