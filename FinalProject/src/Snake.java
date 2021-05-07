import java.awt.Color;
import java.awt.Graphics;

public class Snake {
	
	public int xCoor, yCoor, width, height;
	
	public Snake(int xCoor, int yCoor, int size){
		this.xCoor = xCoor;
		this.yCoor = yCoor;
		width = size;
		height = size;
	}
	
	public void draw(Graphics g) {
		g.setColor(Color.RED);
		g.fillRect(xCoor * width, yCoor * height, width, height); //posns multiples of 5 | squares are of size 5
	}															  // width & height = 5
	
	
	public void draw2(Graphics g) {
		g.setColor(Color.BLUE);
		g.fillRect(xCoor * width, yCoor * height, width, height);
	}
	
	public void draw3(Graphics g) {
		g.setColor(Color.green);
		g.fillRect(xCoor, yCoor, width, height);
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
