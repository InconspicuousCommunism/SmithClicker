package griffin.smithclicker.main;

public class SmithClicker{
	
	public static SmithFrame frame;
	public static SmithThread thread;
	
	public static void main(String[] args){
		
		thread = new SmithThread();
		frame = new SmithFrame();
		
		GameManager.getManager().setupLoad();
		
		thread.start();
		
	}
	
}
