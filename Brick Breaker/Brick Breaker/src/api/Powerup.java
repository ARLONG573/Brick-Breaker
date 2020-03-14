package api;

import state.Bar;

/**
 * Interface used to describe powerups. Powerups need to be drawn to the screen
 * and have their position updated, as well as check for collision with the bar
 * (activation method) and define what they do when activated.
 * 
 * @author Aaron Tetens
 */
public interface Powerup extends Drawable, Updatable {

	/**
	 * Defines this object's behavior upon activation.
	 */
	public void activate();

	/**
	 * Defines how this object checks for bar collision and behaves when colliding
	 * into a bar (typically the behavior is to call activate()).
	 * 
	 * @param bar
	 * @return True if this powerup collidied with the given bar, otherwise false
	 */
	public boolean checkForBarCollision(final Bar bar);

	/**
	 * @return The current y-coordinate of this Powerup.
	 */
	public int getY();
}
