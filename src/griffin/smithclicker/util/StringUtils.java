package griffin.smithclicker.util;

import java.awt.Font;
import java.awt.Graphics;
import java.math.BigInteger;
import java.util.Arrays;
import java.util.List;

public class StringUtils {
	
	public static String formatNumber(BigInteger number) {
		String n = number.toString();
		if(n.length() > 27) {
			n = n.substring(0, n.length()-27) + "." + n.substring(n.length()-15,n.length()-12);
			n+= " Octillion";
		}else if(n.length() > 24) {
			n = n.substring(0, n.length()-24) + "." + n.substring(n.length()-15,n.length()-12);
			n+= " Septillion";
		}else if(n.length() > 21) {
			n = n.substring(0, n.length()-21) + "." + n.substring(n.length()-15,n.length()-12);
			n+= " Sextillion";
		}else if(n.length() > 18) {
			n = n.substring(0, n.length()-18) + "." + n.substring(n.length()-15,n.length()-12);
			n+= " Quintillion";
		}else if(n.length() > 15) {
			n = n.substring(0, n.length()-15) + "." + n.substring(n.length()-15,n.length()-12);
			n+= " Quadrillion";
		}else if(n.length() > 12) {
			n = n.substring(0, n.length()-12) + "." + n.substring(n.length()-12,n.length()-9);
			n+= " Trillion";
		}else if(n.length() > 9) {
			n = n.substring(0, n.length()-9) + "." + n.substring(n.length()-9,n.length()-6);
			n+=" Billion";
		} else if(n.length() > 6) {
			n = n.substring(0, n.length()-6) + "." + n.substring(n.length()-6,n.length()-3);
			n+=" Million";
		}
		return n;
	}
	
	public static int getStringWidth(Graphics g, Font f, String str) {
		return g.getFontMetrics(f).stringWidth(str);
	}
	
	public static int getStringHeight(Graphics g, Font f) {
		return g.getFontMetrics(f).getHeight();
	}
	
	public static void drawStringCentered(Graphics g, Font f, String str, int x, int width, int y) {
		g.setFont(f);
		int sWidth = getStringWidth(g, f, str);
		g.drawString(str, x + ((width)/2) - sWidth/2, y);
	}
	
	public static void drawStringFullCentered(Graphics g, Font f, String str, int x, int width, int y, int height) {
		drawStringCentered(g, f, str, x, width, y + getStringHeight(g, f)/2 + height/2 - getStringHeight(g, f)/4);
	}
	
	public static void drawStats(Graphics g, Font f, String word, String number, int x, int width, int y) {
		g.setFont(f);
		g.drawString(word, x, y);
		g.drawString(number, x + width - getStringWidth(g, f, number), y+20);
	}
	
	public static String packString(Graphics g, Font f, String s1, String s2, int packWidth) {
		String str = s1 + s2;
		String spaces = "";
		while(getStringWidth(g, f, str) < packWidth) {
			spaces+= " ";
			str = s1 + spaces + s2;
		}
		spaces = spaces.substring(0, spaces.length()-1);
		return s1 + spaces + s2;
	}
	
	/**
	 * 
	 * @param g
	 * @param f
	 * @param desc
	 * @param x
	 * @param y
	 * @param width
	 * @param yspacing
	 * @return Returns next Y position
	 */
	public static int drawDescription(Graphics g, Font f, String desc, int x, int y, int width, int yspacing) {
		g.setFont(f);
		List<String> split = Arrays.asList(desc.split(" "));
		String cur = "";
		for(String s : split) {
			int length = s.length();
			cur += s;
			if(getStringWidth(g, f, cur) > width) {
				cur = cur.substring(0, cur.length()-length);
				g.drawString(cur, x, y);
				y+=yspacing;
				cur = s + " ";
			}else cur += " ";
		}
		g.drawString(cur, x, y);
		return y;
	}
	
}
