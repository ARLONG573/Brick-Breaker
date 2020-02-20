package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;
import api.Updatable;
import ui.BrickBreakerPanel;

/**
 * Each ball stores its game state by holding on to the position of the top-left
 * corner, the width, and the height used to draw its bounding box. Each ball is
 * responsible for drawing itself, checking if it has gone past the bar (player
 * loses), and checking for collisions with the bar, walls, and bricks.
 * 
 * @author Aaron Tetens
 */
public class Ball implements Drawable, Updatable {

	// The width and height of all balls are constant.
	private static final int BALL_WIDTH = 30;
	private static final int BALL_HEIGHT = 30;

	static final int MAX_Y = BrickBreakerPanel.BOTTOM_WALL - BALL_HEIGHT;
	private static final int MAX_X = BrickBreakerPanel.RIGHT_WALL - BALL_WIDTH;

	private int x;
	private int y;

	// Speed variables are not final because some powerups speed up/slow down the
	// ball's speed.
	private int dx;
	private int dy;

	/**
	 * Default constructor - to be used when initializing game state.
	 */
	public Ball() {
		this(BrickBreakerPanel.MID_X - (BALL_WIDTH / 2), Bar.BAR_Y - BALL_HEIGHT, 0, 0);
	}

	/**
	 * Parametrized constructor - to be used when spawning a new ball in the middle
	 * of the game (due to a powerup).
	 * 
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public Ball(final int x, final int y, final int dx, final int dy) {
		this.setX(x);
		this.setY(y);
		this.setSpeed(dx, dy);
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, BALL_WIDTH, BALL_HEIGHT);
	}

	@Override
	public void update() {
		this.setX(this.x + this.dx);
		this.setY(this.y + this.dy);

		/*
		 * Check for wall bounces (excluding bottom wall, since lost balls will get
		 * cleaned up by BallSet)
		 */
		if (this.x < BrickBreakerPanel.LEFT_WALL) {
			this.setX(BrickBreakerPanel.LEFT_WALL);
			this.setDx(-this.dx);
		} else if (this.x > MAX_X) {
			this.setX(MAX_X);
			this.setDx(-this.dx);
		}

		if (this.y < BrickBreakerPanel.TOP_WALL) {
			this.setY(BrickBreakerPanel.TOP_WALL);
			this.setDy(-this.dy);
		}
	}

	public void setSpeed(final int dx, final int dy) {
		this.setDx(dx);
		this.setDy(dy);
	}

	/**
	 * Checks if the ball is in a position to bounce off the top of the bar; if it
	 * is, negate its speed in the y direction.
	 * 
	 * The ball is in a position to bounce off of the top of the bar if its center
	 * is between the left and right edge of the bar (horizontal requirement) and
	 * the top edge of the bar is somewhere between the bottom and center of the
	 * ball (vertical requirement).
	 * 
	 * @param bar
	 *            The bar to check for a collision with
	 */
	void checkForBarCollision(final Bar bar) {
		final int centerX = this.x + (BALL_WIDTH / 2);
		final int centerY = this.y + (BALL_HEIGHT / 2);
		final int barX = bar.getX();

		final boolean xRequirement = this.valueLiesWithinRange(centerX, barX, barX + bar.getWidth());
		final boolean yRequirement = this.valueLiesWithinRange(Bar.BAR_Y, centerY, this.y + BALL_HEIGHT);

		if (xRequirement && yRequirement) {
			this.setY(Bar.BAR_Y - BALL_HEIGHT);
			this.setDy(-this.dy);
		}
	}

	/**
	 * Checks if the ball is in a position to bounce off of the given brick; if it
	 * is, negate its speed in either the x or y direction (whichever is
	 * appropriate).
	 * 
	 * @param brick
	 *            The brick to check for a collision with
	 * 
	 * @return Whether or not the ball bounced off of the given brick.
	 */
	boolean checkForBrickCollision(final Brick brick) {
		final int centerX = this.x + (BALL_WIDTH) / 2;
		final int centerY = this.y + (BALL_HEIGHT) / 2;

		final int brickLeft = brick.getX();
		final int brickTop = brick.getY();
		final int brickRight = brickLeft + Brick.BRICK_WIDTH;
		final int brickBottom = brickTop + Brick.BRICK_HEIGHT;

		boolean xRequirement;
		boolean yRequirement;

		boolean hit = false;
		// check for collisions; note that a collision can occur on two sides at once
		// bottom
		xRequirement = this.valueLiesWithinRange(centerX, brickLeft, brickRight);
		yRequirement = this.valueLiesWithinRange(brickBottom, this.y, centerY);
		if (xRequirement && yRequirement) {
			this.setY(brickBottom);
			this.setDy(-this.dy);
			hit = true;
		}
		// left
		xRequirement = this.valueLiesWithinRange(brickLeft, centerX, this.x + BALL_WIDTH);
		yRequirement = this.valueLiesWithinRange(centerY, brickTop, brickBottom);
		if (xRequirement && yRequirement) {
			this.setX(brickLeft - BALL_WIDTH);
			this.setDx(-this.dx);
			hit = true;
		}
		// right
		xRequirement = this.valueLiesWithinRange(brickRight, this.x, centerX);
		yRequirement = this.valueLiesWithinRange(centerY, brickTop, brickBottom);
		if (xRequirement && yRequirement) {
			this.setX(brickRight);
			this.setDx(-this.dx);
			hit = true;
		}
		// top
		xRequirement = this.valueLiesWithinRange(centerX, brickLeft, brickRight);
		yRequirement = this.valueLiesWithinRange(brickTop, centerY, this.y + BALL_HEIGHT);
		if (xRequirement && yRequirement) {
			this.setY(brickTop - BALL_HEIGHT);
			this.setDy(-this.dy);
			hit = true;
		}

		return hit;
	}

	int getY() {
		return this.y;
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

	private void setDx(final int dx) {
		this.dx = dx;
	}

	private void setDy(final int dy) {
		this.dy = dy;
	}

	private void setX(final int x) {
		this.x = x;
	}

	private void setY(final int y) {
		this.y = y;
	}

	/**
	 * @return True if the magnitude of the ball's speed vector is equal to zero,
	 *         false otherwise.
	 */
	boolean isMoving() {
		return !(this.dx == 0 && this.dy == 0);
	}
}
