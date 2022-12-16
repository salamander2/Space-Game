package game;

import java.awt.Color;
import java.awt.Rectangle;

/* This will just be moving vertically (initially)
 */
class Laser extends Rectangle{
	
	static final int MAXSHOT = 15;
	static final int SHOTDELAY = 400;  //milliseconds
	static Color clr = Color.YELLOW;
	
	static int range = 1500;  //how many pixels the laser will go. To make it interesting
	int displ = 0;
	double vx = 0.0;
	double vy = -3.0;	
	double xx, yy;  //double versions of x,y for precise moving
	
	
	Laser(int x, int y) {
		width = 2;
		height = 10;
//		xx = player.x + player.width/2;
//		yy = player.y + 10;
//		x = (int)x;
//		y = (int)y;
		this.x = x;
		this.y = y;
	}
	
	void move() {
		//x += vx;
		y += vy;
		displ += vy;
		if (displ > range) {
			//TODO somehow delete this laser shot!
		}
		
		//wrap off the top of the screen
		if (y < 0) y = SpaceMain.panH;
	}
}
