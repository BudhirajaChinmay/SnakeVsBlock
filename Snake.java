package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;

public class Snake {

	/**
	 * The current x coordinate of the snake. 
	 */
	private double curx;
	/**
	 * The default length of the snake = 10. 
	 */
	private int size=10;
	
	private Image snakeHeadImg;
	/**
	 * The node type object of the snake's head. 
	 */
	private ImageView snake;
	private Pane SnakeBody = new Pane();
	
	public void setSize(int x)
	{
		size=x;
	}
	

	
	public int getSize()
	{
		return size;
	}
	
	public double getCurx() {
		return curx;
	}

	public void setCurx(double curx) {
		this.curx = curx;
	}

	/**
	 * Generates the snake on updated lengths.
	 * @param Initialpos : The position of the snake.
	 * @param Length : new length of the snake.
	 * @throws FileNotFoundException
	 */
	public void makeSnake(int Initialpos,int Length) throws FileNotFoundException {
		
		snakeHeadImg = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\upmouth.png"));
		
//		getSnakeBox().getChildren().clear();
//		getSnakeBox().setTranslateX(Initialpos);
//		getSnakeBox().setTranslateY(Length-350);
		SnakeBody.getChildren().clear();
		setSnake(new ImageView(snakeHeadImg));
		SnakeBody.getChildren().add(snake);
		getSnakeBody().setTranslateX(Initialpos);
		getSnakeBody().setTranslateY(Length-350);
//		getSnakeBox().getChildren().add(snake);	
		
		//System.err.println(getSize());
		for(int i=0;i<getSize();i++)
		{
			if (i == 0) {
				getSnakeBody().getChildren().add(new Circle(7, Color.DARKTURQUOISE));
				getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-1).setTranslateY(getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-2).getTranslateY()+31);
				getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-1).setTranslateX(12);
			}
			else {
				getSnakeBody().getChildren().add(new Circle(7, Color.DARKTURQUOISE));
				getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-1).setTranslateY(getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-2).getTranslateY()+14);
				getSnakeBody().getChildren().get(getSnakeBody().getChildren().size()-1).setTranslateX(12);
			}
		
		}	
	}
	public ImageView getSnake() {
		return snake;
	}
	public void setSnake(ImageView snake) {
		this.snake = snake;
	}
	public Pane getSnakeBody() {
		return SnakeBody;
	}
	public void setSnakeBody(Pane snakeBody) {
		SnakeBody = snakeBody;
	}
	

	

}
