import java.util.Random;

import org.newdawn.slick.Input;
/**
 * BasicShooter class
 * A subclass of the Enemy class
 * Creates all the Basic Shooters
 */
public class BasicShooter extends Enemy{
	/** Basic Shooter's score */
	public static final int BASICSHOOTER_POINTS = 200;
	
	/** The min number y Basic shooter could get*/
	private static final int MIN_Y = 48;
	/** The max number y Basic shooter could get*/
	private static final int MAX_Y = 464;
	/** Basic Shooter's speed */
	private static final float BASICSHOOTER_SPEED = 0.2f;
	
	/** timer for shoot */
	private static int shoot_time = 0;
	/** delay between each enemy bullet */
	private static int enemy_bullet_delay = 3500;
	
	/** position that basic shooter chose */
	private float randomY;
	
	/** BasicShooter constructor
	 * @param imageSrc Image sources
	 * @param x x-axis of that the basic shooter enemy
	 * @param y y-axis of that the basic shooter enemy
	 * @param delay Basic shooter's delay
	 */
	public BasicShooter(String imageSrc, float x, float y, int delay) {
		super(imageSrc, x, y, delay,BASICSHOOTER_POINTS);
		// Create random and give randomY a random value between min and max
		Random rand = new Random();
		randomY = (float)rand.nextInt(MAX_Y) + MIN_Y; 
		
	}

	@Override
	public void update(Input input, int delta) {
		if (World.timer>=getDelay()) {
			// Get to the position
			if (Float.compare(getY(), randomY) < 0){
				setY(getY()+BASICSHOOTER_SPEED*delta);
			}
			// shoot after getting to the position 
			else{
				shoot_time-=delta;
				if (shoot_time <=0) {
					World.getInstance().addSprites(new EnemyLaser(getX(), getY()));
					shoot_time = enemy_bullet_delay;
				}
			}
		}
		// create Bounding box
		super.update(input, delta);
	}
}
