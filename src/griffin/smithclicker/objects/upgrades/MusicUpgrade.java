package griffin.smithclicker.objects.upgrades;

import java.awt.Image;
import java.math.BigInteger;

import griffin.smithclicker.util.SoundHelper;

public class MusicUpgrade extends Upgrade{

	private String music;
	
	public MusicUpgrade(int id, Image img, int baseCost, int sps, double addClick, String name, String music) {
		super(id, img, baseCost, sps, addClick, name);
		this.music = music;
	}
	
	public MusicUpgrade(int id, Image img, BigInteger baseCost, int sps, double addClick, String name, String music) {
		super(id, img, baseCost, sps, addClick, name);
		this.music = music;
	}
	
	@Override
	protected void onBuy() {
		super.onBuy();
		SoundHelper.playMusic(music);
	}
	
}
