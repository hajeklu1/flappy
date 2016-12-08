package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import pro2_flappy.game.Tile;

public abstract class AbstractTile implements Tile {

	Image image;

	public void draw(Graphics g, int x, int y) {
		g.drawImage(image, x, y,null);
		
	}
	public AbstractTile(Image image){
		this.image = image;
	}
}

