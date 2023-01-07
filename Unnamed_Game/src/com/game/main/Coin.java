package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Coin extends GameObject{
	
	public Coin(int x, int y, ID id) {
		super(x, y, id);
		damage = 2;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(x, y, 20, 20);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 20, 20);
	}

}
