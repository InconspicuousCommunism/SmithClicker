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

public abstract class Miniupgrade extends GameObject implements IClickable, Comparable<Miniupgrade>{
	
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
	private boolean displayed = false;
	
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
		GameManager.getManager().addObject(new MiniDescript(this, getWidth(), getHeight()));
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
			GraphicsHelper.drawBorderedImg(g, img, c, getX(), getY(), getWidth(), getHeight(), ScaleUtils.scaleNumber(4));
			g.setColor(color);
			g.fillRect(getX() + 4, getY() +  + getHeight() - 8, getWidth() - 8, 4);
			displayed = true;
		} else {
			this.setX(-1000);
		}
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
	
	public List<Requirement> getRequirements(){
		return this.requires;
	}
	
	public String getName() {
		return this.name;
	}
	
	public String getDesc() {
		return this.desc;
	}
	
	public BigInteger getCost() {
		return this.cost;
	}
	
	@Override
	public int compareTo(Miniupgrade o) {
		return o.getZ() - this.getZ();
	}
	
	public boolean isDisplayed() {
		return this.displayed;
	}
	
	public void setDisplayed(boolean b) {
		this.displayed = b;
	}
}
