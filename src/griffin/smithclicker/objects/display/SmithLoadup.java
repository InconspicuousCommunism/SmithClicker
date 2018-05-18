package griffin.smithclicker.objects.display;

import java.awt.Graphics;
import java.awt.Image;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.util.ImageHelper;

public class SmithLoadup extends GameObject{
	
	private static Image smithBackground = ImageHelper.loadImage("/background/backgroundSmith.png");
	
	private int counter;
	
	public SmithLoadup() {
		super(0, 0, GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT);
		counter = 50;
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(smithBackground, -130, 0, GameManager.GAME_WIDTH + 300, GameManager.GAME_HEIGHT, null);
		counter--;
		if(counter <= 0) {
			GameManager.getManager().setupBaseGame();
			this.kill();
		}
	}
	
}
