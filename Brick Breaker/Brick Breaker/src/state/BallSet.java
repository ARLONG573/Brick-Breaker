package state;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import api.Drawable;
import api.Updatable;

/**
 * Extension of HashSet<Ball> that provides useful methods for dealing with the
 * balls' state.
 * 
 * @author Aaron Tetens
 */
public class BallSet extends HashSet<Ball> implements Drawable, Updatable {

	private static final long serialVersionUID = -3785092919996232711L;

	/**
	 * On initialization, BallSet adds just the default starting ball to itself.
	 */
	public BallSet() {
		this.add(new Ball());
	}

	/**
	 * This method does nothing unless this BallSet consists of a single, non-moving
	 * ball, indicating that the game has not started yet. If that is the case, then
	 * the ball's speed is set to the given dx and dy values.
	 * 
	 * @param dx
	 * @param dy
	 */
	public void launchFirstBall(final int dx, final int dy) {
		if (this.size() != 1) {
			return;
		}

		final Ball ball = this.iterator().next();

		if (ball.isMoving()) {
			return;
		}

		ball.setSpeed(dx, dy);
	}

	@Override
	public void draw(final Graphics g) {
		for (final Ball ball : this) {
			ball.draw(g);
		}
	}

	@Override
	public void update() {
		for (final Ball ball : this) {
			ball.update();
		}

		this.removeLostBalls();
	}

	/**
	 * Removes any balls that have gone off the bottom of the screen.
	 */
	private void removeLostBalls() {
		final Set<Ball> lostBalls = new HashSet<>();

		for (final Ball ball : this) {
			if (ball.getY() > Ball.MAX_Y) {
				lostBalls.add(ball);
			}
		}

		this.removeAll(lostBalls);

		if (this.size() == 0) {
			// TODO handle this situation
			System.out.println("YOU LOSE");
		}
	}
}
