import org.newdawn.slick.Input;
/**
 * BasicEnemy class
 * A subclass of the Enemy class
 * Creates all the basic enemies
 */
public class BasicEnemy extends Enemy{
	/** Basic Enemy's score */
	public static final int BASICENEMY_POINTS = 50;
	/** Basic enemy's speed */
	private static final float BASICENEMY_SPEED = 0.2f;
	
	/** BasicEnemy constructor
	 * @param imageSrc Image sources
	 * @param x x-axis of that the basic enemy
	 * @param y y-axis of that the basic enemy
	 * @param delay Basic enemy's delay
	 */
	public BasicEnemy(String imageSrc, float x, float y, int delay) {
		super(imageSrc, x, y, delay,BASICENEMY_POINTS);
	}

	@Override
	public void update(Input input, int delta) {
		// Create bounding box for all the basic Enemy
		super.update(input, delta);
		
		// Move Basic Enemy
		if ((World.timer >=getDelay())) {
			setY(getY()+BASICENEMY_SPEED*delta);
		}
	}
}
