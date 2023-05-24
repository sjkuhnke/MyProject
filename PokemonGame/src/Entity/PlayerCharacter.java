package Entity;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Rectangle;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JProgressBar;
import javax.swing.SwingConstants;

import Overworld.GamePanel;
import Overworld.KeyHandler;
import Swing.Battle.JGradientButton;
import Swing.Player;
import Swing.Pokemon;

public class PlayerCharacter extends Entity {
	
	GamePanel gp;
	KeyHandler keyH;
	
	public final int screenX;
	public final int screenY;
	public Player p;
	
	public PlayerCharacter(GamePanel gp, KeyHandler keyH) {
		this.gp = gp;
		this.keyH = keyH;
		
		screenX = gp.screenWidth / 2 - (gp.tileSize/2);
		screenY = gp.screenHeight / 2 - (gp.tileSize/2);
		
		solidArea = new Rectangle(6, 12, 30, 30);
		
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
		try {
			up1 = ImageIO.read(getClass().getResourceAsStream("/player/red2.png"));
			up2 = ImageIO.read(getClass().getResourceAsStream("/player/red2_1.png"));
			down1 = ImageIO.read(getClass().getResourceAsStream("/player/red1.png"));
			down2 = ImageIO.read(getClass().getResourceAsStream("/player/red1_1.png"));
			left1 = ImageIO.read(getClass().getResourceAsStream("/player/red3.png"));
			left2 = ImageIO.read(getClass().getResourceAsStream("/player/red3_1.png"));
			right1 = ImageIO.read(getClass().getResourceAsStream("/player/red4.png"));
			right2 = ImageIO.read(getClass().getResourceAsStream("/player/red4_1.png"));
		} catch (IOException e) {
			e.printStackTrace();
		}
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
		if (keyH.pPressed) {
			showParty();
			keyH.pPressed = false;
		}
	}
	
	private void showParty() {
	    JPanel partyPanel = new JPanel();
	    partyPanel.setLayout(new GridLayout(6, 1));

	    JProgressBar[] partyHP = new JProgressBar[6];
	    for (int i = 0; i < 6; i++) {
	        JButton party = new JGradientButton("");
	        partyHP[i] = new JProgressBar(0, 50);
	        final int index = i;

	        party.setText("");
	        if (p.team[i] != null) {
	        	if (p.team[i].isFainted()) {
	        		party = new JButton("");
	            	party.setBackground(new Color(200, 0, 0));
	            } else {
	            	party.setBackground(p.team[i].type1.getColor());
	            }
	            party.setText(p.team[i].getName() + "  lv " + p.team[i].getLevel());
	            party.setHorizontalAlignment(SwingConstants.CENTER);
	            party.setFont(new Font("Tahoma", Font.PLAIN, 11));
	            party.setVisible(true);

	        } else {
	            party.setVisible(false);
	        }

	        if (p.team[i] != null) {
	            partyHP[i].setMaximum(p.team[i].getStat(0));
	            partyHP[i].setValue(p.team[i].currentHP);
	            if (partyHP[i].getPercentComplete() > 0.5) {
	                partyHP[i].setForeground(new Color(0, 255, 0));
	            } else if (partyHP[i].getPercentComplete() <= 0.5 && partyHP[i].getPercentComplete() > 0.25) {
	                partyHP[i].setForeground(new Color(255, 255, 0));
	            } else {
	                partyHP[i].setForeground(new Color(255, 0, 0));
	            }
	            partyHP[i].setVisible(true);
	        } else {
	            partyHP[i].setVisible(false);
	        }
	        
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
	            if (p.team[index] != null) statusLabel.setText("Status: " + p.team[index].getStatus());
	            
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
	        memberPanel.add(partyHP[i], BorderLayout.SOUTH);
	        partyPanel.add(memberPanel);
	    }

	    JOptionPane.showMessageDialog(null, partyPanel, "Party", JOptionPane.PLAIN_MESSAGE);
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
