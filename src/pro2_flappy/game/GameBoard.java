package pro2_flappy.game;

import java.awt.Graphics;
import java.awt.Image;
import pro2_flappy.game.tiles.BonusTile;
import pro2_flappy.game.tiles.WallTile;

public class GameBoard implements TickAware {

	Tile[][] tiles;
	int shiftX = 0;
	int viewportWidth = 200; // TODO upravit
	Bird bird;
	Boolean gameOver = false;

	public GameBoard(Tile[][] tiles, Image imgBird) {
		this.tiles = tiles;
		bird = new Bird(viewportWidth / 2, tiles.length * Tile.SIZE / 2, imgBird);
	}

	public int getHeightPix() {
		return tiles.length * Tile.SIZE;
	}

	public void kickTheBird() {
		bird.kick();
	}

	/**
	 * Kresli cely herni svet (zdi, bonusy, ptaka) na platno g a kontorluje zda
	 * nedošlo ke kolizi ptáèka s dlaždicí
	 * 
	 * @param g
	 */
	public void drawAndTestCollisions(Graphics g) {

		int minJ = shiftX / Tile.SIZE; // index prvního viditelného sloupce
		int maxJ = minJ + viewportWidth / Tile.SIZE + 2; // zajištìní zobrazení
		System.out.println(shiftX);
		for (int i = 0; i < tiles.length; i++) {
			for (int j = minJ; j < maxJ + 60; j++) {

				// chceme, aby se svìt toèil dokola, takže j2 se pohybuje od 0
				// do poèet sloupcu pole -1
				int j2 = j % tiles[i].length;

				Tile t = tiles[i][j2];

				if (t == null)
					continue;

				int screenX = j * Tile.SIZE - shiftX;
				int screenY = i * Tile.SIZE;

				// nakreslíme dlaždici
				t.draw(g, screenX, screenY);

				// otestujeme možnou kolizi s ptákem
				if (t instanceof WallTile) {
					// dlazdice typu zed
					if (bird.collidesWhitRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)) {
						gameOver = true;
					}
				}
				if (t instanceof BonusTile) {
					if (bird.collidesWhitRectangle(screenX, screenY, Tile.SIZE, Tile.SIZE)) {
						((BonusTile) t).setIsActive(false);

					}
					if (shiftX % 200 == 0) {
						((BonusTile) t).setIsActive(true);
					}
				}
			}
		}

		// kresli ptáka
		bird.draw(g);

	}

	@Override
	public void tick(long ticksSinceStart) {
		if (!gameOver) {
			// s kazdym tikem posuneme hru o jeden pixel
			// tj. pocet ticku a pixelu posunu se rovnaji
			shiftX = (int) ticksSinceStart;

			// dame vedet jeste ptakovi, ze hodiny tickly
			bird.tick(ticksSinceStart);

		}
	}

}
