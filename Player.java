import org.newdawn.slick.Input;
/**
 * Player class
 * A subclass of the Sprite class 
 * For creating player object
 */
public class Player extends Sprite{
	/** Decide if a player should lose one live */
	public static boolean loseLive= true;
	/** Decide if a player should speed up */
	public static boolean ifSpeedUp = false;
	/** Player lives counts, start with maxiumn 3*/
	public static int lifeCount = 3;
	
	/** Player movement speed*/
	private final static float PLAYER_SPEED = 0.5f;
	/** Player's starting x-axis, in pixels*/
	private final static float PLAYER_STARTING_X = 512;
	/** Player's starting y-axis, in pixels*/
	private final static float PLAYER_STARTING_Y = 688;
	/** Time for play's shiled after contacting enemies or enemy's laser*/
	private final int SHIELD_TIME2 = 3000;
	/** Player resource*/
	private final static String PLAYER_STRING = "res/spaceship.png";
	
	/** Delay between each normal bullet, in millisecond*/
	private static int bullet_delay = 320;
	/** Delay time between each poweruped bullet, in millisecond*/
	private static int powerup_bullet_delay= 150;
	/** Time for speed up*/
	private static int speed_up_time = 5000;
	/** Timer for bullets*/
	private static int time = 0;
	/** x-axis used for shield */
	private static float x;
	/** y-axis used for shield */
	private static float y;

	/**Player constructor*/
	public Player() {
		super(PLAYER_STRING, PLAYER_STARTING_X, PLAYER_STARTING_Y);
	}
	
	public void update(Input input, int delta) {
		time -= delta;
		// Case 1: When pressed key down down
		if ((input.isKeyDown(Input.KEY_DOWN))&(super.getY()<App.SCREEN_HEIGHT)){
			super.setY(getY()+PLAYER_SPEED*delta);
		}
		
		// Case 2: When pressed key up down
		if ((input.isKeyDown(Input.KEY_UP))&(super.getY()>0)){
			super.setY(getY()-PLAYER_SPEED*delta);
		}
		
		// Case 3: When pressed key left down
		if ((input.isKeyDown(Input.KEY_LEFT))&(super.getX()>0)){
			super.setX(getX()-PLAYER_SPEED*delta);
		}
		
		
		// Case 4: When pressed key right down
		if ((input.isKeyDown(Input.KEY_RIGHT))&(super.getX()<App.SCREEN_WIDTH)){
			super.setX(getX()+PLAYER_SPEED*delta);
		}
		
		// When player got the speed up power up
		if (ifSpeedUp == true) {
			speed_up_time-=delta;
			if (speed_up_time <= 0) {
				ifSpeedUp =false;
				speed_up_time = 5000;
			}
		}
		// Case 5: when space key is pressed
		if ((time <= 0)&&input.isKeyDown(Input.KEY_SPACE)) {
			World.getInstance().addSprites(new Laser(getX(), getY()));
			// When player did not get powerup
			if (ifSpeedUp == false) {
				time = bullet_delay;
			} 
			// When player got the powerup
			if (ifSpeedUp == true){
				time = powerup_bullet_delay;
			}
		}
		
		// give shield player's position
		x = getX();
		y = getY();
		
		// Create boundingbox for player
		super.update(input, delta);
		
		
	}
	
	/**
	 * Player gets Shield
	 */
	public static void createShield(int shield_time) {
		loseLive = false;
		World.getInstance().addSprites(new Shield(x,y,shield_time));
	}
	
	/**
	 * Player gets speed up
	 */
	public static void createSpeedUp(int speedup_time) {
		ifSpeedUp = true;
	}
	
	/** Handle Player and Enemies and Player and Enemies' Laser contact
	 * @param other The sprite that made the contact to this class
	 */
	public void contactSprite(Sprite other) {
		if (other instanceof Enemy || other instanceof EnemyLaser) {
			// If player still have lives
			if (lifeCount -1 >=0 && loseLive == true) {
				// Creat shield for player
				Player.createShield(SHIELD_TIME2);
				lifeCount-=1;
				// Player loses one live
				World.getInstance().loseLife();
				// Destory Enemy Laser
				if (other instanceof EnemyLaser) {
					World.getInstance().removeSprites(other);
				}
			}
			
			// If player doesn't have lives anymore, exit game
			if (lifeCount == 0) {
				System.exit(0);
				System.out.println("You have died!!");
			}
		}
	}
	
	/** 
	 * Get the x-axis for shield 
	 */
	public static float getStaticX() {
		return x;
	}
	
	/** 
	 * Get the y-axis for shield 
	 */
	public static float getStaticY() {
		return y;
	}
	

}
