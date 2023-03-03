package Swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.*;
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
import javax.swing.UIManager;
import javax.swing.JTextField;

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
	private JLabel currentHPLabel;
	private JLabel slashLabel;
	private JLabel maxHPLabel;
	private JLabel foeText;
	private JProgressBar foeHealthBar;
	public static Player me;
	private JTextField idInput;
	private JTextField levelInput;
	private JLabel currentText;
	private JButton party1;
	private JButton party2;
	private JButton party3;
	private JButton party4;
	private JButton party5;
	private JButton healButton;
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    foe = new Pokemon(10, 5);

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player.dat"))) {
	        me = (Player) ois.readObject();
	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        // If there's an error reading the file, create a new Player object
	        me = new Player();
	        me.catchPokemon(new Pokemon(11,70));
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

	public Battle() {
		// Initialize frame
		initialize();
		
		updateFoe();
		playerPanel.add(foeText);
		playerPanel.add(foeHealthBar);
		
		JButton catchButton = new JButton("CATCH!");
		catchButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		catchButton.setBounds(20, 171, 89, 23);
		playerPanel.add(catchButton);
		
		idInput = new JTextField();
		idInput.setBounds(31, 53, 27, 20);
		playerPanel.add(idInput);
		idInput.setColumns(3);
		
		levelInput = new JTextField();
		levelInput.setColumns(2);
		levelInput.setBounds(71, 53, 27, 20);
		playerPanel.add(levelInput);
		
		JButton fightButton = new JButton("FIGHT!");
		fightButton.setFont(new Font("Tahoma", Font.BOLD, 11));
		fightButton.setBounds(20, 80, 89, 23);
		playerPanel.add(fightButton);
		
		healButton = new JButton("HEAL");
		healButton.setFont(new Font("Tahoma", Font.BOLD, 9));
		healButton.setBounds(10, 267, 68, 23);
		playerPanel.add(healButton);
		
		healButton.addActionListener(e -> {
			for (Pokemon member : me.team) {
				member.heal();
			}
			updateCurrent();
			updateBars();
			displayParty();
			playerPanel.repaint();
        });
		
		fightButton.addActionListener(e -> {
			try {
				foe = new Pokemon(Integer.parseInt(idInput.getText()), Integer.parseInt(levelInput.getText()));
				updateFoe();
			} catch (NumberFormatException e1) {
				foe = new Pokemon (10, 5);
				updateFoe();
			}
        });
		party1 = new JButton();
		party2 = new JButton();
		party3 = new JButton();
		party4 = new JButton();
		party5 = new JButton();
		displayParty();
		
		party1.addActionListener(e -> {
			me.swap(me.team[1], 1);
			if (!me.team[1].isFainted()) foe.move(me.getCurrent(),foe.randomMove());
			updateCurrent();
			updateBars();
			displayParty();
        });
		
		party2.addActionListener(e -> {
			me.swap(me.team[2], 2);
			if (!me.team[2].isFainted()) foe.move(me.getCurrent(),foe.randomMove());
			updateCurrent();
			updateBars();
			displayParty();
        });
		
		party3.addActionListener(e -> {
			me.swap(me.team[3], 3);
			if (!me.team[3].isFainted()) foe.move(me.getCurrent(),foe.randomMove());
			updateCurrent();
			updateBars();
			displayParty();
        });
		
		party4.addActionListener(e -> {
			me.swap(me.team[4], 4);
			if (!me.team[4].isFainted()) foe.move(me.getCurrent(),foe.randomMove());
			updateCurrent();
			updateBars();
			displayParty();
        });
		
		party5.addActionListener(e -> {
			me.swap(me.team[5], 5);
			if (!me.team[5].isFainted()) foe.move(me.getCurrent(),foe.randomMove());
			updateCurrent();
			updateBars();
			displayParty();
        });
		
		
		catchButton.addActionListener(e -> {
			me.catchPokemon(new Pokemon(foe.id, foe.level));
        });
		
		System.out.println(me.toString());
	}
	
	private void initialize() {
		
		// Initializing panel
		setResizable(false);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 648, 330);
		playerPanel = new JPanel();
		playerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(playerPanel);
		playerPanel.setLayout(null);
		
		// Initializing current elements
		currentText = new JLabel("");
		
		move1 = new JGradientButton("");
		move2 = new JGradientButton("");
		move3 = new JGradientButton("");
		move4 = new JGradientButton("");
		
		// Set current elements
		updateCurrent();
		
		// Add current elements to panel
		playerPanel.add(currentText);
        move1.setBounds(121, 212, 89, 23);
		playerPanel.add(move1);
		
		move2.setBounds(220, 212, 89, 23);
		playerPanel.add(move2);
		
		move3.setBounds(121, 245, 89, 23);
		playerPanel.add(move3);
		
		move4.setBounds(220, 245, 89, 23);
		playerPanel.add(move4);
		
		// Add action listeners to buttons
        move1.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().move1,foe.randomMove());
        });

        move2.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().move2,foe.randomMove());
        });

        move3.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().move3,foe.randomMove());
        });

        move4.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().move4,foe.randomMove());
        });
        
        slashLabel = new JLabel("/");
		slashLabel.setHorizontalAlignment(SwingConstants.CENTER);
		slashLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		slashLabel.setBounds(206, 192, 21, 14);
		playerPanel.add(slashLabel);
		
		healthBar = new JProgressBar(0, me.getCurrent().getStat(0));
		healthBar.setBackground(UIManager.getColor("Button.darkShadow"));
		healthBar.setFont(new Font("Tahoma", Font.PLAIN, 11));
		healthBar.setValue(me.getCurrent().getCurrentHP());
		if (healthBar.getPercentComplete() > 0.5) {
			healthBar.setForeground(new Color(0, 255, 0));
		} else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
			healthBar.setForeground(new Color(255, 255, 0));
		} else {
			healthBar.setForeground(new Color(255, 0, 0));
		}
		
		healthBar.setBounds(143, 171, 146, 14);
		playerPanel.add(healthBar);
		
		progressBar = new JProgressBar(0, me.getCurrent().expMax);
		progressBar.setForeground(new Color(0, 128, 255));
		progressBar.setValue(me.getCurrent().exp);
		progressBar.setBounds(143, 186, 146, 7);
		playerPanel.add(progressBar);
		
		currentHPLabel = new JLabel(me.getCurrent().getCurrentHP() + "");
		currentHPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		currentHPLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		currentHPLabel.setBounds(163, 192, 46, 14);
		playerPanel.add(currentHPLabel);
		
		maxHPLabel = new JLabel(me.getCurrent().getStat(0) + "");
		maxHPLabel.setHorizontalAlignment(SwingConstants.CENTER);
		maxHPLabel.setFont(new Font("Tahoma", Font.PLAIN, 9));
		maxHPLabel.setBounds(223, 192, 46, 14);
		playerPanel.add(maxHPLabel);
		
		foeText = new JLabel("");
		foeText.setText(foe.getName() + "  lv " + foe.getLevel());
		foeHealthBar = new JProgressBar(0, 100);
	}

	private void updateCurrent() {
		currentText.setText(me.getCurrent().getName() + "  lv " + me.getCurrent().getLevel()); 
		currentText.setHorizontalAlignment(SwingConstants.CENTER);
		currentText.setBounds(133, 149, 164, 20);
		currentText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		setMoveButtons();
		playerPanel.repaint();
		
	}

	private void setMoveButtons() {
		if (me.getCurrent().move1 != null) {
        	move1.setText(me.getCurrent().move1.toString());
        	move1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move1.setBackground(getColor(me.getCurrent().move1));
        } else {
        	move1.setText("No Move");
        	move1.setVisible(false);
        }
        if (me.getCurrent().move2 != null) {
        	move2.setText(me.getCurrent().move2.toString());
        	move2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move2.setBackground(getColor(me.getCurrent().move2));
        } else {
        	move2.setText("No Move");
        	move2.setVisible(false);
        }
        if (me.getCurrent().move3 != null) {
        	move3.setText(me.getCurrent().move3.toString());
        	move3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move3.setBackground(getColor(me.getCurrent().move3));
        } else {
        	move3.setText("No Move");
        	move3.setVisible(false);
        }
        if (me.getCurrent().move4 != null) {
        	move4.setText(me.getCurrent().move4.toString());
        	move4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move4.setBackground(getColor(me.getCurrent().move4));
        } else {
        	move4.setText("No Move");
        	move4.setVisible(false);
        }
        
        playerPanel.repaint();
		
	}

	private void updateFoe() {
		// Foe text
		foeText.setText(foe.getName() + "  lv " + foe.getLevel());
		foeText.setBounds(133, 37, 164, 20);
		foeText.setHorizontalAlignment(SwingConstants.CENTER);
		foeText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		// Foe
		foeHealthBar.setMinimum(0);
		foeHealthBar.setMaximum(foe.getStat(0));
		foeHealthBar.setBackground(UIManager.getColor("Button.darkShadow"));
		foeHealthBar.setValue(foe.getCurrentHP());
		if (foeHealthBar.getPercentComplete() > 0.5) {
			foeHealthBar.setForeground(new Color(0, 255, 0));
		} else if (foeHealthBar.getPercentComplete() <= 0.5 && foeHealthBar.getPercentComplete() > 0.25) {
			foeHealthBar.setForeground(new Color(255, 255, 0));
		} else {
			foeHealthBar.setForeground(new Color(255, 0, 0));
		}
		foeHealthBar.setBounds(143, 59, 146, 14);
		
		playerPanel.repaint();
		
	}

	private void displayParty() {
		party1.setText("");
		if (me.team[1] != null && !me.team[1].isFainted()) {
			party1.setText(me.team[1].getName() + "  lv " + me.team[1].getLevel());
			party1.setHorizontalAlignment(SwingConstants.CENTER);
			party1.setFont(new Font("Tahoma", Font.PLAIN, 12));
			party1.setBounds(330, 21, 120, 30);
			playerPanel.add(party1);
			
		} else {
			party1.setVisible(false);
		}
		
		party2.setText("");
		if (me.team[2] != null && !me.team[2].isFainted()) {
			party2.setText(me.team[2].getName() + "  lv " + me.team[2].getLevel());
			party2.setHorizontalAlignment(SwingConstants.CENTER);
			party2.setFont(new Font("Tahoma", Font.PLAIN, 12));
			party2.setBounds(330, 66, 120, 30);
			playerPanel.add(party2);
			
		} else {
			party2.setVisible(false);
		}
		
		party3.setText("");
		if (me.team[3] != null && !me.team[3].isFainted()) {
			party3.setText(me.team[3].getName() + "  lv " + me.team[3].getLevel());
			party3.setHorizontalAlignment(SwingConstants.CENTER);
			party3.setFont(new Font("Tahoma", Font.PLAIN, 12));
			party3.setBounds(330, 111, 120, 30);
			playerPanel.add(party3);
			
		} else {
			party3.setVisible(false);
		}
		
		party4.setText("");
		if (me.team[4] != null && !me.team[4].isFainted()) {
			party4.setText(me.team[4].getName() + "  lv " + me.team[4].getLevel());
			party4.setHorizontalAlignment(SwingConstants.CENTER);
			party4.setFont(new Font("Tahoma", Font.PLAIN, 12));
			party4.setBounds(330, 156, 120, 30);
			playerPanel.add(party4);
			
		} else {
			party4.setVisible(false);
		}
		
		party5.setText("");
		if (me.team[5] != null && !me.team[5].isFainted()) {
			party5.setText(me.team[5].getName() + "  lv " + me.team[5].getLevel());
			party5.setHorizontalAlignment(SwingConstants.CENTER);
			party5.setFont(new Font("Tahoma", Font.PLAIN, 12));
			party5.setBounds(330, 201, 120, 30);
			playerPanel.add(party5);
			
		} else {
			party5.setVisible(false);
		}
		
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
		updateCurrent();
		
		
	}

	private void updateBars() {
		healthBar.setValue(me.getCurrent().getCurrentHP());
		maxHPLabel.setText(me.getCurrent().getStat(0) + "");
		healthBar.setMaximum(me.getCurrent().getStat(0));
		currentHPLabel.setText(me.getCurrent().getCurrentHP() + "");
		if (healthBar.getPercentComplete() > 0.5) {
			healthBar.setForeground(new Color(0, 255, 0));
		} else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
			healthBar.setForeground(new Color(255, 255, 0));
		} else {
			healthBar.setForeground(new Color(255, 0, 0));
		}
		
		progressBar.setValue(me.getCurrent().exp);
		
		foeHealthBar.setValue(foe.getCurrentHP());
		if (foeHealthBar.getPercentComplete() > 0.5) {
			foeHealthBar.setForeground(new Color(0, 255, 0));
		} else if (foeHealthBar.getPercentComplete() <= 0.5 && foeHealthBar.getPercentComplete() > 0.25) {
			foeHealthBar.setForeground(new Color(255, 255, 0));
		} else {
			foeHealthBar.setForeground(new Color(255, 0, 0));
		}
		playerPanel.repaint();
		
	}
	
	public Color getColor(Move m) {
		if (m.mtype == PType.NORMAL) {
			return new Color(168, 167, 122);
		} else if (m.mtype == PType.FIRE) {
			return new Color(238, 129, 48);
		} else if (m.mtype == PType.WATER) {
			return new Color(99, 144, 240);
		} else if (m.mtype == PType.ELECTRIC) {
			return new Color(247, 208, 44);
		} else if (m.mtype == PType.GRASS) {
			return new Color(122, 199, 76);
		} else if (m.mtype == PType.MAGIC) {
			return new Color(251, 6, 153);
		} else if (m.mtype == PType.FIGHTING) {
			return new Color(194, 46, 40);
		} else if (m.mtype == PType.POISON) {
			return new Color(163, 62, 161);
		} else if (m.mtype == PType.GROUND) {
			return new Color(226, 191, 101);
		} else if (m.mtype == PType.FLYING) {
			return new Color(169, 143, 243);
		} else if (m.mtype == PType.BUG) {
			return new Color(166, 185, 26);
		} else if (m.mtype == PType.ROCK) {
			return new Color(182, 161, 54);
		} else if (m.mtype == PType.GHOST) {
			return new Color(115, 87, 151);
		} else if (m.mtype == PType.DRAGON) {
			return new Color(111, 53, 252);
		} else if (m.mtype == PType.DARK) {
			return new Color(112, 87, 70);
		} else if (m.mtype == PType.STEEL) {
			return new Color(183, 183, 206);
		}
		return null;
	}
	
	private static final class JGradientButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 639680055516122456L;

		private JGradientButton(String text){
	        super(text);
	        setContentAreaFilled(false);
	    }

	    @Override
	    protected void paintComponent(Graphics g){
	        Graphics2D g2 = (Graphics2D)g.create();
	        g2.setPaint(new GradientPaint(
	                new Point(0, 0), 
	                getBackground(), 
	                new Point(0, getHeight()/3), 
	                Color.WHITE));
	        g2.fillRect(0, 0, getWidth(), getHeight()/3);
	        g2.setPaint(new GradientPaint(
	                new Point(0, getHeight()/3), 
	                Color.WHITE, 
	                new Point(0, getHeight()), 
	                getBackground()));
	        g2.fillRect(0, getHeight()/3, getWidth(), getHeight());
	        g2.dispose();

	        super.paintComponent(g);
	    }
	}
}
