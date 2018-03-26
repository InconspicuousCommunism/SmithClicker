package griffin.smithclicker.event;

public abstract class Event {
	
	private boolean cancel;
	
	public void cancelEvent(boolean cancel) {
		this.cancel = cancel;
	}
	
	public boolean isCancelled() {
		return cancel;
	}
	
	public abstract void triggerEvent();
	
	public void onEventCancelled() {}
	
}
