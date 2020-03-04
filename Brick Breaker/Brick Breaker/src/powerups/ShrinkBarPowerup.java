package powerups;

import java.awt.Graphics;

import impl.AbstractPowerup;

/**
 * Powerup that, upon activation, shrinks the bar by 10%
 * 
 * @author Aaron Tetens
 */
public class ShrinkBarPowerup extends AbstractPowerup {

	/**
	 * Constructs a new ShrinkBarPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public ShrinkBarPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		// TODO Auto-generated method stub
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup shrinks the bar by 10%
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
