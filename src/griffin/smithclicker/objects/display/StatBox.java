package griffin.smithclicker.objects.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.main.SmithClicker;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.upgrades.Upgrade;
import griffin.smithclicker.objects.upgrades.Upgrades;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.StringUtils;

public class StatBox extends GameObject{
	
	private static final Font STATS_TITLE = new Font("Comic Sans MS", Font.BOLD, 24);
	private static final Font STATS_INFO = new Font("Comic Sans MS", Font.PLAIN, 20);
	private static final Font UPGRADE_STATS = new Font("Comic Sans MS", Font.PLAIN, 18);
	
	private static final int POS_X;
	private static final int POS_Y;
	private static final int WIDTH;
	private static final int HEIGHT;
	
	static {
		POS_X = (int)((350.0/1200)*GameManager.GAME_WIDTH);
		WIDTH = (int)(350 * GameManager.GAME_RATIO);
		POS_Y = (int)((300.0/800)*GameManager.GAME_HEIGHT);
		HEIGHT = GameManager.GAME_HEIGHT - POS_Y;
	}
	
	public StatBox() {
		super(POS_X, POS_Y, WIDTH,HEIGHT);
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.GRAY);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawLine(getX(), getY() + 30, getX() + getWidth(), getY() + 30);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.cyan);
		StringUtils.drawStringCentered(g, STATS_TITLE, "Game Statistics", 350, getWidth(), getY() + 23);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Per Second", StringUtils.formatNumber(GameManager.getSmithsPerSecond()), getX(), getWidth(), getY() + 60);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Collected From Upgrades", StringUtils.formatNumber(GameManager.getUpgradeSmiths()), getX(), getWidth(), getY() + 100);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Collected From Clicks", StringUtils.formatNumber(GameManager.getClickSmiths()), getX(), getWidth(), getY() + 140);
		StringUtils.drawStats(g, STATS_INFO, "Total Amount of Smiths Collected", StringUtils.formatNumber(GameManager.getTotalSmiths()), getX(), getWidth(), getY() + 180);
		StringUtils.drawStats(g, STATS_INFO, "Total Clicks", StringUtils.formatNumber(GameManager.getTotalClicks()), getX(), getWidth(), getY() + 220);
		
		Point mouse = SmithClicker.frame.getMouseLocation();
		if(mouse != null) {
			int mx = mouse.x - GameManager.GAME_INSET_LEFT;
			int my = mouse.y - GameManager.GAME_INSET_TOP;
			
			if(mx > getX() && mx < getX() + getWidth() && my > getY() + 70 && my < getY() + 110) {
				mx += 15;
				my += 15;
				g.setColor(Color.gray);
				g.fillRect(mx, my, ScaleUtils.scaleNumber(200), ScaleUtils.scaleNumber(300));
				g.setColor(Color.BLACK);
				g.drawRect(mx, my, ScaleUtils.scaleNumber(200), ScaleUtils.scaleNumber(300));
				int loc = 0;
				for(Upgrade u : Upgrades.upgrades.getUpgrades()) {
					StringUtils.drawStats(g, UPGRADE_STATS, u.getName(), StringUtils.formatNumber(u.getSmithsMade()), mx + ScaleUtils.scaleNumber(2), ScaleUtils.scaleNumber(196), my + 30 * loc + ScaleUtils.scaleNumber(12));
					loc++;
				}
			}
		}
		
		super.render(g);
	}
	
}
