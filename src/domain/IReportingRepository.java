package domain;

import java.util.List;

public interface IReportingRepository<T> {
	public void save(T aggregateDTO);
	public void update(T aggreatedDTO);
	public void delete(String aggregateDTOId);
	public List<T> getBy(Object type);
}
