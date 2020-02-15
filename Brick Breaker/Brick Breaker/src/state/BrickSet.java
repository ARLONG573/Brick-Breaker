package state;

import java.awt.Graphics;
import java.util.HashSet;

import api.Drawable;

/**
 * Extension of HashSet<Brick> that provides useful methods for dealing with the
 * bricks' state.
 * 
 * @author Aaron Tetens
 */
public class BrickSet extends HashSet<Brick> implements Drawable {

	private static final long serialVersionUID = 5365999543111341615L;

	/**
	 * On initialization, BrickSet adds the set of initial bricks to itself.
	 */
	public BrickSet() {
		for (int x = 2; x <= 572; x += Brick.BRICK_WIDTH) {
			for (int y = 0; y <= 172; y += Brick.BRICK_HEIGHT) {
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
