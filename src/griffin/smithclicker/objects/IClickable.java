package griffin.smithclicker.objects;

/**
 * 
 * @author griff
 * 
 * Use for when you need to check if the click was within the object otherwise use MouseClickEvent for universal clicks
 */
public interface IClickable {
	
	public void clicked(int x, int y, int type);
	public void pressed(int x, int y, int type);
	
}
