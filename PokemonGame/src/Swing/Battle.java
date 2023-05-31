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
	private JButton boxButton;
	private JGradientButton[] boxButtons;
	private JButton returnButton;
	
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
		
		foe = new Pokemon(-10, 5, false, false);
	    foe.currentHP = 0;
	    foe.faint(false, me);
		
		// Initializing panel
		setResizable(false);
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setPreferredSize(new Dimension(768, 576));
		setBounds(100, 100, 648, 530);
		playerPanel = new JPanel();
		playerPanel.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(playerPanel);
		playerPanel.setLayout(null);
		// Initialize frame
		initialize();
		updateFoe();
		
		foeTrainer = foeT;
		if (me.trainersBeat.contains(foeTrainer.toString())) {
			JOptionPane.showMessageDialog(null, foeTrainer.toString() + " already beaten!");
            return;
		}
		if (foeT == null) {
			return;
		}
		foe = foeTrainer.getTeam()[0];
		JOptionPane.showMessageDialog(null, "\nYou are challenged by " + foeTrainer.toString() + "!\n" + foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
//		System.out.println("\nYou are challenged by " + foeTrainer.toString() + "!");
//		System.out.println(foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
		updateFoe();
//		boxButton.setVisible(false);
//		healButton.setVisible(false);
//		encounterButton.setVisible(false);
//		encounterInput.setVisible(false);
//		trainerSelect.setVisible(false);
		me.clearBattled();
		me.getCurrent().clearVolatile();
		me.getCurrent().battled = true;
		
		addButton = createJButton("ADD", new Font("Tahoma", Font.BOLD, 9), 10, 200, 75, 23);
		fightButton = createJButton("FIGHT", new Font("Tahoma", Font.BOLD, 11), 20, 80, 89, 23);
		catchButton = createJButton("CATCH", new Font("Tahoma", Font.BOLD, 11), 20, 120, 89, 23);
		healButton = createJButton("HEAL", new Font("Tahoma", Font.BOLD, 9), 10, 262, 75, 23);
		infoButton = createJButton("INFO", new Font("Tahoma", Font.BOLD, 9), 10, 231, 75, 23);
		boxButton = createJButton("Box", new Font("Tahoma", Font.PLAIN, 12), 553, 35, 62, 21);
		exitButton = createJButton("EXIT", new Font("Tahoma", Font.BOLD, 9), 10, 15, 75, 23);
		encounterButton = createJButton("Fight", new Font("Tahoma", Font.PLAIN, 12), 486, 35, 62, 21);
		returnButton = createJButton("Exit", new Font("Tahoma", Font.BOLD, 12), 553, 35, 62, 21);
		returnButton.setVisible(false);
		
		exitButton.addActionListener(e -> {
			dispose();
		});
		
		encounterButton.addActionListener(new ActionListener() {
		    public void actionPerformed(ActionEvent e) {
				foe = encounterPokemon((String)encounterInput.getSelectedItem());
				foeTrainer = null;
				updateFoe();
				if (!foe.isFainted()) boxButton.setVisible(false);
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
	                JGradientButton swapButton = new JGradientButton("Swap with a party member");
	                swapButton.setBackground(new Color(0, 227, 252));
	                JGradientButton moveButton = new JGradientButton("Teach Move");
	                moveButton.setBackground(new Color(255, 251, 0));
	                JGradientButton seeForgottenMoves = new JGradientButton("See Forgotten Moves");
	                seeForgottenMoves.setBackground(new Color(252, 147, 0));
	                JGradientButton rareCandy = new JGradientButton("Elevate Level");
	                rareCandy.setBackground(new Color(152, 6, 209));
	                JGradientButton releaseButton = new JGradientButton("Release");
	                releaseButton.setBackground(new Color(214, 6, 17));
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
	                                } else if (me.team[j].status != Status.HEALTHY) {
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
	                    	if (me.box[index] == null) {
                        		JOptionPane.showMessageDialog(null, "No Pokemon to teach.");
	                            return;
                        	}
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
	                        int confirm = JOptionPane.showConfirmDialog(null, "Are you sure you want to release this Pokemon?", "Release Pokemon", JOptionPane.YES_NO_OPTION);
	                        if (confirm == JOptionPane.YES_OPTION) {
	                            // code to release the box member
	                            me.box[index] = null;
	                            boxButtons[index].setText("");
	                            boxButtons[index].setBackground(null);
	                            boxMemberPanel.setVisible(false);
	                            SwingUtilities.getWindowAncestor(boxMemberPanel).dispose();
	                        }
	                    }
	                });
	                rareCandy.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                    	if (me.box[index] == null) {
                        		JOptionPane.showMessageDialog(null, "No Pokemon to elevate.");
	                            return;
                        	}
	                    	me.elevate(me.box[index]);
	                    	nameLabel.setText("Name: " + me.box[index].getName());;
	    	                levelLabel.setText("Level: " + me.box[index].getLevel());
	    	                statsLabel.setText("Stats: " + intArrayToString(me.box[index].stats));
	    	                movesLabel.setText("Moves: " + movesToString(me.box[index]));
	    	                displayBox();
	                    }
	                });
	                seeForgottenMoves.addActionListener(new ActionListener() {
	                    @Override
	                    public void actionPerformed(ActionEvent e) {
	                    	if (me.box[index] == null) {
                        		JOptionPane.showMessageDialog(null, "No Pokemon to check.");
	                            return;
                        	}
	                        ArrayList<Move> forgottenMoves = new ArrayList<>();
	                        for (int i = 0; i < me.box[index].getLevel(); i++) {
	                        	if (i < me.box[index].movebank.length) {
		                            if (me.box[index].movebank[i] != null && !me.box[index].knowsMove(me.box[index].movebank[i])) {
		                                forgottenMoves.add(me.box[index].movebank[i]);
		                            }
	                        	}
	                        }
	                        if (forgottenMoves.isEmpty()) {
	                            JOptionPane.showMessageDialog(boxMemberPanel, "This Pokemon has not forgotten any moves.");
	                        } else {
	                            String message = "This Pokemon has forgotten the following moves:\n";
	                            for (Move move : forgottenMoves) {
	                                message += "- " + move + "\n";
	                            }
	                            JOptionPane.showMessageDialog(boxMemberPanel, message);
	                        }
	                    }
	                });

	                boxMemberPanel.add(nameLabel);
	                boxMemberPanel.add(levelLabel);
	                boxMemberPanel.add(statsLabel);
	                boxMemberPanel.add(movesLabel);
	                boxMemberPanel.add(swapButton);
	                boxMemberPanel.add(moveButton);
	                boxMemberPanel.add(seeForgottenMoves);
	                boxMemberPanel.add(rareCandy);
	                boxMemberPanel.add(releaseButton);
	                JOptionPane.showMessageDialog(null, boxMemberPanel, "Box member details", JOptionPane.PLAIN_MESSAGE);
		        }
		    });
		}
		
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
				if (!foe.isFainted()) boxButton.setVisible(false);
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
		
