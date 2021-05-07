import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class MultiPlayerGP extends JPanel implements KeyListener{	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 500, HEIGHT = 500;
	private final int coinLimit = 95;
	
	public Snake playerOnesnake;
	public Snake playerTwosnake;

	private ArrayList<Snake> playerOne;
	private ArrayList<Snake> playerTwo;
	
	private Random r; //for random placement of coins on the GP
	
	int playerOneX = 10, playerOneY = 50, playerOneS = 5, playerOneLength = 50;
	
	int playerTwoX = 90, playerTwoY = 50, playerTwoS = 5, playerTwoLength = 50;
	
	private int ticks = 0;
	
	private Coin c;
	private ArrayList<Coin> coins;
	int coinS = 5;
	
	private final HashMap<String, Boolean> pressedKeys = new HashMap<String, Boolean>();
	
	private boolean collisionStateP1 = false;
	private boolean collisionStateP2 = false;
	private int p1Score = 0, p2Score = 0;
	
	public MultiPlayerGP(){
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		
		playerOne = new ArrayList<Snake>();
		playerTwo = new ArrayList<Snake>();
		
		coins = new ArrayList<Coin>();
				
		pressedKeys.put("W", false);		// HashMap handles the KeyPressed
		pressedKeys.put("A", false);		// ensures that there are no difficulties when pressing multiple keys at the same time
		pressedKeys.put("S", false);
		pressedKeys.put("D", true);

		pressedKeys.put("up", false);
		pressedKeys.put("left", true);
		pressedKeys.put("down", false);
		pressedKeys.put("right", false);
		
		r = new Random();		
		
	}
	
	
	public void movePlayer() {
		
		ticks++; // adds one as the Thread executes the runLoop
		
		// Controls the Speed of the program
		// The larger the number the slower the program runs
		// The larger the number, the longer it takes for ticks to reach that number and thus return false to move on to the next method
		if(ticks > 250000) {
			
			// Creates a new Snake object and adds it to PlayerOne ArrayList if PlayerOne ArrayList is empty.
			if(playerOne.size() == 0) {
				playerOnesnake = new Snake(playerOneX, playerOneY, playerOneS);
				playerOne.add(playerOnesnake);
			}
			if(playerTwo.size() == 0) {
				playerTwosnake = new Snake(playerTwoX, playerTwoY, playerTwoS);
				playerTwo.add(playerTwosnake);
			}
			
			// If a certain key is pressed, creates a new Snake object with that new x or y posn
			// adds new Snake object to player ArrayList
			// paint method will later paint this Snake object onto the screen
			
			if(pressedKeys.get("W") == true) {
				playerOneY--;
				
				ticks = 0; // resets the ticks -> makes if(ticks > 250000) false, therefore continuing the runLoop
						   // in other words this terminates this method to allow the runLoop to execute the other methods
				
				playerOnesnake = new Snake(playerOneX, playerOneY, playerOneS);		// creates a new Snake object with update x & y posns
				playerOne.add(playerOnesnake);										// adds that new Snake object to the players ArrayList of Snake objects
				
				if (playerOne.size() > playerOneLength) {							// "length" determines how many Snake objects are allowed in the ArrayList
					playerOne.remove(0);											// the larger the length, the larger the ArrayList, the larger the Snake is
					}																// if the size of the ArrayList is larger than the desired "length", remove that first element of the ArrayList (remove the tail end of the Snake)
				}
			if(pressedKeys.get("A") == true) {
				playerOneX--;
				
				ticks = 0;
				
				playerOnesnake = new Snake(playerOneX, playerOneY, playerOneS);
				playerOne.add(playerOnesnake);
				
				if (playerOne.size() > playerOneLength) {
					playerOne.remove(0);
					}
				}
			if(pressedKeys.get("S") == true) {
				playerOneY++;
				
				ticks = 0;
				
				playerOnesnake = new Snake(playerOneX, playerOneY, playerOneS);
				playerOne.add(playerOnesnake);
				
				if (playerOne.size() > playerOneLength) {
					playerOne.remove(0);
					}
				}
			if(pressedKeys.get("D") == true) {
				playerOneX++;
				
				ticks = 0;
				
				playerOnesnake = new Snake(playerOneX, playerOneY, playerOneS);
				playerOne.add(playerOnesnake);
				
				if (playerOne.size() > playerOneLength) {
					playerOne.remove(0);
					}
				}
			
			//PlayerTwo Movement
			
			if(pressedKeys.get("left") == true) {
				playerTwoX--;
				
				ticks = 0;
				
				playerTwosnake = new Snake(playerTwoX, playerTwoY, playerTwoS);
				playerTwo.add(playerTwosnake);
				
				if (playerTwo.size() > playerTwoLength) {
					playerTwo.remove(0);
					}
				}
			if(pressedKeys.get("down") == true) {
				playerTwoY++;
				
				ticks = 0;
				
				playerTwosnake = new Snake(playerTwoX, playerTwoY, playerTwoS);
				playerTwo.add(playerTwosnake);
				
				if (playerTwo.size() > playerTwoLength) {
					playerTwo.remove(0);
					}
				}
			if(pressedKeys.get("right") == true) {
				playerTwoX++;
				
				ticks = 0;
				
				playerTwosnake = new Snake(playerTwoX, playerTwoY, playerTwoS);
				playerTwo.add(playerTwosnake);
				
				if (playerTwo.size() > playerTwoLength) {
					playerTwo.remove(0);
					}
		
				}
			if(pressedKeys.get("up") == true) {
				playerTwoY--;
				
				ticks = 0;
				
				playerTwosnake = new Snake(playerTwoX, playerTwoY, playerTwoS);
				playerTwo.add(playerTwosnake);
				
				if (playerTwo.size() > playerTwoLength) {
					playerTwo.remove(0);
					}
				}
			}
		
	}
	
	public void updateScore() {
		
		// scan through the coin ArrayList to grab both x & y posns of the ith Coin object
		
		for(int i = 0; i < coins.size(); i++) {
			
			// if the players x & y posns are == to the x & y posns of the ith Coin object
			// then add a point to the players score and increase their length by 10
			
			if(playerOneX == coins.get(i).getxCoor() && playerOneY == coins.get(i).getyCoor()) {
				p1Score++;
				playerOneLength += 10;
				coins.remove(i);
				updateCoin();
			}
			if(playerTwoX == coins.get(i).getxCoor() && playerTwoY == coins.get(i).getyCoor()) {
				p2Score++;
				playerTwoLength += 10;
				updateCoin();
				coins.remove(i);

			}
		}
	}
	
	public void updateCoin() {
		
		// when there are no coins in the coin ArrayList, add two coins of random x & y posns
		
		if(coins.size() == 0) {
			int xCoor = r.nextInt(coinLimit);
			int yCoor = r.nextInt(coinLimit);
			
			c = new Coin(xCoor, yCoor, coinS);
			coins.add(c);
			
			int xCoor2 = r.nextInt(coinLimit);
			int yCoor2 = r.nextInt(coinLimit);
			
			c = new Coin(xCoor2, yCoor2, coinS);
			coins.add(c);
		}
		
		// if there's only one coin inside on the coin ArrayList, add another coin of random x & y posns
		
		if(coins.size() == 1) {
			int xCoor = r.nextInt(coinLimit);
			int yCoor = r.nextInt(coinLimit);
			
			c = new Coin(xCoor, yCoor, coinS);
			coins.add(c);
		}
	}
	
	public boolean collisionWithBorder() {
		
		// GamePanel is not 500 pixels wide
		// because it's filled with squares of size 5, the size of the GP is 500 / 5 or 100
		// therefore 100 is the bounds
		
		boolean check = false;
		if(playerOneX < 0 || playerOneX > 100 || playerOneY < 0 || playerOneY > 100) {
			System.out.println("Game Over");
			collisionStateP1 = true;
			System.out.println("Player Two Won!");
			check = true;
		}
		if(playerTwoX < 0 || playerTwoX > 100 || playerTwoY < 0 || playerTwoY > 100) {
			System.out.println("Game Over");
			collisionStateP2 = false;
			System.out.println("Player One Won!");

			check = true;
		}
		return check;
	}
	
	public boolean collisionWithSelf() {
		boolean check = false;
		
		// Collision With Self:
		// Goes through the player ArrayList and checks whether the most recent x-posn (playerOneX) is == to Snake objects' x-posn
		// for loops ends before the last Snake of the ArrayList b/c that is where the most recent posns are
		
		for(int i = 0; i < playerOne.size() - 1; i++) {
			if(playerOneX == playerOne.get(i).getxCoor() && playerOneY == playerOne.get(i).getyCoor()) {
				check = true;
				collisionStateP1 = true;
				System.out.println("Player Two Won!");
			}
		}
		for(int i = 0; i < playerTwo.size() - 1; i++) {
			if (playerTwoX == playerTwo.get(i).getxCoor() && playerTwoY == playerTwo.get(i).getyCoor()) {
				check = true;
				collisionStateP2 = true;
				System.out.println("Player One Won!");

			}
		}
		
		if(pressedKeys.get("up") == true && pressedKeys.get("down") == true) {
			check = false;
		}
		
		return check;
	}
	
	public boolean collisionWithPlayer() {
		boolean check = false;
		
		// Scans through the playerTwo Snake ArrayList
		// If playerOne's X & Y position == the X & Y positions taken from any snake object within the playerTwo ArrayList -> stop program
		
		for(int i = 0; i < playerTwo.size(); i++) {
			if(playerOneX == playerTwo.get(i).getxCoor() && playerOneY == playerTwo.get(i).getyCoor()){
				check = true;
				collisionStateP1 = true;
				System.out.println("Player Two Won!");
			}
		}
		for(int i = 0; i < playerOne.size(); i++) {
			if(playerTwoX == playerOne.get(i).getxCoor() && playerTwoY == playerOne.get(i).getyCoor()) {
				check = true;
				collisionStateP2 = true;
				System.out.println("Player One Won!");

			}
		}
		return check;
	}
	
	public void whoIsWinner() {
		if(collisionStateP1 == true) {
			System.out.println("Player Two Won!");
		} else if(collisionStateP2 == true) {
			System.out.println("Player One Won!");
		}
	}

	public void paint(Graphics g) {
		
		// paints the GP black before painting the Snake and Coin onto the GP
		// this ensures that when the Snake is redrawn, it is redraw on a blank canvas with no reference of the other Snake that was drawn previously
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		// this paints the snakes onto the JPanel
		// the loops go through the ArrayList of the snake and draws that part of the snake
		// according to the draw() method within the Snake class
		for (int i = 0; i < playerOne.size(); i++) {
			playerOne.get(i).draw(g);	
		}
		for (int i = 0; i < playerTwo.size(); i++) {
			playerTwo.get(i).draw2(g);
		}
		
		for(int i = 0; i < coins.size(); i++) { // go through coins ArrayList and execute draw method of the Coin class
			coins.get(i).draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		
		int key = e.getKeyCode();
		
		//PLAYERONE KEY EVENTS
		if (key == KeyEvent.VK_D) { //if playerOne pressed D | move right
			pressedKeys.replace("D", true);
			
			pressedKeys.replace("A", false);
			pressedKeys.replace("W", false);
			pressedKeys.replace("S", false);
		}
		if (key == KeyEvent.VK_A) { //if playerOne pressed A | move left
			pressedKeys.replace("A", true);
			
			pressedKeys.replace("D", false);
			pressedKeys.replace("W", false);
			pressedKeys.replace("S", false);
		}
		if (key == KeyEvent.VK_W) { //if playerOne pressed W | move up
			pressedKeys.replace("W", true);
			
			pressedKeys.replace("A", false);
			pressedKeys.replace("D", false);
			pressedKeys.replace("S", false);
		}
		if (key == KeyEvent.VK_S) { //if playerOne pressed S | move down
			pressedKeys.replace("S", true);
			
			pressedKeys.replace("A", false);
			pressedKeys.replace("D", false);
			pressedKeys.replace("W", false);
		}
		
		//PLAYERTWO KEY EVENTS
		
		if (key == KeyEvent.VK_UP) { //if playerTwo pressed up | move up
			pressedKeys.replace("up", true);
			
			pressedKeys.replace("down", false);
			pressedKeys.replace("left", false);
			pressedKeys.replace("right", false);
		}
		if (key == KeyEvent.VK_DOWN) { //if playerTwo pressed down | move down
			pressedKeys.replace("down", true);
			
			pressedKeys.replace("up", false);
			pressedKeys.replace("left", false);
			pressedKeys.replace("right", false);
		}
		if (key == KeyEvent.VK_RIGHT) { // playerTwo pressed right | move right
			pressedKeys.replace("right", true);
			
			pressedKeys.replace("up", false);
			pressedKeys.replace("down", false);
			pressedKeys.replace("left", false);
		}
		if (key == KeyEvent.VK_LEFT) {// if playerTwo pressed left | move left
			pressedKeys.replace("left", true);
			
			pressedKeys.replace("up", false);
			pressedKeys.replace("down", false);
			pressedKeys.replace("right", false);
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

}