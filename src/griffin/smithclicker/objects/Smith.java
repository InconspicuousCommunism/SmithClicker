package griffin.smithclicker.objects;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.math.BigInteger;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.IncreaseSmithEvent;
import griffin.smithclicker.event.IncreaseSmithEvent.FromType;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.util.ImageHelper;

public class Smith extends GameObject implements IClickable{
	
	private static final BufferedImage img = ImageHelper.loadImage("/icons/smith.png");
	
	private static final int POS_X = 7;
	private static final int POS_Y = 410;
	private static final int WIDTH = 287;
	private static final int HEIGHT = 350;
	
	public Smith() {
		super(POS_X,POS_Y, WIDTH, HEIGHT);
	}
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(img, POS_X, POS_Y, getWidth(), getHeight(), null);
		
		super.render(g);
	}

	@Override
	public void clicked(int x, int y, int type) {
		BigInteger add = new BigInteger("1");
		GameManager.getManager().addObject(new Hitmarker(x,y,30));
		EventManager.queueEvent(new IncreaseSmithEvent(add, FromType.click, null));
		GameManager.getManager().addClicks(new BigInteger("1"));
	}

	@Override
	public void pressed(int x, int y, int type) {
	}

	public static int getPosY() {
		return POS_Y;
	}

	public static int getBoxWidth() {
		return WIDTH;
	}

	public static int getBoxHeight() {
		return HEIGHT;
	}
	
}
