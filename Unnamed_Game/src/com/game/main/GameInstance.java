package com.game.main;

import java.awt.Color;
import java.awt.Graphics;
import java.util.Random;

public class GameInstance {
	
	private Game game;
	private Handler handler;
	private SettingsData settings;
	private Random random = new Random();
	
	//player settings
	//to be added
	
	//game data
	private final float maxHealth = 200;
	private float health = maxHealth;
	private int level = 1;
	private int score = 0;
	
	GameInstance(Game game, Handler handler, SettingsData settings){
		this.game = game;
		this.handler = handler;
		this.settings = settings;
	}
	
	private void healthBar(Graphics g) {
		g.setColor(Color.red);
		g.fillRect(0, 0, game.WIDTH, 10);
		
		g.setColor(Color.green);
		int percentHP = (int) ((health / maxHealth) * game.WIDTH);
		g.fillRect(0, 0, percentHP, 10);
	}
	
	public void createCoin() {
		int randCoin = random.nextInt(level) + 1;
		if(randCoin >= 5 && randCoin % 5 == 0) {
			handler.addObject(new SpecialCoin(random.nextInt(game.WIDTH - 25), random.nextInt(game.HEIGHT - 52) + 12, ID.Coin));
		}
		else {
		handler.addObject(new Coin(random.nextInt(game.WIDTH - 25), random.nextInt(game.HEIGHT - 52) + 12, ID.Coin));
		}
	}

	public void createEnemy() {
		int enemyChoice = random.nextInt(level) + 1;
		if(enemyChoice <= 3) {
			handler.addObject(new BasicEnemy(-32 , random.nextInt(game.HEIGHT - 76) + 12, ID.BasicEnemy, handler, this));
		}
		else if(enemyChoice <= 6) {
			GameObject obj = new BasicEnemy(random.nextInt(game.WIDTH - 76) + 12, -40, ID.BasicEnemy, handler, this);
			obj.setDx(0);
			obj.setDy(8);
			handler.addObject(obj);
		}
		else if(enemyChoice <= 10) {
			GameObject obj = new BasicEnemy(random.nextInt(game.WIDTH - 76) + 12, -40, ID.BasicEnemy, handler, this);
			obj.setDx(14);
			handler.addObject(obj);
		}
		else {
			GameObject obj = new BasicEnemy(-32 , random.nextInt(game.HEIGHT - 76) + 12, ID.BasicEnemy, handler, this);
			obj.setDx(10);
			handler.addObject(obj);
		}
	}
	
	public void levelUp() {
		level++;
		createEnemy();
	}
	
	public void startGame() {
		handler.addObject(new Player(100, 100, ID.Player, handler, this, settings));
		health = maxHealth;
		level = 1;
		score = 0;
		createCoin();
		createEnemy();
		createEnemy();
		createEnemy();
	}
	
	public void endGame() {
		System.out.println("dead");
		for(int i=0; i<handler.object.size(); i++) {
			GameObject item = handler.object.get(i);
			handler.removeObject(item);
			i--;
		}
		game.gameState = GameState.Menu;
	}
	
	public boolean playerOffScreen(int x, int y, int sizeX, int sizeY) {
		if(x + sizeX >= game.WIDTH || x < 0) {
			return true;
		}
		else if(y + (2 * sizeY) >= game.HEIGHT || y < 15) {
			return true;
		}
		
		return false;
	}
	
	public boolean enemyOffScreen(int x, int y, int sizeX, int sizeY) {
		if(x - sizeX >= game.WIDTH) {
			return true;
		}
		else if(y - sizeY >= game.HEIGHT) {
			return true;
		}
		
		return false;
	}
	
	public void tick() {
		
	}
	
	public void render(Graphics g) {
		healthBar(g);
		
		g.setColor(Color.white);
		g.drawString("Level: " + level, 0, 25);
		g.drawString("Score: " + score, 0, 40);
	}

	public float getHealth() {
		return health;
	}

	public void setHealth(float health) {
		this.health = health;
	}

	public int getLevel() {
		return level;
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}
	
	
}
