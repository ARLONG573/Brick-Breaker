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
	 * Displays the BrickBreakerFrame after setting the initial game state based on
	 * the selections made in OptionsFrame.
	 */
	public void displayNewGame() {
		this.brickBreakerPanel.initGameState();
		super.setVisible(true);
	}
}
