package powerups;

import java.awt.Color;
import java.awt.Graphics;

import impl.AbstractPowerup;
import ui.BrickBreakerPanel;

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
		g.setColor(Color.GREEN);

		// arrow
		g.drawLine(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT / 2), super.x + POWERUP_WIDTH,
				super.y + (POWERUP_HEIGHT / 2));
		g.drawLine(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 2) / 3,
				super.y + (POWERUP_HEIGHT / 3));
		g.drawLine(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 2) / 3,
				super.y + (POWERUP_HEIGHT * 2) / 3);
		// ball
		g.setColor(Color.RED);
		g.fillOval(super.x, super.y + POWERUP_HEIGHT / 3, POWERUP_WIDTH / 3, POWERUP_HEIGHT / 3);
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup slows down each ball by 10%
	 */
	@Override
	public void activate() {
		BrickBreakerPanel.getInstance().slowDownBalls();
	}
}
