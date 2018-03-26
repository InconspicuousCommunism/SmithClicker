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
	
	private static final BufferedImage img = ImageHelper.loadImage("/icons/smith.jpg");
	
	private static final int POS_Y;
	private static final int WIDTH;
	private static final int HEIGHT;
	
	static {
		WIDTH = (int)((350.0/1200)*GameManager.GAME_WIDTH);
		HEIGHT = (int)((500.0/800)*GameManager.GAME_HEIGHT);
		POS_Y = GameManager.GAME_HEIGHT - HEIGHT;
	}
	
	public Smith() {
		super(0,POS_Y, WIDTH, HEIGHT);
	}
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(img, 0, POS_Y, getWidth(), getHeight(), null);
		
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
