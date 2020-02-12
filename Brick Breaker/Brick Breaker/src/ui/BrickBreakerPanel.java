package ui;

import java.awt.Graphics;

import javax.swing.JPanel;

/**
 * This singleton panel is where game state is updated and drawn.
 * 
 * @author Aaron Tetens
 */
public class BrickBreakerPanel extends JPanel {

	private static final long serialVersionUID = 4423585134456760646L;
	private static final int DEFAULT_FPS = 60;

	private static BrickBreakerPanel instance;

	private int fps = DEFAULT_FPS;

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
	 * OptionsFrame.
	 */
	public void initGameState() {
		this.fps = OptionsFrame.getInstance().getFPS();
	}

	/**
	 * Draws the current game state.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
	}
}
