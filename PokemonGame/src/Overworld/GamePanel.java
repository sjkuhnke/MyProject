package Overworld;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;

import javax.swing.JPanel;

import Entity.Entity;
import Entity.PlayerCharacter;
import tile.TileManager;
import Swing.Battle;
import Swing.Battle.BattleCloseListener;

public class GamePanel extends JPanel implements Runnable, BattleCloseListener {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1898610723315381969L;
	
	// SETTINGS
	final int originalTileSize = 16;
	final int scale = 3;
	
	public final int tileSize = originalTileSize * scale;
	public final int maxScreenCol = 16;
	public final int maxScreenRow = 12;
	public final int screenWidth = tileSize * maxScreenCol;
	public final int screenHeight = tileSize * maxScreenRow;
	
	public final int maxWorldCol = 50;
	public final int maxWorldRow = 50;
	public final int worldWidth = tileSize * maxWorldCol;
	public final int worldHeight = tileSize * maxWorldRow;
	
	KeyHandler keyH = new KeyHandler();
	public AssetSetter aSetter = new AssetSetter(this);
	Thread gameThread;
	public CollisionChecker cChecker = new CollisionChecker(this);
	public PlayerCharacter player = new PlayerCharacter(this,keyH);
	public Entity npc[] = new Entity[10];
	
	TileManager tileM = new TileManager(this);
	
	int FPS = 60;
	private volatile boolean inBattle;
	
	public GamePanel() {
		this.setPreferredSize(new Dimension(screenWidth, screenHeight));
		this.setBackground(Color.black);
		this.setDoubleBuffered(true);
		this.addKeyListener(keyH);
		this.setFocusable(true);
	}
	
	public void startGameThread() {
		gameThread = new Thread(this);
		gameThread.start();
	}

	@Override
	public void run() {
		
		double drawInterval = 1000000000/FPS;
		double nextDrawTime = System.nanoTime() + drawInterval;
		
		while (gameThread != null) {
			if (!inBattle) {
				update();
				
				repaint();
				
				//System.out.println(player.worldX / tileSize + "," + player.worldY / tileSize);
				
				try {
					double remainingTime = nextDrawTime - System.nanoTime();
					remainingTime /= 1000000;
					
					if (remainingTime < 0) remainingTime = 0;
					
					Thread.sleep((long)remainingTime);
					
					nextDrawTime += drawInterval;
					
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				
			}
		}
	}
	
	public void update() {
		player.update();
		
	}
	public void paintComponent(Graphics g) {
		super.paintComponent(g);
		
		Graphics2D g2 = (Graphics2D)g;
		tileM.draw(g2);
		
		for (int i = 0; i < npc.length; i++) {
			if (npc[i] != null) npc[i].draw(g2);
		}
		
		player.draw(g2);
		
		g2.dispose();
	}
	
	public void startBattle(int trainer) {
	    // Create the Battle instance and set the window listener to save on close
		if (trainer == -1) return;
		if (player.trainersBeat[trainer]) return;
		inBattle = true;
		keyH.pause();
		
		Battle frame = new Battle(player, Main.trainers[trainer], trainer);
        frame.setBattleCloseListener(this);
        frame.setVisible(true);
	}

	@Override
	public void onBattleClosed(int trainer) {
		inBattle = false;
		player.trainersBeat[trainer] = true;
		keyH.resume();
	}

	public void setupGame() {
		aSetter.setNPC();
		
	}


}
