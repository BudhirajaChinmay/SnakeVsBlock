package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class Snake {

	private double curx;
	
	private int size=7; //default length=3;
	private double speed=2;
	private VBox snakeBox= new VBox();
	private Image snakeHeadImg;
	private Image snakeBodyImg;
	
	
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

	public void makeSnake(int Initialpos,int Length) throws FileNotFoundException {
		snakeHeadImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\upmouth.png"));
		snakeBodyImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\snakeimage.png"));
								
		getSnakeBox().setTranslateX(Initialpos);
		getSnakeBox().setTranslateY(Length-150);
		getSnakeBox().getChildren().add(new ImageView(snakeHeadImg));	
		
		for(int i=0;i<getSize();i++)
		{
			getSnakeBox().getChildren().add(new ImageView(snakeBodyImg));
		}	
	}
}
