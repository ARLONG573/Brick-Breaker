package ui;

import java.awt.GridLayout;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import powerups.PowerupFactory;

/**
 * This class is a singleton options menu for the brick breaker game, where the
 * user can select the desired FPS and which powerups to enable before each
 * game. The default is 60 FPS with all powerups enabled, and options are saved
 * between games.
 * 
 * @author Aaron Tetens
 */
public class OptionsFrame extends JFrame {

	static final int MAX_FPS = 120;

	private static final long serialVersionUID = -5501116354649798614L;
	private static final Integer[] FPS_CHOICES = { 30, 60, 120 };

	private static OptionsFrame instance;

	private final JLabel selectFPSLabel = new JLabel("Select FPS");
	private final JComboBox<Integer> fpsMenu = new JComboBox<>(FPS_CHOICES);
	private final JButton startButton = new JButton("Start");
	private final List<JCheckBox> checkBoxes = new LinkedList<>();
	private final List<String> enabledPowerups = new ArrayList<>();

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
		this.initComponents();
		this.layoutComponents();
		this.addListeners();
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
	 * @return The currently selected FPS.
	 */
	public int getFPS() {
		return (Integer) this.fpsMenu.getSelectedItem();
	}

	/**
	 * Methods that set object's speeds multiply them by the value returned by this
	 * method to account for FPS differences.
	 */
	public int getSpeedCoefficient() {
		return MAX_FPS / this.getFPS();
	}

	/**
	 * @return A List of the names of the enabled powerups.
	 */
	public List<String> getEnabledPowerups() {
		return this.enabledPowerups;
	}

	/**
	 * Makes sure that all of the options are initally set to their defaults.
	 */
	private void initComponents() {
		this.fpsMenu.setSelectedItem(MAX_FPS);

		final String[] powerups = PowerupFactory.getPowerupNames();
		for (final String powerup : powerups) {
			this.checkBoxes.add(new JCheckBox(powerup, true));
		}
	}

	/**
	 * Lays out the initialized components.
	 */
	private void layoutComponents() {
		final int numPowerups = this.checkBoxes.size();
		super.setSize(500, 75 * (numPowerups + 2)); // add one for the FPS option and one for the start button
		super.setLayout(new GridLayout(numPowerups + 2, 2)); // add one for the FPS option and one for the start button
		super.add(this.selectFPSLabel);
		super.add(this.fpsMenu);

		for (final JCheckBox checkBox : this.checkBoxes) {
			super.add(new JPanel());
			super.add(checkBox);
		}

		super.add(new JPanel());
		super.add(this.startButton);
	}

	/**
	 * Adds functionality to the start button (set OptionsFrame to invisible, cache
	 * the enabled powerups, and make the game visible).
	 */
	private void addListeners() {
		this.startButton.addActionListener((e) -> {
			super.setVisible(false);
			this.setEnabledPowerups();
			BrickBreakerFrame.getInstance().displayNewGame();
		});
	}

	/**
	 * Reads the list of checkboxes and caches the ones that are enabled.
	 */
	private void setEnabledPowerups() {
		this.enabledPowerups.clear();

		for (final JCheckBox checkBox : this.checkBoxes) {
			if (checkBox.isSelected()) {
				this.enabledPowerups.add(checkBox.getText());
			}
		}
	}
}
