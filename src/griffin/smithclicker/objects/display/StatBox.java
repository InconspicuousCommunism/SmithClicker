package griffin.smithclicker.objects.display;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Point;

import griffin.smithclicker.effect.Effect;
import griffin.smithclicker.effect.Effects;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.main.SmithClicker;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.upgrades.Upgrade;
import griffin.smithclicker.objects.upgrades.Upgrades;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.StringUtils;

public class StatBox extends GameObject{
	
	private static final Font STATS_INFO;
	private static final Font UPGRADE_STATS;
	
	static {
		STATS_INFO = new Font("Comic Sans MS", Font.PLAIN, 15);
		UPGRADE_STATS = new Font("Comic Sans MS", Font.PLAIN, 12);
	}
	
	private static final int POS_X = 303;
	private static final int POS_Y = 459;
	private static final int WIDTH = 399;
	private static final int HEIGHT = 301;
	private static final int START_TEXT_Y = 15;
	private static final int DIST_BETWEEN_TEXT = 30;
	
	public StatBox() {
		super(POS_X, POS_Y, WIDTH,HEIGHT);
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.cyan);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Per Second", StringUtils.formatNumber(GameManager.getSmithsPerSecond()), getX(), getWidth(), getY()+START_TEXT_Y);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Collected From Upgrades", StringUtils.formatNumber(GameManager.getUpgradeSmiths()), getX(), getWidth(), getY() + START_TEXT_Y + DIST_BETWEEN_TEXT * 1);
		StringUtils.drawStats(g, STATS_INFO, "Smiths Collected From Clicks", StringUtils.formatNumber(GameManager.getClickSmiths()), getX(), getWidth(), getY() + START_TEXT_Y + DIST_BETWEEN_TEXT * 2);
		StringUtils.drawStats(g, STATS_INFO, "Total Amount of Smiths Collected", StringUtils.formatNumber(GameManager.getTotalSmiths()), getX(), getWidth(), getY() + START_TEXT_Y + DIST_BETWEEN_TEXT * 3);
		StringUtils.drawStats(g, STATS_INFO, "Total Clicks", StringUtils.formatNumber(GameManager.getTotalClicks()), getX(), getWidth(), getY() + START_TEXT_Y + DIST_BETWEEN_TEXT * 4);
		g.setColor(Color.black);
		StringUtils.drawStringCentered(g, STATS_INFO, "EFFECTS", getX(), WIDTH, 175 + getY());
		int y = getY() + 20;
		g.setColor(Color.RED);
		for(Effect e : Effects.effects.getList()) {
			y+=20;
			y = StringUtils.drawDescription(g, STATS_INFO, e.toString(), getX(), y, getWidth(), 20);
		}
		
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
