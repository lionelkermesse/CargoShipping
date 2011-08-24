package infrastructure;

import java.lang.reflect.Method;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import commands.CommandBus;

import events.EventBus;

public class FakeBus<T> implements EventBus, CommandBus{
	private HashMap<Type, List<Method>> routes = new HashMap<Type, List<Method>>(); 
	
	
	public void RegisterHandler(Method handler){
		Type [] types = handler.getTypeParameters();
		List<Method> methodHanlers;
		
		if(types.length > 0){
			methodHanlers = routes.get(types[0]);
			if(methodHanlers == null){
				methodHanlers = new ArrayList<Method>();
				routes.put(types[0], methodHanlers);
			}
			methodHanlers.add(handler);
		}
	}
	
	@Override
	public <T> void publish(T event) {
		// TODO Auto-generated method stub

	}

	@Override
	public <T> void send(T command) {
		// TODO Auto-generated method stub
		
	}
	
	
}