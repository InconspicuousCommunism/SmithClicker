package griffin.smithclicker.objects.upgrades;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;
import java.util.List;

import griffin.smithclicker.event.MouseClickEvent;
import griffin.smithclicker.event.WatchEvent;
import griffin.smithclicker.main.GameManager;
import griffin.smithclicker.main.SmithClicker;
import griffin.smithclicker.objects.GameObject;
import griffin.smithclicker.objects.IClickable;

public class UpgradeScrollbar extends GameObject implements IClickable{
	
	private List<Upgrade> upgradeList;
	private int upgradeHeight;
	private int windowHeight;
	private int scrollLength;
	private int scrollTop;
	private int scrollBot;
	boolean clicked = false;
	private int prev_y = 0;
	private int last_y = 0;
	
	public UpgradeScrollbar(List<Upgrade> upgradeList) {
		super(GameManager.GAME_WIDTH-30,0,30,GameManager.GAME_HEIGHT);
		this.upgradeList = upgradeList;
		windowHeight = GameManager.GAME_HEIGHT;
		upgradeHeight = this.upgradeList.get(0).getHeight();
		scrollTop = 0;
		scrollBot = (int)(windowHeight * 1.0 / (upgradeHeight * upgradeList.size()) * windowHeight);
		scrollLength = scrollBot-scrollTop;
	}
	
	@Override
	public void render(Graphics g) {
//		super.render(g);
//		g.setColor(Color.gray);
//		g.fillRect(getX(), getY(), getWidth(), getHeight());
//		g.setColor(Color.black);
//		if(clicked)g.setColor(Color.DARK_GRAY);
//		g.drawRect(getX(), getY(), getWidth(), getHeight());
//		g.fillRect(getX() + 3, scrollTop + 3, getWidth() - 6, scrollLength - 6);
	}
	
	@Override
	public void tick() {
		super.tick();
		if(clicked) {
			if(SmithClicker.frame.getMouseLocation() != null)
				scrollTop = SmithClicker.frame.getMouseLocation().y - GameManager.GAME_INSET_TOP - prev_y;
			if(scrollTop < 0)scrollTop = 0;
			if(scrollTop + scrollLength > windowHeight)scrollTop-=(scrollTop+scrollLength)-windowHeight;
		}
		double diff = scrollTop - last_y;
		diff /= windowHeight;
		diff *= upgradeHeight * upgradeList.size();
		last_y = scrollTop;
		for(Upgrade u : this.upgradeList) {
			u.moveUp((int)-diff);
		}
	}
	
	@Override
	public void clicked(int x, int y, int type) {
		clicked = false;
	}

	@Override
	public void pressed(int x, int y, int type) {
		Rectangle bar = new Rectangle(getX() + 3, scrollTop + 3, getWidth() - 6, scrollLength - 6);
		if(bar.contains(x, y)) {
			clicked = true;
			prev_y = y - scrollTop;
		}
	}
	
	@WatchEvent
	public void onMouseReleased(MouseClickEvent event) {
		if(event.isReleased()) {
			clicked = false;
		}
	}
	
}
