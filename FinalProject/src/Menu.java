import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Menu implements ActionListener{
	
	JFrame menuFrame;
	JPanel menuPanel;
	JPanel playPanel;
	JPanel playerselectionPanel;
	JButton[] menuButtons;
	
	JButton playButton, oneplayerButton, twoplayerButton;
	JTextField textfield1;
	JTextField textfield2;
	JTextField textfield3;
	
	Font titleFont = new Font("Ariel", Font.BOLD, 25);
	Font buttonFont = new Font("Ariel", Font.BOLD, 15);
	String playercount = "";
	
	Menu(){
		
		menuFrame = new JFrame();
		menuFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		menuFrame.setSize(500, 500);
		menuFrame.setLayout(null);
		
		menuPanel = new JPanel();
		menuPanel.setBounds(0, 0, 500, 500);
		menuPanel.setBackground(Color.BLACK);
		
		playPanel = new JPanel();
		playPanel.setBounds(150, 150, 200, 50);
		playPanel.setBackground(Color.BLACK);
		
		playerselectionPanel = new JPanel();
		playerselectionPanel.setBounds(150, 300, 200, 50);
		playerselectionPanel.setBackground(Color.BLACK);
		
		textfield1 = new JTextField();
		textfield1.setBounds(100, 10, 300, 100);
		textfield1.setFont(titleFont);
		textfield1.setBackground(Color.BLACK);
		textfield1.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textfield1.setForeground(Color.WHITE);
		textfield1.setText("Epic Game!");
		textfield1.setEditable(false);
		textfield1.setVisible(true);
		
		textfield2 = new JTextField();
		textfield2.setBounds(125, 200, 300, 25);
		textfield2.setFont(buttonFont);
		textfield2.setBackground(Color.BLACK);
		textfield2.setForeground(Color.RED);
		textfield2.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textfield2.setText("Choose a Player Count Below!");
		textfield2.setEditable(false);
		textfield2.setVisible(false);
		
		textfield3 = new JTextField();
		textfield3.setBounds(210, 200, 300, 25);
		textfield3.setFont(buttonFont);
		textfield3.setBackground(Color.BLACK);
		textfield3.setForeground(Color.RED);
		textfield3.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		textfield3.setText("Press Play!");
		textfield3.setEditable(false);
		textfield3.setVisible(false);
		
		menuButtons = new JButton[3];
		
		playButton = new JButton("Play Game");
		oneplayerButton = new JButton("Single Player");
		twoplayerButton = new JButton("Multiplayer");
		
		menuButtons[0] = playButton;
		menuButtons[1] = oneplayerButton;
		menuButtons[2] = twoplayerButton;
		
		for(int i = 0; i < 3; i++) {
			
			menuButtons[i].addActionListener(this);
			menuButtons[i].setFont(buttonFont);
			menuButtons[i].setForeground(Color.WHITE);
			menuButtons[i].setBackground(Color.BLACK);
			menuButtons[i].setBorder(BorderFactory.createLineBorder(Color.BLACK));
			menuButtons[i].setVisible(true);
		}
		
		menuFrame.add(textfield2);
		menuFrame.add(playerselectionPanel);
		menuFrame.add(playPanel);
		menuFrame.add(menuPanel);
		menuPanel.add(textfield1);
		menuPanel.add(textfield3);
		playPanel.add(playButton);
		playerselectionPanel.add(oneplayerButton);
		playerselectionPanel.add(twoplayerButton);
		menuFrame.setVisible(true);
		playPanel.setVisible(true);
		menuPanel.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		
		if(e.getSource() == menuButtons[0]) { //if you click Play			
			if(playercount == "") {
				textfield2.setVisible(true);
			} else if (playercount != "") {
				textfield2.setVisible(false);
				
				//Play Game
				if(playercount == "one") {
					SinglePlayerGF spgf = new SinglePlayerGF();
					menuFrame.setVisible(false);
					
					System.out.println("SinglePlayer");
				}
				if(playercount == "two") {
					MultiPlayerGF mpgf = new MultiPlayerGF();
					menuFrame.setVisible(false);
					
					System.out.println("MultiPlayer");
				}
			}
		}
		
		if(e.getSource() == menuButtons[1]) { //if you click SinglePlayer
			textfield3.setVisible(true);
			textfield2.setVisible(false);
			playercount = "one";			
		}
		
		if(e.getSource() == menuButtons[2]) { //if you click MultiPlayer
			textfield3.setVisible(true);
			textfield2.setVisible(false);
			playercount = "two";
		}
		
	}
	
}
