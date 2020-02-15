package state;

import java.awt.Graphics;
import java.util.HashSet;

import api.Drawable;

/**
 * Extension of HashSet<Ball> that provides useful methods for dealing with the
 * balls' state.
 * 
 * @author Aaron Tetens
 */
public class BallSet extends HashSet<Ball> implements Drawable {

	private static final long serialVersionUID = -3785092919996232711L;

	/**
	 * On initialization, BallSet adds just the default starting ball to itself.
	 */
	public BallSet() {
		this.add(new Ball());
	}

	@Override
	public void draw(final Graphics g) {
		for (final Ball ball : this) {
			ball.draw(g);
		}
	}
}
