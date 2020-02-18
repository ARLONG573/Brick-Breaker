package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;

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
	private static final int BAR_Y = 796;
	private static final int BAR_HEIGHT = 21;

	private static final int INIT_X = 274;
	private static final int INIT_WIDTH = 120;

	private static Bar instance;

	private int x;
	private int width;

	private Bar() {
		this.x = INIT_X;
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
		this.x = INIT_X;
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

		if (desiredNewX < 0) { // run into the left wall
			this.x = 0;
		} else if (desiredNewX > (669 - this.width)) { // run into the right wall
			this.x = 669 - this.width;
		} else { // all other cases
			this.x = desiredNewX;
		}
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.x, BAR_Y, this.width, BAR_HEIGHT);
	}
}
