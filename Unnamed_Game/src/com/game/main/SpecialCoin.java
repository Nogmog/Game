package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class SpecialCoin extends GameObject{
	
	public SpecialCoin(int x, int y, ID id) {
		super(x, y, id);
		damage = 5;
	}

	@Override
	public void tick() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.cyan);
		g.fillRect(x, y, 15, 15);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 15, 15);
	}

}
