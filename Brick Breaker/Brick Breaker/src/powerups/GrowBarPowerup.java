package powerups;

import java.awt.Graphics;

import impl.AbstractPowerup;

/**
 * Powerup that, upon activation, widens the bar by 20%
 * 
 * @author Aaron Tetens
 */
public class GrowBarPowerup extends AbstractPowerup {
	
	/**
	 * Constructs a new GrowBarPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public GrowBarPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup widens the bar by 20%
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
