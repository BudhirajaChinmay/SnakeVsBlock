package Game2;

import javafx.scene.layout.VBox;

public class Snake {

	private double curx;
	
	private int size=7; //default length=3;
	private double speed=2;
	private VBox snakeBox= new VBox();


	
	public void setSize(int x)
	{
		size=x;
	}
	
	public void setSpeed(double x)
	{
		speed=x;
	}
	
	public int getSize()
	{
		return size;
	}
	
	public double getSpeed()
	{
		return speed;
	}

	public double getCurx() {
		return curx;
	}

	public void setCurx(double curx) {
		this.curx = curx;
	}

	public VBox getSnakeBox() {
		return snakeBox;
	}

	public void setSnakeBox(VBox snakeBox) {
		this.snakeBox = snakeBox;
	}
}
