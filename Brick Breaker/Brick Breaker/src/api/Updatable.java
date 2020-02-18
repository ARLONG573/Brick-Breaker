package api;

/**
 * Interface used to describe classes that should define how they are updated
 * each frame.
 * 
 * @author Aaron Tetens
 */
public interface Updatable {

	/**
	 * Defines how this object is updated each frame.
	 */
	public void update();
}
