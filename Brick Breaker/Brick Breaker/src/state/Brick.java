package state;

import java.awt.Color;
import java.awt.Graphics;

/**
 * Each brick stores its game state by holding on to the position of its
 * top-left corner, its width, and its height. Each brick is also responsible
 * for drawing itself and checking for collisions with balls.
 * 
 * @author Aaron Tetens
 */
public class Brick {

	private final int x;
	private final int y;
	private final int width;
	private final int height;

	public Brick(final int x, final int y, final int width, final int height) {
		this.x = x;
		this.y = y;
		this.width = width;
		this.height = height;
	}

	/**
	 * Draws this brick (red with a black border).
	 * 
	 * @param g
	 */
	public void drawSelf(final Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, this.width, this.height);
		g.setColor(Color.RED);
		g.fillRect(this.x + 1, this.y + 1, this.width - 1, this.height - 1);
	}
}
