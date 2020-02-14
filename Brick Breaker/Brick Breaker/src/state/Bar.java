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

	/**
	 * These variables are final because the bar can only move, grow, and shrink in
	 * the horizontal direction.
	 */
	private final int y;
	private final int height;

	private int x;
	private int width;

	/**
	 * No parameters here because the bar is drawn at the same spot at the start of
	 * every game.
	 */
	public Bar() {
		this.x = 274;
		this.y = 796;
		this.width = 120;
		this.height = 21;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.BLACK);
		g.fillRect(this.x, this.y, this.width, this.height);
	}
}
