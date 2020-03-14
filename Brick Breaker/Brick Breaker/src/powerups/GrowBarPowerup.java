package powerups;

import java.awt.Color;
import java.awt.Graphics;

import impl.AbstractPowerup;
import state.Bar;

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
		// the bar
		g.setColor(Color.BLACK);
		g.fillRect(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT * 3 / 8), POWERUP_WIDTH / 3,
				POWERUP_HEIGHT / 4);

		// the arrows
		g.setColor(Color.GREEN);

		// left arrow
		g.drawLine(super.x, super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH / 3),
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_HEIGHT / 6), super.y + (POWERUP_HEIGHT * 3 / 8), super.x,
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_HEIGHT / 6), super.y + (POWERUP_HEIGHT * 5 / 8), super.x,
				super.y + (POWERUP_HEIGHT / 2));

		// right arrow
		g.drawLine(super.x + (POWERUP_WIDTH * 2 / 3), super.y + (POWERUP_HEIGHT / 2), super.x + POWERUP_WIDTH,
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + POWERUP_WIDTH, super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 5 / 6),
				super.y + (POWERUP_HEIGHT * 3 / 8));
		g.drawLine(super.x + POWERUP_WIDTH, super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 5 / 6),
				super.y + (POWERUP_HEIGHT * 5 / 8));
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup widens the bar by 20%
	 */
	@Override
	public void activate() {
		Bar.getInstance().grow();
	}
}