//		trainers = new Trainer[]{
//				new Trainer("A", new Pokemon[]{new Pokemon(-14, 3, false, true)}, 100),
//				new Trainer("B", new Pokemon[]{new Pokemon(-24, 4, false, true), new Pokemon(-24, 4, false, true)}, 100),
//				new Trainer("C", new Pokemon[]{new Pokemon(-12, 5, false, true), new Pokemon(-10, 6, false, true)}, 100),
//				new Trainer("D", new Pokemon[]{new Pokemon(-33, 7, false, true), new Pokemon(-33, 7, false, true)}, 100),
//				new Trainer("E", new Pokemon[]{new Pokemon(-18, 8, false, true)}, 100),
//				new Trainer("F", new Pokemon[]{new Pokemon(-12, 9, false, true)}, 100),
//				new Trainer("Rival 1", new Pokemon[]{new Pokemon(-130, 7, false, true)}, 500),
//				new Trainer("G", new Pokemon[]{new Pokemon(-42, 7, false, true), new Pokemon(-42, 8, false, true)}, 100),
//				new Trainer("TT", new Pokemon[]{new Pokemon(-24, 10, false, true), new Pokemon(-24, 10, false, true), new Pokemon(-24, 10, false, true)}, 100),
//				new Trainer("UU", new Pokemon[]{new Pokemon(-24, 15, false, true)}, 100),
//				new Trainer("1 Gym A", new Pokemon[]{new Pokemon(-18, 5, false, true), new Pokemon(-18, 5, false, true)}, 200),
//				new Trainer("1 Gym B", new Pokemon[]{new Pokemon(-18, 12, false, true)}, 200),
//				new Trainer("1 Gym C", new Pokemon[]{new Pokemon(-18, 6, false, true), new Pokemon(-21, 4, false, true), new Pokemon(-18, 8, false, true)}, 200),
//				new Trainer("1 Gym D", new Pokemon[]{new Pokemon(-21, 7, false, true), new Pokemon(-21, 7, false, true), new Pokemon(-21, 9, false, true)}, 200),
//				new Trainer("1 Gym Leader 1", new Pokemon[]{new Pokemon(-18, 7, false, true), new Pokemon(-18, 13, false, true), new Pokemon(-21, 9, false, true)}, 500),
//				new Trainer("2 Gym A", new Pokemon[]{new Pokemon(-11, 16, false, true)}, 200),
//				new Trainer("2 Gym B", new Pokemon[]{new Pokemon(-10, 10, false, true), new Pokemon(-10, 10, false, true)}, 200),
//				new Trainer("2 Gym Leader 1", new Pokemon[]{new Pokemon(-10, 10, false, true), new Pokemon(-12, 10, false, true), new Pokemon(-11, 19, false, true)}, 500),
//				new Trainer("H", new Pokemon[]{new Pokemon(-71, 17, false, true)}, 100),
//				new Trainer("I", new Pokemon[]{new Pokemon(-106, 14, false, true), new Pokemon(-107, 15, false, true)}, 100),
//				new Trainer("Rival 2", new Pokemon[]{new Pokemon(-131, 18, false, true), new Pokemon(-24, 12, false, true)}, 500),
//				new Trainer("3 Gym A", new Pokemon[]{new Pokemon(-37, 12, false, true), new Pokemon(-37, 14, false, true), new Pokemon(-37, 16, false, true)}, 200),
//				new Trainer("3 Gym B", new Pokemon[]{new Pokemon(-41, 21, false, true)}, 200),
//				new Trainer("3 Gym C", new Pokemon[]{new Pokemon(-38, 15, false, true), new Pokemon(-38, 18, false, true)}, 200),
//				new Trainer("3 Gym Leader 1", new Pokemon[]{new Pokemon(-37, 15, false, true), new Pokemon(-40, 17, false, true), new Pokemon(-41, 22, false, true)}, 500),
//				new Trainer("J", new Pokemon[]{new Pokemon(-46, 16, false, true)}, 100),
//				new Trainer("K", new Pokemon[]{new Pokemon(-24, 14, false, true), new Pokemon(-24, 14, false, true)}, 100),
//				new Trainer("L", new Pokemon[]{new Pokemon(-51, 11, false, true), new Pokemon(-49, 16, false, true)}, 100),
//				new Trainer("M", new Pokemon[]{new Pokemon(-71, 18, false, true)}, 100),
//				new Trainer("N", new Pokemon[]{new Pokemon(-75, 16, false, true), new Pokemon(-75, 17, false, true)}, 100),
//				new Trainer("O", new Pokemon[]{new Pokemon(-106, 20, false, true)}, 100),
//				new Trainer("P", new Pokemon[]{new Pokemon(-46, 12, false, true), new Pokemon(-44, 12, false, true), new Pokemon(-46, 12, false, true), new Pokemon(-44, 12, false, true)}, 100),
//				new Trainer("Rival 3", new Pokemon[]{new Pokemon(-131, 25, false, true), new Pokemon(-24, 16, false, true), new Pokemon(-21, 19, false, true)}, 500),
//				new Trainer("4 Gym A", new Pokemon[]{new Pokemon(-46, 15, false, true), new Pokemon(-44, 20, false, true)}, 200),
//				new Trainer("4 Gym Leader 1", new Pokemon[]{new Pokemon(-43, 16, false, true), new Pokemon(-46, 17, false, true), new Pokemon(-46, 24, false, true), new Pokemon(-44, 21, false, true)}, 500),
//				new Trainer("Q", new Pokemon[]{new Pokemon(-50, 25, false, true)}, 100),
//				new Trainer("5 Gym A", new Pokemon[]{new Pokemon(-63, 14, false, true), new Pokemon(-63, 16, false, true), new Pokemon(-63, 18, false, true)}, 200),
//				new Trainer("5 Gym B", new Pokemon[]{new Pokemon(-66, 27, false, true)}, 200),
//				new Trainer("5 Gym C", new Pokemon[]{new Pokemon(-24, 18, false, true), new Pokemon(-25, 21, false, true)}, 200),
//				new Trainer("5 Gym D", new Pokemon[]{new Pokemon(-69, 21, false, true)}, 200),
//				new Trainer("5 Gym E", new Pokemon[]{new Pokemon(-70, 22, false, true)}, 200),
//				new Trainer("5 Gym Leader 1", new Pokemon[]{new Pokemon(-65, 20, false, true), new Pokemon(-66, 30, false, true), new Pokemon(-69, 28, false, true), new Pokemon(-64, 25, false, true)}, 500),
//				new Trainer("R", new Pokemon[]{new Pokemon(-106, 24, false, true)}, 100),
//				new Trainer("S", new Pokemon[]{new Pokemon(-92, 20, false, true), new Pokemon(-92, 20, false, true), new Pokemon(-46, 15, false, true)}, 100),
//				new Trainer("T", new Pokemon[]{new Pokemon(-75, 18, false, true), new Pokemon(-77, 23, false, true)}, 100),
//				new Trainer("U", new Pokemon[]{new Pokemon(-84, 24, false, true)}, 100),
//				new Trainer("V", new Pokemon[]{new Pokemon(-86, 25, false, true)}, 100),
//				new Trainer("W", new Pokemon[]{new Pokemon(-61, 23, false, true), new Pokemon(-55, 23, false, true)}, 100),
//				new Trainer("X", new Pokemon[]{new Pokemon(-8, 24, false, true), new Pokemon(-80, 25, false, true)}, 100),
//				new Trainer("6 Gym A", new Pokemon[]{new Pokemon(-90, 32, false, true)}, 200),
//				new Trainer("6 Gym B", new Pokemon[]{new Pokemon(-87, 30, false, true)}, 200),
//				new Trainer("6 Gym C", new Pokemon[]{new Pokemon(-89, 24, false, true), new Pokemon(-89, 24, false, true)}, 200),
//				new Trainer("6 Gym D", new Pokemon[]{new Pokemon(-86, 24, false, true), new Pokemon(-86, 27, false, true)}, 200),
//				new Trainer("6 Gym E", new Pokemon[]{new Pokemon(-69, 29, false, true)}, 200),
//				new Trainer("6 Gym F", new Pokemon[]{new Pokemon(-90, 31, false, true)}, 200),
//				new Trainer("6 Gym Leader 1", new Pokemon[]{new Pokemon(-89, 29, false, true), new Pokemon(-90, 34, false, true), new Pokemon(-87, 36, false, true)}, 500),
//				new Trainer("Y", new Pokemon[]{new Pokemon(-93, 24, false, true), new Pokemon(-89, 23, false, true)}, 100),
//				new Trainer("Z", new Pokemon[]{new Pokemon(-75, 26, false, true)}, 100),
//				new Trainer("AA", new Pokemon[]{new Pokemon(-97, 19, false, true), new Pokemon(-98, 26, false, true)}, 100),
//				new Trainer("BB", new Pokemon[]{new Pokemon(-41, 26, false, true)}, 100),
//				new Trainer("CC", new Pokemon[]{new Pokemon(-46, 25, false, true), new Pokemon(-47, 31, false, true)}, 100),
//				new Trainer("CA", new Pokemon[]{new Pokemon(-18, 10, false, true), new Pokemon(-21, 10, false, true)}, 100),
//				new Trainer("CB", new Pokemon[]{new Pokemon(-18, 21, false, true), new Pokemon(-19, 26, false, true), new Pokemon(-19, 28, false, true)}, 100),
//				new Trainer("CD", new Pokemon[]{new Pokemon(-38, 21, false, true), new Pokemon(-40, 21, false, true)}, 100),
//				new Trainer("CE", new Pokemon[]{new Pokemon(-33, 20, false, true), new Pokemon(-33, 20, false, true), new Pokemon(-34, 25, false, true)}, 100),
//				new Trainer("DD", new Pokemon[]{new Pokemon(-80, 10, false, true), new Pokemon(-74, 8, false, true)}, 100),
//				new Trainer("EE", new Pokemon[]{new Pokemon(-18, 9, false, true), new Pokemon(-18, 9, false, true), new Pokemon(-18, 9, false, true), new Pokemon(-18, 9, false, true)}, 100),
//				new Trainer("FF", new Pokemon[]{new Pokemon(-25, 25, false, true), new Pokemon(-25, 25, false, true)}, 100),
//				new Trainer("GG", new Pokemon[]{new Pokemon(-63, 23, false, true), new Pokemon(-64, 27, false, true)}, 100),
//				new Trainer("HH", new Pokemon[]{new Pokemon(-65, 23, false, true), new Pokemon(-66, 27, false, true)}, 100),
//				new Trainer("II", new Pokemon[]{new Pokemon(-69, 30, false, true)}, 100),
//				new Trainer("JJ", new Pokemon[]{new Pokemon(-68, 20, false, true), new Pokemon(-68, 25, false, true), new Pokemon(-68, 25, false, true)}, 100),
//				new Trainer("VV", new Pokemon[]{new Pokemon(-28, 36, false, true)}, 100),
//				new Trainer("Rival 4", new Pokemon[]{new Pokemon(-131, 33, false, true), new Pokemon(-25, 24, false, true), new Pokemon(-22, 29, false, true), new Pokemon(-41, 28, false, true)}, 500),
//				new Trainer("MM", new Pokemon[]{new Pokemon(-35, 12, false, true), new Pokemon(-35, 12, false, true), new Pokemon(-36, 12, false, true), new Pokemon(-36, 12, false, true)}, 100),
//				new Trainer("NN", new Pokemon[]{new Pokemon(-14, 13, false, true), new Pokemon(-29, 13, false, true), new Pokemon(-29, 14, false, true)}, 100),
//				new Trainer("7 Gym A", new Pokemon[]{new Pokemon(-75, 30, false, true), new Pokemon(-75, 30, false, true)}, 200),
//				new Trainer("7 Gym B", new Pokemon[]{new Pokemon(-78, 36, false, true)}, 200),
//				new Trainer("7 Gym C", new Pokemon[]{new Pokemon(-80, 26, false, true), new Pokemon(-81, 29, false, true)}, 200),
//				new Trainer("7 Gym D", new Pokemon[]{new Pokemon(-84, 26, false, true), new Pokemon(-85, 33, false, true)}, 200),
//				new Trainer("7 Gym Leader 1", new Pokemon[]{new Pokemon(-78, 39, false, true), new Pokemon(-85, 30, false, true), new Pokemon(-76, 41, false, true)}, 500),
//				new Trainer("Rival 5", new Pokemon[]{new Pokemon(-132, 36, false, true), new Pokemon(-25, 27, false, true), new Pokemon(-22, 32, false, true), new Pokemon(-41, 29, false, true), new Pokemon(-78, 35, false, true)}, 500),
//				new Trainer("KK", new Pokemon[]{new Pokemon(-96, 25, false, true), new Pokemon(-97, 20, false, true), new Pokemon(-98, 30, false, true)}, 100),
//				new Trainer("LL", new Pokemon[]{new Pokemon(-39, 25, false, true), new Pokemon(-41, 25, false, true), new Pokemon(-39, 30, false, true), new Pokemon(-41, 30, false, true)}, 100),
//				new Trainer("OO", new Pokemon[]{new Pokemon(-39, 30, false, true), new Pokemon(-41, 30, false, true)}, 100),
//				new Trainer("PP", new Pokemon[]{new Pokemon(-45, 33, false, true)}, 100),
//				new Trainer("Rival 6", new Pokemon[]{new Pokemon(-132, 45, false, true), new Pokemon(-25, 33, false, true), new Pokemon(-23, 40, false, true), new Pokemon(-41, 34, false, true), new Pokemon(-78, 44, false, true), new Pokemon(-87, 38, false, true)}, 500),
//				new Trainer("QQ", new Pokemon[]{new Pokemon(-64, 30, false, true), new Pokemon(-66, 30, false, true)}, 100),
//				new Trainer("RR", new Pokemon[]{new Pokemon(-78, 35, false, true)}, 100),
//				new Trainer("SS", new Pokemon[]{new Pokemon(-25, 30, false, true), new Pokemon(-25, 30, false, true), new Pokemon(-69, 28, false, true), new Pokemon(-70, 28, false, true), new Pokemon(-64, 30, false, true), new Pokemon(-66, 30, false, true)}, 100),
//				new Trainer("8 Gym A", new Pokemon[]{new Pokemon(-100, 30, false, true), new Pokemon(-100, 30, false, true), new Pokemon(-100, 30, false, true), new Pokemon(-100, 30, false, true), new Pokemon(-100, 30, false, true), new Pokemon(-100, 30, false, true)}, 200),
//				new Trainer("8 Gym B", new Pokemon[]{new Pokemon(-101, 40, false, true), new Pokemon(-101, 40, false, true)}, 200),
//				new Trainer("8 Gym C", new Pokemon[]{new Pokemon(-70, 45, false, true), new Pokemon(-78, 42, false, true)}, 200),
//				new Trainer("8 Gym D", new Pokemon[]{new Pokemon(-122, 50, false, true)}, 200),
//				new Trainer("8 Gym E", new Pokemon[]{new Pokemon(-101, 41, false, true), new Pokemon(-100, 32, false, true), new Pokemon(-78, 39, false, true)}, 200),
//				new Trainer("8 Gym F", new Pokemon[]{new Pokemon(-122, 30, false, true), new Pokemon(-122, 35, false, true), new Pokemon(-122, 40, false, true), new Pokemon(-122, 45, false, true)}, 200),
//				new Trainer("8 Gym Leader 1", new Pokemon[]{new Pokemon(-100, 33, false, true), new Pokemon(-101, 45, false, true), new Pokemon(-79, 55, false, true), new Pokemon(-122, 50, false, true), new Pokemon(-70, 48, false, true), new Pokemon(-102, 55, false, true)}, 500),
//				new Trainer("TA", new Pokemon[]{new Pokemon(-112, 50, false, true)}, 100),
//				new Trainer("TB", new Pokemon[]{new Pokemon(-113, 50, false, true)}, 100),
//				new Trainer("TC", new Pokemon[]{new Pokemon(-114, 50, false, true)}, 100),
//				new Trainer("TD", new Pokemon[]{new Pokemon(-115, 50, false, true)}, 100),
//				new Trainer("TE", new Pokemon[]{new Pokemon(-116, 50, false, true)}, 100),
//				new Trainer("TF", new Pokemon[]{new Pokemon(-117, 50, false, true)}, 100),
//				new Trainer("TG", new Pokemon[]{new Pokemon(-118, 50, false, true)}, 100),
//				new Trainer("TH", new Pokemon[]{new Pokemon(-119, 50, false, true)}, 100),
//				new Trainer("TI", new Pokemon[]{new Pokemon(-120, 50, false, true)}, 100),
//				new Trainer("TJ", new Pokemon[]{new Pokemon(-121, 50, false, true)}, 100),
//				new Trainer("TK", new Pokemon[]{new Pokemon(-122, 50, false, true)}, 100),
//				new Trainer("TL", new Pokemon[]{new Pokemon(-123, 50, false, true)}, 100),
//				new Trainer("TA 2", new Pokemon[]{new Pokemon(-112, 80, false, true)}, 100),
//				new Trainer("TB 2", new Pokemon[]{new Pokemon(-113, 80, false, true)}, 100),
//				new Trainer("TC 2", new Pokemon[]{new Pokemon(-114, 80, false, true)}, 100),
//				new Trainer("TD 2", new Pokemon[]{new Pokemon(-115, 80, false, true)}, 100),
//				new Trainer("TE 2", new Pokemon[]{new Pokemon(-116, 80, false, true)}, 100),
//				new Trainer("TF 2", new Pokemon[]{new Pokemon(-117, 80, false, true)}, 100),
//				new Trainer("TG 2", new Pokemon[]{new Pokemon(-118, 80, false, true)}, 100),
//				new Trainer("TH 2", new Pokemon[]{new Pokemon(-119, 80, false, true)}, 100),
//				new Trainer("TI 2", new Pokemon[]{new Pokemon(-120, 80, false, true)}, 100),
//				new Trainer("TJ 2", new Pokemon[]{new Pokemon(-121, 80, false, true)}, 100),
//				new Trainer("TK 2", new Pokemon[]{new Pokemon(-122, 80, false, true)}, 100),
//				new Trainer("TL 2", new Pokemon[]{new Pokemon(-123, 80, false, true)}, 100),
//				new Trainer("TM", new Pokemon[]{new Pokemon(-92, 50, false, true), new Pokemon(-92, 50, false, true), new Pokemon(-92, 50, false, true), new Pokemon(-92, 50, false, true)}, 100),
//				new Trainer("TN", new Pokemon[]{new Pokemon(-47, 50, false, true), new Pokemon(-45, 50, false, true), new Pokemon(-37, 50, false, true), new Pokemon(-37, 50, false, true), new Pokemon(-90, 50, false, true), new Pokemon(-90, 50, false, true)}, 100),
//				new Trainer("TO", new Pokemon[]{new Pokemon(-90, 50, false, true), new Pokemon(-43, 50, false, true), new Pokemon(-37, 50, false, true), new Pokemon(-87, 50, false, true), new Pokemon(-41, 50, false, true), new Pokemon(-91, 55, false, true)}, 100),
//				new Trainer("1 Gym Leader 2", new Pokemon[]{new Pokemon(-20, 50, false, true), new Pokemon(-23, 55, false, true), new Pokemon(-20, 50, false, true), new Pokemon(-118, 58, false, true), new Pokemon(-3, 57, false, true), new Pokemon(-6, 57, false, true)}, 500),
//				new Trainer("2 Gym Leader 2", new Pokemon[]{new Pokemon(-13, 60, false, true), new Pokemon(-3, 60, false, true), new Pokemon(-11, 54, false, true), new Pokemon(-113, 61, false, true), new Pokemon(-60, 58, false, true), new Pokemon(-126, 61, false, true)}, 500),
//				new Trainer("3 Gym Leader 2", new Pokemon[]{new Pokemon(-41, 59, false, true), new Pokemon(-39, 60, false, true), new Pokemon(-41, 61, false, true), new Pokemon(-94, 64, false, true), new Pokemon(-94, 64, false, true), new Pokemon(-39, 60, false, true)}, 500),
//				new Trainer("4 Gym Leader 2", new Pokemon[]{new Pokemon(-43, 66, false, true), new Pokemon(-45, 64, false, true), new Pokemon(-47, 63, false, true), new Pokemon(-45, 65, false, true), new Pokemon(-117, 67, false, true), new Pokemon(-83, 62, false, true)}, 500),
//				new Trainer("5 Gym Leader 2", new Pokemon[]{new Pokemon(-132, 72, false, true), new Pokemon(-66, 69, false, true), new Pokemon(-9, 70, false, true), new Pokemon(-64, 70, false, true), new Pokemon(-144, 67, false, true), new Pokemon(-115, 71, false, true)}, 500),
//				new Trainer("6 Gym Leader 2", new Pokemon[]{new Pokemon(-90, 73, false, true), new Pokemon(-41, 72, false, true), new Pokemon(-90, 73, false, true), new Pokemon(-87, 74, false, true), new Pokemon(-119, 76, false, true), new Pokemon(-69, 74, false, true)}, 500),
//				new Trainer("7 Gym Leader 2", new Pokemon[]{new Pokemon(-85, 75, false, true), new Pokemon(-108, 77, false, true), new Pokemon(-129, 78, false, true), new Pokemon(-76, 76, false, true), new Pokemon(-114, 77, false, true), new Pokemon(-81, 76, false, true)}, 500),
//				new Trainer("8 Gym Leader 2", new Pokemon[]{new Pokemon(-79, 82, false, true), new Pokemon(-102, 83, false, true), new Pokemon(-102, 83, false, true), new Pokemon(-122, 85, false, true), new Pokemon(-79, 82, false, true), new Pokemon(-102, 83, false, true)}, 500),
//				new Trainer("Rival 7", new Pokemon[]{new Pokemon(-132, 90, false, true), new Pokemon(-25, 71, false, true), new Pokemon(-23, 82, false, true), new Pokemon(-41, 74, false, true), new Pokemon(-79, 88, false, true), new Pokemon(-87, 78, false, true)}, 500),
//		};
		
