package state;

import java.awt.Graphics;
import java.util.HashSet;

import api.Drawable;

/**
 * Class responsible for maintaining and drawing all instances of Drawable
 * objects in the game.
 * 
 * @author Aaron Tetens
 */
public class DrawableSet extends HashSet<Drawable> implements Drawable {

	private static final long serialVersionUID = -7379627127390351439L;

	@Override
	public void draw(final Graphics g) {
		for (final Drawable drawable : this) {
			drawable.draw(g);
		}
	}

}
