package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

import state.Bar;
import state.BrickSet;
import state.DrawableSet;

/**
 * This singleton panel is where game state is updated and drawn.
 * 
 * @author Aaron Tetens
 */
public class BrickBreakerPanel extends JPanel {

	private static final long serialVersionUID = 4423585134456760646L;

	private static BrickBreakerPanel instance;

	private final DrawableSet drawables = new DrawableSet();

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
		this.initDrawableSet();
	}

	/**
	 * Fills the DrawableSet with the game's initial conditions.
	 */
	private void initDrawableSet() {
		this.drawables.clear();
		this.drawables.add(new BrickSet());
		this.drawables.add(new Bar());
	}

	/**
	 * Draws the current game state.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawables.draw(g);
	}
}
