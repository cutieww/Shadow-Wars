import org.newdawn.slick.Input;
/**
 * Enemy class
 * A subclass of the Sprite class 
 * A superclass for all enemies
 */
public class Enemy extends Sprite{
	/** each enemy's delay time */
	private int delay;
	/** each enemy's score */
	private int score;
	
	/** Enemy Constructor */
	public Enemy(String imageSrc, float x, float y,int delay,int score) {
		super(imageSrc, x, y);
		this.delay = delay;
		this.score = score;
	}
	

	@Override
	public void update(Input input, int delta) {
		// If enemy is not on screen anymore, remove them
		if (enemyOffScreen()){
			World.getInstance().removeSprites(this);
		}
		// Create boudingbox for each enemy
		super.update(input, delta);
	}
	
	/** Handle Enemy and Laser's contacts
	 * @param other The sprite that made the contact to this class
	 */
	@Override
	public void contactSprite(Sprite other) {
		if (other instanceof Laser) {
			World.getInstance().removeSprites(this);
			World.getInstance().removeSprites(other);
			// Get the score for each destoried enemy
			World.score += getScore();
			//let the chance of dropping powerup is 5%
			float d = (float) Math.random();
			if (d < 0.05) {
				World.getInstance().addSprites(new PowerUp(getX(), getY()));
			}
		}
	}
	
	/** Get Enemies' delay time*/
	public int getDelay() {
		return delay;
	}
	
	/** Get Enemies' score*/
	public int getScore() {
		return score;
	}

}
