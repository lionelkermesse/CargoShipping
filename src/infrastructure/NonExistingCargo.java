package infrastructure;

@SuppressWarnings("serial")
public class NonExistingCargo extends Exception{
	
	public NonExistingCargo(String message){
		super(message);
	}
}
