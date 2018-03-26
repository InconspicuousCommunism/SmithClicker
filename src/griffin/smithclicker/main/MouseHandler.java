package griffin.smithclicker.main;

import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import griffin.smithclicker.event.EventManager;
import griffin.smithclicker.event.MouseClickEvent;

public class MouseHandler implements MouseListener{

	@Override
	public void mouseClicked(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {		
	}

	@Override
	public void mouseExited(MouseEvent e) {		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		GameManager.getManager().press(e);
		EventManager.queueEvent(new MouseClickEvent(e.getX(), e.getY(), e.getButton(), false));
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		GameManager.getManager().click(e);
		EventManager.queueEvent(new MouseClickEvent(e.getX(), e.getY(), e.getButton(), true));
	}

}
