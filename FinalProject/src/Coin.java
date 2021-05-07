import java.awt.Color;
import java.awt.Graphics;

public class Coin {
	
	private int xCoor, yCoor, width, height;
	
	public Coin(int xCoor, int yCoor, int size) {
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = size;
		height = size;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.yellow);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}

	public int getxCoor() {
		return xCoor;
	}

	public void setxCoor(int xCoor) {
		this.xCoor = xCoor;
	}

	public int getyCoor() {
		return yCoor;
	}

	public void setyCoor(int yCoor) {
		this.yCoor = yCoor;
	}
	
}
