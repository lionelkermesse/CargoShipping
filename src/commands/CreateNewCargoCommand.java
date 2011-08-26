package commands;

@SuppressWarnings("serial")
public class CreateNewCargoCommand implements ICommand {
	private String cargoName;
	
	public CreateNewCargoCommand(String cargoName) {
		this.cargoName = cargoName;
	}

	public String getCargoName() {
		return cargoName;
	}
}
