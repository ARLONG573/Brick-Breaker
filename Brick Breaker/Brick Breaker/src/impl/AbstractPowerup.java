package impl;

import java.awt.Graphics;

import api.Powerup;
import state.Ball;
import state.Bar;
import ui.OptionsFrame;

/**
 * This class is a convenient parent class for most implementations of the
 * Powerup interface that takes care of updates and checking for bar collisions.
 * Powerup objects have a random chance to be created when a brick is hit (the
 * powerup will take its place).
 * 
 * @author Aaron Tetens
 */
public abstract class AbstractPowerup implements Powerup {

	protected static final int POWERUP_WIDTH = Ball.BALL_WIDTH * 2;
	protected static final int POWERUP_HEIGHT = Ball.BALL_HEIGHT * 2;

	protected int x;
	protected int y;

	private static final int POWERUP_SPEED = OptionsFrame.getInstance().getSpeedCoefficient() * Ball.DEFAULT_SPEED / 2;

	protected AbstractPowerup() {
		// left empty because never used, but must exist for compilation
	}

	/**
	 * Creates a new AbstractPowerup centered at (x, y).
	 * 
	 * @param x
	 * @param y
	 */
	public AbstractPowerup(final int x, final int y) {
		this.x = x - (POWERUP_WIDTH / 2);
		this.y = y - (POWERUP_HEIGHT / 2);
	}

	@Override
	public abstract void draw(final Graphics g);

	@Override
	public abstract void activate();

	@Override
	public int getY() {
		return this.y;
	}

	@Override
	public void update() {
		this.y += POWERUP_SPEED;
	}

	@Override
	public boolean checkForBarCollision(final Bar bar) {
		final int centerX = this.x + (POWERUP_WIDTH / 2);
		final int barX = bar.getX();
		final int nextY = this.y + POWERUP_SPEED;

		final boolean xRequirement = this.valueLiesWithinRange(centerX, barX, barX + bar.getWidth());
		final boolean yRequirement = this.valueLiesWithinRange(Bar.BAR_Y, nextY, nextY + POWERUP_HEIGHT);

		if (xRequirement && yRequirement) {
			this.activate();
			return true;
		}

		return false;
	}

	/**
	 * @param num
	 * @param min
	 * @param max
	 * @return Whether or not num lies in the range [min,max]
	 */
	private boolean valueLiesWithinRange(final int num, final int min, final int max) {
		return ((min <= num) && (num <= max));
	}
}
