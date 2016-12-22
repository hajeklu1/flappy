package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import pro2_flappy.game.Tile;

public class BonusTile extends AbstractTile {
	private boolean isActive = true;
	private Tile empty;

	public BonusTile(Image image, Tile tile) {
		super(image);
		empty = tile;
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if (isActive) {
			super.draw(g, x, y);
		} else {
			empty.draw(g, x, y);
		}
	}

	public void setIsActive(boolean value) {
		isActive = value;
	}
	
	/*
	 * Kopirovaci konstruktor
	 */
	public BonusTile(BonusTile original) {
		super(original.image);
		empty = original.empty;
	}
}
