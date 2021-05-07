import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;

import javax.swing.JPanel;

public class SinglePlayerGP extends JPanel implements KeyListener {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private final int WIDTH = 500, HEIGHT = 500;
	private final int coinLimit = 90;
	
	public Snake singlePlayerSnake;
	
	private ArrayList<Snake> singlePlayer;
	
	private Random r;
	
	int singlePlayerX = 10, singlePlayerY = 50, singlePlayerS = 5, singlePlayerLength = 50;
	
	private int ticks = 0;
	
	private Coin c;
	private ArrayList<Coin> coins;
	int coinS = 5;
	
	private boolean w, a, s, d;
	
	private final HashMap<String, Boolean> pressedKeys = new HashMap<String, Boolean>();
	
	public SinglePlayerGP(){
		setFocusable(true);
		
		setPreferredSize(new Dimension(WIDTH, HEIGHT));
		
		addKeyListener(this);
		
		singlePlayer = new ArrayList<Snake>();
		
		coins = new ArrayList<Coin>();
		
		r = new Random();
		
		pressedKeys.put("W", false);
		pressedKeys.put("A", false);
		pressedKeys.put("S", false);
		pressedKeys.put("D", true);

	}
	
	public void movePlayer() {
		
		ticks++;
		
		if(ticks > 350000) {
			
			if(singlePlayer.size() == 0) {
				singlePlayerSnake = new Snake(singlePlayerX, singlePlayerY, singlePlayerS);
				singlePlayer.add(singlePlayerSnake);
			}
			
			if(pressedKeys.get("W") == true) {
				singlePlayerY--;
				
				ticks = 0;
				
				singlePlayerSnake = new Snake(singlePlayerX, singlePlayerY, singlePlayerS);
				singlePlayer.add(singlePlayerSnake);
				
				if(singlePlayer.size() > singlePlayerLength) {
					singlePlayer.remove(0);
				}
			}
			if(pressedKeys.get("A") == true) {
				singlePlayerX--;
				
				ticks = 0;
				
				singlePlayerSnake = new Snake(singlePlayerX, singlePlayerY, singlePlayerS);
				singlePlayer.add(singlePlayerSnake);
				
				if(singlePlayer.size() > singlePlayerLength) {
					singlePlayer.remove(0);
				}
			}
			if(pressedKeys.get("S") == true) {
				singlePlayerY++;
				
				ticks = 0;
				
				singlePlayerSnake = new Snake(singlePlayerX, singlePlayerY, singlePlayerS);
				singlePlayer.add(singlePlayerSnake);
				
				if(singlePlayer.size() > singlePlayerLength) {
					singlePlayer.remove(0);
				}
			}
			if(pressedKeys.get("D") == true) {
				singlePlayerX++;
				
				ticks = 0;
				
				singlePlayerSnake = new Snake(singlePlayerX, singlePlayerY, singlePlayerS);
				singlePlayer.add(singlePlayerSnake);
				
				if(singlePlayer.size() > singlePlayerLength) {
					singlePlayer.remove(0);
				}
			}
			
		}
		
	}
	
	public void updateScore() {
		for(int i = 0; i < coins.size(); i++) {
			if(singlePlayerX == coins.get(i).getxCoor() && singlePlayerY == coins.get(i).getyCoor()) {

				singlePlayerLength += 10;
				coins.remove(i);
				updateCoin();
			}
		}
	}
	
	public void updateCoin() {
		if(coins.size() == 0) {
			int xCoor = r.nextInt(coinLimit);
			int yCoor = r.nextInt(coinLimit);
			
			c = new Coin(xCoor, yCoor, coinS);
			coins.add(c);
		}
	}
	
	public boolean collisionWithBorder() {
		boolean check = false;
		if(singlePlayerX < 0 || singlePlayerX > 100 || singlePlayerY < 0 || singlePlayerY > 100) {
			System.out.println("Game Over");
			check = true;
		}
		return check;
	}
	
	public boolean collisionWithSelf() {
		boolean check = false;
		for(int i = 0; i < singlePlayer.size() - 1; i++) {
			if(singlePlayerX == singlePlayer.get(i).getxCoor() && singlePlayerY == singlePlayer.get(i).getyCoor()) {
				check = true;
			}
		}
		return check;
	}
	
	public void paint(Graphics g) {
		g.clearRect(0, 0, WIDTH, HEIGHT);
		g.setColor(Color.black);
		g.fillRect(0, 0, WIDTH, HEIGHT);
		
		for(int i = 0; i < singlePlayer.size(); i++) {
			singlePlayer.get(i).draw(g);
		}
		
		for(int i = 0; i < coins.size(); i++) {
			coins.get(i).draw(g);
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int key = e.getKeyCode();
		
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
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	
}
