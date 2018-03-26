package griffin.smithclicker.util;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;

public class GraphicsHelper {
	
	public static void drawBorderedRect(Graphics g, Color in, Color out, int x, int y, int width, int height, int inset) {
		g.setColor(out);
		g.fillRect(x, y, width, height);
		g.setColor(in);
		g.fillRect(x + inset, y + inset, width - 2*inset, height - 2*inset);
	}
	
	public static void drawBorderedImg(Graphics g, Image img, Color out, int x, int y, int width, int height, int inset) {
		g.setColor(out);
		g.fillRect(x, y, width, height);
		g.drawImage(img, x+inset, y+inset, width-inset*2, height-inset*2, null);
	}
	
	public static void drawBorderedImgColor(Graphics g, Image img, Color out, Color in, int x, int y, int width, int height, int inset) {
		g.setColor(out);
		g.fillRect(x, y, width, height);
		g.drawImage(img, x+inset, y+inset, width-inset*2, height-inset*2, in, null);
	}
	
}
