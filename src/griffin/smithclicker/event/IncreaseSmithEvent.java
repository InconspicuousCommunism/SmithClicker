package griffin.smithclicker.event;

import java.math.BigInteger;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.upgrades.Upgrade;

public class IncreaseSmithEvent extends Event{
	
	public BigInteger increase;
	private FromType type;
	private Upgrade upgrade;
	public double modifier = 1.0;
	
	public IncreaseSmithEvent(BigInteger increase, FromType type, Upgrade upgrade) {
		this.increase = increase;
		this.type = type;
		this.upgrade = upgrade;
	}
	
	@Override
	public void triggerEvent() {
		GameManager.getManager().addSmiths(increase.multiply(new BigInteger("" + ((int) modifier))));
		switch(type) {
		case click:
			GameManager.getManager().addClickSmiths(increase.multiply(new BigInteger("" + ((int) modifier))));
			break;
		case upgrade:
			GameManager.getManager().addUpgradeSmiths(increase);
		}
	}
	
	public FromType getType() {
		return type;
	}
	
	public Upgrade getUpgrade() {
		return upgrade;
	}
	
	public enum FromType{
		upgrade, click;
	}
	
}
