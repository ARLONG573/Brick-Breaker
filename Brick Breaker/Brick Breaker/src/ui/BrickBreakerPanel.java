package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;

import javax.swing.JPanel;

import state.BallSet;
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
	private static final String START_GAME_TEXT = "CLICK ANYWHERE TO START";
	private static final Font START_GAME_TEXT_FONT = new Font("Arial", Font.BOLD, 40);

	private static BrickBreakerPanel instance;

	private final DrawableSet drawables = new DrawableSet();

	// Game state variables
	private final Bar bar = Bar.getInstance();
	private int fps;
	private BrickSet brickSet;
	private BallSet ballSet;
	private boolean gameStarted;

	private BrickBreakerPanel() {
		// EMPTY
	}

	/**
	 * @return The singleton instance of BrickBreakerPanel.
	 */
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
		this.initStateVariables();
		this.initDrawableSet();
	}

	/**
	 * Sets the game state variables to their initial values.
	 */
	private void initStateVariables() {
		this.fps = OptionsFrame.getInstance().getFPS();
		this.bar.initState();
		this.brickSet = new BrickSet();
		this.ballSet = new BallSet();
		this.gameStarted = false;
	}

	/**
	 * Initializes the set of drawable game state variables
	 */
	private void initDrawableSet() {
		this.drawables.clear();
		this.drawables.add(this.bar);
		this.drawables.add(this.brickSet);
		this.drawables.add(this.ballSet);
	}

	/**
	 * Draws the current game state.
	 */
	@Override
	protected void paintComponent(final Graphics g) {
		super.paintComponent(g);
		this.drawables.draw(g);

		if (!this.gameStarted) {
			this.displayStartGameText(g);
		}
	}

	/**
	 * Displays "CLICK ANYWHERE TO START" on the screen.
	 * 
	 * @param g
	 */
	private void displayStartGameText(final Graphics g) {
		g.setColor(Color.BLACK);
		g.setFont(START_GAME_TEXT_FONT);

		final FontMetrics metrics = g.getFontMetrics(START_GAME_TEXT_FONT);
		final int textWidth = metrics.stringWidth(START_GAME_TEXT);
		final int textHeight = metrics.getAscent();

		g.drawString(START_GAME_TEXT, 334 - (textWidth / 2), 430 - (textHeight / 2));
	}
}
