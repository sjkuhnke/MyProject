package Entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.SwingUtilities;

import Overworld.GamePanel;
import Overworld.KeyHandler;
import Overworld.Main;
import Swing.Battle.JGradientButton;
import Swing.Player;
import Swing.Pokemon;
import Swing.Status;
import Swing.Bag.Entry;

public class PlayerCharacter extends Entity {
	
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public Player p;
	public boolean[] trainersBeat = new boolean[Main.trainers.length];
	
	public PlayerCharacter(GamePanel gp, KeyHandler keyH) {
		super(gp);
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2 - (gp.tileSize/2);
		
		solidArea = new Rectangle();
		solidArea.x = 8;
		solidArea.y = 16;
		solidAreaDefaultX = solidArea.x;
		solidAreaDefaultY = solidArea.y;
		solidArea.width = 32;
		solidArea.height = 32;
		
		setDefaultValues();
		getPlayerImage();
	}
	
	public void setDefaultValues() {
		worldX = gp.tileSize * 23;
		worldY = gp.tileSize * 21;
		speed = 4;
		direction = "down";
	}
	public void getPlayerImage() {
		up1 = setup("/player/red2");
		up2 = setup("/player/red2_1");
		down1 = setup("/player/red1");
		down2 = setup("/player/red1_1");
		left1 = setup("/player/red3");
		left2 = setup("/player/red3_1");
		right1 = setup("/player/red4");
		right2 = setup("/player/red4_1");
	}
	
	public void update() {
		if (keyH.upPressed || keyH.downPressed || keyH.leftPressed || keyH.rightPressed) {
			spriteCounter++;
			if (keyH.upPressed) {
				direction = "up";
				
			} else if (keyH.downPressed) {
				direction = "down";
				
			} else if (keyH.leftPressed) {
				direction = "left";
				
			} else if (keyH.rightPressed) {
				direction = "right";
				
			}
			
			collisionOn = false;
			gp.cChecker.checkTile(this);
			
			gp.cChecker.checkEntity(this, gp.npc);
			
			if (!collisionOn) {
				switch(direction) {
				case "up":
					worldY -= speed;
					break;
				case "down":
					worldY += speed;
					break;
				case "left":
					worldX -= speed;
					break;
				case "right":
					worldX += speed;
					break;
				}
			}
			
			if (spriteCounter > 8) {
				if (spriteNum == 1) {
					spriteNum = 2;
				}
				else if (spriteNum == 2) {
					spriteNum = 1;
				}
				spriteCounter = 0;
			}
		}
		if (keyH.dPressed) {
			keyH.pause();
			showMenu();
		}
		if (keyH.pPressed) {
			keyH.pause();
			showParty();
		}
		if (keyH.sPressed) {
			speed = 8;
		} else {
			speed = 4;
		}
		if (keyH.bPressed) {
			keyH.pause();
			showBag();
		}
		if (keyH.backslashPressed) {
			saveGame();
		}
		if (keyH.wPressed) {
			int npcIndex = gp.cChecker.checkEntity(this, gp.npc);
			if (npcIndex != 999 && gp.npc[npcIndex] instanceof NPC_Nurse) interactNurse();
			if (npcIndex != 999 && gp.npc[npcIndex] instanceof NPC_Clerk) interactClerk();
			if (npcIndex != 999 && gp.npc[npcIndex] instanceof NPC_Trainer) gp.startBattle(gp.npc[npcIndex].trainer);
			if (npcIndex != 999 && gp.npc[npcIndex] instanceof NPC_GymLeader) gp.startBattle(gp.npc[npcIndex].trainer);
		}
		for (int i = 0; i < gp.npc.length; i++) {
			if (gp.cChecker.checkTrainer(this, gp.npc[i])) gp.startBattle(gp.npc[i].trainer);
		}
	}
	
