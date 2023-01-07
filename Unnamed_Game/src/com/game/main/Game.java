package com.game.main;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferStrategy;

public class Game extends Canvas implements Runnable{
	

	private static final long serialVersionUID = -7764115714063645212L;
	public GameState gameState = GameState.Menu;
	
	public final int WIDTH = 800, HEIGHT = 600;
	private Thread thread;
	private boolean running = false;
	
	private Handler handler;
	private Menu mainMenu;
	private GameInstance gameInstance;
	private SettingsData settings = new SettingsData();
	
	public Game() {
		handler = new Handler();
		gameInstance = new GameInstance(this, handler, settings);
		mainMenu = new Menu(this, gameInstance, settings);

		this.addMouseListener(mainMenu);
		this.addKeyListener(new KeyInput(handler));
		
		new Window(WIDTH, HEIGHT, "test", this);
//		handler.addObject(new Player(100, 100, ID.Player));
	}
	
	public synchronized void start() {
		thread = new Thread(this);
		thread.start();
		running = true;
	}
	
	public synchronized void stop() {
		try{
		thread.join();
		running = false;
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}

	public static void main(String[] args) {
		new Game();
	}

	@Override
	public void run() {
		long lastTime = System.nanoTime();
		double amountOfTicks = 60.0;
		double ns = 1000000000 / amountOfTicks;
		double delta = 0;
		long timer = System.currentTimeMillis();
		int frames = 0;
		while (running) {
			long now = System.nanoTime();
			delta += (now - lastTime) / ns;
			lastTime = now;

			while (delta >= 1) {
				tick();
				delta--;
			}
			if (running) {
				render();
			}

			frames++;

			if (System.currentTimeMillis() - timer > 1000) {
				timer += 1000;
				//System.out.println("FPS: " + frames);
				frames = 0;
			}
		}
		stop();
	}

	private void tick() {
		
		if(gameState == GameState.Menu || gameState == GameState.Settings) {
			mainMenu.tick();
		}
		else if(gameState == GameState.Playing) {
			gameInstance.tick();
			handler.tick();
		}
	}
	
	private void render() {
		BufferStrategy bs = this.getBufferStrategy();
		if(bs == null) {
			this.createBufferStrategy(3);
			return;
		}
		
		Graphics g = bs.getDrawGraphics();
		
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		if(gameState == GameState.Menu || gameState == GameState.Settings) {
			mainMenu.render(g);
		}
		else if(gameState == GameState.Playing) {
			g.setColor(Color.black);
			g.fillRect(0, 0, WIDTH, HEIGHT);
			
			gameInstance.render(g);
			handler.render(g);
			
		}
		
		g.dispose();
		bs.show();
	}
	
}
