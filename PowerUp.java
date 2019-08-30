import java.util.Random;

import org.newdawn.slick.Input;
/**
 * PowerUp class
 * A subclass of the Sprite
 * Create powerups
 */
public class PowerUp extends Sprite{
	/** powerup moving speed */
	private final static float POWERUP_SPEED = 0.1f;
	/** shield powerup string recourse */
	private final static String POWERUP_STRING1 = "res/shield-powerup.png";
	/** speedup powerup string recourse */
	private final static String POWERUP_STRING2 = "res/shotspeed-powerup.png";
	/** the powerup that would be chosen */
	private static String powerup;
	/** current powerup */
	private String current_powerup;
	/** powerup time */
	private final int POWERUP_TIME = 5000;
	
	/** choose a random powerup */
	public static String getString() {
		String [] arr = {POWERUP_STRING1,POWERUP_STRING2};
		Random random = new Random();
		int choice = random.nextInt(arr.length); 
		powerup = arr[choice];
		return powerup;
	}
	
	/** PowerUp constructor
	 * @param x Powerup x-axis
	 * @param y powerup y-axis
	 */
	public PowerUp(float x, float y) {
		super(getString(), x, y);
		current_powerup = powerup;
		super.render();
	}
	
	@Override
	public void update(Input input, int delta) {
		setY(getY()+POWERUP_SPEED*delta);
		//create bb box
		super.update(input, delta);
	}
	
	/** Handle powerups and player's contacts
	 * @param other The sprite that made the contact to this class
	 */
	@Override 
	public void contactSprite(Sprite other) {
		//if powerup contacts player, player create shield or speedup
		if (other instanceof Player) {
			World.getInstance().removeSprites(this);
			if (current_powerup.equals(POWERUP_STRING1)) {
				Player.createShield(POWERUP_TIME);
			}
			if (current_powerup.equals(POWERUP_STRING2)) {
				Player.createSpeedUp(POWERUP_TIME);
			}
		}
	}

}
