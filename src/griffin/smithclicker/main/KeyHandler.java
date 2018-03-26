package griffin.smithclicker.main;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

public class KeyHandler implements KeyListener{

	@Override
	public void keyPressed(KeyEvent e) {	
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			GameManager.shift_held = true;
			break;
		case KeyEvent.VK_CONTROL:
			GameManager.ctrl_held = true;
			break;
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		switch(e.getKeyCode()) {
		case KeyEvent.VK_SHIFT:
			GameManager.shift_held = false;
			break;
		case KeyEvent.VK_CONTROL:
			GameManager.ctrl_held = false;
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {		
	}

}
