package powerups;

import api.Powerup;

/**
 * Class that provides the list of powerup names to OptionsFrame for rendering,
 * as well as a factory method for creating new instances of Powerups.
 * 
 * @author Aaron Tetens
 */
public class PowerupFactory {

	private static final String[] POWERUP_NAMES = { "Multi-Ball", "Speed Up", "Speed Down", "Grow Bar", "Shrink Bar" };

	private PowerupFactory() {
		// purposefully left empty; no instances of this class should ever be created
	}

	/**
	 * @return A copy of the array of Powerup names.
	 */
	public static String[] getPowerupNames() {
		return POWERUP_NAMES.clone();
	}

	/**
	 * @param name
	 *            The name of the Powerup to be created.
	 * @param x
	 *            The x-coordinate of the Powerup to be created.
	 * @param y
	 *            The y-coordinate of the Powerup to be created.
	 * @return A new Powerup instance centered at (x, y), whose type is determined
	 *         by the given name, or null if the given name is not in the array
	 *         returned by getPowerupNames().
	 */
	public static Powerup createPowerup(final String name, final int x, final int y) {
		switch (name) {
		case "Multi-Ball":
			return new MultiBallPowerup(x, y);
		case "Speed Up":
			return new SpeedupPowerup(x, y);
		case "Speed Down":
			return new SpeedDownPowerup(x, y);
		case "Grow Bar":
			return new GrowBarPowerup(x, y);
		case "Shrink Bar":
			return new ShrinkBarPowerup(x, y);
		default:
			return null;
		}
	}

}
