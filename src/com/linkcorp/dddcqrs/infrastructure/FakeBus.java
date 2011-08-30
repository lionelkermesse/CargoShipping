package com.linkcorp.dddcqrs.infrastructure;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import com.linkcorp.dddcqrs.annotations.EventPublisherAnnotation;
import com.linkcorp.dddcqrs.commands.CommandSender;
import com.linkcorp.dddcqrs.commands.ICommand;
import com.linkcorp.dddcqrs.events.EventPublisher;
import com.linkcorp.dddcqrs.events.IEvent;
import com.linkcorp.dddcqrs.events.Message;

@EventPublisherAnnotation
public class FakeBus implements EventPublisher, CommandSender{
	private HashMap<Class<?>, List<String>> routes = new HashMap<Class<?>, List<String>>();
	private static FakeBus singleton = new FakeBus();
	
	private FakeBus(){
	}
	
	public static FakeBus get(){
		return singleton;
	}
	
	public void RegisterHandler(Class<?> messageType, String handlerName){
		
		List<String> handlers = getMessageRouters(messageType);
		if(handlers == null){
			handlers = new ArrayList<String>();
			routes.put(messageType, handlers);
		}
		
		handlers.add(handlerName);
	}
	
	public void send(ICommand command){
		route(command);
	}

	public void publish(IEvent event){
		route(event);
	}
	
	private void route(Message message){
		List<String> handlers = this.routes.get(message.getClass());
		try{
			if(handlers == null)
				throw new MessageHandlerNotFoundException("There is not exist handler for message with type " + message.getClass().getName());
		
			for (String handlerName : handlers) {
				Class<?> clazz = Class.forName(handlerName);
				Method method = clazz.getDeclaredMethod("handles", new Class[]{message.getClass()});
				method.invoke(clazz.newInstance(), new Object[]{message});
			}
		} 
		catch (ClassNotFoundException e) {e.printStackTrace();} 
		catch (InstantiationException e) {e.printStackTrace();} 
		catch (IllegalAccessException e) {e.printStackTrace();} 
		catch (MessageHandlerNotFoundException e) {e.printStackTrace();} 
		catch (NoSuchMethodException e) {e.printStackTrace();} 
		catch (SecurityException e) {e.printStackTrace();} 
		catch (IllegalArgumentException e) {e.printStackTrace();} 
		catch (InvocationTargetException e) {e.printStackTrace();}
	}
	
	public HashMap<Class<?>, List<String>> getRoutes(){
		return this.routes;
	}
	
	public List<String> getMessageRouters(Class<?> message){
		List<String> handlers = this.routes.get(message);
		return handlers;
	}
	
	public List<String> getMessageRouters(Message message){
		List<String> handlers = this.routes.get(message.getClass());
		return handlers;
	}
}