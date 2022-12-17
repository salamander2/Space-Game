package game;

import java.awt.Color;
import java.awt.Rectangle;

/* This will just be moving vertically (initially)
 */
class Laser extends Rectangle{
	
	static final int MAXSHOT = 15;
	static final int SHOTDELAY = 400;  //milliseconds
	static Color clr = Color.YELLOW;
	static final int RANGE = 500;  //how many pixels the laser will go. To make it interesting
	
	//double precision not used in this class.
	private int dist = 0;
	int vx = 0;
	int vy = -3;	
	
	
	Laser(int x, int y) {
		width = 2;
		height = 10;
		this.x = x;
		this.y = y;
	}
	
	boolean move() {
		x += vx;
		y += vy;
		
		dist += Math.abs(vy);
		if (dist > RANGE) return false;
		
		//Standard wrapping code
		if (x < 0 - width) x = SpaceMain.panW;
		if (y < 0 - height) y = SpaceMain.panH;
		if (x > SpaceMain.panW) x = 0;
		if (y > SpaceMain.panH) y = 0;
		
		return true;
	}
}
