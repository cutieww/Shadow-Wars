import org.newdawn.slick.Input;
/**
 * SineEnemy class
 * A subclass of the Enemy class
 * Creates all the sine enemies
 */
public class SineEnemy extends Enemy{
	/** Sine Enemy's score */
	private static final int SINEENEMY_POINTS = 100;
	/** Sine Enemy speed */
	private static final float SINEENEMY_SPEED = 0.15f;
	/** amplitude constant */
	private static final int A = 96;
	/** period constant */
	private static final int T = 1500;
	/** the starting x-axis */
	private float startX;
	
	/** SineEnemy constructor
	 * @param imageSrc Image sources
	 * @param x x-axis of that the sine enemy
	 * @param y y-axis of that the sine enemy
	 * @param delay Sine enemy's delay
	 */
	public SineEnemy(String imageSrc, float x, float y, int delay) {
		super(imageSrc, x, y, delay,SINEENEMY_POINTS);
		startX = x;
	}

	@Override
	public void update(Input input, int delta) {
		if (World.timer>=getDelay()) {
			setY(getY()+SINEENEMY_SPEED*delta);
			float offset = (float) (A*(Math.sin((2*(Math.PI)/T)*
					World.timer-getDelay())));
			setX(startX+offset);
			super.update(input, delta);
		}
	}
		

}
