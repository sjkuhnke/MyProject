package Swing;

import javax.swing.border.EmptyBorder;

import Entity.PlayerCharacter;

import java.awt.*;
import java.awt.event.ActionListener;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;
import java.awt.event.ActionEvent;
//import java.awt.event.FocusAdapter;
//import java.awt.event.FocusEvent;
import java.awt.event.KeyEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Random;

import javax.swing.*;

public class Battle extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8943929896582623587L;
	private JPanel playerPanel;
	private JGradientButton[] moveButtons;
	private static Pokemon foe;
	private Trainer foeTrainer;
	private JProgressBar healthBar;
	private JProgressBar progressBar;
	private JLabel currentHPLabel;
	private JLabel slashLabel;
	private JLabel maxHPLabel;
	private JLabel foeText;
	private JProgressBar foeHealthBar;
	public static Player me;
	private JTextField idInput; // debug
	private JTextField levelInput; // debug
	private JLabel currentText;
	private JGradientButton[] party;
	
	private JButton catchButton;
	private JButton addButton; // debug
	private JButton fightButton; // debug
	private JButton healButton;
	private JLabel userStatus;
	private JLabel foeStatus;
	
	private JRadioButton pokeballButton;
	private JRadioButton greatballButton;
	private JRadioButton ultraballButton;
	private JProgressBar[] partyHP;
	private JComboBox<String> encounterInput;
	private ButtonGroup encounterType;
	private ButtonGroup time;
	private JRadioButton morning;
	private JRadioButton day;
	private JRadioButton night;
	private JButton encounterButton;
	private JRadioButton standard;
	private JRadioButton rdbtnFishing;
	private JRadioButton rdbtnSurfing;
	private JRadioButton rdbtnHeadbutt;
	//private JComboBox<Trainer> trainerSelect;
	//private Trainer[] trainers;
	private JButton infoButton;
	private JButton exitButton;
	private int trainerIndex;
	
	private BattleCloseListener battleCloseListener;

	public Battle(PlayerCharacter playerCharacter, Trainer foeT, int trainerIndex) {
		me = playerCharacter.p;
		this.trainerIndex = trainerIndex;
		
		setTitle("Battle");
		
		foe = new Pokemon(-10, 5, false, false);
	    foe.currentHP = 0;
	    foe.faint(false, me);
		
	    // Initializing panel
	    setResizable(false);
	    setPreferredSize(new Dimension(768, 576));
	    setBounds(100, 100, 648, 530);
	    playerPanel = new JPanel();
	    playerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
	    setContentPane(playerPanel);
	    playerPanel.setLayout(null);

	    // Set the location of the Box in the center of the screen
	    setLocationRelativeTo(null);
		// Initialize frame
		initialize();
		updateFoe();
		if (foeT != null) {
			foeTrainer = foeT;
			if (me.trainersBeat.contains(foeTrainer.toString())) {
				JOptionPane.showMessageDialog(null, foeTrainer.toString() + " already beaten!");
	            return;
			}
			foe = foeTrainer.getTeam()[0];
			JOptionPane.showMessageDialog(null, "\nYou are challenged by " + foeTrainer.toString() + "!\n" + foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
		} else {
			foe = encounterPokemon("Route 1");
		}
		updateFoe();
		me.clearBattled();
		me.getCurrent().clearVolatile();
		me.getCurrent().battled = true;
		
		addButton = createJButton("ADD", new Font("Tahoma", Font.BOLD, 9), 10, 200, 75, 23);
		fightButton = createJButton("FIGHT", new Font("Tahoma", Font.BOLD, 11), 20, 80, 89, 23);
		catchButton = createJButton("CATCH", new Font("Tahoma", Font.BOLD, 11), 20, 120, 89, 23);
		healButton = createJButton("HEAL", new Font("Tahoma", Font.BOLD, 9), 10, 262, 75, 23);
		infoButton = createJButton("INFO", new Font("Tahoma", Font.BOLD, 9), 10, 231, 75, 23);
		exitButton = createJButton("EXIT", new Font("Tahoma", Font.BOLD, 9), 10, 15, 75, 23);
		encounterButton = createJButton("Fight", new Font("Tahoma", Font.PLAIN, 12), 486, 35, 62, 21);
		
		exitButton.addActionListener(e -> {
			dispose();
		});
		
		encounterButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				foe = encounterPokemon((String)encounterInput.getSelectedItem());
				foeTrainer = null;
				updateFoe();
				if (!foe.isFainted()) healButton.setVisible(false);
				me.clearBattled();
				me.getCurrent().battled = true;
				me.getCurrent().clearVolatile();
		    }
		});
		
		InputMap inputMap = encounterButton.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW);
		ActionMap actionMap = encounterButton.getActionMap();
		inputMap.put(KeyStroke.getKeyStroke(KeyEvent.VK_E, 0), "clickButton");
		actionMap.put("clickButton", new AbstractAction() {
		    /**
			 * 
			 */
			private static final long serialVersionUID = -4021844080938897923L;

			@Override
		    public void actionPerformed(ActionEvent e) {
		        // Action to perform when E key is pressed
		        encounterButton.doClick();
		    }
		});
		
		
		idInput = createJTextField(2, 31, 53, 27, 20);
		levelInput = createJTextField(2, 71, 53, 27, 20);
		
		encounterInput = new JComboBox<String>();
		encounterInput.addItem("New Pheonix Town");
		encounterInput.addItem("Route 1");
		encounterInput.addItem("Blueberry Grove");
		encounterInput.addItem("Route 2");
		encounterInput.addItem("Route 3");
		encounterInput.addItem("Chupi Forest");
		encounterInput.addItem("Route 5");
		encounterInput.addItem("Route 6");
		encounterInput.addItem("Shorki Cavern");
		encounterInput.addItem("Route 7");
		encounterInput.addItem("Route 9");
		encounterInput.addItem("Cornline City");
		encounterInput.addItem("Brocline City");
		encounterInput.addItem("Route 14");
		encounterInput.addItem("Route 15");
		encounterInput.addItem("Route 11");
		encounterInput.addItem("Route 13");
		encounterInput.addItem("Route 12");
		encounterInput.addItem("Night Scope Town");
		encounterInput.addItem("Mt. Wakauki 1A");
		encounterInput.addItem("Mt. Wakauki 2F");
		encounterInput.addItem("Mt. Wakauki 1F");
		encounterInput.addItem("Mt. Wakauki 3B");
		encounterInput.addItem("Mt. Wakauki 4A");
		encounterInput.addItem("Mt. Wakauki 5F");
		encounterInput.addItem("Mt. Wakauki 5A");
		encounterInput.addItem("Mt. Wakauki 5B");
		encounterInput.addItem("Mt. Wakauki 6A");
		encounterInput.addItem("Route 4");
		encounterInput.addItem("Route 10");
		encounterInput.addItem("Route 16");
		encounterInput.addItem("Breezeline Village");
		encounterInput.addItem("Blantoisa Path 1F");
		encounterInput.addItem("Blantoisa Path B1F");
		encounterInput.addItem("Route 17");
		encounterInput.addItem("Elite Plateau");
		encounterInput.addItem("Route 8");
		encounterInput.addItem("Chomp Lake");
		encounterInput.addItem("Thunder Tower 1T");
		encounterInput.addItem("Thunder Tower 2T");
		encounterInput.addItem("Thunder Tower 3T");
		encounterInput.addItem("Thunder Tower R");
		encounterInput.addItem("Thunder Tower 1TB");
		encounterInput.addItem("Thunder Tower 2TB");
		encounterInput.addItem("Thunder Tower 3TB");
		encounterInput.addItem("Thunder Tower RB");
		encounterInput.addItem("Thunder Tower R6");
		encounterInput.addItem("Thunder Tower RB2");
		encounterInput.addItem("Route 18");
		encounterInput.addItem("Route 19");
		encounterInput.addItem("Route 20");
		encounterInput.addItem("Route 21");
		encounterInput.addItem("Orcai Cavern 1A");
		encounterInput.addItem("Orcai Cavern 0 N");
		encounterInput.addItem("Orcai Cavern 0 SE");
		encounterInput.addItem("Orcai Cavern 0 SW");
		encounterInput.setBounds(478, 80, 142, 21);
		
		encounterInput.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				foe = encounterPokemon((String)encounterInput.getSelectedItem());
				updateFoe();
				if (!foe.isFainted()) healButton.setVisible(false);
				me.clearBattled();
				me.getCurrent().battled = true;
		    }
		});
		encounterInput.setVisible(true);
		playerPanel.add(encounterInput);
		encounterType = new ButtonGroup();
		standard = new JRadioButton("Standard");
		standard.setActionCommand("Standard");
		standard.setSelected(true);
		standard.setBounds(502, 108, 109, 23);
		playerPanel.add(standard);
		rdbtnFishing = new JRadioButton("Fishing");
		rdbtnFishing.setActionCommand("Fishing");
		rdbtnFishing.setBounds(502, 134, 109, 23);
		playerPanel.add(rdbtnFishing);
		rdbtnSurfing = new JRadioButton("Surfing");
		rdbtnSurfing.setBounds(502, 162, 109, 23);
		rdbtnSurfing.setActionCommand("Surfing");
		playerPanel.add(rdbtnSurfing);
		rdbtnHeadbutt = new JRadioButton("Headbutt");
		rdbtnHeadbutt.setActionCommand("Headbutt");
		rdbtnHeadbutt.setBounds(502, 188, 109, 23);
		playerPanel.add(rdbtnHeadbutt);
		encounterType.add(standard);
		encounterType.add(rdbtnFishing);
		encounterType.add(rdbtnSurfing);
		encounterType.add(rdbtnHeadbutt);
		time = new ButtonGroup();
		morning = new JRadioButton("M");
		morning.setActionCommand("M");
		morning.setBounds(481, 57, 35, 23);
		playerPanel.add(morning);
		day = new JRadioButton("D");
		day.setActionCommand("D");
		day.setBounds(531, 57, 35, 23);
		day.setSelected(true);
		playerPanel.add(day);
		night = new JRadioButton("N");
		night.setActionCommand("N");
		night.setBounds(581, 57, 35, 23);
		playerPanel.add(night);
		time.add(morning);
		time.add(day);
		time.add(night);
		
		JTextArea console = new JTextArea();
		console.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(console));
		
		System.setOut(printStream);
		
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setBounds(10, 295, 610, 190);
		playerPanel.add(scrollPane);
		
		addButton.addActionListener(e -> {
			if (!foe.isFainted()) {
				me.catchPokemon(new Pokemon(foe.id, foe.getLevel(), true, false));
				displayParty();
				updateFoe();
			}
        });
		
		catchButton.addActionListener(e -> {
		    if (!foe.isFainted()) {
		    	if (foe.trainerOwned()) {
		    		JOptionPane.showMessageDialog(null, "Cannot catch trainer's Pokemon!");
                    return;
		    	}
		        Random rand = new Random();
		        double catchRate = 0;
		        double statusBonus = 1;
		        
		        if (foe.id == 103 || foe.id == 104 || foe.id == 105 || foe.id == 91 || foe.id == 133 || foe.id == 134 ||foe.id == 135 || foe.id == 136 || foe.id == 137 ||foe.id == 138 || foe.id == 139 || foe.id == 140) statusBonus = 0.25; // if legendary
		        
		        if (pokeballButton.isSelected()) {
		        	if (me.bag.count[1] <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 12.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 0.25;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 8.0;
		            }
		            if (foe.status != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed a Pokeball!");
		            me.bag.remove(new Item(1));
		        } else if (greatballButton.isSelected()) {
		        	if (me.bag.count[2] <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 10.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 0.5;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 6.0;
		            }
		            if (foe.status != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed a Great Ball!");
		            me.bag.remove(new Item(2));
		        } else if (ultraballButton.isSelected()) {
		        	if (me.bag.count[3] <= 0) {
		        		JOptionPane.showMessageDialog(null, "No balls remaining!");
                        return;
		        	}
		            catchRate = 1.0 / 6.0;
		            if (foe.currentHP <= (foe.getStat(0) / 4)) {
		                catchRate = 1.0;
		            } else if (foe.currentHP <= (foe.getStat(0) / 2)) {
		                catchRate = 1.0 / 3.0;
		            }
		            if (foe.status != Status.HEALTHY) {
		                statusBonus *= 2.0;
		            }
		            System.out.println("\nUsed an Ultra Ball!");
		            me.bag.remove(new Item(3));
		        }
		        
		        double randomValue = rand.nextDouble();
		        double modifiedCatchRate = catchRate * statusBonus;
		        if (randomValue <= modifiedCatchRate) {
		            me.catchPokemon(new Pokemon(foe.id, foe.getLevel(), true, false));
		            foe.currentHP = 0;
					foe.faint(false, me);
					displayParty();
					updateFoe();
					healButton.setVisible(true);
		        } else {
		            System.out.println("Oh no! " + foe.name + " broke free!");
		            foe.move(me.getCurrent(),foe.randomMove(), me);
					Pokemon.endOfTurn(foe, me.getCurrent(), me);
					Pokemon.endOfTurn(me.getCurrent(), foe, me);
					updateBars();
					updateCurrent();
					updateStatus();
					displayParty();
		        }
		        System.out.println();
			    System.out.println("------------------------------");
		    }
		});
		
		fightButton.addActionListener(e -> {
			fightMon();
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
		
		infoButton.addActionListener(e -> {
            JPanel teamMemberPanel = me.getCurrent().showSummary();
            JOptionPane.showMessageDialog(null, teamMemberPanel, "Party member details", JOptionPane.PLAIN_MESSAGE);
		});
		
		party = new JGradientButton[5];
		partyHP = new JProgressBar[5];
		for (int i = 0; i < 5; i++) {
			party[i] = new JGradientButton("");
			partyHP[i] = new JProgressBar(0, 100);
			final int index = i + 1;
			
			party[i].addActionListener(e -> {
				if (me.getCurrent().vStatuses.contains(Status.SPUN) || me.getCurrent().vStatuses.contains(Status.CHARGING) || me.getCurrent().vStatuses.contains(Status.RECHARGE) || me.getCurrent().vStatuses.contains(Status.LOCKED)) {
	        		JOptionPane.showMessageDialog(null, "You are trapped and cannot switch!");
	                return;
				}
				me.swap(me.team[index], index);
				healthBar.setMaximum(me.getCurrent().getStat(0));
				healthBar.setValue(me.getCurrent().currentHP);
				if (healthBar.getPercentComplete() > 0.5) {
					healthBar.setForeground(new Color(0, 255, 0));
	            } else if (healthBar.getPercentComplete() <= 0.5 && healthBar.getPercentComplete() > 0.25) {
	            	healthBar.setForeground(new Color(255, 255, 0));
	            } else {
	            	healthBar.setForeground(new Color(255, 0, 0));
	            }
				if (!me.team[index].isFainted()) {
					if (foe.trainerOwned()) {
		        		foe.move(me.getCurrent(),foe.bestMove(me.team[index]), me);
		        	} else {
		        		foe.move(me.getCurrent(),foe.randomMove(), me);
		        	}
					Pokemon.endOfTurn(foe, me.getCurrent(), me);
					Pokemon.endOfTurn(me.getCurrent(), foe, me);
				}
				if (foe.isFainted()) {
					if (foeTrainer != null && foeTrainer.getTeam() != null) {
						if (foeTrainer.hasNext()) {
							foe = foeTrainer.next();
							System.out.println("\n" + foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
							updateFoe();
							me.clearBattled();
							me.getCurrent().battled = true;
							
						} else {
							System.out.println("\n" + foeTrainer.toString() + " was defeated!");
							me.trainersBeat.add(foeTrainer.toString());
							me.money += foeTrainer.getMoney();
							System.out.println("Won $" + foeTrainer.getMoney() + "!");
							healButton.setVisible(true);
							encounterButton.setVisible(true);
							encounterInput.setVisible(true);
							//trainerSelect.setVisible(true);
							//updateTrainers();
						}
					} else {
						healButton.setVisible(true);
					}
				}
				updateCurrent();
				updateBars();
				displayParty();
				updateStatus();
				System.out.println();
			    System.out.println("------------------------------");
	        });
			party[i].addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			        if (SwingUtilities.isRightMouseButton(e)) {
			        	JPanel teamMemberPanel = me.team[index].showSummary();
			            JOptionPane.showMessageDialog(null, teamMemberPanel, "Party member details", JOptionPane.PLAIN_MESSAGE);
			        }
			    }
			});

		}
		displayParty();
	}

	private void initialize() {
		// Initializing current elements
		currentText = new JLabel("");
		
		moveButtons = new JGradientButton[4];
		int[] xPositions = {121, 220, 121, 220};
		int[] yPositions = {212, 212, 245, 245};
		for (int i = 0; i < moveButtons.length; i++) {
			final int index = i;
			moveButtons[i] = new JGradientButton("");
			moveButtons[i].setBounds(xPositions[i], yPositions[i], 89, 23);
			playerPanel.add(moveButtons[i]);
			
			moveButtons[i].addMouseListener(new MouseAdapter() {
			    @Override
			    public void mouseClicked(MouseEvent e) {
			    	if (SwingUtilities.isRightMouseButton(e)) {
			            String message = "Move: " + me.getCurrent().moveset[index].toString() + "\n";
			            message += "Type: " + me.getCurrent().moveset[index].mtype + "\n";
			            message += "BP: " + me.getCurrent().moveset[index].getbp() + "\n";
			            message += "Accuracy: " + me.getCurrent().moveset[index].accuracy + "\n";
			            message += "Category: " + me.getCurrent().moveset[index].getCategory() + "\n";
			            message += "Description: " + me.getCurrent().moveset[index].getDescription();
			            JOptionPane.showMessageDialog(null, message, "Move Description", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			        	if (foe.trainerOwned()) {
			        		turn(me.getCurrent(), foe, me.getCurrent().moveset[index], foe.bestMove(me.getCurrent()));
			        	} else {
			        		turn(me.getCurrent(), foe, me.getCurrent().moveset[index], foe.randomMove());
			        	}
			        }
			    }
			});
		}
		
		// Set current elements
		updateCurrent();
		playerPanel.add(currentText);
		
		userStatus = new JLabel("");
		userStatus.setHorizontalAlignment(SwingConstants.CENTER);
		userStatus.setFont(new Font("Tahoma", Font.PLAIN, 8));
		userStatus.setText(me.getCurrent().status.getName());
		userStatus.setForeground(me.getCurrent().status.getTextColor());
		userStatus.setBackground(me.getCurrent().status.getColor());
		userStatus.setBounds(143, 149, 21, 20);
		userStatus.setVisible(true);
		userStatus.setOpaque(true);
		playerPanel.add(userStatus);
		
		foeStatus = new JLabel("");
		foeStatus.setHorizontalAlignment(SwingConstants.CENTER);
		foeStatus.setFont(new Font("Tahoma", Font.PLAIN, 8));
		foeStatus.setText(foe.status.getName());
		foeStatus.setForeground(foe.status.getTextColor());
		foeStatus.setBackground(foe.status.getColor());
		foeStatus.setBounds(143, 37, 21, 20);
		foeStatus.setVisible(true);
		foeStatus.setOpaque(true);
		playerPanel.add(foeStatus);
		
		
        
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
		newT.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
		        fightMon();
		    }
		});
		playerPanel.add(newT);
		return newT;
	}


	public Pokemon encounterPokemon(String routeName) {
	    // Create an ArrayList of PokemonEncounter objects for the route
		//String selectedEncounterType = encounterType.getSelection().getActionCommand();
		//String selectedTime = time.getSelection().getActionCommand();
	    ArrayList<Encounter> encounters = Encounter.getEncounters(routeName, "Standard", "D");

	    // Calculate the total encounter chance for the route
	    double totalChance = 0.0;
	    for (Encounter encounter : encounters) {
	        totalChance += encounter.getEncounterChance();
	    }

	    // Randomly select an encounter based on the Pokemon's encounter chance
	    double rand = Math.random() * totalChance;
	    for (Encounter encounter : encounters) {
	        rand -= encounter.getEncounterChance();
	        if (rand < 0) {
	            // Randomly generate a level within the level range
	            int level = (int) (Math.random() * (encounter.getMaxLevel() - encounter.getMinLevel() + 1) + encounter.getMinLevel());
	            return new Pokemon(encounter.getId(), level, false, false);
	        }
	    }

	    // If no encounter was selected, return null
	    JOptionPane.showMessageDialog(null, "No encounters available for this combination.");
	    return foe;
	}

	private JButton createJButton(String string, Font font, int i, int j, int k, int l) {
		JButton newB = new JButton(string);
		newB.setFont(font);
		newB.setBounds(i, j, k, l);
		playerPanel.add(newB);
		return newB;
	}

	private void updateCurrent() {
		currentText.setText(me.getCurrent().name + "  lv " + me.getCurrent().getLevel()); 
		currentText.setHorizontalAlignment(SwingConstants.CENTER);
		currentText.setBounds(143, 149, 164, 20);
		currentText.setFont(new Font("Tahoma", Font.PLAIN, 16));
		
		setMoveButtons();
		playerPanel.repaint();
		
	}

	private void setMoveButtons() {
	    Move[] moveset = me.getCurrent().moveset;
	    for (int i = 0; i < moveButtons.length; i++) {
	        if (moveset[i] != null) {
	            moveButtons[i].setText(moveset[i].toString());
	            moveButtons[i].setFont(new Font("Tahoma", Font.PLAIN, 9));
	            moveButtons[i].setBackground(moveset[i].mtype.getColor());
	            moveButtons[i].setVisible(true);
	        } else {
	        	moveButtons[i].setText("No Move");
	        	moveButtons[i].setVisible(false);
	        }
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

	private void animateBar(JProgressBar bar, int value, int currentHP) {
	    int diff = value - currentHP;
	    int current = value;
	    
	    // Determine the step size for the animation
	    int step = diff > 0 ? 1 : -1;
	    // Create a Timer to animate the progress bar
	    Timer timer = new Timer(5, new ActionListener() {
	        int count = 0;
	        @Override
	        public void actionPerformed(ActionEvent e) {
	            // Update the progress bar value and repaint it
	            bar.setValue(current - step * count++);
	            bar.repaint();

	            if (bar.getPercentComplete() > 0.5) {
	                bar.setForeground(new Color(0, 255, 0));
	            } else if (bar.getPercentComplete() <= 0.5 && bar.getPercentComplete() > 0.25) {
	                bar.setForeground(new Color(255, 255, 0));
	            } else {
	                bar.setForeground(new Color(255, 0, 0));
	            }
	            // Stop the Timer when the animation is finished
	            if (count > Math.abs(diff)) {
	                ((Timer) e.getSource()).stop();

	            }
	        }
	    });
	    // Start the Timer
	    timer.start();
	}




	private void displayParty() {
		for (int i = 0; i < 5; i++) {
			party[i].setText("");
			if (me.team[i + 1] != null && !me.team[i + 1].isFainted()) {
				party[i].setText(me.team[i + 1].getName() + "  lv " + me.team[i + 1].getLevel());
				party[i].setHorizontalAlignment(SwingConstants.CENTER);
				party[i].setFont(new Font("Tahoma", Font.PLAIN, 11));
				party[i].setBounds(330, 21 + (i % 5) * 54, 124, 30);
				party[i].setBackground(me.team[i + 1].type1.getColor());
				playerPanel.add(party[i]);
				party[i].setVisible(true);
				
			} else {
				party[i].setVisible(false);
			}
			if (me.team[i + 1] != null && !me.team[i + 1].isFainted()) {
				partyHP[i].setMaximum(me.team[i + 1].getStat(0));
				partyHP[i].setValue(me.team[i + 1].currentHP);
				if (partyHP[i].getPercentComplete() > 0.5) {
					partyHP[i].setForeground(new Color(0, 255, 0));
				} else if (partyHP[i].getPercentComplete() <= 0.5 && partyHP[i].getPercentComplete() > 0.25) {
					partyHP[i].setForeground(new Color(255, 255, 0));
				} else {
					partyHP[i].setForeground(new Color(255, 0, 0));
				}
				partyHP[i].setBounds(330, 54 + (i % 5) * 54, 124, 7);
				partyHP[i].setVisible(true);
				playerPanel.add(partyHP[i]);
			} else {
				partyHP[i].setVisible(false);
			}
		}
		
	}

	public void turn(Pokemon p1, Pokemon p2, Move m1, Move m2) {
		if (p1.isFainted() || p2.isFainted()) return;
		int p1speed = p1.getSpeed();
		int p2speed = p2.getSpeed();
		
		// Check for priority moves
	    if (m1.isPriority() && !m2.isPriority()) {
	    	if (m1 == Move.SUCKER_PUNCH) {
	    		if (m2.isAttack()) {
	    			p1.move(p2, m1, me);
	    			p2.move(p1, m2, me);
	    	        Pokemon.endOfTurn(p1, p2, me);
	    			Pokemon.endOfTurn(p2, p1, me);
	    		} else {
	    			m1 = Move.FAILED_SUCKER;
	    			p1.move(p2, m1, me);
	    			p2.move(p1, m2, me);
	    	        Pokemon.endOfTurn(p1, p2, me);
	    			Pokemon.endOfTurn(p2, p1, me);
	    		}
	    	} else {
	    		p1.move(p2, m1, me);
		        p2.move(p1, m2, me);
		        Pokemon.endOfTurn(p1, p2, me);
				Pokemon.endOfTurn(p2, p1, me);
	    	}
	    } else if (!m1.isPriority() && m2.isPriority()) {
	    	if (m2 == Move.SUCKER_PUNCH) {
	    		if (m1.isAttack()) {
	    			p2.move(p1, m2, me);
	    	        p1.move(p2, m1, me);
	    	        Pokemon.endOfTurn(p2, p1, me);
	    			Pokemon.endOfTurn(p1, p2, me);
	    		} else {
	    			m2 = Move.FAILED_SUCKER;
	    			p2.move(p1, m2, me);
	    	        p1.move(p2, m1, me);
	    	        Pokemon.endOfTurn(p2, p1, me);
	    			Pokemon.endOfTurn(p1, p2, me);
	    		}
	    	} else {
	    		p2.move(p1, m2, me);
		        p1.move(p2, m1, me);
		        Pokemon.endOfTurn(p2, p1, me);
				Pokemon.endOfTurn(p1, p2, me);
	    	}
	    } else if (m1.isPriority() && m2.isPriority()) {
	        if (p1speed >= p2speed) {
	            p1.move(p2, m1, me);
	            p2.move(p1, m2, me);
	            Pokemon.endOfTurn(p1, p2, me);
				Pokemon.endOfTurn(p2, p1, me);
	        } else {
	            p2.move(p1, m2, me);
	            p1.move(p2, m1, me);
	            Pokemon.endOfTurn(p2, p1, me);
				Pokemon.endOfTurn(p1, p2, me);
	        }
	    } else {
	        // Regular turn order
	        if (p1speed > p2speed) {
	            p1.move(p2, m1, me);
	            p2.move(p1, m2, me);
	            Pokemon.endOfTurn(p1, p2, me);
				Pokemon.endOfTurn(p2, p1, me);
	        } else if (p1speed < p2speed) {
	            p2.move(p1, m2, me);
	            p1.move(p2, m1, me);
	            Pokemon.endOfTurn(p2, p1, me);
				Pokemon.endOfTurn(p1, p2, me);
	        } else {
	            Random speedTie = new Random();
	            double random = speedTie.nextDouble();
	            if (random < 0.5) {
	                p1.move(p2, m1, me);
	                p2.move(p1, m2, me);
	                Pokemon.endOfTurn(p1, p2, me);
	    			Pokemon.endOfTurn(p2, p1, me);
	            } else {
	                p2.move(p1, m2, me);
	                p1.move(p2, m1, me);
	                Pokemon.endOfTurn(p2, p1, me);
	    			Pokemon.endOfTurn(p1, p2, me);
	            }
	        }
	    }
		if (foe.isFainted()) {
			if (foeTrainer != null && foeTrainer.getTeam() != null) {
				if (foeTrainer.hasNext()) {
					foe = foeTrainer.next();
					System.out.println("\n" + foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
					updateFoe();
					me.clearBattled();
					me.getCurrent().battled = true;
					
				} else {
					// Show the prompt with the specified text
					me.money += foeTrainer.getMoney();
		            JOptionPane.showMessageDialog(null, foeTrainer.toString() + " was defeated!\nWon $" + foeTrainer.getMoney() + "!");
		            if (foeTrainer.getMoney() == 500 && me.badges < 8) me.badges++;

		            // Close the current Battle JFrame
		            dispose();
				}
			} else {
				JOptionPane.showMessageDialog(null, foe.name + " was defeated!");
				dispose();
			}
		}
		updateBars();
		updateCurrent();
		updateStatus();
		displayParty();
		System.out.println();
	    System.out.println("------------------------------");
		
	}

	private void updateStatus() {
		userStatus.setText(me.getCurrent().status.getName());
		userStatus.setBackground(me.getCurrent().status.getColor());
		
		foeStatus.setText(foe.status.getName());
		foeStatus.setBackground(foe.status.getColor());
		
		userStatus.setForeground(me.getCurrent().status.getTextColor());
		foeStatus.setForeground(foe.status.getTextColor());
		
		playerPanel.repaint();
	}

	private void updateBars() {
		healthBar.setMaximum(me.getCurrent().getStat(0));
		animateBar(healthBar, healthBar.getValue(), me.getCurrent().getCurrentHP());
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
		
		foeHealthBar.setMaximum(foe.getStat(0));
		animateBar(foeHealthBar, foeHealthBar.getValue(), foe.getCurrentHP());
		if (foeHealthBar.getPercentComplete() > 0.5) {
			foeHealthBar.setForeground(new Color(0, 255, 0));
		} else if (foeHealthBar.getPercentComplete() <= 0.5 && foeHealthBar.getPercentComplete() > 0.25) {
			foeHealthBar.setForeground(new Color(255, 255, 0));
		} else {
			foeHealthBar.setForeground(new Color(255, 0, 0));
		}
		playerPanel.repaint();
		
	}
	
	public static final class JGradientButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = 639680055516122456L;

		public JGradientButton(String text){
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
	
	private void fightMon() {
		try {
			if (Integer.parseInt(idInput.getText()) >= -144 && Integer.parseInt(idInput.getText()) <= 237) {
				foe = new Pokemon(Integer.parseInt(idInput.getText()), Integer.parseInt(levelInput.getText()), false, true);
			} else {
				foe = new Pokemon(-10, 5, false, true);
			}
			updateFoe();
		} catch (NumberFormatException e1) {
			foe = new Pokemon(-10, 5, false, true);
			updateFoe();
		}
		me.clearBattled();
		me.getCurrent().battled = true;
	}
	
	public static int displayMoveOptions(Pokemon pokemon, Move move) {
	    String[] moves = new String[4];
	    JGradientButton[] buttons = new JGradientButton[4];
	    JPanel panel = new JPanel();
	    int[] choice = new int[1];
	    choice[0] = -1;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    JLabel label = new JLabel(pokemon.getName() + " wants to learn " + move.toString() + ".");
	    JLabel label2 = new JLabel("Select a move to replace:");
	    JGradientButton learnButton = new JGradientButton(move.toString());
	    learnButton.setBackground(move.mtype.getColor());
	    panel.add(label);
	    panel.add(learnButton);
	    panel.add(label2);
	    for (int i = 0; i < 4; i++) {
	        if (pokemon.moveset[i] != null) {
	            moves[i] = pokemon.moveset[i].toString();
	        } else {
	            moves[i] = "";
	        }
	        buttons[i] = new JGradientButton(moves[i]);
	        buttons[i].setBackground(pokemon.moveset[i].mtype.getColor());
	        if (moves[i].equals("")) {
	            buttons[i].setEnabled(false);
	        }
	        int index = i;
	        buttons[i].addMouseListener(new MouseAdapter() {
	        	@Override
			    public void mouseClicked(MouseEvent e) {
			    	if (SwingUtilities.isRightMouseButton(e)) {
			            String message = "Move: " + pokemon.moveset[index].toString() + "\n";
			            message += "Type: " + pokemon.moveset[index].mtype + "\n";
			            message += "BP: " + pokemon.moveset[index].getbp() + "\n";
			            message += "Accuracy: " + pokemon.moveset[index].accuracy + "\n";
			            message += "Category: " + pokemon.moveset[index].getCategory() + "\n";
			            message += "Description: " + pokemon.moveset[index].getDescription();
			            JOptionPane.showMessageDialog(null, message, "Move Description", JOptionPane.INFORMATION_MESSAGE);
			        } else {
			        	choice[0] = index;
		                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
		                dialog.dispose();
			        }
			    }
	        });
	        panel.add(buttons[i]);
	    }
	    learnButton.addMouseListener(new MouseAdapter() {
        	@Override
		    public void mouseClicked(MouseEvent e) {
		    	if (SwingUtilities.isRightMouseButton(e)) {
		            String message = "Move: " + move.toString() + "\n";
		            message += "Type: " + move.mtype + "\n";
		            message += "BP: " + move.getbp() + "\n";
		            message += "Accuracy: " + move.accuracy + "\n";
		            message += "Category: " + move.getCategory() + "\n";
		            message += "Description: " + move.getDescription();
		            JOptionPane.showMessageDialog(null, message, "Move Description", JOptionPane.INFORMATION_MESSAGE);
		        } else {
		        	choice[0] = JOptionPane.CLOSED_OPTION;
	                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
	                dialog.dispose();
		        }
		    }
        });

	    JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
	    JDialog dialog = optionPane.createDialog("Learn New Move");
	    dialog.setVisible(true);
	    int result = choice[0];
	    return result == JOptionPane.CLOSED_OPTION ? JOptionPane.CLOSED_OPTION : choice[0];
	}

	
	public static boolean displayEvolution(Pokemon pokemon) {
		int option = JOptionPane.showOptionDialog(null,
				pokemon.name + " is evolving!\nDo you want to evolve your " + pokemon.name + "?",
	            "Evolution",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null, null, null);
	    return option == JOptionPane.YES_OPTION;
	}
	public static int dogoEvo(Pokemon pokemon) {
		JGradientButton[] buttons = new JGradientButton[11];
	    JPanel panel = new JPanel();
	    int[] choice = new int[1];
	    choice[0] = -1;
	    panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));
	    JLabel label = new JLabel(pokemon.getName() + " is evolving.");
	    JLabel label2 = new JLabel("Select which evolution:");
	    panel.add(label);
	    panel.add(label2);
	    
	    for (int i = 0; i < 11; i++) {
	    	Pokemon evo = new Pokemon(-i - 113, 25, false, false);
	        buttons[i] = new JGradientButton(evo.name);
	        buttons[i].setBackground(evo.type1.getColor());
	        int index = -i - 113;
	        buttons[i].addActionListener(new ActionListener() {
	            @Override
	            public void actionPerformed(ActionEvent e) {
	                choice[0] = index;
	                JDialog dialog = (JDialog) SwingUtilities.getWindowAncestor((JButton) e.getSource());
	                dialog.dispose();
	            }
	        });
	        panel.add(buttons[i]);
	    }

	    JOptionPane optionPane = new JOptionPane(panel, JOptionPane.PLAIN_MESSAGE, JOptionPane.DEFAULT_OPTION, null, new Object[]{}, null);
	    JDialog dialog = optionPane.createDialog("Choose Evolution");
	    dialog.setVisible(true);
	    int result = choice[0];
	    return result == JOptionPane.CLOSED_OPTION ? JOptionPane.CLOSED_OPTION : choice[0];
	}
	
//	private Trainer[] getUnbeatenTrainers() {
//		// First, create a list to store trainers that haven't been beaten
//		ArrayList<Trainer> unbeatenTrainers = new ArrayList<>();
//
//		// Iterate through all trainers and add unbeaten trainers to the list
//		for (Trainer trainer : trainers) {
//		    if (!me.trainersBeat.contains(trainer.toString())) {
//		        unbeatenTrainers.add(trainer);
//		    }
//		}
//		// Create a new array with the unbeaten trainers
//		Trainer[] unbeatenTrainersArray = unbeatenTrainers.toArray(new Trainer[0]);
//		return unbeatenTrainersArray;
//	}
	
//	private void updateTrainers() {
//		Trainer[] unbeatenTrainers = getUnbeatenTrainers();
//	    DefaultComboBoxModel<Trainer> model = new DefaultComboBoxModel<>(unbeatenTrainers);
//	    model.insertElementAt(new Trainer(true), 0); // Add placeholder at index 0
//	    trainerSelect.setModel(model);
//	    trainerSelect.setSelectedIndex(0); // Set placeholder as default selection
//	}
	
	public void setBattleCloseListener(BattleCloseListener listener) {
        this.battleCloseListener = listener;
    }
	
	@Override
	public void dispose() {
		super.dispose();
		if (battleCloseListener != null) battleCloseListener.onBattleClosed(trainerIndex);
	}
	
	public interface BattleCloseListener {
	    void onBattleClosed(int trainer);
	}
	
}
