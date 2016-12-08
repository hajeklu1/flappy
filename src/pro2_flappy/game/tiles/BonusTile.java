package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

public class BonusTile extends AbstractTile {
	boolean isActive = true;

	public BonusTile(Image image) {
		super(image);
		// TODO Auto-generated constructor stub
	}

	@Override
	public void draw(Graphics g, int x, int y) {
		if (isActive) {
			super.draw(g, x, y);
		}
	}
	public void setIsActive(boolean value){
		isActive = value;
	}
}
