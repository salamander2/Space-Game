package game;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.*;

public class SpaceMain {

	public static void main(String[] args) {
		//TODO: add in the swingUtiulities way of starting graphics
		new SpaceMain();		
	}
	
	int panW = 900;
	int panH = 900;
	
	DrawingPanel panel;
	
	SpaceMain(){
		panel = new DrawingPanel();
		
		JFrame window = new JFrame("Best Space Game");
		window.add(panel);
		window.pack();
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		//timer.start();
	}
	
	class DrawingPanel extends JPanel {
		DrawingPanel(){
			this.setBackground(Color.BLACK);
			this.setPreferredSize(new Dimension(panW, panH));
		}
	}
}
