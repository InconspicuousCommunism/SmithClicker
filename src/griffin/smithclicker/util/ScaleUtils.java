package griffin.smithclicker.util;

import griffin.smithclicker.main.GameManager;

public class ScaleUtils {
	
	public static int scaleNumber(int n) {
		return (int)(n * GameManager.GAME_RATIO);
	}
	
}
