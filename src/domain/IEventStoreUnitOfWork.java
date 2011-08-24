package domain;

public interface IEventStoreUnitOfWork {
	public void commit();
	public void rollback();
}
