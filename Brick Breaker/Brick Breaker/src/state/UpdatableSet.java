package state;

import java.util.HashSet;

import api.Updatable;

/**
 * Class responsible for maintaining and updating all instances of Updatable
 * objects in the game.
 * 
 * @author Aaron Tetens
 */
public class UpdatableSet extends HashSet<Updatable> implements Updatable {

	private static final long serialVersionUID = -3932158680979461509L;

	@Override
	public void update() {
		for (final Updatable updatable : this) {
			updatable.update();
		}
	}
}
