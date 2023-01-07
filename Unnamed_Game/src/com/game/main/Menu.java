package com.game.main;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseListener;


public class Menu implements MouseListener{
	
	private Game game;
	private GameInstance gameInstance;
	private SettingsData settings;
	
	public Menu(Game game, GameInstance gameInstance, SettingsData settings) {
		this.game = game;
		this.gameInstance = gameInstance;
		this.settings = settings;
		
	}

	@Override
	public void mouseClicked(java.awt.event.MouseEvent e) {
		int mx = e.getX();
		int my = e.getY();
		if(game.gameState == GameState.Menu) {
			if(mouseOver(mx, my, 275, 150, 250, 64)) { // play
				game.gameState = GameState.Playing;
				gameInstance.startGame();
			} 
			else if(mouseOver(mx, my, 275, 250, 250, 64)) { // settings
				game.gameState = GameState.Settings;
			}
			else if(mouseOver(mx, my, 275, 350, 250, 64)) { // quit
				System.exit(0);
			}
		}
		else if(game.gameState == GameState.Settings) {
			if(mouseOver(mx, my, 0, 0, 64, 64)) { // back
				game.gameState = GameState.Menu;
			}
			else if(mouseOver(mx, my, 275, 300, 50, 50)) {
				settings.setPlayerColour(Color.white);
			}
			else if(mouseOver(mx, my, 375, 300, 50, 50)) {
				settings.setPlayerColour(Color.blue);
			}
			else if(mouseOver(mx, my, 475, 300, 50, 50)) {
				settings.setPlayerColour(Color.orange);
			}
		}
	}

	@Override
	public void mousePressed(java.awt.event.MouseEvent e) {
		return;
	}

	@Override
	public void mouseReleased(java.awt.event.MouseEvent e) {
		return;
	}

	@Override
	public void mouseEntered(java.awt.event.MouseEvent e) {
		return;
	}

	@Override
	public void mouseExited(java.awt.event.MouseEvent e) {
		return;
	}
	
	private boolean mouseOver(int mx, int my, int x, int y, int width, int height) {
		if(mx > x && mx < x + width) {
			if(my > y && my < y + height) {
				return true;
			}else return false;
		}else return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		Font titleFont = new Font("arial", 1, 50);
		Font smallFont = new Font("arial", 1, 20);
		g.setFont(titleFont);
		if(game.gameState == GameState.Menu) {
			g.setColor(Color.white);
			g.drawString("MAIN MENU", 280, 50);
			
			if(gameInstance.getScore() != 0) {
				g.setFont(smallFont);
				g.drawString("Your score was " + gameInstance.getScore(), 300, 75);
			}
			
			g.setFont(titleFont);
			g.drawRect(275, 150, 250, 64);
			g.drawString("Play", 350, 200);
			
			g.drawRect(275, 250, 250, 64);
			g.drawString("Help", 350, 300);
			
			g.drawRect(275, 350, 250, 64);
			g.drawString("Quit", 350, 400);
		}
		else if(game.gameState == GameState.Settings) {
			g.setColor(Color.white);
			g.drawString("SETTINGS", 300, 50);
			
			g.drawRect(0, 0, 64, 64);
			g.drawString("<-", 10, 50);
			
			g.setFont(smallFont);
			g.drawString("Use WASD to move and don't die - simple as", 200, 200);
			g.drawString("Change character colour", 300, 250);
			
			g.fillRect(275, 300, 50, 50);
			g.setColor(Color.blue);
			g.fillRect(375, 300, 50, 50);
			g.setColor(Color.orange);
			g.fillRect(475, 300, 50, 50);
		}
		
	}
}
