package griffin.smithclicker.objects.upgrades;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Point;
import java.awt.Rectangle;
import java.math.BigInteger;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.IncreaseSmithEvent;
import griffin.smithclicker.event.IncreaseSmithEvent.FromType;
import griffin.smithclicker.event.WatchEvent;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.util.GraphicsHelper;
import griffin.smithclicker.util.ScaleUtils;
import griffin.smithclicker.util.SoundHelper;
import griffin.smithclicker.util.StringUtils;

public class Upgrade extends GameObject implements IClickable{
	
	private static final int WIDTH;
	private static final int HEIGHT;
	
	private UpgradeButton upButton;
	private int id;
	private int amount;
	private int tick;
	private BigInteger baseCost, sps;
	private double costExp, baseMult, addClick;
	private Image img;
	private String name;
	
	private BigInteger smiths_made = new BigInteger("0");
	
	static{
		WIDTH = (int)((500.0/1200)*GameManager.GAME_WIDTH);
		HEIGHT = (int)((100.0/800)*GameManager.GAME_HEIGHT);
	}
	
	public Upgrade(int id, Image img, int baseCost, double costExp, double baseMult, int sps, double addClick, String name) {
		this(id, img, new BigInteger(baseCost +""), costExp, baseMult, sps, addClick, name);
	}
	
	public Upgrade(int id, Image img, BigInteger baseCost, double costExp, double baseMult, int sps, double addClick, String name) {
		this(id, img, baseCost, costExp, baseMult, new BigInteger(sps + ""), addClick, name);
	}
	
	public Upgrade(int id, Image img, BigInteger baseCost, double costExp, double baseMult, BigInteger sps, double addClick, String name) {
		super(GameManager.GAME_WIDTH-WIDTH, id*HEIGHT, WIDTH, HEIGHT);
		this.id = id;
		this.upButton = new UpgradeButton(this.getX(), this.getY(), this);
		this.img = img;
		this.baseCost = baseCost;
		this.costExp = costExp;
		this.baseMult = baseMult;
		this.sps =sps;
		this.addClick = addClick;
		this.name = name;
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.DARK_GRAY);
		g.fillRect(getX(), getY(), getHeight(), getHeight());
		
		g.drawImage(getIcon(), getX(), getY(), getHeight(), getHeight(), null);
		g.setColor(Color.black);
		g.drawRect(getX(), getY(), getHeight(), getHeight());
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		
		upButton.render(g);
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
	protected double getCostExp() {
		return costExp;
	}
	protected double getCostBaseMult() {
		return baseMult;
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
		BigInteger baseCost = getBaseCost();
		BigInteger costMult = new BigInteger((int)getCostBaseMult() +"");
		BigInteger amountBought = new BigInteger((getAmountBought()+pos)+"");
		double costExp = getCostExp();
		BigInteger end = baseCost.add(costMult.multiply(amountBought.pow((int) costExp)));
		return end;
	}
	
	public BigInteger getCost(){
		BigInteger baseCost = getBaseCost();
		BigInteger costMult = new BigInteger((int)getCostBaseMult() +"");
		BigInteger amountBought = new BigInteger((getAmountBought())+"");
		double costExp = getCostExp();
		BigInteger end = baseCost.add(costMult.multiply(amountBought.pow((int) costExp)));
		return end;
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
		upButton.clicked(x, y, type);
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
		this.upButton.moveUp(amount);
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

class UpgradeButton extends GameObject implements IClickable{
	
	private Upgrade upgrade;
	
	private static final int NUMBER_BOUGHT_SIZE = ScaleUtils.scaleNumber(36);
	private static final Font NUMBER_BOUGHT_FONT = new Font("Comic Sans MS", Font.PLAIN, NUMBER_BOUGHT_SIZE);
	private static final int X1_SIZE = ScaleUtils.scaleNumber(28);
	private static final Font X1_FONT = new Font("Comic Sans MS", Font.PLAIN, X1_SIZE);
	private static final int COST_SIZE = ScaleUtils.scaleNumber(20);
	private static final Font COST_FONT = new Font("Comic Sans MS", Font.PLAIN, COST_SIZE);
	
	public UpgradeButton(int upgradeX, int upgradeY, Upgrade upgrade) {
		super(upgradeX + ScaleUtils.scaleNumber(120), upgradeY + ScaleUtils.scaleNumber(20), upgrade.getWidth() - ScaleUtils.scaleNumber(150), ScaleUtils.scaleNumber(60));
		this.upgrade = upgrade;
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.black);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.darkGray);
		g.fillRect(getX()+ScaleUtils.scaleNumber(4), getY()+ScaleUtils.scaleNumber(4), getWidth()-ScaleUtils.scaleNumber(8), getHeight()-ScaleUtils.scaleNumber(8));
		
		Color BlAcK = Color.BLACK;
		g.setColor(BlAcK);
		g.setFont(NUMBER_BOUGHT_FONT);
		
		//# of bought box
		int ax = getX() + 4;
		g.fillRect(ax, getY()+ScaleUtils.scaleNumber(4), (getWidth()-ScaleUtils.scaleNumber(12))/4, getHeight()-ScaleUtils.scaleNumber(8));
		String s = String.format("%4d", this.upgrade.getAmountBought());
		g.setColor(Color.GREEN);
		g.drawString(s, ax, getY() + NUMBER_BOUGHT_SIZE + NUMBER_BOUGHT_SIZE/4);
		
		ax += (getWidth()-ScaleUtils.scaleNumber(12))/4;
		g.setColor(BlAcK);
		g.setColor(Color.gray);
		GraphicsHelper.drawBorderedRect(g, Color.DARK_GRAY, BlAcK, ax, getY(), ScaleUtils.scaleNumber(80), getHeight(), 6);
		buy = new Rectangle(ax, getY()+ScaleUtils.scaleNumber(4),ScaleUtils.scaleNumber(80), getHeight()-ScaleUtils.scaleNumber(8));
		g.setFont(X1_FONT);
		g.setColor(Color.green);
		s = GameManager.ctrl_held ? "X100" : (GameManager.shift_held ? "X10" : "X1");
		StringUtils.drawStringFullCentered(g, X1_FONT, s, ax, ScaleUtils.scaleNumber(80), getY()+ScaleUtils.scaleNumber(4),getHeight()-ScaleUtils.scaleNumber(8));
		ax+=ScaleUtils.scaleNumber(82);
		int amount = GameManager.ctrl_held ? 100 : (GameManager.shift_held ? 10 : 1);
		StringUtils.drawStringFullCentered(g, COST_FONT, String.format("$%-6s", StringUtils.formatNumber(this.upgrade.getTotalNext(amount))),
				ax,getWidth()-(ax-getX()), getY()+ScaleUtils.scaleNumber(4), getHeight()-ScaleUtils.scaleNumber(8));
		g.setColor(Color.red);
				
		super.render(g);
		
	}
	
	Rectangle buy = new Rectangle();
	
	@Override
	public void clicked(int x, int y, int type) {
		Point p = new Point(x,y);
		if(this.buy.contains(p)){
			int n = GameManager.ctrl_held ? 100 : (GameManager.shift_held ? 10 : 1);
			for(int i = 0; i < n; i++) {
				upgrade.buy();
			}
		}
	}

	@Override
	public void pressed(int x, int y, int type) {
	}
	
	public void moveUp(int amount) {
		this.setY(this.getY() + amount);
	}
	
}