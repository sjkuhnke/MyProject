package Swing;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Random;
import java.util.Scanner;

import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import javax.swing.JTextField;
import javax.swing.JRadioButton;

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
	
	private JButton catchButton;
	private JButton addButton;
	private JButton fightButton;
	private JButton healButton;
	private JLabel userStatus;
	private JLabel foeStatus;
	private JButton boxButton;
	private JGradientButton[] boxButtons;
	private JButton returnButton;
	
	private static Scanner stdIn;
	private static Scanner stdIn2;
	private JRadioButton pokeballButton;
	private JRadioButton greatballButton;
	private JRadioButton ultraballButton;
	private JGradientButton addMoney;
	private JGradientButton buyPoke;
	private JGradientButton buyGreat;
	private JGradientButton buyUltra;
	private JLabel moneyLabel;
	private JLabel[] bag;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
	    foe = new Pokemon(10, 5, false);
	    foe.currentHP = 0;
	    foe.faint(false);

	    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream("player.dat"))) {
	        me = (Player) ois.readObject();
	        for (Pokemon p : me.team) {
	        	if (p != null) p.clearVolatile();
	        }
	        ois.close();
	    } catch (IOException | ClassNotFoundException e) {
	        // If there's an error reading the file, create a new Player object
	        me = new Player();
	        me.catchPokemon(new Pokemon(4,5, true));
	    }
	    
	    
	    // Create the Battle instance and set the window listener to save on close
	    EventQueue.invokeLater(new Runnable() {
	        public void run() {
	            try {
	                Battle frame = new Battle();
	                frame.addWindowListener(new WindowAdapter() {
	                    @Override // implementation
	                    public void windowClosing(WindowEvent e) {
	                        try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
	                            oos.writeObject(me);
	                            stdIn.close();
	                            stdIn2.close();
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
		
		addButton = createJButton("ADD", new Font("Tahoma", Font.BOLD, 9), 10, 231, 75, 23);
		fightButton = createJButton("FIGHT", new Font("Tahoma", Font.BOLD, 11), 20, 80, 89, 23);
		catchButton = createJButton("CATCH", new Font("Tahoma", Font.BOLD, 11), 20, 120, 89, 23);
		healButton = createJButton("HEAL", new Font("Tahoma", Font.BOLD, 9), 10, 262, 75, 23);
		boxButton = createJButton("Box", new Font("Tahoma", Font.PLAIN, 12), 553, 35, 62, 21);
		returnButton = createJButton("Exit", new Font("Tahoma", Font.BOLD, 12), 553, 35, 62, 21);
		returnButton.setVisible(false);
		
		addMoney = new JGradientButton("Win ($100)");
		addMoney.setBounds(30, 410, 100, 50);
		addMoney.setFont(new Font("Tahoma", Font.BOLD, 9));
		addMoney.setBackground(Color.GREEN);
		
		addMoney.addActionListener(e -> {
			me.money += 100;
			JOptionPane.showMessageDialog(null, "Won $100! Current Balance: $" + me.money);
			moneyLabel.setText("$" + me.money);
		});
		
		bag = new JLabel[9];
		buyPoke = new JGradientButton("Buy Pokeballs");
		buyPoke.setBounds(155, 410, 100, 50);
		buyPoke.setFont(new Font("Tahoma", Font.PLAIN, 9));
		buyPoke.setBackground(new Color(199, 6, 32));
		bag[6] = new JLabel("10 for $100");
		bag[6].setBounds(155, 462, 100, 21);
		bag[6].setHorizontalAlignment(SwingConstants.CENTER);
		bag[6].setFont(new Font("Tahoma", Font.BOLD, 12));
		bag[6].setForeground(new Color(79, 2, 2));
		
		buyGreat = new JGradientButton("Buy Great Balls");
		buyGreat.setBounds(280, 410, 100, 50);
		buyGreat.setFont(new Font("Tahoma", Font.PLAIN, 9));
		buyGreat.setBackground(new Color(6, 16, 199));
		bag[7] = new JLabel("5 for $200");
		bag[7].setBounds(280, 462, 100, 21);
		bag[7].setHorizontalAlignment(SwingConstants.CENTER);
		bag[7].setFont(new Font("Tahoma", Font.BOLD, 12));
		bag[7].setForeground(new Color(2, 4, 79));
		
		buyUltra = new JGradientButton("Buy Ultra Balls");
		buyUltra.setBounds(405, 410, 100, 50);
		buyUltra.setFont(new Font("Tahoma", Font.PLAIN, 9));
		buyUltra.setBackground(new Color(199, 192, 6));
		bag[8] = new JLabel("5 for $500");
		bag[8].setBounds(405, 462, 100, 21);
		bag[8].setHorizontalAlignment(SwingConstants.CENTER);
		bag[8].setFont(new Font("Tahoma", Font.BOLD, 12));
		bag[8].setForeground(new Color(92, 97, 6));
		
		moneyLabel = new JLabel("$" + me.money);
		moneyLabel.setHorizontalAlignment(SwingConstants.LEFT);
		moneyLabel.setBounds(553, 10, 100, 21);
		moneyLabel.setFont(new Font("Tahoma", Font.BOLD, 12));
		
		bag[0] = new JLabel("Pokeballs:");
		bag[0].setBounds(553, 60, 60, 21);
		bag[0].setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		bag[1] = new JLabel(me.pokeballCount + "");
		bag[1].setBounds(553, 80, 40, 21);
		bag[1].setFont(new Font("Tahoma", Font.BOLD, 15));
		bag[1].setForeground(new Color(199, 6, 32));
		
		bag[2] = new JLabel("Great Balls:");
		bag[2].setBounds(553, 105, 60, 21);
		bag[2].setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		bag[3] = new JLabel(me.greatballCount + "");
		bag[3].setBounds(553, 125, 40, 21);
		bag[3].setFont(new Font("Tahoma", Font.BOLD, 15));
		bag[3].setForeground(new Color(6, 16, 199));
		
		bag[4] = new JLabel("Ultra Balls:");
		bag[4].setBounds(553, 150, 60, 21);
		bag[4].setFont(new Font("Tahoma", Font.PLAIN, 11));
		
		bag[5] = new JLabel(me.ultraballCount + "");
		bag[5].setBounds(553, 170, 40, 21);
		bag[5].setFont(new Font("Tahoma", Font.BOLD, 15));
		bag[5].setForeground(new Color(199, 192, 6));
		
		buyPoke.addActionListener(e -> {
			if (me.money >= 100) {
				me.pokeballCount += 10;
				me.money -= 100;
				JOptionPane.showMessageDialog(null, "Bought 10 Pokeballs for $100! You have $" + me.money + " remaining");
				moneyLabel.setText("$" + me.money);
				bag[1].setText(me.pokeballCount + "");
			} else {
				JOptionPane.showMessageDialog(null, "Not enough money!");
			}
		});
		
		buyGreat.addActionListener(e -> {
			if (me.money >= 200) {
				me.greatballCount += 5;
				me.money -= 200;
				JOptionPane.showMessageDialog(null, "Bought 5 Great Balls for $200! You have $" + me.money + " remaining");
				moneyLabel.setText("$" + me.money);
				bag[3].setText(me.greatballCount + "");
			} else {
				JOptionPane.showMessageDialog(null, "Not enough money!");
			}
		});
		
		buyUltra.addActionListener(e -> {
			if (me.money >= 500) {
				me.ultraballCount += 5;
				me.money -= 500;
				JOptionPane.showMessageDialog(null, "Bought 5 Ultra Balls for $500! You have $" + me.money + " remaining");
				moneyLabel.setText("$" + me.money);
				bag[5].setText(me.ultraballCount + "");
			} else {
				JOptionPane.showMessageDialog(null, "Not enough money!");
			}
		});
		
		
		boxButtons = new JGradientButton[30];
		for (int i = 0; i < boxButtons.length; i++) {
			final int index = i;
			boxButtons[i] = new JGradientButton("");
			boxButtons[i].setBounds(10 + (i % 6) * 90, 10 + (i / 6) * 80, 80, 50);
			boxButtons[i].setVisible(false);
			playerPanel.add(boxButtons[i]);
			boxButtons[i].addActionListener(new ActionListener() {
		        @Override
		        public void actionPerformed(ActionEvent e) {
	                // Display the details of the selected box member
	                // and provide the option to swap with a party member
	                JPanel boxMemberPanel = new JPanel();
	                boxMemberPanel.setLayout(new BoxLayout(boxMemberPanel, BoxLayout.Y_AXIS));
	                JLabel nameLabel = new JLabel("Name: N/A");
	                if (me.box[index] != null) nameLabel.setText("Name: " + me.box[index].getName());
	                JLabel levelLabel = new JLabel("Level: N/A");
	                if (me.box[index] != null) levelLabel.setText("Level: " + me.box[index].getLevel());
	                JLabel statsLabel = new JLabel("Stats: N/A");
	                if (me.box[index] != null) statsLabel.setText("Stats: " + intArrayToString(me.box[index].stats));
	                JLabel movesLabel = new JLabel("Moves: N/A");
	                if (me.box[index] != null) movesLabel.setText("Moves: " + movesToString(me.box[index]));
	                JButton swapButton = new JButton("Swap with a party member");
	                JButton moveButton = new JButton("Teach Move");
	                JButton releaseButton = new JButton("Release");
	                swapButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        // Open a new panel to display the party and allow the user to select a party member to swap with
	                        JPanel partyPanel = new JPanel();
	                        partyPanel.setLayout(new BoxLayout(partyPanel, BoxLayout.Y_AXIS));
	                        JLabel titleLabel = new JLabel("Select a party member to swap with:");
	                        partyPanel.add(titleLabel);
	                        boolean oneVisible = false;
	                        for (int j = 0; j < me.team.length; j++) {
	                            final int jndex = j;
	                            JButton partyButton = new JGradientButton("EMPTY");
	                            partyButton.setVisible(false);
	                            if (me.team[j] != null) {
	                                partyButton.setText(me.team[j].getName() + "  lv " + me.team[j].getLevel());
	                                if (me.team[j].isFainted()) {
	                                    partyButton.setBackground(Color.RED);
	                                } else if (me.team[j].getStatus() != Status.HEALTHY) {
	                                    partyButton.setBackground(Color.YELLOW);
	                                } else {
	                                    partyButton.setBackground(Color.GREEN);
	                                }
	                                partyButton.setVisible(true);
	                            } else {
	                                if (!oneVisible) {
	                                    partyButton.setVisible(true);
	                                    oneVisible = true;
	                                }
	                            }
	                            partyButton.addActionListener(new ActionListener() {
	                                @Override
	                                public void actionPerformed(ActionEvent e) {

	                                    // Swap the selected party member with the selected box member
	                                    if (jndex == 0) {
	                                    	if (me.box[index] == null) {
	                                    		JOptionPane.showMessageDialog(null, "You cannot remove the lead from your party.");
	            	                            return;
	                                    	}
	                                        me.current = me.box[index];
	                                    }
	                                    Pokemon temp = me.team[jndex];
	                                    if (temp != null) {
	                                        temp.heal();
	                                    }
	                                    me.team[jndex] = me.box[index];
	                                    me.box[index] = temp;

	                                    // Update the display
	                                    SwingUtilities.getWindowAncestor(partyPanel).dispose();
	                                    SwingUtilities.getWindowAncestor(boxMemberPanel).dispose();
	                                    displayBox();
	                                }

	                            });
	                            partyPanel.add(partyButton);
	                        }
	                        JScrollPane scrollPane = new JScrollPane(partyPanel);
	                        scrollPane.setPreferredSize(new Dimension(300, 200));
	                        JOptionPane.showMessageDialog(null, scrollPane, "Swap with a party member", JOptionPane.PLAIN_MESSAGE);
	                    }
	                });
	                moveButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        // Prompt the player to enter the name of the move they want to teach
	                        String moveName = JOptionPane.showInputDialog(null, "Enter the name of the move you want to teach:");

	                        // Find the move in the move database
	                        Move move = Move.getMove(moveName);
	                        if (move == null) {
	                            JOptionPane.showMessageDialog(null, "Invalid move name.");
	                            return;
	                        }
	                        for (int l = 0; l < me.box[index].moveset.length; l++) {
	                        	if (move == me.box[index].moveset[l]) {
	                        		JOptionPane.showMessageDialog(null, me.box[index].name + " already knows " + move.toString() + "!");
		                            return;
	                        	}
	                        }

	                     // Add a boolean flag to keep track of whether an empty move slot has been found
	                        boolean foundEmptySlot = false;

	                        // Display the moveset and allow the player to select a move to replace
	                        JPanel moveset = new JPanel();
	                        moveset.setLayout(new BoxLayout(moveset, BoxLayout.Y_AXIS));
	                        JLabel titleLabel = new JLabel("Select a move to override:");
	                        moveset.add(titleLabel);
	                        for (int j = 0; j < me.box[index].moveset.length; j++) {
	                            JButton moveslot;
	                            if (me.box[index].moveset[j] != null) {
	                                moveslot = new JGradientButton(me.box[index].moveset[j].toString());
	                                moveslot.setBackground(me.box[index].moveset[j].mtype.getColor());
	                            } else {
	                                // Only display the first empty move slot
	                                if (!foundEmptySlot) {
	                                    moveslot = new JGradientButton("EMPTY");
	                                    foundEmptySlot = true;
	                                } else {
	                                    // Don't display any more empty move slots
	                                    continue;
	                                }
	                            }
	                            moveslot.setVisible(true);

	                            int moveIndex = j;
	                            moveslot.addActionListener(new ActionListener() {
	                                @Override
	                                public void actionPerformed(ActionEvent e) {
	                                    // Replace the selected move with the new move
	                                    me.box[index].moveset[moveIndex] = move;

	                                    // Update the display
	                                    SwingUtilities.getWindowAncestor(moveset).dispose();
	                                    SwingUtilities.getWindowAncestor(boxMemberPanel).dispose();
	                                    displayBox();
	                                }
	                            });
	                            moveset.add(moveslot);
	                        }
	                        JScrollPane scrollPane = new JScrollPane(moveset);
	                        scrollPane.setPreferredSize(new Dimension(300, 200));
	                        JOptionPane.showMessageDialog(null, scrollPane, "Override move", JOptionPane.PLAIN_MESSAGE);

	                    }
	                });


	                releaseButton.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                        // code to release the box member
	                        me.box[index] = null;
	                        boxButtons[index].setText("");
	                        boxButtons[index].setBackground(null);
	                        boxMemberPanel.setVisible(false);
	                        SwingUtilities.getWindowAncestor(boxMemberPanel).dispose();
	                    }
	                });
	                boxMemberPanel.add(nameLabel);
	                boxMemberPanel.add(levelLabel);
	                boxMemberPanel.add(statsLabel);
	                boxMemberPanel.add(movesLabel);
	                boxMemberPanel.add(swapButton);
	                boxMemberPanel.add(moveButton);
	                boxMemberPanel.add(releaseButton);
	                JOptionPane.showMessageDialog(null, boxMemberPanel, "Box member details", JOptionPane.PLAIN_MESSAGE);
		        }
		    });
		}
		
		idInput = createJTextField(3, 31, 53, 27, 20);
		levelInput = createJTextField(2, 71, 53, 27, 20);
		
		addButton.addActionListener(e -> {
			if (!foe.isFainted()) {
				me.catchPokemon(new Pokemon(foe.id, foe.getLevel(), true));
				foe.currentHP = 0;
				foe.faint(false);
				displayParty();
				updateFoe();
				boxButton.setVisible(true);
			}
        });
		
		catchButton.addActionListener(e -> {
		    if (!foe.isFainted()) {
		        Random rand = new Random();
		        double catchRate = 0;
		        double statusBonus = 1;
		        
		        if (foe.id == 103 || foe.id == 104 || foe.id == 105 || foe.id == 91 || foe.id == 133 || foe.id == 134 ||foe.id == 135 || foe.id == 136 || foe.id == 137 ||foe.id == 138 || foe.id == 139 || foe.id == 140) statusBonus = 0.25; // if legendary
		        
		        if (pokeballButton.isSelected()) {
		        	if (me.pokeballCount <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 12.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 0.25;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 8.0;
		            }
		            if (foe.getStatus() != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed a Pokeball!");
		            me.pokeballCount--;
		        } else if (greatballButton.isSelected()) {
		        	if (me.greatballCount <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 10.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 0.5;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 6.0;
		            }
		            if (foe.getStatus() != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed a Great Ball!");
		            me.greatballCount--;
		        } else if (ultraballButton.isSelected()) {
		        	if (me.ultraballCount <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 6.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 1.0;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 3.0;
		            }
		            if (foe.getStatus() != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed an Ultra Ball!");
		            me.ultraballCount--;
		        }
		        
		        double randomValue = rand.nextDouble();
		        double modifiedCatchRate = catchRate * statusBonus;
		        if (randomValue <= modifiedCatchRate) {
		            me.catchPokemon(new Pokemon(foe.id, foe.getLevel(), true));
		            foe.currentHP = 0;
					foe.faint(false);
					displayParty();
					updateFoe();
					boxButton.setVisible(true);
		        } else {
		            System.out.println("Oh no! " + foe.name + " broke free!");
		            foe.move(me.getCurrent(),foe.randomMove());
					Pokemon.endOfTurn(foe, me.getCurrent());
					Pokemon.endOfTurn(me.getCurrent(), foe);
					updateCurrent();
					updateBars();
					displayParty();
					updateStatus();
		        }
		        System.out.println();
			    System.out.println("------------------------------");
		    }
		});
		
		fightButton.addActionListener(e -> {
			try {
				foe = new Pokemon(Integer.parseInt(idInput.getText()), Integer.parseInt(levelInput.getText()), false);
				updateFoe();
				boxButton.setVisible(false);
			} catch (NumberFormatException e1) {
				foe = new Pokemon (10, 5, false);
				updateFoe();
				boxButton.setVisible(false);
			}
        });
		
		healButton.addActionListener(e -> {
			System.out.println();
			for (Pokemon member : me.team) {
				if (member != null) member.heal();
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			playerPanel.repaint();
			System.out.println();
			System.out.println("------------------------------");
        });
		
		boxButton.addActionListener(e -> {
			Component[] components = getContentPane().getComponents();
			for (Component component : components) {
			    component.setVisible(false);
			}
			displayBox();
		});
		
		returnButton.addActionListener(e -> {
			Component[] components = getContentPane().getComponents();
			for (Component component : components) {
			    component.setVisible(false);
			}
			initialize();
			updateCurrent();
			updateFoe();
			updateBars();
			displayParty();
			updateStatus();
			playerPanel.add(catchButton);
			catchButton.setVisible(true);
			playerPanel.add(addButton);
			addButton.setVisible(true);
			playerPanel.add(healButton);
			healButton.setVisible(true);
			playerPanel.add(fightButton);
			fightButton.setVisible(true);
			playerPanel.add(idInput);
			idInput.setVisible(true);
			playerPanel.add(levelInput);
			levelInput.setVisible(true);
			playerPanel.add(boxButton);
			boxButton.setVisible(true);
			JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(playerPanel);
			frame.setBounds(100, 100, 648, 330);
			playerPanel.repaint();
		});
		
		party1 = new JButton();
		party2 = new JButton();
		party3 = new JButton();
		party4 = new JButton();
		party5 = new JButton();
		displayParty();
		
		party1.addActionListener(e -> {
			if (me.getCurrent().vStatuses.contains(Status.SPUN)) {
        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
                return;
        	}
			me.swap(me.team[1], 1);
			if (!me.team[1].isFainted()) {
				foe.move(me.getCurrent(),foe.randomMove());
				Pokemon.endOfTurn(foe, me.getCurrent());
				Pokemon.endOfTurn(me.getCurrent(), foe);
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			System.out.println();
		    System.out.println("------------------------------");
        });
		
		party2.addActionListener(e -> {
			if (me.getCurrent().vStatuses.contains(Status.SPUN)) {
        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
                return;
        	}
			me.swap(me.team[2], 2);
			if (!me.team[2].isFainted()) {
				foe.move(me.getCurrent(),foe.randomMove());
				Pokemon.endOfTurn(foe, me.getCurrent());
				Pokemon.endOfTurn(me.getCurrent(), foe);
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			System.out.println();
		    System.out.println("------------------------------");
        });
		
		party3.addActionListener(e -> {
			if (me.getCurrent().vStatuses.contains(Status.SPUN)) {
        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
                return;
        	}
			me.swap(me.team[3], 3);
			if (!me.team[3].isFainted()) {
				foe.move(me.getCurrent(),foe.randomMove());
				Pokemon.endOfTurn(foe, me.getCurrent());
				Pokemon.endOfTurn(me.getCurrent(), foe);
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			System.out.println();
		    System.out.println("------------------------------");
        });
		
		party4.addActionListener(e -> {
			if (me.getCurrent().vStatuses.contains(Status.SPUN)) {
        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
                return;
        	}
			me.swap(me.team[4], 4);
			if (!me.team[4].isFainted()) {
				foe.move(me.getCurrent(),foe.randomMove());
				Pokemon.endOfTurn(foe, me.getCurrent());
				Pokemon.endOfTurn(me.getCurrent(), foe);
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			System.out.println();
		    System.out.println("------------------------------");
        });
		
		party5.addActionListener(e -> {
			if (me.getCurrent().vStatuses.contains(Status.SPUN)) {
        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
                return;
        	}
			me.swap(me.team[5], 5);
			if (!me.team[5].isFainted()) {
				foe.move(me.getCurrent(),foe.randomMove());
				Pokemon.endOfTurn(foe, me.getCurrent());
				Pokemon.endOfTurn(me.getCurrent(), foe);
			}
			updateCurrent();
			updateBars();
			displayParty();
			updateStatus();
			System.out.println();
		    System.out.println("------------------------------");
        });
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
		stdIn = new Scanner(System.in);
		stdIn2 = new Scanner(System.in);
		currentText = new JLabel("");
		
		move1 = new JGradientButton("");
		move2 = new JGradientButton("");
		move3 = new JGradientButton("");
		move4 = new JGradientButton("");
		
		// Set current elements
		updateCurrent();
		
		userStatus = new JLabel("");
		userStatus.setHorizontalAlignment(SwingConstants.CENTER);
		userStatus.setFont(new Font("Tahoma", Font.PLAIN, 8));
		userStatus.setText(me.getCurrent().getStatus().getName());
		userStatus.setForeground(me.getCurrent().getStatus().getTextColor());
		userStatus.setBackground(me.getCurrent().getStatus().getColor());
		userStatus.setBounds(143, 149, 21, 20);
		userStatus.setVisible(true);
		userStatus.setOpaque(true);
		playerPanel.add(userStatus);
		
		foeStatus = new JLabel("");
		foeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		foeStatus.setFont(new Font("Tahoma", Font.PLAIN, 8));
		foeStatus.setText(foe.getStatus().getName());
		foeStatus.setForeground(foe.getStatus().getTextColor());
		foeStatus.setBackground(foe.getStatus().getColor());
		foeStatus.setBounds(143, 37, 21, 20);
		foeStatus.setVisible(true);
		foeStatus.setOpaque(true);
		playerPanel.add(foeStatus);
		
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
        	turn(me.getCurrent(),foe,me.getCurrent().moveset[0],foe.randomMove());
        });

        move2.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().moveset[1],foe.randomMove());
        });

        move3.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().moveset[2],foe.randomMove());
        });

        move4.addActionListener(e -> {
        	turn(me.getCurrent(),foe,me.getCurrent().moveset[3],foe.randomMove());
        });
        
        slashLabel = new JLabel("/");
		slashLabel.setHorizontalAlignment(SwingConstants.CENTER);
		slashLabel.setFont(new Font("Tahoma", Font.BOLD, 11));
		slashLabel.setBounds(206, 192, 21, 14);
		playerPanel.add(slashLabel);
		
		ButtonGroup ballType = new ButtonGroup();
		pokeballButton = new JRadioButton("");
		pokeballButton.setBounds(18, 145, 21, 23);
		pokeballButton.setSelected(true);
		playerPanel.add(pokeballButton);
		pokeballButton.setVisible(true);
		greatballButton = new JRadioButton("");
		greatballButton.setBounds(53, 145, 21, 23);
		playerPanel.add(greatballButton);
		greatballButton.setVisible(true);
		ultraballButton = new JRadioButton("");
		ultraballButton.setBounds(88, 145, 21, 23);
		playerPanel.add(ultraballButton);
		ultraballButton.setVisible(true);
		ballType.add(pokeballButton);
		ballType.add(greatballButton);
		ballType.add(ultraballButton);
		
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
		
		playerPanel.add(foeText);
		playerPanel.add(foeHealthBar);
	}
	
	private JTextField createJTextField(int i, int j, int k, int l, int m) {
		JTextField newT = new JTextField();
		newT.setColumns(i);
		newT.setBounds(j, k, l, m);
		newT.addFocusListener(new FocusAdapter() {
		    @Override // implementation
		    public void focusGained(FocusEvent e) {
		        newT.selectAll();
		    }
		});
		playerPanel.add(newT);
		return newT;
	}

	private JButton createJButton(String string, Font font, int i, int j, int k, int l) {
		JButton newB = new JButton(string);
		newB.setFont(font);
		newB.setBounds(i, j, k, l);
		playerPanel.add(newB);
		return newB;
	}

	private void updateCurrent() {
		currentText.setText(me.getCurrent().getName() + "  lv " + me.getCurrent().getLevel()); 
		currentText.setHorizontalAlignment(SwingConstants.CENTER);
		currentText.setBounds(143, 149, 164, 20);
		currentText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		setMoveButtons();
		playerPanel.repaint();
		
	}

	private void setMoveButtons() {
		if (me.getCurrent().moveset[0] != null) {
        	move1.setText(me.getCurrent().moveset[0].toString());
        	move1.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move1.setBackground(me.getCurrent().moveset[0].mtype.getColor());
        	move1.setVisible(true);
        } else {
        	move1.setText("No Move");
        	move1.setVisible(false);
        }
        if (me.getCurrent().moveset[1] != null) {
        	move2.setText(me.getCurrent().moveset[1].toString());
        	move2.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move2.setBackground(me.getCurrent().moveset[1].mtype.getColor());
        	move2.setVisible(true);
        } else {
        	move2.setText("No Move");
        	move2.setVisible(false);
        }
        if (me.getCurrent().moveset[2] != null) {
        	move3.setText(me.getCurrent().moveset[2].toString());
        	move3.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move3.setBackground(me.getCurrent().moveset[2].mtype.getColor());
        	move3.setVisible(true);
        } else {
        	move3.setText("No Move");
        	move3.setVisible(false);
        }
        if (me.getCurrent().moveset[3] != null) {
        	move4.setText(me.getCurrent().moveset[3].toString());
        	move4.setFont(new Font("Tahoma", Font.PLAIN, 9));
        	move4.setBackground(me.getCurrent().moveset[3].mtype.getColor());
        	move4.setVisible(true);
        } else {
        	move4.setText("No Move");
        	move4.setVisible(false);
        }
        
        playerPanel.repaint();
		
	}

	private void updateFoe() {
		// Foe text
		foeText.setText(foe.getName() + "  lv " + foe.getLevel());
		foeText.setBounds(143, 37, 164, 20);
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
		updateStatus();
		playerPanel.repaint();
		
	}

	private void displayParty() {
		party1.setText("");
		if (me.team[1] != null && !me.team[1].isFainted()) {
			party1.setText(me.team[1].getName() + "  lv " + me.team[1].getLevel());
			party1.setHorizontalAlignment(SwingConstants.CENTER);
			party1.setFont(new Font("Tahoma", Font.PLAIN, 11));
			party1.setBounds(330, 21, 124, 30);
			playerPanel.add(party1);
			party1.setVisible(true);
			
		} else {
			party1.setVisible(false);
		}
		
		party2.setText("");
		if (me.team[2] != null && !me.team[2].isFainted()) {
			party2.setText(me.team[2].getName() + "  lv " + me.team[2].getLevel());
			party2.setHorizontalAlignment(SwingConstants.CENTER);
			party2.setFont(new Font("Tahoma", Font.PLAIN, 11));
			party2.setBounds(330, 66, 124, 30);
			playerPanel.add(party2);
			party2.setVisible(true);
			
		} else {
			party2.setVisible(false);
		}
		
		party3.setText("");
		if (me.team[3] != null && !me.team[3].isFainted()) {
			party3.setText(me.team[3].getName() + "  lv " + me.team[3].getLevel());
			party3.setHorizontalAlignment(SwingConstants.CENTER);
			party3.setFont(new Font("Tahoma", Font.PLAIN, 11));
			party3.setBounds(330, 111, 124, 30);
			playerPanel.add(party3);
			party3.setVisible(true);
			
		} else {
			party3.setVisible(false);
		}
		
		party4.setText("");
		if (me.team[4] != null && !me.team[4].isFainted()) {
			party4.setText(me.team[4].getName() + "  lv " + me.team[4].getLevel());
			party4.setHorizontalAlignment(SwingConstants.CENTER);
			party4.setFont(new Font("Tahoma", Font.PLAIN, 11));
			party4.setBounds(330, 156, 124, 30);
			playerPanel.add(party4);
			party4.setVisible(true);
			
		} else {
			party4.setVisible(false);
		}
		
		party5.setText("");
		if (me.team[5] != null && !me.team[5].isFainted()) {
			party5.setText(me.team[5].getName() + "  lv " + me.team[5].getLevel());
			party5.setHorizontalAlignment(SwingConstants.CENTER);
			party5.setFont(new Font("Tahoma", Font.PLAIN, 11));
			party5.setBounds(330, 201, 124, 30);
			playerPanel.add(party5);
			party5.setVisible(true);
			
		} else {
			party5.setVisible(false);
		}
		
	}

	public void turn(Pokemon p1, Pokemon p2, Move m1, Move m2) {
		if (p1.isFainted() || p2.isFainted()) return;
		Pokemon newP;
		int p1speed = p1.getSpeed();
		int p2speed = p2.getSpeed();
		
		// Check for priority moves
	    if (m1.isPriority() && !m2.isPriority()) {
	        newP = p1.move(p2, m1);
	        p2.move(p1, m2);
	        Pokemon.endOfTurn(p1, p2);
			Pokemon.endOfTurn(p2, p1);
	    } else if (!m1.isPriority() && m2.isPriority()) {
	        p2.move(p1, m2);
	        newP = p1.move(p2, m1);
	        Pokemon.endOfTurn(p2, p1);
			Pokemon.endOfTurn(p1, p2);
	    } else if (m1.isPriority() && m2.isPriority()) {
	        if (p1speed >= p2speed) {
	            newP = p1.move(p2, m1);
	            p2.move(p1, m2);
	            Pokemon.endOfTurn(p1, p2);
				Pokemon.endOfTurn(p2, p1);
	        } else {
	            p2.move(p1, m2);
	            newP = p1.move(p2, m1);
	            Pokemon.endOfTurn(p2, p1);
				Pokemon.endOfTurn(p1, p2);
	        }
	    } else {
	        // Regular turn order
	        if (p1speed > p2speed) {
	            newP = p1.move(p2, m1);
	            p2.move(p1, m2);
	            Pokemon.endOfTurn(p1, p2);
				Pokemon.endOfTurn(p2, p1);
	        } else if (p1speed < p2speed) {
	            p2.move(p1, m2);
	            newP = p1.move(p2, m1);
	            Pokemon.endOfTurn(p2, p1);
				Pokemon.endOfTurn(p1, p2);
	        } else {
	            Random speedTie = new Random();
	            double random = speedTie.nextDouble();
	            if (random < 0.5) {
	                newP = p1.move(p2, m1);
	                p2.move(p1, m2);
	                Pokemon.endOfTurn(p1, p2);
	    			Pokemon.endOfTurn(p2, p1);
	            } else {
	                p2.move(p1, m2);
	                newP = p1.move(p2, m1);
	                Pokemon.endOfTurn(p2, p1);
	    			Pokemon.endOfTurn(p1, p2);
	            }
	        }
	    }
	    System.out.println();
	    System.out.println("------------------------------");
		me.current = newP;
		me.team[0] = me.getCurrent();
		updateBars();
		updateCurrent();
		updateStatus();
		if (foe.isFainted()) boxButton.setVisible(true);
	}

	private void updateStatus() {
		userStatus.setText(me.getCurrent().getStatus().getName());
		userStatus.setBackground(me.getCurrent().getStatus().getColor());
		
		foeStatus.setText(foe.getStatus().getName());
		foeStatus.setBackground(foe.getStatus().getColor());
		
		userStatus.setForeground(me.getCurrent().getStatus().getTextColor());
		foeStatus.setForeground(foe.getStatus().getTextColor());
		
		playerPanel.repaint();
	}

	private void updateBars() {
		healthBar.setMaximum(me.getCurrent().getStat(0));
		healthBar.setValue(me.getCurrent().getCurrentHP());
		maxHPLabel.setText(me.getCurrent().getStat(0) + "");
		currentHPLabel.setText(me.getCurrent().getCurrentHP() + "");
		if (healthBar.getPercentComplete() > 0.5) {
			healthBar.setForeground(new Color(0, 255, 0));
		} else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
			healthBar.setForeground(new Color(255, 255, 0));
		} else {
			healthBar.setForeground(new Color(255, 0, 0));
		}
		
		progressBar.setMaximum(me.getCurrent().expMax);
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
	
	private void displayBox() {
		JFrame frame = (JFrame) SwingUtilities.getWindowAncestor(playerPanel);
		frame.setBounds(100, 100, 648, 530);
		for (int i = 0; i < me.box.length; i++) {
	        boxButtons[i].setText("");
	        if (me.box[i] != null) {
	            boxButtons[i].setText(me.box[i].name);
	            boxButtons[i].setHorizontalAlignment(SwingConstants.CENTER);
	            boxButtons[i].setFont(new Font("Tahoma", Font.BOLD, 9));
	            boxButtons[i].setBackground(me.box[i].type1.getColor());
	        } else {
	            boxButtons[i].setText("");
	            boxButtons[i].setBackground(null);
	        }
	        boxButtons[i].setVisible(true);
	        playerPanel.add(boxButtons[i]);
		}
		playerPanel.add(returnButton);
		returnButton.setVisible(true);
		
		addMoney.setVisible(true);
		playerPanel.add(addMoney);
		
		buyPoke.setVisible(true);
		playerPanel.add(buyPoke);
		buyGreat.setVisible(true);
		playerPanel.add(buyGreat);
		buyUltra.setVisible(true);
		playerPanel.add(buyUltra);
		moneyLabel.setVisible(true);
		playerPanel.add(moneyLabel);
		
		bag[1].setText(me.pokeballCount + "");
		bag[3].setText(me.greatballCount + "");
		bag[5].setText(me.ultraballCount + "");
		
		for (int i = 0; i < bag.length; i++) {
			bag[i].setVisible(true);
			playerPanel.add(bag[i]);
		}
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

	    @Override // implementation
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

	public static Scanner getScanner() {
		return stdIn;
	}
	
	public static Scanner getScanner2() {
		return stdIn2;
	}
	
	private String intArrayToString(int[] arr) {
	    StringBuilder sb = new StringBuilder();
	    sb.append("[");
	    for (int i = 0; i < arr.length; i++) {
	        sb.append(arr[i]);
	        if (i != arr.length - 1) {
	            sb.append(", ");
	        }
	    }
	    sb.append("]");
	    return sb.toString();
	}
	
	private String movesToString(Pokemon p) {
		String moveString = "";
	    
	    for (int i = 0; i < p.moveset.length; i++) {
	    	if (p.moveset[i] != null) {
	    		moveString += p.moveset[i].toString();
	    		if (i != p.moveset.length - 1) {
	    			moveString += " / ";
	    		}
	    	}
	    }
	    
	    return moveString;
	}
}
