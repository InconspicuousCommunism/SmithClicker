package griffin.smithclicker.objects.display;

import java.awt.Graphics;
import java.awt.Image;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.util.ImageHelper;

public class BackgroundOverlay extends GameObject{
	
	private static Image overlay = ImageHelper.loadImage("/background/SmithClickerOverlay.png");
	
	public BackgroundOverlay() {
		super(0, 0, GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT, 1000);
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(overlay, getX(), getY(), getWidth(), getHeight(), null);
	}
	
}
