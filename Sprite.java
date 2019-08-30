import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;

import utilities.BoundingBox;
/**
 * Sprite class
 * Super class of all the sprites and has the commen attributes
 * and methods for sub classes
 */
public class Sprite{
	/** Image */
	private Image image;
	/** x axis for all sprites */
	private float x;
	/** y axis for all sprites */
	private float y;
	/** Boundingbox bb */
	private BoundingBox bb;
	
	/**
	 * @param imageSrc Image sources
	 * @param x x-axis of that sprite
	 * @param y y-axis of that sprite
	 */
	public Sprite(String imageSrc, float x, float y) {
		try {
			image = new Image(imageSrc);
		} catch (SlickException e) {
			e.printStackTrace();
		}

		this.x = x;
		this.y = y;
		
		bb = new BoundingBox(image, x, y);

	}
	
	
	public void update(Input input, int delta) {
		//create update bounding box x-axis and y-axis
		bb.setX(getX());
		bb.setY(getY());
	}
	
	public void render() {
		//draw sprites
		image.drawCentered(x, y);
	}
	
	/** Handle enemy contacts
	 * @param other The sprite that made the contact to this class
	 */
	public void contactSprite(Sprite other) {

	}
	
	/** Get x-axis*/
	public float getX() {
		return x;
	}
	
	/** Get y-axis*/
	public float getY() {
		return y;
	}
	
	/** Set x-axis
	 * @param x x-axis of the sprite
	 */
	public void setX(float x) {
		this.x = x;
	}
	
	/** Set y-axis
	 * @param x y-axis of the sprite
	 */
	public void setY(float y) {
		this.y = y;
	}
	
	/** 
	 * Create BoundingBox
	 */
	public BoundingBox creatBbbox() {
		return new BoundingBox(bb);
	}
	
	/** 
	 * Check if sprites on screen
	 */
	public boolean onScreen() {
		return x >= 0 && x <= App.SCREEN_WIDTH - bb.getWidth()
			&& y >= 0 && y <= App.SCREEN_HEIGHT - bb.getHeight();
	}
	
	/** 
	 * Check if enemy off screen already
	 */
	public boolean enemyOffScreen() {
		return  x >= App.SCREEN_WIDTH - bb.getWidth()
			&&  y >= App.SCREEN_HEIGHT - bb.getHeight();
	}
}
