package Overworld;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

import javax.swing.*;

import Swing.Player;
import Swing.Pokemon;

public class Main {

	public static void main(String[] args) {

		JFrame window = new JFrame();
		window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		window.setResizable(false);
		window.setTitle("Pokemon Game");
		
		GamePanel gamePanel = new GamePanel();
		try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player.dat"))) {
	        gamePanel.player.p = (Player) ois.readObject();
	        for (Pokemon p : gamePanel.player.p.team) {
	            if (p != null) p.clearVolatile();
	        }
	        gamePanel.player.worldX = gamePanel.player.p.getPosX();
	        gamePanel.player.worldY = gamePanel.player.p.getPosY();
	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        // If there's an error reading the file, create a new Player object
	        gamePanel.player.p = new Player();
	        gamePanel.player.p.catchPokemon(new Pokemon(-4,5, true, false));
	    }
		window.add(gamePanel);
		
		window.pack();
		
		window.setLocationRelativeTo(null);
		window.setVisible(true);
		
		gamePanel.startGameThread();
		
		window.addWindowListener(new WindowAdapter() {
            @Override // implementation
            public void windowClosing(WindowEvent e) {
                try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
                	gamePanel.player.p.setPosX(gamePanel.player.worldX);
                	gamePanel.player.p.setPosY(gamePanel.player.worldY);
                    oos.writeObject(gamePanel.player.p);
                    oos.close();
                } catch (IOException ex) {
                    System.err.println("Error writing object to file: " + ex.getMessage());
                }
            }
        });

	}

}
