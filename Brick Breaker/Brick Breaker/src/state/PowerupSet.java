package state;

import java.awt.Graphics;
import java.util.HashSet;

import api.Drawable;
import api.Powerup;
import api.Updatable;

/**
 * Extension of HashSet<Powerup> that provides useful methods for dealing with
 * the powerups' states.
 * 
 * @author Aaron Tetens
 */
public class PowerupSet extends HashSet<Powerup> implements Drawable, Updatable {

	private static final long serialVersionUID = 3816792343895050844L;

	/**
	 * Each Powerup in this set checks for a collision with the given bar. It is the
	 * each Powerup's responsibility to handle how it checks for and reacts to bar
	 * collisions. Any powerups that collided with the given bar are removed from
	 * this set.
	 * 
	 * @param bar
	 *            The bar to check for collisions with
	 */
	public void checkForBarCollisions(final Bar bar) {
		for (final Powerup powerup : this) {
			if (powerup.checkForBarCollision(bar)) {
				super.remove(powerup);
			}
		}
	}

	@Override
	public void update() {
		for (final Powerup powerup : this) {
			powerup.update();
		}
	}

	@Override
	public void draw(final Graphics g) {
		for (final Powerup powerup : this) {
			powerup.draw(g);
		}
	}
}
