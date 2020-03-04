package powerups;

import java.awt.Graphics;

import impl.AbstractPowerup;

/**
 * Powerup that, upon activation, slows down each ball by 10%
 * 
 * @author Aaron Tetens
 */
public class SpeedDownPowerup extends AbstractPowerup {

	/**
	 * Constructs a new SpeedDownPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public SpeedDownPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup slows down each ball by 10%
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
