package application;

import infrastructure.FakeBus;

public class Application {
	private FakeBus bus;
	public Application(){
		bus = new FakeBus();
	}
}