	private void showMenu() {
		JPanel menu = new JPanel();
	    menu.setLayout(new GridLayout(6, 1));
	    
	    JButton party = new JButton("Party");
	    JButton bag = new JButton("Bag");
	    JButton save = new JButton("Save");
	    JButton player = new JButton("Player");
	    
	    party.addActionListener(e -> {
	    	showParty();
	    });
	    bag.addActionListener(e -> {
	    	showBag();
	    });
	    save.addActionListener(e -> {
	    	saveGame();
	    });
	    player.addActionListener(e -> {
	    	JPanel playerInfo = new JPanel();
	    	playerInfo.setLayout(new GridLayout(6, 1));
	    	
	    	JLabel moneyLabel = new JLabel();
	    	moneyLabel.setText("$" + p.money);
	    	JLabel badgesLabel = new JLabel();
	    	badgesLabel.setText(p.badges + " Badges");
	    	
	    	playerInfo.add(moneyLabel);
	    	playerInfo.add(badgesLabel);
	    	
	    	JOptionPane.showMessageDialog(null, playerInfo, "Player Info", JOptionPane.PLAIN_MESSAGE);
	    });
	    menu.add(party);
	    menu.add(bag);
	    menu.add(save);
	    menu.add(player);
	    
	    JOptionPane.showMessageDialog(null, menu, "Menu", JOptionPane.PLAIN_MESSAGE);
	    keyH.resume();
	}

