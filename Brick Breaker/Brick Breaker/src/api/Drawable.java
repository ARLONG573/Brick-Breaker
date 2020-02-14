package api;

import java.awt.Graphics;

/**
 * Interface used to describe classes that should define how they are drawn to
 * the screen.
 * 
 * @author Aaron Tetens
 */
public interface Drawable {

	/**
	 * Defines how this object is drawn to the screen.
	 * 
	 * @param g
	 */
	public void draw(final Graphics g);
}
