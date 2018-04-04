package griffin.smithclicker.main;

import java.awt.Graphics;
import java.awt.Point;
import java.awt.event.MouseEvent;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.TreeMap;

import griffin.smithclicker.effect.Effects;
import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.objects.Smith;
import griffin.smithclicker.objects.display.SmithCount;
import griffin.smithclicker.objects.display.StatBox;
import griffin.smithclicker.objects.extrasmith.GoldenSmith;
import griffin.smithclicker.objects.upgrades.Upgrade;
import griffin.smithclicker.objects.upgrades.UpgradeScrollbar;
import griffin.smithclicker.objects.upgrades.Upgrades;
import griffin.smithclicker.objects.upgrades.mini.Miniupgrade;
import griffin.smithclicker.objects.upgrades.mini.UpgradeWindow;
import griffin.smithclicker.util.SoundHelper;
import griffin.smithclicker.util.StringUtils;

public class GameManager {
	
	public static int GAME_WIDTH = 1600;
	public static int GAME_HEIGHT = 1000;
	public static double GAME_RATIO = GAME_WIDTH / 1200.0;
	
	public static int GAME_INSET_TOP;
	public static int GAME_INSET_LEFT;
	
	private int curTick = 0;
	
	private static GameManager manager = new GameManager();
	
	private static BigInteger smiths = new BigInteger("0");
	private static BigInteger total_smiths = new BigInteger("0");
	private static BigInteger upgrade_smiths = new BigInteger("0");
	private static BigInteger click_smiths = new BigInteger("0");
	private static BigInteger smiths_per_second = new BigInteger("0");
	private static BigInteger total_clicks = new BigInteger("0");
	private static BigInteger smiths_this_second = new BigInteger("0");
	
	public static final String FONT_NAME = "Comic Sans MS";
	
	public static boolean shift_held = false;
	public static boolean ctrl_held = false;
	
	private TreeMap<Integer, ArrayList<GameObject>> renderList = new TreeMap<Integer, ArrayList<GameObject>>();
	public HashMap<GameObject, Integer> zChangeList = new HashMap<GameObject, Integer>();
	
	public ArrayList<GameObject> objectList;
	public ArrayList<GameObject> removeList;
	public ArrayList<GameObject> addList;
	
	public ArrayList<MouseEvent> clicks;
	public ArrayList<MouseEvent> presses;
	
	
	
	
	public GameManager() {
		objectList = new ArrayList<GameObject>();
		removeList = new ArrayList<GameObject>();
		addList = new ArrayList<GameObject>();
		clicks = new ArrayList<MouseEvent>();
		presses = new ArrayList<MouseEvent>();
	}
	
	public static GameManager getManager(){
		return manager;
	}
	
	public void setupBaseGame(){
		addList.add(new Smith());
		for(Upgrade u : Upgrades.upgrades.getUpgrades()) {
			addList.add(u);
			EventManager.registerListener(u);
		}
		UpgradeScrollbar bar = new UpgradeScrollbar(Upgrades.upgrades.getUpgrades());
		EventManager.registerListener(bar);
		addList.add(bar);
		addList.add(new SmithCount());
		addList.add(new StatBox());
		addList.add(new UpgradeWindow(Upgrades.upgrades.getMiniupgrades()));
		SoundHelper.setBackgroundMusic("screaming.wav");
		SaveManager.loadFromGameFile();
	}
	
	
	/*
	 * tick and render
	 */
	public void tick(){
		for(GameObject o : objectList){
			o.tick();
		}
		handleClick();
		handlePress();
		mergeLists();
		
		EventManager.fireEvents();
		
		//Get SPS Data
		curTick++;
		if(curTick >= 20) {
			curTick = 0;
			smiths_per_second = new BigInteger(smiths_this_second+"");
			smiths_this_second = smiths_this_second.subtract(smiths_this_second);//Totally not too much code for a simple task
		}
		
		//Golden Smith
		Random rand = new Random();
		if(gSmithCounter <= 0) {
			if(gSmithCounter == 0) {
				int posX = rand.nextInt(Smith.getBoxWidth() - GoldenSmith.getBoxWidth());
				int posY = rand.nextInt(Smith.getBoxHeight() - GoldenSmith.getBoxHeight()) + Smith.getPosY();
				addObject(new GoldenSmith(posX, posY));
			}
			gSmithCounter = rand.nextInt(3600) + 2400;
		}else gSmithCounter--;
		
		Effects.effects.tickEffects();
		
		SaveManager.saveCurrentGameState();
	}
	
	
	private int gSmithCounter = -1;
	
	
	public void render(Graphics g){
		for(Integer i : renderList.keySet()) {
			ArrayList<GameObject> objectList = renderList.get(i);
			for(GameObject o : objectList){
				o.render(g);
			}
		}
	}
	
