package com.game.main;

import java.awt.Graphics;
import java.util.LinkedList;

public class Handler {
	LinkedList<GameObject> object = new LinkedList<GameObject>();
	
	public void tick() {
		for(int i=0; i < object.size(); i++) {
			GameObject item = object.get(i);
			
			item.tick();
		}
	}
	
	public void render(Graphics g) {
		for(int i=0; i < object.size(); i++) {
			GameObject item = object.get(i);
			
			item.render(g);
		}
	}
	
	public void addObject(GameObject obj) {
		this.object.add(obj);
	}
	
	public void removeObject(GameObject obj) {
		this.object.remove(obj);
	}
}
