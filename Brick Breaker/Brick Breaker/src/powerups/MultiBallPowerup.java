package powerups;

import java.awt.Graphics;

import impl.AbstractPowerup;

/**
 * Powerup that, upon activation, spawns 2 more balls from every ball.
 * 
 * @author Aaron Tetens
 */
public class MultiBallPowerup extends AbstractPowerup {

	/**
	 * Constructs a new MultiBallPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public MultiBallPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup spawns 2 more balls from every
	 * ball's current position. These balls are launched in random directions at the
	 * current speed of all balls.
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
