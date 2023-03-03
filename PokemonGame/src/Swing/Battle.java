package Swing;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;

import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import javax.swing.UIManager;

public class Battle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8943929896582623587L;
	private JPanel playerPanel;
	private JButton move1, move2, move3, move4;
	private static Pokemon foe;
	private JProgressBar healthBar;
	private JProgressBar progressBar;
	private JLabel lblNewLabel_1;
	private JLabel lblNewLabel_2;
	private JLabel lblNewLabel_3;
	private JLabel lblNewLabel_4;
	private JProgressBar progressBar_1;
	public static Player me;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    foe = new Pokemon(5, 5);

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player.dat"))) {
	        me = (Player) ois.readObject();
	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        // If there's an error reading the file, create a new Player object
	        me = new Player();
	        me.catchPokemon(new Pokemon(10,3));
	    }
	    
	    // Create the Battle instance and set the window listener to save on close
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                Battle frame = new Battle();
	                frame.addWindowListener(new WindowAdapter() {
	                    @Override
	                    public void windowClosing(WindowEvent e) {
	                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
	                            oos.writeObject(me);
	                            oos.close();
	                        } catch (IOException ex) {
	                            System.err.println("Error writing Player object to file: " + ex.getMessage());
	                        }
	                    }
	                });
	                frame.setVisible(true);
	            } catch (Exception e) {
	                e.printStackTrace();
	            }
	        }
	    });
	}

	/**
	 * Create the frame.
	 */
	public Battle() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 450, 300);
		playerPanel = new JPanel();
		playerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(playerPanel);
		playerPanel.setLayout(null);
		
		JLabel lblNewLabel = new JLabel(Player.getCurrent().getName() + "  lv " + Player.getCurrent().getLevel());
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setBounds(133, 140, 164, 20);
		lblNewLabel.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerPanel.add(lblNewLabel);
		
		if (Player.getCurrent().move1 != null) {
        	move1 = new JButton(Player.getCurrent().move1.toString());
        } else {
        	move1 = new JButton("No Move");
        	move1.setVisible(false);
        }
        if (Player.getCurrent().move2 != null) {
        	move2 = new JButton(Player.getCurrent().move2.toString());
        } else {
        	move2 = new JButton("No Move");
        	move2.setVisible(false);
        }
        if (Player.getCurrent().move3 != null) {
        	move3 = new JButton(Player.getCurrent().move3.toString());
        } else {
        	move3 = new JButton("No Move");
        	move3.setVisible(false);
        }
        if (Player.getCurrent().move4 != null) {
        	move4 = new JButton(Player.getCurrent().move4.toString());
        } else {
        	move4 = new JButton("No Move");
        	move4.setVisible(false);
        }
        
        move1.setBounds(22, 207, 89, 23);
		playerPanel.add(move1);
		
		move2.setBounds(121, 207, 89, 23);
		playerPanel.add(move2);
		
		move3.setBounds(220, 207, 89, 23);
		playerPanel.add(move3);
		
		move4.setBounds(319, 207, 89, 23);
		playerPanel.add(move4);

        // add action listeners to buttons
        move1.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move1,foe.randomMove());
        });

        move2.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move2,foe.randomMove());
        });

        move3.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move3,foe.randomMove());
        });

        move4.addActionListener(e -> {
        	turn(Player.getCurrent(),foe,Player.getCurrent().move4,foe.randomMove());
        });
		
		healthBar = new JProgressBar(0, Player.getCurrent().getStat(0));
		healthBar.setBackground(UIManager.getColor("Button.darkShadow"));
		healthBar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		healthBar.setValue(Player.getCurrent().getCurrentHP());
		if (healthBar.getPercentComplete() > 0.5) {
			healthBar.setForeground(new Color(0, 255, 0));
		} else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
			healthBar.setForeground(new Color(255, 255, 0));
		} else {
			healthBar.setForeground(new Color(255, 0, 0));
		}
		
		healthBar.setBounds(143, 171, 146, 14);
		playerPanel.add(healthBar);
		
		progressBar = new JProgressBar(0, Player.getCurrent().expMax);
		progressBar.setForeground(new Color(0, 128, 255));
		progressBar.setValue(Player.getCurrent().exp);
		progressBar.setBounds(143, 186, 146, 7);
		playerPanel.add(progressBar);
		
		lblNewLabel_1 = new JLabel(Player.getCurrent().getCurrentHP() + "");
		lblNewLabel_1.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_1.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_1.setBounds(162, 192, 46, 14);
		playerPanel.add(lblNewLabel_1);
		
		lblNewLabel_2 = new JLabel("/");
		lblNewLabel_2.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_2.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblNewLabel_2.setBounds(207, 192, 21, 14);
		playerPanel.add(lblNewLabel_2);
		
		lblNewLabel_3 = new JLabel(Player.getCurrent().getStat(0) + "");
		lblNewLabel_3.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_3.setFont(new Font("Tahoma", Font.PLAIN, 9));
		lblNewLabel_3.setBounds(225, 192, 46, 14);
		playerPanel.add(lblNewLabel_3);
		
		// Foe text
		lblNewLabel_4 = new JLabel(foe.getName() + "  lv " + foe.getLevel());
		lblNewLabel_4.setBounds(133, 37, 164, 20);
		lblNewLabel_4.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel_4.setFont(new Font("Tahoma", Font.PLAIN, 16));
		playerPanel.add(lblNewLabel_4);
		
		// Foe
		progressBar_1 = new JProgressBar(0, foe.getStat(0));
		progressBar_1.setBackground(UIManager.getColor("Button.darkShadow"));
		progressBar_1.setValue(foe.getCurrentHP());
		if (progressBar_1.getPercentComplete() > 0.5) {
			progressBar_1.setForeground(new Color(0, 255, 0));
		} else if (progressBar_1.getPercentComplete() <= 0.5 && progressBar_1.getPercentComplete() > 0.25) {
			progressBar_1.setForeground(new Color(255, 255, 0));
		} else {
			progressBar_1.setForeground(new Color(255, 0, 0));
		}
		progressBar_1.setBounds(143, 59, 146, 14);
		playerPanel.add(progressBar_1);
		
		JButton btnNewButton = new JButton("Catch Pokemon!");
		btnNewButton.setBounds(26, 124, 89, 23);
		playerPanel.add(btnNewButton);
		btnNewButton.addActionListener(e -> {
			me.catchPokemon(new Pokemon(14, 5));
        });
		
		
	}
	
	public void turn(Pokemon p1, Pokemon p2, Move m1, Move m2) {
		
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
		updateBars();
		
		
	}

	private void updateBars() {
		healthBar.setValue(Player.getCurrent().getCurrentHP());
		lblNewLabel_1.setText(Player.getCurrent().getCurrentHP() + "");
		if (healthBar.getPercentComplete() > 0.5) {
			healthBar.setForeground(new Color(0, 255, 0));
		} else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
			healthBar.setForeground(new Color(255, 255, 0));
		} else {
			healthBar.setForeground(new Color(255, 0, 0));
		}
		
		progressBar.setValue(Player.getCurrent().exp);
		
		progressBar_1.setValue(foe.getCurrentHP());
		if (progressBar_1.getPercentComplete() > 0.5) {
			progressBar_1.setForeground(new Color(0, 255, 0));
		} else if (progressBar_1.getPercentComplete() <= 0.5 && progressBar_1.getPercentComplete() > 0.25) {
			progressBar_1.setForeground(new Color(255, 255, 0));
		} else {
			progressBar_1.setForeground(new Color(255, 0, 0));
		}
		playerPanel.repaint();
		
	}
}
