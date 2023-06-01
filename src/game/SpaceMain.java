package game;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.Iterator;

import javax.swing.*;

public class SpaceMain implements ActionListener {

	public static void main(String[] args) {
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new SpaceMain();
			}
		});
	}
	
	//graphics related
	static int panW = 900;
	static int panH = 600;	
	DrawingPanel panel;
	
	//game objects
	SpaceShip player = new SpaceShip();
	Ship enemy = new Ship();
	Timer mainTimer = new Timer(10, this);
	BetterKeyListener bKeyL = new BetterKeyListener(); 
	ArrayList<Laser> laserList = new ArrayList<Laser>();
	
	SpaceMain(){
		panel = new DrawingPanel();
		
		JFrame window = new JFrame("Best Space Game");
		window.add(panel);
		window.pack();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		mainTimer.start();
	}
	
	
	
	class DrawingPanel extends JPanel {
		
		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
			this.addKeyListener( bKeyL );
			this.setFocusable(true); //needed for Jpanel & keys
		}
		
		public void paintComponent(Graphics g) {
			if(player.health < 35) this.setBackground(new Color(100,0,0));
			super.paintComponent(g);
			
			if (player.img != null) {
				g.drawImage(player.img,player.x, player.y, null);
			} else {
				g.setColor(player.clr);
				g.fillRect(player.x,  player.y,  player.width, player.height);
			}
			
			if(enemy.isAlive()) {
				g.setColor(enemy.clr);
				g.fillRect(enemy.x,  enemy.y,  enemy.width, enemy.height);
			}
			
			g.setColor(Laser.clr);
			for (Laser laser : laserList) {
				g.fillRect(laser.x,  laser.y, laser.width, laser.height);
			}
			g.drawString("Enemy health = " + enemy.health, 10,50);
		}

		
	} //end of DrawingPanel class
	
	
	/*** for mainTimer ***/
	//FIXME:  this code is too long. It needs to be split up into methods.
	@Override
	public void actionPerformed(ActionEvent e) {
		
		//move ship (assuming that a key has been pressed)		
		if (bKeyL.isKeyDown('A') || bKeyL.isKeyDown(37)) player.move('A');
		if (bKeyL.isKeyDown('W') || bKeyL.isKeyDown(38)) player.move('W');
		if (bKeyL.isKeyDown('D') || bKeyL.isKeyDown(39)) player.move('D');
		if (bKeyL.isKeyDown('S') || bKeyL.isKeyDown(40)) player.move('S');
		
		//shoot laser using space
		if (bKeyL.isKeyDown(' ')) {
			if (Laser.canShoot() && laserList.size() < Laser.MAXSHOT) {
				laserList.add( player.shoot() );
			}
		}
		
		//move all objects. Should this be in a separate timer?	
		enemy.move(player);
		
		for (int i = laserList.size()-1; i >= 0; i--) {
			Laser laser = laserList.get(i);	
			if (!laser.move()) laserList.remove(i);
		}
		
		//check collisions
		for (Laser laser : laserList) {
			if (enemy.isAlive() && laser.intersects(enemy)) {
				enemy.health -= laser.damage;				
				if (enemy.health <= 0) enemy.die();
				laserList.remove(laser);
				break;
			}
		}
		if (enemy.intersects(player)) {
			
			//FIXME get actual damage 
			//FIXME move enemy to random location 
			player.health -= 50;
			enemy.xx = 100;
			enemy.yy = 100;
		}
		
		panel.repaint();
	}
}
