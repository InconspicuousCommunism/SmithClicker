package griffin.smithclicker.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.util.Random;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.util.ImageHelper;
import griffin.smithclicker.util.ScaleUtils;

public class Hitmarker extends GameObject{
	
	private static final BufferedImage HIT_MARKER = ImageHelper.loadImage("/misc_icons/hitmarker.png");
	
	private BufferedImage img;
	private int time;
	private int totalTime;
	private Random r;
	private int size;
	
	public Hitmarker(int x, int y, int time) {
		super(x - HIT_MARKER.getWidth()/2, y - HIT_MARKER.getHeight()/2);
		this.time = time;
		this.totalTime = time;
		r = new Random();
		size = r.nextInt(32) + 20;
		this.setX(x - ScaleUtils.scaleNumber(size)/2);
		this.setY(y - ScaleUtils.scaleNumber(size)/2);
		Color c = new Color(Color.HSBtoRGB(r.nextFloat(), 1f, 1f));
		img = ImageHelper.colorize(HIT_MARKER, c);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		
		if(time == 0)this.kill();
		g.drawImage(img, getX(), getY(), ScaleUtils.scaleNumber(size), ScaleUtils.scaleNumber(size), null);
		
		time--;
	}
	
}
