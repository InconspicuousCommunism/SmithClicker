package griffin.smithclicker.objects.upgrades.mini;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.main.SmithClicker;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.util.GraphicsHelper;
import griffin.smithclicker.util.ImageHelper;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.StringUtils;

public class MiniDescript extends GameObject{
	
	private static final Image CHECK_ICON = ImageHelper.loadImage("/misc_icons/checkmark.png");
	private static final Image X_ICON = ImageHelper.loadImage("/misc_icons/x_icon.png");
	private static Font NAME_FONT = new Font("Comic Sans MS", Font.PLAIN, ScaleUtils.scaleNumber(15));
	private static Font DESC_FONT = new Font("Comic Sans MS", Font.PLAIN, ScaleUtils.scaleNumber(11));
	
	private Miniupgrade mini;
	
	public MiniDescript(Miniupgrade mini, int width, int height) {
		super(0, 0, width, height, 10000);
		this.mini = mini;
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		try {
			if(mini.isDisplayed()) {
				int mx = SmithClicker.frame.getMouseLocation().x - GameManager.GAME_INSET_LEFT;
				int my = SmithClicker.frame.getMouseLocation().y - GameManager.GAME_INSET_TOP;
				if(mx > mini.getX() && my > mini.getY() && mx < mini.getX() + mini.getWidth() && my < mini.getY() + mini.getHeight()) {
					renderMouseHover(g, mx,my);
				}
			}
		}catch(Exception e) {}
	}
	
	private void renderMouseHover(Graphics g, int mx, int my) {
		mx += 15; //Move away form the mouse a tad
		my += 15;
		int borderEndX = ScaleUtils.scaleNumber(3) + mx;
		int borderEndY = ScaleUtils.scaleNumber(3) + my;
		int betweenWidth = ScaleUtils.scaleNumber(190-6);
		GraphicsHelper.drawBorderedRect(g, Color.gray, Color.black, mx, my, ScaleUtils.scaleNumber(190), ScaleUtils.scaleNumber(230), ScaleUtils.scaleNumber(3));
		g.setColor(Color.black);
		g.drawRect(mx, my, ScaleUtils.scaleNumber(190), ScaleUtils.scaleNumber(230));
		g.setFont(NAME_FONT);
		g.drawString(mini.getName(), borderEndX, borderEndY + ScaleUtils.scaleNumber(13));
		int cury = StringUtils.drawDescription(g, DESC_FONT, mini.getDesc(), borderEndX, borderEndY + ScaleUtils.scaleNumber(27), betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4);
		g.fillRect(borderEndX, cury + ScaleUtils.scaleNumber(2), betweenWidth, ScaleUtils.scaleNumber(3));
		cury+=StringUtils.getStringHeight(g, DESC_FONT);
		for(Requirement r : mini.getRequirements()) {
			Image img = r.checkCompleted() ? CHECK_ICON : X_ICON;
			g.drawImage(img, borderEndX, cury - StringUtils.getStringHeight(g, DESC_FONT) /2 -4, StringUtils.getStringHeight(g, DESC_FONT) - 4, StringUtils.getStringHeight(g, DESC_FONT) - 4, null);
			cury = StringUtils.drawDescription(g, DESC_FONT, r.toString(), borderEndX + StringUtils.getStringHeight(g, DESC_FONT) - 4, cury, betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4) + StringUtils.getStringHeight(g, DESC_FONT) - 4;
		}
		Image img = (GameManager.getSmiths().compareTo(mini.getCost()) >= 0) ? CHECK_ICON : X_ICON;
		g.drawImage(img, borderEndX, cury - StringUtils.getStringHeight(g, DESC_FONT) /2 -4, StringUtils.getStringHeight(g, DESC_FONT) - 4, StringUtils.getStringHeight(g, DESC_FONT) - 4, null);
		StringUtils.drawDescription(g, DESC_FONT, "Costs " + StringUtils.formatNumber(mini.getCost()), borderEndX + StringUtils.getStringHeight(g, DESC_FONT) - 4, cury, betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4);
	}
	
}
