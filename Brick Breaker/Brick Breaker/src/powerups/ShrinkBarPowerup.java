package powerups;

import java.awt.Color;
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
		// the bar
		g.setColor(Color.BLACK);
		g.fillRect(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT * 3 / 8), POWERUP_WIDTH / 3,
				POWERUP_HEIGHT / 4);

		// the arrows
		g.setColor(Color.RED);

		// left arrow
		g.drawLine(super.x, super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH / 3),
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_HEIGHT / 6), super.y + (POWERUP_HEIGHT * 3 / 8), super.x + (POWERUP_WIDTH / 3),
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_HEIGHT / 6), super.y + (POWERUP_HEIGHT * 5 / 8), super.x + (POWERUP_WIDTH / 3),
				super.y + (POWERUP_HEIGHT / 2));

		// right arrow
		g.drawLine(super.x + (POWERUP_WIDTH * 2 / 3), super.y + (POWERUP_HEIGHT / 2), super.x + POWERUP_WIDTH,
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_WIDTH * 2 / 3), super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 5 / 6),
				super.y + (POWERUP_HEIGHT * 3 / 8));
		g.drawLine(super.x + (POWERUP_WIDTH * 2 / 3), super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 5 / 6),
				super.y + (POWERUP_HEIGHT * 5 / 8));

	}

	/**
	 * {@inheritDoc} Upon activation, this powerup shrinks the bar by 10%
	 */
	@Override
	public void activate() {
		// TODO Auto-generated method stub
	}
}
