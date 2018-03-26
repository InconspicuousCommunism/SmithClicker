package griffin.smithclicker.effect;

import java.util.ArrayList;

public class Effects {
	
	public static Effects effects = new Effects();
	private ArrayList<Effect> effectList;
	
	public Effects() {
		effectList = new ArrayList<Effect>();
	}
	
	public void addEffect(Effect e) {
		this.effectList.add(e);
	}
	
	public void removeEffect(Effect e) {
		this.effectList.remove(e);
	}
	
	public void tickEffects() {
		for(Effect e : effectList) e.tickEffect();
	}
	
}
