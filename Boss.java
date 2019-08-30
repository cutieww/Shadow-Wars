import org.newdawn.slick.Input;
import java.util.Random;
/**
 * Boss class
 * A subclass of the Enemy class
 * Creates Boss
 */
public class Boss extends Enemy{
	/** Boss' score */
	private final static int BOSS_POINTS = 5000;
	
	/** Boss starting y */
	private final float BOSS_FIRST_Y = 72;
	/** Boss first movement speed */
	private final float BOSS_MOVE_SPEED1 = 0.05f;
	/** Boss second movement speed */
	private final float BOSS_MOVE_SPEED2 = 0.2f;
	/** Boss third movement speed */
	private final float BOSS_MOVE_SPEED3 = 0.1f;
	/** Boss shoot offsets */
	private final float SHOOT_ONE_OFFSET = -97;
	private final float SHOOT_TWO_OFFSET = -74;
	private final float SHOOT_THREE_OFFSET = 74;
	private final float SHOOT_FOUR_OFFSET = 97;
	
	/** Boss shoot time */
	private final int BOSS_SHOOT_TIME = 3000;
	/** Boss min x-xais */
	private final int MIN_X = 128;
	/** Boss max x-xais */
	private final int MAX_X = 896;
	/** Second delay for boss second movement */
	private final int SECOND_DELAY = 5000;
	/** Third delay for boss third movement */
	private final int THIRD_DELAY = 2000;
	/** Boss has 60 lives in total */
	private final int BOSS_LIVES = 60;
	
	/** for boss' second delay */
	private static int second;
	/** for boss' third delay */
	private static int third;
	/** record how many times boss contact with player laser */
	private static int countBoss = 0;
	/** record boss start x-axis */
	private static float boss_start_x;
	
	/** record boss second x-axis */
	private float secondX;
	/** delay between each bullet */
	private int boss_bullet_delay = 200;
	private float randomX;
	private float randomX2;
	private int time = 0;
	private int shootTime;
	
	/** Boss constructor
	 * @param imageSrc Image sources
	 * @param x x-axis of that the boss
	 * @param y y-axis of that the boss
	 * @param delay Boss's delay
	 */
	public Boss(String imageSrc, float x, float y, int delay) {
		super(imageSrc, x, y, delay,BOSS_POINTS);
		getRandom();
		shootTime = BOSS_SHOOT_TIME;
		second = SECOND_DELAY;
		third = THIRD_DELAY;
		boss_start_x = x;
	}

	@Override
	public void update(Input input, int delta) {
		if (World.timer >= getDelay()){
			// Boss comes out
			if (getY() < BOSS_FIRST_Y) {
				setY(getY()+BOSS_MOVE_SPEED1*delta);
			}
			// Boss move after coming out 
			else {
				second -= delta;
				if (getX()>=randomX && second<=0 && third==THIRD_DELAY) {
					setX(getX()-BOSS_MOVE_SPEED2*delta);
					secondX = getX();
				}
				else if (getX()<=randomX && second<=0 && third==THIRD_DELAY) {
					setX(getX()+BOSS_MOVE_SPEED2*delta);
					secondX = getX();
				}
				
				// Boss shoot after moving to the first random x-axis
				if (((boss_start_x>randomX && secondX<= randomX)||
						(boss_start_x<randomX && secondX>= randomX)) && 
						(second <=0)) {
					third-=delta;
					// Shoot while moving
					if (third<=0) {
						if (getX()<randomX2 && randomX <= randomX2) {
							setX(getX()+BOSS_MOVE_SPEED3*delta);
						}
						if (getX()>randomX2 && randomX >= randomX2) {
							setX(getX()-BOSS_MOVE_SPEED3*delta);
						}
						time -= delta;
						shootTime -= delta;
						float shoot1 = getX()+SHOOT_ONE_OFFSET;
						float shoot2 = getX()+SHOOT_TWO_OFFSET;
						float shoot3 = getX()+SHOOT_THREE_OFFSET;
						float shoot4 = getX()+SHOOT_FOUR_OFFSET;
						// Create laser
						if (time <= 0 && shootTime>=0) {
							World.getInstance().addSprites(new EnemyLaser(shoot1, getY()));
							World.getInstance().addSprites(new EnemyLaser(shoot2, getY()));
							World.getInstance().addSprites(new EnemyLaser(shoot3, getY()));
							World.getInstance().addSprites(new EnemyLaser(shoot4, getY()));
							time = boss_bullet_delay;
						}
						// Repeat above process
						else if (time >0 && shootTime < 0) {
							shootTime = BOSS_SHOOT_TIME;
							time = 0;
							second = SECOND_DELAY;
							third = THIRD_DELAY;
							boss_start_x = randomX2;
							getRandom();
						}
					}
				}
			}
		}
		// Create bb box for boss
		super.update(input, delta);
	}
	
	/** Handle Boss and player laser's contacts
	 * @param other The sprite that made the contact to this class
	 */
	@Override
	public void contactSprite(Sprite other) {
		if (other instanceof Laser) {
			/** Make sure boss is immutable before coming out 
			 *  also count how many times boss got shooted*/
			if (World.timer >= getDelay()) {
				countBoss +=1; 
				World.getInstance().removeSprites(other);
				// Destory boss after enough shoot times
				if (countBoss == BOSS_LIVES){
					World.getInstance().removeSprites(this);
					World.score+=getScore();
				}
			}
		}
	}
	
	/** 
	 * Get randam x-axis for Boss
	 */
	public void getRandom() {
		Random rand = new Random();
		randomX = (float)rand.nextInt(MAX_X-MIN_X) + MIN_X;
		randomX2 = (float)rand.nextInt(MAX_X-MIN_X) + MIN_X;
	}
}

