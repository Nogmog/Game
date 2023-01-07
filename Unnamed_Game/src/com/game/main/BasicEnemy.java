package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class BasicEnemy extends GameObject{
	
	private int damage = 10;
	private Handler handler;
	private GameInstance gameInstance;
	
	public BasicEnemy(int x, int y, ID id, Handler handler, GameInstance gameInstance) {
		super(x, y, id);
		this.dx = 5;
		this.dy = 0;
		this.handler = handler;
		this.gameInstance = gameInstance;
	}
	

	@Override
	public void tick() {
		this.x += dx;
		this.y += dy;
		
		if(gameInstance.enemyOffScreen(x, y, 32, 32)) {
			handler.removeObject(this);
			gameInstance.createEnemy();
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	public void setDamage(int damage) {
		this.damage = damage;
	}
	
	public int getDamage() {
		return damage;
	}

}
