package com.linkcorp.dddcqrs.domain;

public interface IEventStoreUnitOfWork {
	public void commit() throws ClassNotFoundException, InstantiationException, IllegalAccessException;
	public void rollback();
}
