package Swing;

import java.util.Random;

import javax.swing.*;

public class old extends JFrame {


	/**
	 * 
	 */
	private static final long serialVersionUID = 5213025326868043995L;
	private JPanel playerPanel;
	private JButton move1Button, move2Button, move3Button, move4Button;
	private static Pokemon foe;
	
	public old() {
        // Set up the main window
        setTitle("Battle");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        GroupLayout layout = new GroupLayout(playerPanel);
        
        // Create the player panel
        playerPanel = new JPanel();
        playerPanel.setBounds(0, 0, 1000, 1000);
        playerPanel.setLayout(layout);
        
        // Add the player's Monster name and health bar
        JLabel nameLabel = new JLabel(Player.getCurrent().getName());
        nameLabel.setBounds(200, 50, 100, 20);
        playerPanel.add(nameLabel, layout);
        
        JProgressBar healthBar = new JProgressBar(0, Player.getCurrent().getStat(0));
        healthBar.setValue(Player.getCurrent().getCurrentHP());
        healthBar.setStringPainted(true);
        healthBar.setBounds(200, 80, 150, 20);
        playerPanel.add(healthBar);
        
        // Add the panel to the main window
        add(playerPanel);
        
        // create buttons
        if (Player.getCurrent().move1 != null) {
        	move1Button = new JButton(Player.getCurrent().move1.toString());
        } else {
        	move1Button = new JButton("No Move");
        }
        if (Player.getCurrent().move2 != null) {
        	move2Button = new JButton(Player.getCurrent().move2.toString());
        } else {
        	move2Button = new JButton("No Move");
        }
        if (Player.getCurrent().move3 != null) {
        	move3Button = new JButton(Player.getCurrent().move3.toString());
        } else {
        	move3Button = new JButton("No Move");
        }
        if (Player.getCurrent().move4 != null) {
        	move4Button = new JButton(Player.getCurrent().move4.toString());
        } else {
        	move4Button = new JButton("No Move");
        }

        // add action listeners to buttons
        move1Button.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move1,foe.randomMove());
        });

        move2Button.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move2,foe.randomMove());
        });

        move3Button.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move3,foe.randomMove());
        });

        move4Button.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move4,foe.randomMove());
        });

        // add buttons to panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(move1Button);
        buttonPanel.add(move2Button);
        buttonPanel.add(move3Button);
        buttonPanel.add(move4Button);

        // add panel to frame
        this.getContentPane().add(buttonPanel);

        // set frame size and visibility
        this.setSize(1000, 500);
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        // Show the main window
        setVisible(true);
        
        
    }

	public static void main(String[] args) {
		Player me = new Player();
		me.catchPokemon(new Pokemon(4,5));
		foe = new Pokemon(1,5);
		new Battle();
		
	}
	
	public static void turn(Pokemon p1, Pokemon p2, Move m1, Move m2) {
		
		if (p1.getStat(5) > p2.getStat(5)) {
			p1.move(p2, m1);
			p2.move(p1, m2);
		} else if (p1.getStat(5) < p2.getStat(5)) {
			p2.move(p1, m2);
			p1.move(p2, m1);
		} else {
			Random speedTie = new Random();
			double random = speedTie.nextDouble();
			if (random < 0.5) {
				p1.move(p2, m1);
				p2.move(p1, m2);
			} else {
				p2.move(p1, m2);
				p1.move(p2, m1);
			}
		}
	}
}
