package griffin.smithclicker.effect;

import java.util.ArrayList;

public class Effects {
	
	public static Effects effects = new Effects();
	private ArrayList<Effect> effectList;
	private ArrayList<Effect> deathList;
	
	public Effects() {
		effectList = new ArrayList<Effect>();
		deathList = new ArrayList<Effect>();
	}
	
	public void addEffect(Effect e) {
		this.effectList.add(e);
	}
	
	public void removeEffect(Effect e) {
		this.effectList.remove(e);
	}
	
	public void tickEffects() {
		for(Effect e : effectList) e.tickEffect();
		for(Effect e : deathList) {
			effectList.remove(e);
		}
		deathList.clear();
	}
	
	public ArrayList<Effect> getList(){
		return this.effectList;
	}
	
	public void sendToDeath(Effect e) {
		deathList.add(e);
	}
	
}
