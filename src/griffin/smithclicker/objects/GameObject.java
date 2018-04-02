package griffin.smithclicker.objects;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

import griffin.smithclicker.main.GameManager;

public abstract class GameObject {
	
	private int x, y;
	private int w, h;
	private int z;

	public GameObject(int x, int y, int w, int h, int z) {
		this.x = x;
		this.y = y;
		this.h = h;
		this.w = w;
		this.z = z;
	}
	
	public GameObject(int x, int y, int w, int h){
		this(x,y,w,h,1000);
	}
	
	public GameObject(int x, int y) {
		this(x,y,0,0);
	}
	
	public GameObject() {
		this(0,0);
	}
	
	/**
	 * Called after rendering to reset color (debugging)
	 * @param g
	 */
	public void render(Graphics g){
		g.setColor(Color.black);
	}
	
	public void tick(){
		
	}
	
	public Rectangle getBoundingBox(){
		return new Rectangle(x,y,w,h);
	}
	
	public int getX() {
		return x;
	}

	public int getY() {
		return y;
	}

	public int getHeight() {
		return h;
	}

	public int getWidth() {
		return w;
	}
	
	public int getZ() {
		return z;
	}
	
	protected void setX(int x) {
		this.x = x;
	}
	
	protected void setY(int y) {
		this.y = y;
	}
	
	protected void setZ(int z) {
		int oldZ = this.z;
		this.z = z;
		GameManager.getManager().changeZLevel(this, oldZ);
	}
	
	public void kill() {
		GameManager.getManager().removeObject(this);
	}
	
	public void onAdded() {}
	
}