	/*
	 * Click
	 */
	public void click(MouseEvent e){
		clicks.add(e);
	}
	
	private void handleClick(){
		try {
			for(MouseEvent e : clicks){
				for(int i = objectList.size()-1; i >= 0; i-- ){
					GameObject o = objectList.get(i);
					if(o instanceof IClickable && o.getBoundingBox().contains(new Point(e.getX() - GAME_INSET_LEFT, e.getY() - GAME_INSET_TOP))){
						((IClickable)o).clicked(e.getX() - GAME_INSET_LEFT, e.getY() - GAME_INSET_TOP, e.getButton());
						break;
					}
				}
			}
			clicks.clear();
		}catch(Exception e) {}
	}
	
	public void press(MouseEvent e) {
		presses.add(e);
	}
	
	public void handlePress() {
		for(MouseEvent e : presses) {
			for(int i = objectList.size()-1; i >= 0; i-- ){
				GameObject o = objectList.get(i);
				if(o instanceof IClickable && o.getBoundingBox().contains(new Point(e.getX() - GAME_INSET_LEFT, e.getY() - GAME_INSET_TOP))){
					((IClickable)o).pressed(e.getX() - GAME_INSET_LEFT, e.getY() - GAME_INSET_TOP, e.getButton());
					break;
				}
			}
		}
		presses.clear();
	}
	
	/*
	 * List management
	 */
	public void addObject(GameObject o){
		addList.add(o);
		o.onAdded();
	}
	
	public void removeObject(GameObject o){
		removeList.add(o);
	}
	
	private void mergeLists(){
		for(GameObject o : addList){
			objectList.add(o);
			if(renderList.get(o.getZ()) == null) {
				ArrayList<GameObject> list = new ArrayList<GameObject>();
				renderList.put(o.getZ(), list);
			}
			renderList.get(o.getZ()).add(o);
		}
		for(GameObject o : removeList){
			if(objectList.contains(o))objectList.remove(o);
			if(renderList.get(o.getZ()).contains(o))renderList.get(o.getZ()).remove(o);
		}
		for(GameObject o : zChangeList.keySet()) {
			int oldZ = zChangeList.get(o);
			if(renderList.get(oldZ) == null)renderList.put(oldZ, new ArrayList<GameObject>());
			if(renderList.get(oldZ).contains(o))renderList.get(oldZ).add(o);
			if(renderList.get(o.getZ()) == null) {
				renderList.put(o.getZ(), new ArrayList<GameObject>());
			}
			renderList.get(o.getZ()).add(o);
		}
		addList.clear();
		removeList.clear();
		zChangeList.clear();
	}
	
	public void changeZLevel(GameObject o, int oldZ) {
		zChangeList.put(o, oldZ);
	}
	
	/*
	 * Score management
	 */
	public static BigInteger getSmiths(){
		return smiths;
	}
	
	public void addSmiths(BigInteger add){
		addTotalSmiths(add);
		GameManager.smiths = GameManager.smiths.add(add);
	}
	
	public static String getFormattedSmiths() {
		return StringUtils.formatNumber(getSmiths());
	}
	
	public void addTotalSmiths(BigInteger add) {
		if(add.compareTo(new BigInteger("0")) <= -1)return;
		GameManager.total_smiths = GameManager.total_smiths.add(add);
		GameManager.smiths_this_second = GameManager.smiths_this_second.add(add);	
	}
	
	public void addUpgradeSmiths(BigInteger add) {
		GameManager.upgrade_smiths = GameManager.upgrade_smiths.add(add);
	}
	
	public void addClickSmiths(BigInteger add) {
		GameManager.click_smiths = GameManager.click_smiths.add(add);
	}
	
	public void addClicks(BigInteger add) {
		GameManager.total_clicks = GameManager.total_clicks.add(add);
	}
	
	/**
	 * 
	 * @return the total number of smiths clicked since the start
	 */
	public static BigInteger getTotalSmiths() {
		return total_smiths;
	}
	
	/**
	 * 
	 * @return The total number of smiths obtained by upgrades
	 */
	public static BigInteger getUpgradeSmiths() {
		return upgrade_smiths;
	}
	
	/**
	 * 
	 * @return The total number of smiths obtained by clicking
	 */
	public static BigInteger getClickSmiths() {
		return click_smiths;
	}
	
	/**
	 * 
	 * @return The amount of smiths earned in the past second
	 */
	public static BigInteger getSmithsPerSecond() {
		return smiths_per_second;
	}
	
	/**
	 * 
	 * @return The total amount of times the user has clicked
	 */
	public static BigInteger getTotalClicks() {
		return total_clicks;
	}
	
}
