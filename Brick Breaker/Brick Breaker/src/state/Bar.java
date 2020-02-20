package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;
import ui.BrickBreakerPanel;

/**
 * The bar stores its game state by holding on to the position of its top-left
 * corner, its width, and its height. The bar is also responsible for drawing
 * itself and checking for collisions with balls and falling powerups.
 * 
 * @author Aaron Tetens
 */
public class Bar implements Drawable {

	// The bar only moves and changes size in the horizontal dirction, so these
	// variables are final.
	static final int BAR_Y = BrickBreakerPanel.BOTTOM_WALL - ((int) (1.5 * Brick.BRICK_HEIGHT));
	static final int BAR_HEIGHT = Brick.BRICK_HEIGHT / 2;

	private static final int INIT_WIDTH = (int) (1.25 * Brick.BRICK_WIDTH);
	private static final int INIT_X = BrickBreakerPanel.MID_X - (INIT_WIDTH / 2);

	private static Bar instance;

	private int x;
	private int width;

	private Bar() {
		this.setX(INIT_X);
		this.width = INIT_WIDTH;
	}

	/**
	 * @return The singleton instance of Bar.
	 */
	public static Bar getInstance() {
		if (instance == null) {
			instance = new Bar();
		}

		return instance;
	}

	/**
	 * Resets the x-position and the width to their start-of-game defaults.
	 */
	public void initState() {
		this.setX(INIT_X);
		this.width = INIT_WIDTH;
	}

	/**
	 * Attempts to move the bar such that its center is vertically aligned with the
	 * mouse. If this is not possible given the bar's width and current position,
	 * get it as close as possible.
	 * 
	 * @param mouseX
	 *            The x-coordinate of the mouse
	 */
	public void moveToMouse(final int mouseX) {
		final int desiredNewX = mouseX - (this.width / 2);

		if (desiredNewX < BrickBreakerPanel.LEFT_WALL) { // run into the left wall
			this.setX(BrickBreakerPanel.LEFT_WALL);
		} else {
			final int largestPossibleX = BrickBreakerPanel.RIGHT_WALL - this.width;

			if (desiredNewX > largestPossibleX) { // run into the right wall
				this.setX(largestPossibleX);
			} else { // all other cases
				this.setX(desiredNewX);
			}
		}
	}

	public void setX(final int x) {
		this.x = x;
	}

	int getX() {
		return this.x;
	}

	int getWidth() {
		return this.width;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.x, BAR_Y, this.width, BAR_HEIGHT);
	}
}
