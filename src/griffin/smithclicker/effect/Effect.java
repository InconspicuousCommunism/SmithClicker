package griffin.smithclicker.effect;

public abstract class Effect {
	
	private int effectLength;
	private boolean isOver;
	
	public Effect(int length) {
		this.effectLength = length;
	}
	
	@Override
	public String toString() {
		return "+0% To Everything";
	}
	
	public void tickEffect() {
		if(effectLength != -1) {
			if(effectLength == 0) isOver = true;
			effectLength--;
		}
		if(isOver) {
			kill();
		}
	}
	
	public boolean isOver() {
		return isOver;
	}
	
	public int getRemainingTime() {
		return effectLength;
	}
	
	public abstract void kill();
	
}
