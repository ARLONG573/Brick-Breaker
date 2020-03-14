package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;
import api.Updatable;
import ui.BrickBreakerPanel;
import ui.OptionsFrame;

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
	public static final int BALL_WIDTH = 30;
	public static final int BALL_HEIGHT = 30;
	public static final int DEFAULT_SPEED = 10;

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
	 * The ball is in a position to bounce off of the top of the bar if, next frame,
	 * its center is between the left and right edge of the bar (horizontal
	 * requirement) and the top edge of the bar is somewhere between the bottom and
	 * top of the ball.
	 * 
	 * @param bar
	 *            The bar to check for a collision with
	 */
	void checkForBarCollision(final Bar bar) {
		final int centerX = this.x + (BALL_WIDTH / 2);
		final int barX = bar.getX();
		final int nextY = this.y + this.dy;

		final boolean xRequirement = this.valueLiesWithinRange(centerX, barX, barX + bar.getWidth());
		final boolean yRequirement = this.valueLiesWithinRange(Bar.BAR_Y, nextY, nextY + BALL_HEIGHT);

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
		final int centerX = this.x + (BALL_WIDTH / 2);
		final int centerY = this.y + (BALL_HEIGHT / 2);
		final int nextX = this.x + this.dx;
		final int nextY = this.y + this.dy;

		final int brickLeft = brick.getLeft();
		final int brickTop = brick.getTop();
		final int brickRight = brick.getRight();
		final int brickBottom = brick.getBottom();

		boolean xRequirement;
		boolean yRequirement;

		boolean hit = false;
		// check for collisions; note that a collision can occur on two sides at once
		// bottom
		xRequirement = this.valueLiesWithinRange(centerX, brickLeft, brickRight);
		yRequirement = this.valueLiesWithinRange(brickBottom, nextY, nextY + BALL_HEIGHT);
		if (xRequirement && yRequirement) {
			this.setY(brickBottom);
			this.setDy(-this.dy);
			hit = true;
		}
		// left
		xRequirement = this.valueLiesWithinRange(brickLeft, nextX, nextX + BALL_WIDTH);
		yRequirement = this.valueLiesWithinRange(centerY, brickTop, brickBottom);
		if (xRequirement && yRequirement) {
			this.setX(brickLeft - BALL_WIDTH);
			this.setDx(-this.dx);
			hit = true;
		}
		// right
		xRequirement = this.valueLiesWithinRange(brickRight, nextX, nextX + BALL_WIDTH);
		yRequirement = this.valueLiesWithinRange(centerY, brickTop, brickBottom);
		if (xRequirement && yRequirement) {
			this.setX(brickRight);
			this.setDx(-this.dx);
			hit = true;
		}
		// top
		xRequirement = this.valueLiesWithinRange(centerX, brickLeft, brickRight);
		yRequirement = this.valueLiesWithinRange(brickTop, nextY, nextY + BALL_HEIGHT);
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
	 * The ball's speed is set to the initial speed in the direction towards the
	 * given point.
	 * 
	 * @param x
	 * @param y
	 */
	void launchTowards(final int x, final int y) {
		// we want the ball's center to pass through (x, y), not its top-left corner
		// so, we do that adjustement before calculating the launch
		final int destX = x - BALL_WIDTH;
		final int destY = y - BALL_HEIGHT;
		final double theta = Math.atan(((double) (destY - this.y)) / (destX - this.x));

		// if we are launching to the left of the ball, we need to negate the result of
		// arctan
		final int signCoeff = destX < this.x ? -1 : 1;

		// adjust the ball's speed for the FPS
		final int speedCoeff = OptionsFrame.getInstance().getSpeedCoefficient();

		this.setSpeed((int) (signCoeff * speedCoeff * DEFAULT_SPEED * Math.cos(theta)),
				(int) (signCoeff * speedCoeff * DEFAULT_SPEED * Math.sin(theta)));
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

	/**
	 * Speeds the ball up by about 20%
	 */
	public void speedUp() {
		this.multiplySpeed(1.2);
	}

	/**
	 * Slows the ball down by about 20%
	 */
	public void speedDown() {
		this.multiplySpeed(0.8);
	}

	/**
	 * Multiplies EACH of dx and dy by the given number...note that the total speed
	 * will increase by a factor slightly more than mult.
	 * 
	 * @param mult
	 *            The factor by which to increase the speeds
	 */
	private void multiplySpeed(final double mult) {
		this.dx *= mult;
		this.dy *= mult;
	}

	public int getDx() {
		return this.dx;
	}

	public int getDy() {
		return this.dy;
	}

	public int getX() {
		return this.x;
	}
}
