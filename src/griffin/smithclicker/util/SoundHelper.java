package griffin.smithclicker.util;

import java.net.URL;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;

public class SoundHelper {
	
	public static Clip current = null;
	public static Thread cThread = null;
	public static Clip background = null;
	public static Thread bThread = null;
	
	public static void playMusic(String name) {
		if(cThread != null)
			try {
				cThread.join();
			} catch (InterruptedException e1) {}
		cThread = new Thread(new Runnable() {
			
			@Override
			public void run() {
				if(current != null && current.isRunning()) {
					current.stop();
				}
				current = null;
				String url = "/music/" + name;
				try {
					Clip c = AudioSystem.getClip();
					URL u = ImageHelper.class.getResource(url);
					AudioInputStream inputStream = AudioSystem.getAudioInputStream(u.openStream());
					c.open(inputStream);
					c.start(); 
					current = c;
				}catch(Exception e) {
					e.printStackTrace();
				}
			}
		});
		cThread.run();
	}
	
	public static void setBackgroundMusic(String name) {
		bThread = new Thread(new Runnable() {

			@Override
			public void run() {
				String url = "/music/" + name;
				try {
					background = AudioSystem.getClip();
					while(true) {
						if(!background.isRunning()) {
							background.open(AudioSystem.getAudioInputStream(ImageHelper.class.getResource(url)));
							background.start();
						}
					}
				}catch(Exception e) {}
			}
			
		});
		bThread.run();
	}
	
	
	
}