	private void saveGame() {
		keyH.pause();
		int option = JOptionPane.showOptionDialog(null,
				"Would you like to save the game?",
				"Confirm Save",
	            JOptionPane.YES_NO_OPTION,
	            JOptionPane.QUESTION_MESSAGE,
	            null, null, null);
	    if (option == JOptionPane.YES_OPTION) {
	    	try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream("player.dat"))) {
            	gp.player.p.setPosX(gp.player.worldX);
            	gp.player.p.setPosY(gp.player.worldY);
                oos.writeObject(gp.player.p);
                oos.close();
                JOptionPane.showMessageDialog(null, "Game saved sucessfully!");
            } catch (IOException ex) {
            	JOptionPane.showMessageDialog(null, "Error writing object to file: " + ex.getMessage());
            }
	    }
	    keyH.resume();
	}

	private void interactNurse() {
		if (keyH.wPressed) {
			keyH.pause();
			int option = JOptionPane.showOptionDialog(null,
					"Welcome to the Pokemon Center!\nDo you want to rest your Pokemon?",
					"Heal",
		            JOptionPane.YES_NO_OPTION,
		            JOptionPane.QUESTION_MESSAGE,
		            null, null, null);
		    if (option == JOptionPane.YES_OPTION) {
		    	for (Pokemon member : p.team) {
					if (member != null) member.heal();
				}
		    	JOptionPane.showMessageDialog(null, "Your Pokemon were healed to full health!");
		    	
		    }
		    keyH.resume();
		}
	}
	
	private void interactClerk() {
		if (keyH.wPressed) {
			keyH.pause();
			
			JPanel partyPanel = new JPanel();
		    partyPanel.setLayout(new GridLayout(6, 1));
			JOptionPane.showMessageDialog(null, partyPanel, "Shop", JOptionPane.PLAIN_MESSAGE);
			keyH.resume();
		}
	}

	private void showParty() {
	    JPanel partyPanel = new JPanel();
	    partyPanel.setLayout(new GridLayout(6, 1));

	    for (int j = 0; j < 6; j++) {
	    	JButton party = setUpPartyButton(j);
	    	JProgressBar partyHP = setupHPBar(j);
	        final int index = j;
	        
	        party.addActionListener(e -> {
	            JPanel teamMemberPanel = new JPanel();
	            teamMemberPanel.setLayout(new BoxLayout(teamMemberPanel, BoxLayout.Y_AXIS));
	            JLabel nameLabel = new JLabel("Name: N/A");
	            if (p.team[index] != null) nameLabel.setText("Name: " + p.team[index].getName());
	            JLabel levelLabel = new JLabel("Level: N/A");
	            if (p.team[index] != null) levelLabel.setText("Level: " + p.team[index].getLevel());
	            JLabel statsLabel = new JLabel("Stats: N/A");
	            if (p.team[index] != null) statsLabel.setText("Stats: " + intArrayToString(p.team[index].stats));
	            JLabel hpLabel = new JLabel("HP: N/A");
	            if (p.team[index] != null) hpLabel.setText("HP: " + p.team[index].currentHP + " / " + p.team[index].getStat(0));
	            JLabel movesLabel = new JLabel("Moves: N/A");
	            if (p.team[index] != null) movesLabel.setText("Moves: " + movesToString(p.team[index]));
	            JLabel statusLabel = new JLabel("Status: N/A");
	            if (p.team[index] != null) statusLabel.setText("Status: " + p.team[index].status);
	            
	            JButton swapButton = new JButton("Swap");
		        swapButton.addActionListener(f -> {
		            if (p.team[index] != null && p.team[index] != p.current) {
		                p.swap(p.team[index], index); // Call the swap method in the Player class
		                JOptionPane.getRootFrame().dispose();
		                showParty(); // Update the party display after swapping
		            }
		        });
	            teamMemberPanel.add(nameLabel);
	            teamMemberPanel.add(levelLabel);
	            teamMemberPanel.add(statsLabel);
	            teamMemberPanel.add(hpLabel);
	            teamMemberPanel.add(movesLabel);
	            teamMemberPanel.add(statusLabel);
	            if (p.team[index] != p.current && !p.team[index].isFainted()) teamMemberPanel.add(swapButton);

	            JOptionPane.showMessageDialog(null, teamMemberPanel, "Party member details", JOptionPane.PLAIN_MESSAGE);
	        });

	        JPanel memberPanel = new JPanel(new BorderLayout());
	        memberPanel.add(party, BorderLayout.NORTH);
	        memberPanel.add(partyHP, BorderLayout.SOUTH);
	        partyPanel.add(memberPanel);
	    }

	    JOptionPane.showMessageDialog(null, partyPanel, "Party", JOptionPane.PLAIN_MESSAGE);
	    keyH.resume();
	}
	
	private void showBag() {
		JPanel panel = new JPanel();
		panel.setLayout(new BoxLayout(panel, BoxLayout.Y_AXIS));

		ArrayList<Entry> bag = p.getBag().getItems();
		for (Entry i : bag) {
		    JButton item = new JButton();
		    item.setText(i.getItem().toString() + " x " + i.getCount());

		    item.addActionListener(e -> {
		        JPanel itemDesc = new JPanel();
		        itemDesc.setLayout(new BoxLayout(itemDesc, BoxLayout.Y_AXIS));
		        JLabel description = new JLabel(i.getItem().toString());
		        JLabel count = new JLabel("Count: " + i.getCount());

		        JButton useButton = new JButton("Use");
		        useButton.addActionListener(f -> {
		        	
		        	// POTIONS
		        	if (i.getItem().getHealAmount() != 0) {
		        		JPanel partyPanel = new JPanel();
		        	    partyPanel.setLayout(new GridLayout(6, 1));

		        	    
		        	    for (int j = 0; j < 6; j++) {
		        	    	JButton party = setUpPartyButton(j);
		        	    	JProgressBar partyHP = setupHPBar(j);
		        	        final int index = j;

		        	        party.addActionListener(g -> {
		        	        	if (p.team[index].currentHP == p.team[index].getStat(0)) {
		        	        		JOptionPane.showMessageDialog(null, "It won't have any effect.");
		        	        	} else {
		        	        		int difference = 0;
		        	        		if (i.getItem().getHealAmount() > 0) {
		        	        			difference = Math.min(i.getItem().getHealAmount(), p.team[index].getStat(0) - p.team[index].currentHP);
		        	        			p.team[index].currentHP += i.getItem().getHealAmount();
		        	        		} else {
		        	        			difference = p.team[index].getStat(0) - p.team[index].currentHP;
		        	        			p.team[index].currentHP = p.team[index].getStat(0);
		        	        			if (i.getItem().getID() == 8) p.team[index].status = Status.HEALTHY;
		        	        		}
			        	        	p.team[index].verifyHP();
			        	        	p.bag.remove(i.getItem());
			        	        	JOptionPane.showMessageDialog(null, p.team[index].name + " was healed by " + difference + " HP");
			        	        	SwingUtilities.getWindowAncestor(partyPanel).dispose();
			        	        	SwingUtilities.getWindowAncestor(itemDesc).dispose();
			        	        	SwingUtilities.getWindowAncestor(panel).dispose();
			        	        	showBag();
		        	        	}
		        	        });
		        	        
		        	        JPanel memberPanel = new JPanel(new BorderLayout());
		        	        memberPanel.add(party, BorderLayout.NORTH);
		        	        memberPanel.add(partyHP, BorderLayout.SOUTH);
		        	        partyPanel.add(memberPanel);
		        	    }

		        	    JOptionPane.showMessageDialog(null, partyPanel, "Party", JOptionPane.PLAIN_MESSAGE);
		        	}
		        	
		        	// RARE CANDY
		        	if (i.getItem().getID() == 22) {
		        		JPanel partyPanel = new JPanel();
		        	    partyPanel.setLayout(new GridLayout(6, 1));

		        	    for (int j = 0; j < 6; j++) {
		        	    	JButton party = setUpPartyButton(j);
		        	    	JProgressBar partyHP = setupHPBar(j);
		        	        final int index = j;
		        	        
		        	        party.addActionListener(g -> {
		        	        	if (p.team[index].getLevel() == 100) {
		        	        		JOptionPane.showMessageDialog(null, "It won't have any effect.");
		        	        	} else {
		        	        		p.elevate(p.team[index]);
		        	        		p.bag.remove(i.getItem());
		        	        		SwingUtilities.getWindowAncestor(partyPanel).dispose();
			        	        	SwingUtilities.getWindowAncestor(itemDesc).dispose();
			        	        	SwingUtilities.getWindowAncestor(panel).dispose();
			        	        	showBag();
		        	        	}
		        	        });
		        	        
		        	        JPanel memberPanel = new JPanel(new BorderLayout());
		        	        memberPanel.add(party, BorderLayout.NORTH);
		        	        memberPanel.add(partyHP, BorderLayout.SOUTH);
		        	        partyPanel.add(memberPanel);
		        	    }

		        	    JOptionPane.showMessageDialog(null, partyPanel, "Party", JOptionPane.PLAIN_MESSAGE);
		        	}
		        });
		        itemDesc.add(description);
		        itemDesc.add(count);
		        itemDesc.add(useButton);

		        JOptionPane.showMessageDialog(null, itemDesc, "Item details", JOptionPane.PLAIN_MESSAGE);
		    });

		    panel.add(item);
		}

		JScrollPane scrollPane = new JScrollPane(panel);
		scrollPane.setPreferredSize(new Dimension(panel.getPreferredSize().width, 300));
		scrollPane.getVerticalScrollBar().setUnitIncrement(16);

		JPanel containerPanel = new JPanel(new BorderLayout());
		containerPanel.add(scrollPane, BorderLayout.CENTER);

		JOptionPane.showMessageDialog(null, containerPanel, "Bag", JOptionPane.PLAIN_MESSAGE);
		keyH.resume();
	}

	

	private JButton setUpPartyButton(int j) {
		JButton party = new JGradientButton("");
		party.setText("");
        if (p.team[j] != null) {
        	if (p.team[j].isFainted()) {
        		party = new JButton("");
            	party.setBackground(new Color(200, 0, 0));
            } else {
            	party.setBackground(p.team[j].type1.getColor());
            }
            party.setText(p.team[j].getName() + "  lv " + p.team[j].getLevel());
            party.setHorizontalAlignment(SwingConstants.CENTER);
            party.setFont(new Font("Tahoma", Font.PLAIN, 11));
            party.setVisible(true);
        } else {
            party.setVisible(false);
        }
        return party;
	}
	
	private JProgressBar setupHPBar(int j) {
		JProgressBar partyHP = new JProgressBar(0, 50);
		if (p.team[j] != null) {
            partyHP.setMaximum(p.team[j].getStat(0));
            partyHP.setValue(p.team[j].currentHP);
            if (partyHP.getPercentComplete() > 0.5) {
                partyHP.setForeground(new Color(0, 255, 0));
            } else if (partyHP.getPercentComplete() <= 0.5 && partyHP.getPercentComplete() > 0.25) {
                partyHP.setForeground(new Color(255, 255, 0));
            } else {
                partyHP.setForeground(new Color(255, 0, 0));
            }
            partyHP.setVisible(true);
        } else {
            partyHP.setVisible(false);
        }
		return partyHP;
	}

	public void draw(Graphics2D g2) {
		BufferedImage image = null;
		
		switch(direction) {
		case "up":
			if (spriteNum == 1) image = up1;
			if (spriteNum == 2) image = up2;
			break;
		case "down":
			if (spriteNum == 1) image = down1;
			if (spriteNum == 2) image = down2;
			break;
		case "left":
			if (spriteNum == 1) image = left1;
			if (spriteNum == 2) image = left2;
			break;
		case "right":
			if (spriteNum == 1) image = right1;
			if (spriteNum == 2) image = right2;
			break;
		}
		g2.drawImage(image, screenX, screenY, gp.tileSize, gp.tileSize, null);
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
