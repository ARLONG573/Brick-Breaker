package powerups;

import java.awt.Color;
import java.awt.Graphics;

import impl.AbstractPowerup;
import ui.BrickBreakerPanel;

/**
 * Powerup that, upon activation, spawns speeds up all balls by about 20%
 * 
 * @author Aaron Tetens
 */
public class SpeedUpPowerup extends AbstractPowerup {

	/**
	 * Constructs a new SpeedUpPowerup centered at (x, y)
	 * 
	 * @param x
	 * @param y
	 */
	public SpeedUpPowerup(int x, int y) {
		super(x, y);
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.RED);

		// arrow
		g.drawLine(super.x, super.y + (POWERUP_HEIGHT / 2), super.x + (POWERUP_WIDTH * 2) / 3,
				super.y + POWERUP_HEIGHT / 2);
		g.drawLine(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT / 3), super.x + (POWERUP_WIDTH * 2) / 3,
				super.y + POWERUP_HEIGHT / 2);
		g.drawLine(super.x + (POWERUP_WIDTH / 3), super.y + (POWERUP_HEIGHT * 2) / 3, super.x + (POWERUP_WIDTH * 2) / 3,
				super.y + POWERUP_HEIGHT / 2);
		// ball
		g.fillOval(super.x + (POWERUP_WIDTH * 2) / 3, super.y + (POWERUP_HEIGHT / 3), POWERUP_WIDTH / 3,
				POWERUP_HEIGHT / 3);
	}

	/**
	 * {@inheritDoc} Upon activation, this powerup speeds up all balls by about 20%
	 */
	@Override
	public void activate() {
		BrickBreakerPanel.getInstance().speedUpBalls();
	}
}