//		trainerSelect = new JComboBox<>(getUnbeatenTrainers());
//		trainerSelect.setBounds(13, 23, 110, 21);
//		updateTrainers();
//		trainerSelect.setVisible(true);
//		playerPanel.add(trainerSelect);
		
		
		JTextArea console = new JTextArea();
		console.setEditable(false);
		
		PrintStream printStream = new PrintStream(new CustomOutputStream(console));
		
		System.setOut(printStream);
		
		JScrollPane scrollPane = new JScrollPane(console);
		scrollPane.setBounds(10, 295, 610, 190);
		playerPanel.add(scrollPane);
		
//		trainerSelect.addActionListener(new ActionListener() {
//			@Override
//			public void actionPerformed(ActionEvent e) {
//				foeTrainer = (Trainer) trainerSelect.getSelectedItem();
//				if (me.trainersBeat.contains(foeTrainer.toString())) {
//					JOptionPane.showMessageDialog(null, foeTrainer.toString() + " already beaten!");
//                    return;
//				}
//				if (((Trainer) trainerSelect.getSelectedItem()).getTeam() == null) {
//					return;
//				}
//				foe = ((Trainer) trainerSelect.getSelectedItem()).getTeam()[0];
//				System.out.println("\nYou are challenged by " + foeTrainer.toString() + "!");
//				System.out.println(foeTrainer.toString() + " sends out " + foeTrainer.getCurrent().name + "!");
//				updateFoe();
//				boxButton.setVisible(false);
//				healButton.setVisible(false);
//				encounterButton.setVisible(false);
//				encounterInput.setVisible(false);
//				trainerSelect.setVisible(false);
//				me.clearBattled();
//				me.getCurrent().clearVolatile();
//				me.getCurrent().battled = true;
//			}
//		});
		
		addButton.addActionListener(e -> {
			if (!foe.isFainted()) {
				me.catchPokemon(foe);
				displayParty();
				updateFoe();
				boxButton.setVisible(true);
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
					boxButton.setVisible(true);
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
            JPanel teamMemberPanel = new JPanel();
            teamMemberPanel.setLayout(new BoxLayout(teamMemberPanel, BoxLayout.Y_AXIS));
            JLabel nameLabel = new JLabel("Name: N/A");
            nameLabel.setText("Name: " + me.getCurrent().getName());
            JGradientButton type1 = new JGradientButton("N/A");
            JGradientButton type2 = new JGradientButton("N/A");
        	type1.setText(me.getCurrent().type1.toString());
        	type1.setBackground(me.getCurrent().type1.getColor());
        	if (me.getCurrent().type2 != null) {
        		type2.setText(me.getCurrent().type2.toString());
            	type2.setBackground(me.getCurrent().type2.getColor());
        	}
            JLabel levelLabel = new JLabel("Level: N/A");
            if (me.getCurrent() != null) levelLabel.setText("Level: " + me.getCurrent().getLevel());
            JLabel statsLabel = new JLabel("Stats: N/A");
            if (me.getCurrent() != null) statsLabel.setText("Stats: " + intArrayToString(me.getCurrent().stats));
            JLabel ivLabel = new JLabel("IVs: N/A");
            if (me.getCurrent() != null) ivLabel.setText("IVs: " + intArrayToString(me.getCurrent().getIVs()));
            JLabel hpLabel = new JLabel("HP: N/A");
            if (me.getCurrent() != null) hpLabel.setText("HP: " + me.getCurrent().currentHP + " / " + me.getCurrent().getStat(0));
            JLabel movesLabel = new JLabel("Moves: N/A");
            if (me.getCurrent() != null) movesLabel.setText("Moves: " + movesToString(me.getCurrent()));
            JLabel statusLabel = new JLabel("Status: N/A");
            if (me.getCurrent() != null) statusLabel.setText("Status: " + me.getCurrent().status);
            teamMemberPanel.add(nameLabel);
            teamMemberPanel.add(type1);
            if (me.getCurrent().type2 != null) teamMemberPanel.add(type2);
            teamMemberPanel.add(levelLabel);
            teamMemberPanel.add(statsLabel);
            teamMemberPanel.add(ivLabel);
            teamMemberPanel.add(hpLabel);
            teamMemberPanel.add(movesLabel);
            teamMemberPanel.add(statusLabel);

            JOptionPane.showMessageDialog(null, teamMemberPanel, "Party member details", JOptionPane.PLAIN_MESSAGE);
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
			playerPanel.add(infoButton);
			infoButton.setVisible(true);
			playerPanel.add(fightButton);
			fightButton.setVisible(true);
			playerPanel.add(idInput);
			idInput.setVisible(true);
			playerPanel.add(levelInput);
			levelInput.setVisible(true);
			playerPanel.add(boxButton);
			boxButton.setVisible(true);
			playerPanel.add(encounterButton);
			encounterInput.setVisible(true);
			playerPanel.add(encounterInput);
			encounterButton.setVisible(true);
			standard.setVisible(true);
			playerPanel.add(standard);
			rdbtnFishing.setVisible(true);
			playerPanel.add(rdbtnFishing);
			rdbtnHeadbutt.setVisible(true);
			playerPanel.add(rdbtnHeadbutt);
			rdbtnSurfing.setVisible(true);
			playerPanel.add(rdbtnSurfing);
			morning.setVisible(true);
			playerPanel.add(morning);
			day.setVisible(true);
			playerPanel.add(day);
			night.setVisible(true);
			playerPanel.add(night);
			//trainerSelect.setVisible(true);
			//playerPanel.add(trainerSelect);
			scrollPane.setVisible(true);
			playerPanel.add(scrollPane);
			playerPanel.repaint();
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
							boxButton.setVisible(true);
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
			            JPanel teamMemberPanel = new JPanel();
			            teamMemberPanel.setLayout(new BoxLayout(teamMemberPanel, BoxLayout.Y_AXIS));
			            JLabel nameLabel = new JLabel("Name: N/A");
			            if (me.team[index] != null) nameLabel.setText("Name: " + me.team[index].getName());
			            JLabel levelLabel = new JLabel("Level: N/A");
			            if (me.team[index] != null) levelLabel.setText("Level: " + me.team[index].getLevel());
			            JLabel statsLabel = new JLabel("Stats: N/A");
			            if (me.team[index] != null) statsLabel.setText("Stats: " + intArrayToString(me.team[index].stats));
			            JLabel ivLabel = new JLabel("IVs: N/A");
			            if (me.team[index] != null) ivLabel.setText("IVs: " + intArrayToString(me.team[index].getIVs()));
			            JLabel hpLabel = new JLabel("HP: N/A");
			            if (me.team[index] != null) hpLabel.setText("HP: " + me.team[index].currentHP + " / " + me.team[index].getStat(0));
			            JLabel movesLabel = new JLabel("Moves: N/A");
			            if (me.team[index] != null) movesLabel.setText("Moves: " + movesToString(me.team[index]));
			            JLabel statusLabel = new JLabel("Status: N/A");
			            if (me.team[index] != null) statusLabel.setText("Status: " + me.team[index].status);
			            teamMemberPanel.add(nameLabel);
			            teamMemberPanel.add(levelLabel);
			            teamMemberPanel.add(statsLabel);
			            teamMemberPanel.add(ivLabel);
			            teamMemberPanel.add(hpLabel);
			            teamMemberPanel.add(movesLabel);
			            teamMemberPanel.add(statusLabel);

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
		String selectedEncounterType = encounterType.getSelection().getActionCommand();
		String selectedTime = time.getSelection().getActionCommand();
	    ArrayList<Encounter> encounters = Encounter.getEncounters(routeName, selectedEncounterType, selectedTime);

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
					//me.trainersBeat.add(foeTrainer.toString());
//					boxButton.setVisible(true);
//					healButton.setVisible(true);
//					encounterButton.setVisible(true);
//					encounterInput.setVisible(true);
//					trainerSelect.setVisible(true);
//					updateTrainers();
					// Show the prompt with the specified text
					me.money += foeTrainer.getMoney();
		            JOptionPane.showMessageDialog(null, foeTrainer.toString() + " was defeated!\nWon $" + foeTrainer.getMoney() + "!");
		            if (foeTrainer.getMoney() == 500 && me.badges < 8) me.badges++;

		            // Close the current Battle JFrame
		            dispose();
				}
			} else {
				healButton.setVisible(true);
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
		if (foe.isFainted()) boxButton.setVisible(true);
		
	}
	
	private void displayBox() {
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
	
	private void fightMon() {
		try {
			if (Integer.parseInt(idInput.getText()) >= -144 && Integer.parseInt(idInput.getText()) <= 237) {
				foe = new Pokemon(Integer.parseInt(idInput.getText()), Integer.parseInt(levelInput.getText()), false, true);
			} else {
				foe = new Pokemon(-10, 5, false, true);
			}
			updateFoe();
			boxButton.setVisible(false);
		} catch (NumberFormatException e1) {
			foe = new Pokemon(-10, 5, false, true);
			updateFoe();
			boxButton.setVisible(false);
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
