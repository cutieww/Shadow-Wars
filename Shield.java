import org.newdawn.slick.Input;
/**
 * Shield class
 * A subclass of the Sprite
 * Create shield for player
 */
public class Shield extends Sprite{
	/** shield powerup string recourse */
	private final static String SHIELD = "res/shield.png";
	/** for storing shield time */
	private int time2;
	/** set ifShield to true initially */
	public static boolean ifShield = true;
	
	/** Shield constructor
	 * @param x Shield x-axis
	 * @param y Shield y-axis
	 * @param shield_time Time for each shield
	 */
	public Shield(float x, float y,int shield_time) {
		super(SHIELD, x, y);
		time2 = shield_time;
		
	}
	
	@Override
	public void update(Input input, int delta) {
		time2-=delta;
		setX(Player.getStaticX());
		setY(Player.getStaticY());
		//remove shield when time is up
		if (time2<=0) {
			World.getInstance().removeSprites(this);
			Player.loseLive = true;
		}
	}


}
