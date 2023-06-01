package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;

class Ship extends Rectangle {
	//Note: x,y,width,height (all ints) come from Rectangle

	double xx, yy;  //double versions of x,y for precise moving
	double vx = 2.2;
	double vy = 3.3;
	Color clr = Color.ORANGE; 
	int health = 70;
	private boolean isAlive = true;
	BufferedImage img;

	Ship(){
		width = 50;
		height = 30;

		xx = SpaceMain.panW/2 - width/2;
		yy = 100;

		x = (int) xx;
		y = (int) yy;
	}

	void move(SpaceShip player) {

		//calculate angle from centre to centre
		double dx = (player.xx+player.width)-(xx+width);
		double dy = (player.yy+player.height)-(yy+height);
		double angle = Math.atan2(dy,dx);

		xx += 0.5*vx*Math.cos(angle);
		yy += 0.5*vy*Math.sin(angle);

		x = (int) xx;
		y = (int) yy;
	}

	//Laser must be returned so that it can be added to the arraylist
	Laser shoot() {	
		//laser constructor gets the (x,y) where it should start
		Laser z = new Laser(this.x + this.width/2, this.y + 10);
		return z;
	}

	boolean isAlive() 	{ return isAlive; }
	void 	die() 		{ isAlive = false; }	
}
