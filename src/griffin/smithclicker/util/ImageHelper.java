package griffin.smithclicker.util;

import java.awt.AlphaComposite;
import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.util.HashMap;

import javax.imageio.ImageIO;

public class ImageHelper {
	
	private static HashMap<String, BufferedImage> textures = new HashMap<String, BufferedImage>();
	
	public static BufferedImage loadImage(String loc){
		if(textures.containsKey(loc))return textures.get(loc);
		try{
			BufferedImage b = ImageIO.read(ImageHelper.class.getResource(loc));
			textures.put(loc, b);
			return b;
		}catch(Exception e){}
		return null;
	}
	
	public static BufferedImage colorize(BufferedImage image, Color color){
        int w = image.getWidth();
        int h = image.getHeight();
        BufferedImage dyed = new BufferedImage(w,h,BufferedImage.TYPE_INT_ARGB);
        Graphics2D g = dyed.createGraphics();
        g.drawImage(image, 0,0, null);
        g.setComposite(AlphaComposite.SrcAtop);
        g.setColor(color);
        g.fillRect(0,0,w,h);
        g.dispose();
        return dyed;
    }
	
}
