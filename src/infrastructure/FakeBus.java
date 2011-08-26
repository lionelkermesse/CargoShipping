package infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commands.CommandBus;
import commands.ICommand;

import events.EventBus;
import events.IEvent;
import events.Message;

public class FakeBus implements EventBus, CommandBus{
	private HashMap<Class<?>, List<Method>> routes = new HashMap<Class<?>, List<Method>>(); 
	
	public FakeBus(){}
	
	public void RegisterHandler(Class<?> messageClass, Method handler){
		List<Method> handlers = getMessageRouters(messageClass);
		if(handlers == null){
			handlers = new ArrayList<Method>();
			routes.put(messageClass, handlers);
		}
		
		handlers.add(handler);
	}
	
	@Override
	public void send(ICommand command) throws MessageHandlerNotFoundException {
		route(command);
	}

	@Override
	public void publish(IEvent event) throws MessageHandlerNotFoundException {
		route(event);
	}
	
	private void route(Message message){
		List<Method> handlers = this.routes.get(message.getClass());
		try {
			if(handlers == null)
				throw new MessageHandlerNotFoundException("There is not exist handler for message with type " + message.getClass().getName());
		
			for (Method handler : handlers) {
				Class<?> _class = handler.getDeclaringClass();
				handler.invoke(_class.newInstance(), new Object[]{message});
			}
			
		} catch (IllegalAccessException e) {e.printStackTrace();} 
		  catch (IllegalArgumentException e) {e.printStackTrace();}
		  catch (InvocationTargetException e) {e.printStackTrace();}
		  catch (InstantiationException e) {e.printStackTrace();} 
		  catch (MessageHandlerNotFoundException e) {
			e.printStackTrace();
		}
	}
	
	public HashMap<Class<?>, List<Method>> getRoutes(){
		return this.routes;
	}
	
	public List<Method> getMessageRouters(Class<?> message){
		List<Method> handlers = this.routes.get(message);
		return handlers;
	}
	
	public List<Method> getMessageRouters(Message message){
		List<Method> handlers = this.routes.get(message.getClass());
		return handlers;
	}
}