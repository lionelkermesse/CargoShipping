package events;

public interface IEventHandler<T,E> {
	public void handles(T event);
}
