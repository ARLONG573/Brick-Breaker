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

	public int getY() {
		return this.y;
	}

	public void setDx(final int dx) {
		this.dx = dx;
	}

	public void setDy(final int dy) {
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
	public boolean isMoving() {
		return !(this.dx == 0 && this.dy == 0);
	}
}
