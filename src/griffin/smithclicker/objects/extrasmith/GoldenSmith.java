package griffin.smithclicker.objects.extrasmith;

import java.awt.Graphics;
import java.awt.Image;
import java.math.BigInteger;
import java.util.Random;

import griffin.smithclicker.effect.ClickBoost;
import griffin.smithclicker.effect.Effects;
import griffin.smithclicker.effect.SPSBoost;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.util.ImageHelper;

public class GoldenSmith extends GameObject implements IClickable{
	
	private static Image img = ImageHelper.loadImage("/icons/goldensmith.png");
	
	private GoldenType gType;
	
	private static int BOX_WIDTH = 75;
	private static int BOX_HEIGHT = 100;
	
	private int life;
	private static double i;
	
	public GoldenSmith(int x, int y) {
		super(x,y,(int)(BOX_WIDTH * (i = .5 + (.5 * Math.random()))),(int)(BOX_HEIGHT * i),1001);
		gType = GoldenType.getRandomType(new Random());
		life = 1200;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		g.drawImage(img, getX(), getY(), getWidth(), getHeight(), null);
		life--;
		if(life <= 0)this.kill();
	}
	
	@Override
	public void clicked(int x, int y, int type) {
		int mult = 2;
		switch(gType) {
		case EXTREME_ADD:
			mult *=2;
		case LARGE_ADD:
			mult *= 2;
		case MEDIUM_ADD:
			mult *= 2;
		case SMALL_ADD:
			GameManager.getManager().addSmiths(GameManager.getSmithsPerSecond().multiply(new BigInteger(mult+"")).add(new BigInteger("25")));
			break;
		case CLICK_BOOST:
			Effects.effects.addEffect(new ClickBoost(200, 2));
			break;
		case SPS_BOOST:
			Effects.effects.addEffect(new SPSBoost(200, 2));
			break;
		}
		this.kill();
	}

	@Override
	public void pressed(int x, int y, int type) {	
		
	}

	public static int getBoxWidth() {
		return BOX_WIDTH;
	}

	public static int getBoxHeight() {
		return BOX_HEIGHT;
	}
	
}

enum GoldenType {
	SMALL_ADD(1000), MEDIUM_ADD(100), LARGE_ADD(10), EXTREME_ADD(1), SPS_BOOST(500), CLICK_BOOST(500);
	
	private static int totalWeight;
	private int weight;
	private int extent;
	
	private GoldenType(int weight) {
		this.weight = getWeight(weight);
		this.extent = weight;
		addTotal(weight);
	}
	
	private void addTotal(int weight) {
		totalWeight += weight;
	}
	
	private int getWeight(int weight) {
		return totalWeight + weight;
	}
	
	public int getSpecificWeight() {
		return weight;
	}
	
	public int getExtent() {
		return extent;
	}
	
	public static GoldenType getRandomType(Random rand) {
		int place = rand.nextInt(totalWeight);
		for(GoldenType g : GoldenType.values()) {
			if(place >= g.getSpecificWeight() && place < g.getExtent() + g.getSpecificWeight())return g;
		}
		return SMALL_ADD;
	}
}