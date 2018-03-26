package griffin.smithclicker.objects.upgrades.mini;

import java.awt.Color;
import java.awt.Graphics;
import java.util.ArrayList;

import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;
import griffin.smithclicker.objects.upgrades.Upgrades;

public class UpgradeWindow extends GameObject implements IClickable{
	
	private static final int WIDTH;
	private static final int HEIGHT;
	
	static {
		WIDTH = (int)((350.0/1200)*GameManager.GAME_WIDTH);
		HEIGHT = GameManager.GAME_HEIGHT - (int)((500.0/800)*GameManager.GAME_HEIGHT);
	}
	
	private static ArrayList<Miniupgrade> upgradeList;
	
	public UpgradeWindow(ArrayList<Miniupgrade> upgradeList) {
		super(0, 0, WIDTH, HEIGHT);
		UpgradeWindow.upgradeList = upgradeList;
	}
	
	@Override
	public void render(Graphics g) {
		
		g.setColor(Color.gray);
		g.fillRect(getX(), getY(), getWidth(), getHeight());
		g.setColor(Color.black);
		g.drawRect(getX(), getY(), getWidth(), getHeight());
		
		for(int i = 0; i < 42; i++) {
			try {
				upgradeList.get(i).render(g);
			}catch(Exception e) {}
		}
		
		Upgrades.upgrades.orderMinis();
		UpgradeWindow.upgradeList = Upgrades.upgrades.getMiniupgrades();
		
		super.render(g);
	}

	@Override
	public void clicked(int x, int y, int type) {
		for(Miniupgrade m : upgradeList) {
			m.clicked(x, y, type);
		}
	}

	@Override
	public void pressed(int x, int y, int type) {
		// TODO Auto-generated method stub
		
	}
	
}
