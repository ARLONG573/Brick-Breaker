package state;

import java.awt.Graphics;
import java.util.HashSet;

import api.Drawable;
import ui.BrickBreakerPanel;

/**
 * Extension of HashSet<Brick> that provides useful methods for dealing with the
 * bricks' state.
 * 
 * @author Aaron Tetens
 */
public class BrickSet extends HashSet<Brick> implements Drawable {

	static final int BRICKS_PER_ROW = 7;

	private static final int BRICKS_PER_COLUMN = 5;
	private static final long serialVersionUID = 5365999543111341615L;

	// represents the space leftover on each side of the brick structure
	private static final int X_OFFSET = 2;

	/**
	 * On initialization, BrickSet adds the set of initial bricks to itself.
	 */
	public BrickSet() {
		for (int x = X_OFFSET; x <= BrickBreakerPanel.RIGHT_WALL - X_OFFSET
				- Brick.BRICK_WIDTH; x += Brick.BRICK_WIDTH) {
			for (int y = 0; y <= Brick.BRICK_HEIGHT * (BRICKS_PER_COLUMN - 1); y += Brick.BRICK_HEIGHT) {
				super.add(new Brick(x, y));
			}
		}
	}

	@Override
	public void draw(final Graphics g) {
		for (final Brick brick : this) {
			brick.draw(g);
		}
	}
}
