import org.newdawn.slick.Input;
/**
 * Laser class for player laser
 * A subclass of the Sprite
 * Creates all Player laser
 */
public class Laser extends Sprite{
	/** Player Laser string resource*/
	private final static String LASER_STRING = "res/shot.png";
	/** Laser speed*/
	public final float LASER_SPEED =3f;
	
	/** Laser constructor*/
	public Laser(float x, float y) {
		super(LASER_STRING,x , y);
	}
	
	@Override
	public void update(Input input, int delta) {
		//create bb box
		super.update(input, delta);
		if (onScreen()) {
			setY(getY()-LASER_SPEED*delta);
		}
		if (!onScreen()){
			World.getInstance().removeSprites(this);    
		}
	}
}
