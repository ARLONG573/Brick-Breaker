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
	 * the ball's speed is set to the initial speed in the direction towards the
	 * given point.
	 * 
	 * @param x
	 * @param y
	 */
	public void launchFirstBall(final int x, final int y) {
		if (this.size() != 1) {
			return;
		}

		final Ball ball = this.iterator().next();

		if (ball.isMoving()) {
			return;
		}

		ball.launchTowards(x, y);
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
	 * For each ball in the set, checks if that ball is in a position to bounce off
	 * the top of the bar. If it is, negate its speed in the y direction.
	 * 
	 * @param bar
	 *            The bar to check for collisions with
	 */
	public void checkForBarCollisions(final Bar bar) {
		for (final Ball ball : this) {
			ball.checkForBarCollision(bar);
		}
	}

	/**
	 * For each ball in the set, checks if that ball is in a position to bounce off
	 * any of the bricks in the provided BrickSet; if it is, negate its speed in
	 * either the x or y direction (whichever is appropriate) and remove the brick
	 * from the game.
	 * 
	 * @param brickSet
	 *            The set of bricks to check for collisions with
	 */
	public void checkForBrickCollisions(final BrickSet brickSet) {
		// we don't use a BrickSet here because we don't want the initial bricks to be
		// added to it
		final Set<Brick> hitBricks = new HashSet<>();

		for (final Ball ball : this) {
			for (final Brick brick : brickSet) {
				if (ball.checkForBrickCollision(brick)) {
					hitBricks.add(brick);
				}
			}
		}

		brickSet.removeAll(hitBricks);
	}

	/**
	 * Removes any balls that have gone off the bottom of the screen.
	 */
	private void removeLostBalls() {
		// we don't use a BallSet here because we don't want the initial ball to be
		// added to it
		final Set<Ball> lostBalls = new HashSet<>();

		for (final Ball ball : this) {
			if (ball.getY() > Ball.MAX_Y) {
				lostBalls.add(ball);
			}
		}

		super.removeAll(lostBalls);
	}
}
