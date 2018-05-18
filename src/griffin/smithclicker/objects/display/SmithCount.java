package griffin.smithclicker.objects.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.util.StringUtils;

public class SmithCount extends GameObject{
	
	private static final int SMITH_FONT_SIZE = 50;
	private static final Font SMITH_FONT = new Font("Comic Sans MS", Font.BOLD, SMITH_FONT_SIZE);
	
	private static final int WIDTH = 399;
	private static final int HEIGHT = 286;
	private static final int POS_X = 303;
	private static final int POS_Y = 115;
	
	public SmithCount() {
		super(POS_X, POS_Y, WIDTH, HEIGHT);
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setFont(SMITH_FONT);
		g.setColor(Color.cyan);
		String smiths = GameManager.getFormattedSmiths();
		int width = StringUtils.getStringWidth(g, SMITH_FONT, smiths);
		g.drawString(smiths, getX() + ((getWidth()-width)/2), getY() + 100);
		width = StringUtils.getStringWidth(g, SMITH_FONT, smiths.equals("1") ? "Smith" : "Smiths");
		g.drawString(smiths.equals("1") ? "Smith" : "Smiths", getX() + ((getWidth()-width)/2), getY() + 150);
		super.render(g);
	}
	
}
