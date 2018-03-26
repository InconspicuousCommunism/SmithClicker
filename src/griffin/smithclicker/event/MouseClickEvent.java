package griffin.smithclicker.event;

public class MouseClickEvent extends Event {
	
	private int x, y, type;

	private boolean release;
	
	public MouseClickEvent(int x, int y, int type, boolean release) {
		this.x = x;
		this.y = y;
		this.type = type;
		this.release = release;
	}
	
	@Override
	public void triggerEvent() {		
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getType() {
		return type;
	}

	public boolean isReleased() {
		return release;
	}

}
