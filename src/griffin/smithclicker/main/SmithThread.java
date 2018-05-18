package griffin.smithclicker.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;

public class SmithThread extends Thread{
	
	
public static boolean running = false;
	
	public SmithThread() {
	}
	
	@Override
	public void run() {
		running = true;
		long time = System.nanoTime();
		int ticks = 20;
		int timeBetween = 1000000000 / ticks;
		long curTime = System.nanoTime();
		while(running){
			curTime = System.nanoTime();
			if(curTime - time >= timeBetween){
				while(curTime - time >= timeBetween){
					time += timeBetween;
					tick();
				}
				render();
			}
		}
		
	}

	public void render() {
		BufferedImage bImg = new BufferedImage(GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT, BufferedImage.TYPE_INT_ARGB);
		Graphics g = bImg.createGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT);
		
		GameManager.getManager().render(g);
	
		SmithClicker.frame.getGraphics().drawImage(bImg, SmithClicker.frame.getInsets().left, SmithClicker.frame.getInsets().top, null);
	}
	
	public void tick(){
		GameManager.getManager().tick();
	}
	
	
}
