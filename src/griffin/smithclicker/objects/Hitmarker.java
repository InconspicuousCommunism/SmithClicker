package griffin.smithclicker.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.util.ImageHelper;

public class Hitmarker extends GameObject{
	
	private static final BufferedImage HIT_MARKER = ImageHelper.loadImage("/misc_icons/hitmarker.png");
	
	private BufferedImage img;
	private int time;
	private int totalTime;
	
	public Hitmarker(int x, int y, int time) {
		super(x - HIT_MARKER.getWidth()/2, y - HIT_MARKER.getHeight()/2);
		this.time = time;
		this.totalTime = time;
		Random r = new Random();
		Color c = new Color(r.nextInt(255), r.nextInt(255), r.nextInt(255));
		img = ImageHelper.colorize(HIT_MARKER, c);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(time == 0)this.kill();
		double a = time /totalTime;
		
		g.drawImage(img, getX(), getY(), null);
		
		time--;
	}
	
}
