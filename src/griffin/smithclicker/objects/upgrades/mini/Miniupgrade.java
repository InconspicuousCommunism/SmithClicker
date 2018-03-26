package griffin.smithclicker.objects.upgrades.mini;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.math.BigInteger;
import java.util.List;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.main.SmithClicker;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.objects.upgrades.Upgrades;
import griffin.smithclicker.util.GraphicsHelper;
import griffin.smithclicker.util.ImageHelper;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.StringUtils;

public abstract class Miniupgrade extends GameObject implements IClickable{
	
	private static final Image CHECK_ICON = ImageHelper.loadImage("/misc_icons/checkmark.png");
	private static final Image X_ICON = ImageHelper.loadImage("/misc_icons/x_icon.png");
	
	private static int POSX = 0;
	private static int POSY = 0;
	private static int WIDTH = ScaleUtils.scaleNumber(40);
	private static int HEIGHT = ScaleUtils.scaleNumber(40);
	private static int SPACING = ScaleUtils.scaleNumber(7);
	
	private static Font NAME_FONT = new Font("Comic Sans MS", Font.PLAIN, ScaleUtils.scaleNumber(15));
	private static Font DESC_FONT = new Font("Comic Sans MS", Font.PLAIN, ScaleUtils.scaleNumber(11));
	
	private String name, desc;
	private int pos;
	private boolean bought = false;
	
	private BigInteger cost;
	
	private Color color;
	
	private Image img;
	
	private List<Requirement> requires;
	
	public Miniupgrade(String name, String desc, BigInteger cost, Image img, List<Requirement> requires, Color color) {
		super(SPACING, SPACING, WIDTH, HEIGHT);
		this.name = name;
		this.desc = desc;
		this.cost = cost;
		this.img = img;
		this.requires = requires;
		this.color = color;
	}
	
	public void changeListPosition(int pos) {
		this.pos = pos;
		POSX = SPACING + (pos % 7) * (WIDTH + SPACING) + 8;
		POSY = SPACING + (pos / 7 ) * (HEIGHT + SPACING);
		this.setX(POSX);
		this.setY(POSY);
	}
	
	@Override
	public void render(Graphics g) {
		super.render(g);
		if(!bought) {
			Color c = canBeBought() ? Color.green : Color.DARK_GRAY;
			GraphicsHelper.drawBorderedImg(g, img, c, getX(), getY(), getWidth(), getHeight(), 4);
			g.setColor(color);
			g.fillRect(getX() + 4, getY() +  + getHeight() - 8, getWidth() - 8, 4);
			
			try {
				int mx = SmithClicker.frame.getMouseLocation().x - GameManager.GAME_INSET_LEFT;
				int my = SmithClicker.frame.getMouseLocation().y - GameManager.GAME_INSET_TOP;
				if(mx > getX() && my > getY() && mx < getX() + getWidth() && my < getY() + getHeight()) {
					drawHover(g, mx,my);
					if(this.getZ() != 1) 
						this.setZ(1);
				} else {
					if(this.getZ() != 1000)
						this.setZ(1000);
				}
			}catch(Exception e) {};
		} else {
			this.setX(-1000);
		}
	}
	
	private void drawHover(Graphics g, int mx, int my) {
		mx += 15; //Move away form the mouse a tad
		my += 15;
		int borderEndX = ScaleUtils.scaleNumber(3) + mx;
		int borderEndY = ScaleUtils.scaleNumber(3) + my;
		int betweenWidth = ScaleUtils.scaleNumber(190-6);
		GraphicsHelper.drawBorderedRect(g, Color.gray, Color.black, mx, my, ScaleUtils.scaleNumber(190), ScaleUtils.scaleNumber(230), ScaleUtils.scaleNumber(3));
		g.setColor(Color.black);
		g.drawRect(mx, my, ScaleUtils.scaleNumber(190), ScaleUtils.scaleNumber(230));
		g.setFont(NAME_FONT);
		g.drawString(name, borderEndX, borderEndY + ScaleUtils.scaleNumber(13));
		int cury = StringUtils.drawDescription(g, DESC_FONT, desc, borderEndX, borderEndY + ScaleUtils.scaleNumber(27), betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4);
		g.fillRect(borderEndX, cury + ScaleUtils.scaleNumber(2), betweenWidth, ScaleUtils.scaleNumber(3));
		cury+=StringUtils.getStringHeight(g, DESC_FONT);
		for(Requirement r : this.requires) {
			Image img = r.checkCompleted() ? CHECK_ICON : X_ICON;
			g.drawImage(img, borderEndX, cury - StringUtils.getStringHeight(g, DESC_FONT) /2 -4, StringUtils.getStringHeight(g, DESC_FONT) - 4, StringUtils.getStringHeight(g, DESC_FONT) - 4, null);
			cury = StringUtils.drawDescription(g, DESC_FONT, r.toString(), borderEndX + StringUtils.getStringHeight(g, DESC_FONT) - 4, cury, betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4) + StringUtils.getStringHeight(g, DESC_FONT) - 4;
		}
		Image img = (GameManager.getSmiths().compareTo(cost) >= 0) ? CHECK_ICON : X_ICON;
		g.drawImage(img, borderEndX, cury - StringUtils.getStringHeight(g, DESC_FONT) /2 -4, StringUtils.getStringHeight(g, DESC_FONT) - 4, StringUtils.getStringHeight(g, DESC_FONT) - 4, null);
		StringUtils.drawDescription(g, DESC_FONT, "Costs " + StringUtils.formatNumber(cost), borderEndX + StringUtils.getStringHeight(g, DESC_FONT) - 4, cury, betweenWidth, StringUtils.getStringHeight(g, DESC_FONT)-4);
	}
	
	public boolean canBeBought() {
		for(Requirement r : requires)if(!r.checkCompleted())return false;
		if(GameManager.getSmiths().compareTo(cost) < 0)return false;
		return true;
	}
	
	@Override
	public void clicked(int x, int y, int type) {
		if(this.getBoundingBox().contains(new Point(x, y))) {
			buy();
			Upgrades.upgrades.reorderMinis();
		}
	}
	
	@Override
	public void pressed(int x, int y, int type) {	}
	
	public void buy() {
		if(canBeBought()) {
			if(GameManager.getSmiths().compareTo(cost) >= 0) {
				GameManager.getManager().addSmiths(cost.multiply(new BigInteger("-1")));
				bought = true;
				onBuy();
			}
		}
	}
	
	public abstract void onBuy();
	
	public boolean isBought() {
		return bought;
	}
	
}
