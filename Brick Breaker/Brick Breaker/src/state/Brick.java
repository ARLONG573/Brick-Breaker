package state;

import java.awt.Color;
import java.awt.Graphics;

import api.Drawable;
import ui.BrickBreakerPanel;

/**
 * Each brick stores its game state by holding on to the position of its
 * top-left corner, its width, and its height. Each brick is also responsible
 * for drawing itself and checking for collisions with balls.
 * 
 * @author Aaron Tetens
 */
public class Brick implements Drawable {

	// Every Brick instance has the same constant width and height.
	static final int BRICK_WIDTH = BrickBreakerPanel.RIGHT_WALL / BrickSet.BRICKS_PER_ROW;
	static final int BRICK_HEIGHT = BrickBreakerPanel.BOTTOM_WALL / 20;

	private final int x;
	private final int y;

	// collision bounds
	private final int left;
	private final int top;
	private final int right;
	private final int bottom;

	public Brick(final int x, final int y) {
		this.x = x;
		this.y = y;

		this.left = this.x;
		this.top = this.y;
		this.right = this.left + BRICK_WIDTH;
		this.bottom = this.top + BRICK_HEIGHT;
	}

	int getX() {
		return this.x;
	}

	int getY() {
		return this.y;
	}

	int getLeft() {
		return this.left;
	}

	int getTop() {
		return this.top;
	}

	int getRight() {
		return this.right;
	}

	int getBottom() {
		return this.bottom;
	}

	@Override
	public void draw(final Graphics g) {
		g.setColor(Color.BLACK);
		g.drawRect(this.x, this.y, BRICK_WIDTH, BRICK_HEIGHT);
		g.setColor(Color.GREEN);
		g.fillRect(this.x + 1, this.y + 1, BRICK_WIDTH - 1, BRICK_HEIGHT - 1);
	}
}
