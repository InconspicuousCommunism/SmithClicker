package griffin.smithclicker.objects.upgrades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.math.BigDecimal;
import java.math.BigInteger;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.IncreaseSmithEvent;
import griffin.smithclicker.event.IncreaseSmithEvent.FromType;
import griffin.smithclicker.event.WatchEvent;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.util.GraphicsHelper;
import griffin.smithclicker.util.ImageHelper;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.StringUtils;

public class Upgrade extends GameObject implements IClickable{
	
	private static final double MULTIPLIER = 1.15d;
	
	private static final Image UPGRADE_BACKGROUND = ImageHelper.loadImage("/background/upgradeBackground.png");
	private static final Image UPGRADE_OVERLAY = ImageHelper.loadImage("/background/upgradeBackgroundOverlay.png");
	private static final Image UPGRADE_OVERLAY_HOVER = ImageHelper.loadImage("/background/upgradeBackgroundOverlayHover.png");
	
	private static final int WIDTH = 305;
	private static final int HEIGHT = 100;
	private static final int POS_X = 711;
	private static final int POS_Y = 115;
	
	private int id;
	private int amount;
	private int tick;
	private BigInteger baseCost, sps;
	private double addClick;
	private Image img;
	private String name;
	
	private BigInteger smiths_made = new BigInteger("0");
	
	public Upgrade(int id, Image img, int baseCost, int sps, double addClick, String name) {
		this(id, img, new BigInteger(baseCost +""), sps, addClick, name);
	}
	
	public Upgrade(int id, Image img, BigInteger baseCost, int sps, double addClick, String name) {
		this(id, img, baseCost, new BigInteger(sps + ""), addClick, name);
	}
	
	public Upgrade(int id, Image img, BigInteger baseCost, BigInteger sps, double addClick, String name) {
		super(POS_X, id*HEIGHT + POS_Y, WIDTH, HEIGHT);
		this.id = id;
		this.img = img;
		this.baseCost = baseCost;
		this.sps =sps;
		this.addClick = addClick;
		this.name = name;
	}
	
	@Override
	public void render(Graphics g) {
		
		g.drawImage(UPGRADE_BACKGROUND, getX(), getY(), getWidth(), getHeight(), null);
		
		g.drawImage(getIcon(), getX()+3, getY()+3, getHeight()-6, getHeight()-6, null);
		
		g.setColor(Color.RED);
		int mult = 1;
		if(GameManager.shift_held) mult = 10;
		if(GameManager.ctrl_held) mult = 100;
		StringUtils.drawStringCentered(g, new Font("Comic Sans MS", Font.BOLD, 32), mult+"", getX() + 130, 145, getY() + 40);
		
		BigInteger cost = getCostAt(mult);
		StringUtils.drawStringCentered(g, new Font("Comic Sans MS", Font.BOLD, 24), "$" + StringUtils.formatNumber(cost), getX() + 130, 145, getY() + 80);
		
		g.drawImage(UPGRADE_OVERLAY, getX(), getY(), getWidth(), getHeight(), null);
		
		super.render(g);
	}
	
	@Override
	public void tick() {
		tick++;
		if(tick > 20){
			tick = 0;
			BigInteger sps = getSPS();
			BigInteger bought = new BigInteger(getAmountBought()+"");
			EventManager.queueEvent(new IncreaseSmithEvent(sps.multiply(bought), FromType.upgrade, this));
			smiths_made = smiths_made.add(sps.multiply(bought));
		}
	}
	
	public void setAmountBought(int num) {
		this.amount = num;
	}
	
	public void setAmountMade(BigInteger num) {
		this.smiths_made = num;
	}
	
	protected Image getIcon() {
		return img;
	}
	protected BigInteger getBaseCost() {
		return baseCost;
	}
	protected BigInteger getSPS() {
		return sps;
	}
	protected void onBuy(){};
	
	public BigInteger getTotalNext(int next) {
		BigInteger sum = new BigInteger("0");
		for(int i = 0; i < next; i++) {
			sum = sum.add(getCostAt(i));
		}
		return sum;
	}
	
	public BigInteger getCostAt(int pos) {
		BigDecimal d = new BigDecimal(this.getBaseCost());
		d = d.multiply(new BigDecimal(Math.pow(MULTIPLIER, this.getAmountBought()+pos)));
		return d.toBigInteger();
	}
	
	public BigInteger getCost(){
		BigDecimal d = new BigDecimal(getBaseCost());
		double dou = Math.pow(MULTIPLIER, this.getAmountBought());
		d = d.multiply(new BigDecimal(dou));
		return d.toBigInteger();
	}
	
	public boolean buy(){
		if(GameManager.getSmiths().compareTo(new BigInteger(this.getCost()+"")) >= 0){
			GameManager.getManager().addSmiths(getCost().multiply(new BigInteger("-1")));
			amount++;
			onBuy();
			return true;
		}
		return false;
	}
	
	@Override
	public void clicked(int x, int y, int type) {
	}
	
	public int getID(){
		return id;
	}
	
	public int getAmountBought(){
		return amount;
	}

	@Override
	public void pressed(int x, int y, int type) {
	}
	
	public void moveUp(int amount) {
		this.setY(this.getY() + amount);
	}
	
	public BigInteger getSmithsMade() {
		return smiths_made;
	}
	
	public String getName() {
		return name;
	}
	
	@WatchEvent
	public void onIncreaseSmith(IncreaseSmithEvent e) {
		if(e.getType() == FromType.click) {
			e.increase = e.increase.add(new BigInteger("" + ((int)(getAmountBought() * addClick))));
		}
	}
}