package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Rectangle;

public class Player extends GameObject{
	
	private Handler handler;
	private GameInstance gameInstance;
	private SettingsData settings;
	
	private Color playerColour;
	
	public Player(int x, int y, ID id, Handler handler, GameInstance gameInstance, SettingsData settings) {
		super(x, y, id);
		this.handler = handler;
		this.gameInstance = gameInstance;
		this.settings = settings;
		playerColour = settings.getPlayerColour();
	}

	@Override
	public void tick() {
		x += dx;
		y += dy;
		collision();
		if(gameInstance.playerOffScreen(x, y, 32, 32)) {
			this.dx = 0;
			this.dy = 0;
		}
	}

	@Override
	public void render(Graphics g) {
		g.setColor(playerColour);
		g.fillRect(x, y, 32, 32);
	}

	@Override
	public Rectangle getBounds() {
		return new Rectangle(x, y, 32, 32);
	}
	
	
	private void collision() {
		
		for(int x=0; x<handler.object.size(); x++) {
			GameObject i = handler.object.get(x);
			if(getBounds().intersects(i.getBounds())) {
				if(i.getId() == ID.Coin) {
					int previousScore = gameInstance.getScore();
					gameInstance.setScore(gameInstance.getScore() + i.getDamage());
					handler.removeObject(i);
					x--;
					gameInstance.createCoin();
					if(gameInstance.getScore() / 10  > previousScore / 10) {
						int numOfLevelsUp = gameInstance.getScore() / 10  - previousScore / 10;
						for(int y=0; y<numOfLevelsUp; y++) {
							gameInstance.levelUp();
						}
						
					}
				}
				else if(i.getId() == ID.Player) {
					continue;
				}
				else {
					gameInstance.setHealth(gameInstance.getHealth() - i.getDamage());
					handler.removeObject(i);
					x--;
					gameInstance.createEnemy();
					
					if(gameInstance.getHealth() <= 0) {
						gameInstance.endGame();
					}
				}
			}
		}
	}
}
