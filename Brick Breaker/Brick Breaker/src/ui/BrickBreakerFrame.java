package ui;

import javax.swing.JFrame;

/**
 * This singleton frame holds the panel where the game state is updated and
 * drawn.
 * 
 * @author Aaron Tetens
 */
public class BrickBreakerFrame extends JFrame {

	private static final long serialVersionUID = -8886535055377982802L;

	private static BrickBreakerFrame instance;

	private final BrickBreakerPanel brickBreakerPanel = BrickBreakerPanel.getInstance();

	private BrickBreakerFrame() {
		super("Brick Breaker");
		super.add(this.brickBreakerPanel);
		super.setSize(675, 900);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * @return The singleton instace of BrickBreakerFrame.
	 */
	public static BrickBreakerFrame getInstance() {
		if (instance == null) {
			instance = new BrickBreakerFrame();
		}

		return instance;
	}

	/**
	 * Sets the FPS of the game to the given FPS.
	 * 
	 * @param fps
	 *            The FPS to set the game to.
	 */
	public void setFPS(final int fps) {
		this.brickBreakerPanel.setFPS(fps);
	}

	/**
	 * Displays the BrickBreakerFrame.
	 */
	public void displayGame() {
		super.setVisible(true);
	}
}
