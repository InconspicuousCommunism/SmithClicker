package griffin.smithclicker.effect;

import java.math.BigInteger;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.IncreaseSmithEvent;
import griffin.smithclicker.event.WatchEvent;
import griffin.smithclicker.event.IncreaseSmithEvent.FromType;

public class ClickBoost extends Effect{

	private int multBoost;
	
	public ClickBoost(int length, int multBoost) {
		super(length);
		this.multBoost = multBoost;
		EventManager.registerListener(this);
	}
	
	@WatchEvent()
	public void onIncreaseSmithEvent(IncreaseSmithEvent e) {
		if(e.getType() == FromType.click)
			e.modifier *= multBoost;
	}
	
	public void kill() {
		EventManager.removeListener(this);
	}

	@Override
	public String toString() {
		return "X" + multBoost + " to clicks";
	}
	
}
