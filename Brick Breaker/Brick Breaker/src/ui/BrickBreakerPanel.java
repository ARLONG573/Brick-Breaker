package ui;

import java.awt.Graphics;
import java.util.HashSet;
import java.util.Set;

import javax.swing.JPanel;

import state.Brick;

/**
 * This singleton panel is where game state is updated and drawn.
 * 
 * @author Aaron Tetens
 */
public class BrickBreakerPanel extends JPanel {

	private static final long serialVersionUID = 4423585134456760646L;

	private static BrickBreakerPanel instance;

	private final Set<Brick> remainingBricks = new HashSet<>();

	private int fps;

	private BrickBreakerPanel() {
		// EMPTY
	}

	public static BrickBreakerPanel getInstance() {
		if (instance == null) {
			instance = new BrickBreakerPanel();
		}

		return instance;
	}

	/**
	 * Initializes the game state based on the selections made by the user in
	 * OptionsFrame. Calculations need to be done relative to the size of the
	 * component that is returned from super.getParent(), which in this case is 669
	 * x 860.
	 */
	public void initGameState() {
		this.fps = OptionsFrame.getInstance().getFPS();
		this.initBricks();
	}

	/**
	 * Initializes the bricks for the game.
	 */
	private void initBricks() {
		this.remainingBricks.clear();

		for (int x = 2; x <= 572; x += 95) {
			for (int y = 0; y <= 172; y += 43) {
				this.remainingBricks.add(new Brick(x, y, 95, 43));
			}
		}
	}

	/**
	 * Draws the current game state.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		for (final Brick brick : this.remainingBricks) {
			brick.drawSelf(g);
		}
	}
}
