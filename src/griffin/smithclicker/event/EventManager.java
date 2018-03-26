package griffin.smithclicker.event;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class EventManager {
	
	private static List<Object> listeners = new ArrayList<Object>();
	private static List<Event> events = new ArrayList<Event>();
	
	public static void registerListener(Object o) {
		listeners.add(o);
	}
	
	public static void removeListener(Object o) {
		listeners.remove(o);
	}
	
	public static void queueEvent(Event e) {
		events.add(e);
	}
	
	public static void fireEvents() {
		for(Object o : listeners) {
			for(Method m : o.getClass().getDeclaredMethods()) {
				if(m.isAnnotationPresent(WatchEvent.class)) {
					if(m.getParameterCount() == 1) {
						for(Event e : events) {
							if(e.getClass().equals(m.getParameterTypes()[0])) {
								try {
									m.invoke(o, e);
								} catch (Exception except) {}
							}
						}
					}
				}
			}
		}
		for(Event e : events) {
			if(e.isCancelled())e.onEventCancelled();
			else e.triggerEvent();
		}
		events.clear();
	}
	
}