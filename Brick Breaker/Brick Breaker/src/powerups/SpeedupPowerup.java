package powerups;

import java.awt.Graphics;

import impl.AbstractPowerup;

/**
 * Powerup that, upon activation, spawns speeds up all balls by 10%
 * 
 * @author Aaron Tetens
 */
public class SpeedupPowerup extends AbstractPowerup {

	/**
	 * Constructs a new SpeedupPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public SpeedupPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup speeds up all balls by 10%
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
