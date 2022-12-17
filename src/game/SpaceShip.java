package game;

import java.awt.Color;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

class SpaceShip extends Ship {
	
	
	SpaceShip(){
		clr = Color.GREEN; //player ship is different colour
		vx++; vy++; // Player is faster than enemy ship
		
		xx = SpaceMain.panW/2 - width/2;
		yy = SpaceMain.panH - 90;
		
		x = (int) xx;
		y = (int) yy;
		
		try {
			img = ImageIO.read(new File("krakenSM.png"));
			width = img.getWidth();
			height = img.getHeight();
		} catch (IOException e) {
			System.out.println("Warning: gitkrakenSM.png failed to load");
		}
	}
	
	void move (int key) {
		switch (key) {
		case 'W':		
			yy -=vy; break;
		case 'S':
			yy +=vy; break;
		case 'A':
			xx -=vx; break;
		case 'D':
			xx +=vx; break;
		}
		// wrap around on the screen
		if (xx < 0 - width) xx = SpaceMain.panW;
		if (yy < 0 - height) yy = SpaceMain.panH;
		if (xx > SpaceMain.panW) xx = 0;
		if (yy > SpaceMain.panH) yy = 0;
		
		//update final positions
		x = (int)xx;
		y = (int)yy;
	}

	
	
}
