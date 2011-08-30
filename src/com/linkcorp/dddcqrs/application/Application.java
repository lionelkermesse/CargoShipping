package com.linkcorp.dddcqrs.application;

import com.linkcorp.dddcqrs.infrastructure.FakeBus;

public class Application {
	@SuppressWarnings("unused")
	private FakeBus bus;
	public Application(){
		bus = FakeBus.get();
	}
}
