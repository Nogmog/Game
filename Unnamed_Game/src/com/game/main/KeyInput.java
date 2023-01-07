package com.game.main;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;

public class KeyInput extends KeyAdapter {
	private Handler handler;
	private boolean[] keyDown = new boolean[4];
	
	public KeyInput(Handler handler) {
		this.handler = handler;
	}
	
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		for(GameObject i : handler.object) {
			if(i.getId() == ID.Player) { // events
				
				if(key == KeyEvent.VK_W) {i.setDy(-4); keyDown[0]=true;}
				if(key == KeyEvent.VK_S) {i.setDy(4); keyDown[1]=true;}
				if(key == KeyEvent.VK_A) {i.setDx(-4); keyDown[2]=true;}
				if(key == KeyEvent.VK_D) {i.setDx(4); keyDown[3]=true;}

			}
		}
	}
	
	public void keyReleased(KeyEvent e) {
		int key = e.getKeyCode();
		for(GameObject i : handler.object) {
			if(i.getId() == ID.Player) { // events
				
				if(key == KeyEvent.VK_W)  keyDown[0]=false;
				if(key == KeyEvent.VK_S)  keyDown[1]=false;
				if(key == KeyEvent.VK_A)  keyDown[2]=false;
				if(key == KeyEvent.VK_D)  keyDown[3]=false;
				
				if(!keyDown[0] && !keyDown[1]) i.setDy(0);
				if(!keyDown[2] && !keyDown[3]) i.setDx(0);
			}
		}
	}
}
