package infrastructure;

@SuppressWarnings("serial")
public class NonExistingShip extends Exception {

	public NonExistingShip(String message) {
		super(message);
	}
}
