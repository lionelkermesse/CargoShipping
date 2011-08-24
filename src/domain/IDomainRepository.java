package domain;

import java.util.UUID;

import infrastructure.AggregateNotFoundException;
import infrastructure.ConcurrencyException;

public interface IDomainRepository<T> {
	public void save(T aggregate) throws ConcurrencyException;
	public T getById(UUID aggregateId) throws AggregateNotFoundException;
}
