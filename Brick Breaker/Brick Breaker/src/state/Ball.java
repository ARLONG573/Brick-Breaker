package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;

/**
 * Each ball stores its game state by holding on to the position of the top-left
 * corner, the width, and the height used to draw its bounding box. Each ball is
 * responsible for drawing itself, checking if it has gone past the bar (player
 * loses), and checking for collisions with the bar, walls, and bricks.
 * 
 * @author Aaron Tetens
 */
public class Ball implements Drawable {

	// The width and height of all balls are constant.
	private static final int BALL_WIDTH = 30;
	private static final int BALL_HEIGHT = 30;

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
		this(319, 766, 0, 0);
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
		this.x = x;
		this.y = y;
		this.dx = dx;
		this.dy = dy;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.RED);
		g.fillOval(this.x, this.y, BALL_WIDTH, BALL_HEIGHT);
	}

}
