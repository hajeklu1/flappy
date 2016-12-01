package pro2_flappy.game.tiles;

import java.awt.Graphics;
import java.awt.Image;

import pro2_flappy.game.Tile;

public class WallTile implements Tile {
	Image image;

	@Override
	public void draw(Graphics g, int x, int y) {

		//g.drawRect(x, y, Tile.SIZE, Tile.SIZE);
		//g.drawOval(x, y, Tile.SIZE, Tile.SIZE);
		
		g.drawImage(image, x, y,null);
		
	}
	public WallTile(Image image){
		this.image = image;
	}
}
