package pro2_flappy.game;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.geom.Ellipse2D;
import java.awt.image.BufferedImage;

public class Bird implements TickAware {

	// fyzika
	static final double koefUp = -6;
	static final double koefDown = 2.5;
	static final int ticksFlyingUp = 6;
	Image img;

	// souradnice stredu ptaka
	double viewportX;
	double viewportY;

	// rychlost padani (pozitivni), nebo vzledu (negativni)
	double velocityY = koefDown;
	// kolik tiku jeste zbyva, neez ptak zacne padat po nakopnuti
	int ticksToFall = 0;

	public Bird(int initalX, int initialY,Image img) {
		this.viewportX = initalX;
		this.viewportY = initialY;
		this.img = img;
	}

	public void kick() {
		velocityY = koefUp;
		ticksToFall = ticksFlyingUp;
	}

	public void draw(Graphics g) {
	//	g.setColor(Color.GREEN);
	//	g.fillOval((int) viewportX - Tile.SIZE / 2, (int) viewportY - Tile.SIZE / 2, Tile.SIZE - 3, Tile.SIZE - 3);

		g.drawImage(img,(int) viewportX - Tile.SIZE / 2, (int) viewportY - Tile.SIZE / 2, null);
		// vykresleni souradnic pro debugovani
		g.setColor(Color.BLACK);
		g.drawString(viewportX + "," + viewportY, (int) viewportX, (int) viewportY);
	}

	@Override
	public void tick(long ticksSinceStart) {
		// reseni rychlosti a ticku do padu

		if (ticksToFall > 0) {
			ticksToFall--;

		} else {

			velocityY = koefDown;

		}
		viewportY += velocityY;
	}

	/*
	 * overovani souradnic ptaka
	 */
	public boolean collidesWhitRectangle(int x, int y, int w, int h) {
		Ellipse2D.Float birdBoundery = new Ellipse2D.Float((int) viewportX - Tile.SIZE / 2, (int) viewportY - Tile.SIZE / 2, Tile.SIZE - 3, Tile.SIZE - 3);
		return birdBoundery.intersects(x, y, w, h);
	}
	
	public void setDefauldPozicion(){
		viewportX = 100;
		viewportY = 200;
	}
	

}
