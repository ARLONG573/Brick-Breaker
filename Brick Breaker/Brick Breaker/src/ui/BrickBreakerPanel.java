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

	//TODO state variables
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

	public void setFPS(final int fps) {
		this.fps = fps;
	}

	/**
	 * Draws the current game state.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
	}
}
