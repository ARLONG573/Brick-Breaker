package ui;

import java.awt.Color;
import java.awt.Font;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionListener;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import api.Powerup;
import state.BallSet;
import state.Bar;
import state.BrickSet;
import state.DrawableSet;
import state.UpdatableSet;

/**
 * This singleton panel is where game state is updated and drawn.
 * 
 * @author Aaron Tetens
 */
public class BrickBreakerPanel extends JPanel implements ActionListener {

	public static final int LEFT_WALL = 0;
	public static final int BOTTOM_WALL = 860;
	public static final int RIGHT_WALL = 669;
	public static final int TOP_WALL = 0;
	public static final int MID_X = RIGHT_WALL / 2;
	public static final int MID_Y = BOTTOM_WALL / 2;

	private static final long serialVersionUID = 4423585134456760646L;
	private static final String START_GAME_TEXT = "CLICK ANYWHERE TO START";
	private static final Font START_GAME_TEXT_FONT = new Font("Arial", Font.BOLD, 40);

	private static BrickBreakerPanel instance;

	private final DrawableSet drawables = new DrawableSet();
	private final UpdatableSet updatables = new UpdatableSet();

	// If the user clicks and the game hasn't started, start the game
	private final MouseAdapter clickListener = new MouseAdapter() {
		@Override
		public void mouseClicked(final MouseEvent e) {
			if (!BrickBreakerPanel.this.gameStarted) {
				BrickBreakerPanel.this.gameStarted = true;

				final int clickX = e.getX();

				BrickBreakerPanel.this.ballSet.launchFirstBall(clickX, e.getY());

				BrickBreakerPanel.this.bar.moveToMouse(clickX);
				BrickBreakerPanel.this.initTimer();
			}
		}
	};

	// If the mouse moves after the game has started, move the bar
	private final MouseMotionListener motionListener = new MouseMotionListener() {
		@Override
		public void mouseMoved(final MouseEvent e) {
			this.handleMouseMovement(e);
		}

		@Override
		public void mouseDragged(final MouseEvent e) {
			this.handleMouseMovement(e);
		}

		private void handleMouseMovement(final MouseEvent e) {
			if (BrickBreakerPanel.this.gameStarted) {
				BrickBreakerPanel.this.bar.moveToMouse(e.getX());
			}
		}
	};

	private final Bar bar = Bar.getInstance();
	private final OptionsFrame optionsFrame = OptionsFrame.getInstance();

	private int fps;
	private BrickSet brickSet;
	private BallSet ballSet;
	private boolean gameStarted;
	private Timer timer;

	private BrickBreakerPanel() {
		super.addMouseListener(this.clickListener);
		super.addMouseMotionListener(this.motionListener);
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
		this.initUpdatableSet();
	}

	/**
	 * Adds the given Powerup to the set of Powerups, as well as the set of
	 * Drawables and Updatables (since Powerups are also instances of Drawable and
	 * Updatable).
	 * 
	 * @param powerup
	 *            The powerup to add
	 */
	public void addPowerup(final Powerup powerup) {
		//TODO
	}

	/**
	 * Sets the game state variables to their initial values.
	 */
	private void initStateVariables() {
		this.fps = this.optionsFrame.getFPS();
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
	 * Initializes the set of updatable game state variables
	 */
	private void initUpdatableSet() {
		this.updatables.clear();
		this.updatables.add(this.ballSet);
	}

	/**
	 * Reinitializes and starts the animation timer.
	 */
	private void initTimer() {
		this.timer = new Timer(1000 / this.fps, this);
		this.timer.start();
	}

	@Override
	public void actionPerformed(final ActionEvent e) {
		this.updateState();
		super.repaint();
	}

	/**
	 * Applies updates to all updatable objects.
	 */
	private void updateState() {
		this.updatables.update();

		if (this.brickSet.isEmpty()) {
			this.displayEndGameMessage("YOU WIN!");
		}
		if (this.ballSet.isEmpty()) {
			this.displayEndGameMessage("YOU LOSE!");
		}

		// the game is not over, check for anything that might change an object's state
		this.ballSet.checkForBarCollisions(this.bar);
		this.ballSet.checkForBrickCollisions(this.brickSet);
	}

	/**
	 * Stops the update loop and displays the given message to the user. After the
	 * user confirms the message, they are brought back to the options menu.
	 * 
	 * @param message
	 */
	private void displayEndGameMessage(final String message) {
		this.timer.stop();
		JOptionPane.showMessageDialog(this, message);
		this.optionsFrame.displayOptions();
		BrickBreakerFrame.getInstance().setVisible(false);
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

		g.drawString(START_GAME_TEXT, MID_X - (textWidth / 2), MID_Y - (textHeight / 2));
	}
}
