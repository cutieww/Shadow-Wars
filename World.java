import org.newdawn.slick.Graphics;
import java.util.ArrayList;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
/**
 * World class
 * Handles all the classes' rending,updating and check if the objects intersect.
 */
public class World {
	/** Create the timer */
	public static int timer = 0;
	/** Records how much score player got */
	public static int score = 0;
	
	/** Background resource */
	private String BACKGROUND_STRING = "res/space.png";
	/** Background image */
	private Image background;
	
	/** First Lives x axis, in pixels */ 
	private static final float LIVES_X_OFFSET = 20;
	/** First Lives y axis, in pixels */ 
	private static final float LIVES_Y_OFFSET = 696;
	/** Pixels between each live image */ 
	private static final float LIVES_SEP_X = 40;
	
	/** String score x axis, in pixels */ 
	private final int SCORE_X = 20;
	/** String score y axis, in pixels */ 
	private final int SCORE_Y = 738;
	
	/** Background speed */ 
	private float BACKGROUND_SPEED = 0.2f;
	private float backgroundY = 0;
	
	private static World world;
	
	/**
	 * Singelton
	 */
	public static World getInstance(){
		if (world == null) {
			world =  new World();
		}
		return world;
	}
	
	// Create sprites arraylist which contains all the sprites
	private ArrayList<Sprite> sprites = new ArrayList<>();
	
	/** Add things to sprites list
	 * @param sprite The instance of Sprite class
	 */
	public void addSprites(Sprite sprite){
		sprites.add(sprite);
	}
	
	/**
	 * Get background image
	 * and put lives and player into sprites
	 */
	public World() {
		try {
			background = new Image(BACKGROUND_STRING);
		} catch (SlickException e) {
			e.printStackTrace();
		}
		// Create new Lives class and put it in sprites arraylist
		for (int i = 0; i < Player.lifeCount; i += 1) {
			sprites.add(new Lives(LIVES_X_OFFSET + i*LIVES_SEP_X, 
					LIVES_Y_OFFSET));
		}
		//create new Player class and put it into sprites arraylist
		sprites.add(new Player());
		world = this;
	}
	
	/** Remove all the inactive sprites.
	 * @param sprite The instance of Sprite class
	 */
	public void removeSprites(Sprite sprite) {
		sprites.remove(sprite);
	}
	
	/** 
	 * Getter for timer
	 */
	public int getTimer() {
		return timer;
	}
	
	public void update(Input input, int delta) {
		// Speed up when s is pressed
		if (input.isKeyDown(Input.KEY_S)) {
			delta = delta*5;
		}
		// Update all the sprites
		for (int i = 0; i < sprites.size(); ++i) {
			if (sprites.get(i) != null) {
				sprites.get(i).update(input, delta);
			}
		}
		//set background
		backgroundY += BACKGROUND_SPEED * delta;
		backgroundY = backgroundY % background.getHeight();
		// Handle collisions
		for (int k = 0; k< sprites.size(); k++) {
			for (int j = 0; j<sprites.size();j++) {
				if (j<sprites.size() && k<sprites.size()){
					if (sprites.get(k)!=null && sprites.get(j)!=null) {
						if (sprites.get(k) != sprites.get(j) && 
								sprites.get(k).creatBbbox().intersects
								(sprites.get(j).creatBbbox())) {
							sprites.get(k).contactSprite(sprites.get(j));
						}
					}
				}
			}
		}
		timer +=delta;
	}
	
	public void render(Graphics g) {
		// Render background
		for (int i = 0; i < App.SCREEN_WIDTH; i += background.getWidth()) {
			for (int j = -background.getHeight() + 
					(int)backgroundY; j < App.SCREEN_HEIGHT;
					j +=background.getHeight()) {
				background.draw(i, j);
			}
		}
		// Draw sprites
		for (Sprite sprite : sprites) {
			if (sprite != null) {

				sprite.render();
			}
		}
		// Draw string score
		g.drawString("Score: " + Integer.toString(score), SCORE_X, SCORE_Y);
		
	}
	
	/**
	 * Handle lives lose
	 */
	public void loseLife() {
		for (int i = sprites.size()-1; i>0;i--) {
			if (sprites.get(i) instanceof Lives) {
				sprites.remove(i);
				break;
			}
		}
	}
	


}


