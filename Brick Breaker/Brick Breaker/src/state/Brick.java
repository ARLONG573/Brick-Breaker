package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;

/**
 * Each brick stores its game state by holding on to the position of its
 * top-left corner, its width, and its height. Each brick is also responsible
 * for drawing itself and checking for collisions with balls.
 * 
 * @author Aaron Tetens
 */
public class Brick implements Drawable {

	// Every Brick instance has the same constant width and height.
	static final int BRICK_WIDTH = 95;
	static final int BRICK_HEIGHT = 43;

	private final int x;
	private final int y;

	public Brick(final int x, final int y) {
		this.x = x;
		this.y = y;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, BRICK_WIDTH, BRICK_HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(this.x + 1, this.y + 1, BRICK_WIDTH - 1, BRICK_HEIGHT - 1);
	}
}
