package com.linkcorp.dddcqrs.domain;

import java.util.UUID;

import com.linkcorp.dddcqrs.infrastructure.AggregateNotFoundException;
import com.linkcorp.dddcqrs.infrastructure.ConcurrencyException;

public interface IDomainRepository<T> {
	public void save(T aggregate) throws ConcurrencyException;
	public T getById(UUID aggregateId) throws AggregateNotFoundException;
}
