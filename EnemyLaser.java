import org.newdawn.slick.Input;
/**
 * EnemyLaser class
 * A subclass of the Sprite
 * Creates all Enemy laser
 */
public class EnemyLaser extends Sprite{
	/** Enemy laser speed */
	private final float ENEMY_LASER_SPEED =0.7f;
	
	/** Enemy laser constructor*/
	public EnemyLaser(float x, float y) {
		super("res/enemy-shot.png", x, y);
	}
	
	@Override
	public void update(Input input, int delta) {
		super.update(input, delta);
		if (onScreen()) {
			setY(getY()+ENEMY_LASER_SPEED*delta);
		}
		// Destory laser if the laser is not on screen
		if (!onScreen()){
			World.getInstance().removeSprites(this);
		}
	}
}
