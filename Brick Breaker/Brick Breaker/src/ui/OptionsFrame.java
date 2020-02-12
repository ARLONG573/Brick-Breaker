package ui;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

/**
 * This class is a singleton options menu for the brick breaker game, where the
 * user can select the desired FPS and which powerups to enable before each
 * game. The default is 60 FPS with all powerups enabled, and options are saved
 * between games.
 * 
 * @author Aaron Tetens
 */
public class OptionsFrame extends JFrame {

	private static final long serialVersionUID = -5501116354649798614L;
	private static final Integer[] FPS_CHOICES = { 30, 60 };

	private static OptionsFrame instance;

	private final JLabel selectFPSLabel = new JLabel("Select FPS");
	private final JComboBox<Integer> fpsMenu = new JComboBox<>(FPS_CHOICES);
	private final JButton startButton = new JButton("Start");

	/**
	 * @return The singleton instance of OptionsFrame
	 */
	public static OptionsFrame getInstance() {
		if (instance == null) {
			instance = new OptionsFrame();
		}

		return instance;
	}

	private OptionsFrame() {
		super("Game Options");
		super.setSize(500, 125);
		super.setLayout(new GridLayout(2, 2));
		this.initComponents();
		this.addListeners();
		super.add(this.selectFPSLabel);
		super.add(this.fpsMenu);
		super.add(new JPanel());
		super.add(this.startButton);
		super.setLocationRelativeTo(null);
		super.setResizable(false);
		super.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}

	/**
	 * Displays the OptionsFrame.
	 */
	public void displayOptions() {
		super.setVisible(true);
	}

	/**
	 * Makes sure that all of the options are initally set to their defaults.
	 */
	private void initComponents() {
		this.fpsMenu.setSelectedItem(60);
	}

	/**
	 * Adds functionality to the start button (set OptionsFrame to invisible, set
	 * the game FPS, and make the game visible).
	 */
	private void addListeners() {
		this.startButton.addActionListener((e) -> {
			super.setVisible(false);

			final BrickBreakerFrame brickBreakerFrame = BrickBreakerFrame.getInstance();
			brickBreakerFrame.setFPS((Integer) this.fpsMenu.getSelectedItem());
			brickBreakerFrame.displayGame();
		});
	}
}
