package griffin.smithclicker.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Point;

import javax.swing.JFrame;

public class SmithFrame extends JFrame{
	

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	
	public SmithFrame() {
		
		setTitle("Smith Clicker");
		setVisible(true);
		setBackground(Color.black);
		setSize(GameManager.GAME_WIDTH, GameManager.GAME_HEIGHT);
		setDefaultCloseOperation(EXIT_ON_CLOSE);
		addMouseListener(new MouseHandler());
		addKeyListener(new KeyHandler());
		setSize(GameManager.GAME_WIDTH + getInsets().left + getInsets().right, GameManager.GAME_HEIGHT + getInsets().top + getInsets().bottom);
		GameManager.GAME_INSET_TOP = getInsets().top;
		GameManager.GAME_INSET_LEFT = getInsets().left;
	}
	
	public Point getMouseLocation() {
		return getMousePosition();
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
}
