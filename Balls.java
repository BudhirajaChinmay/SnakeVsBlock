package Game2;

import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.ImageView;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.text.Text;

/**
 * The Basic building unit of a Ball(Coin).
 * @author Dashmesh,Chinmay
 *
 */
public class Balls {

	/**
	 * Node type object of the ball.
	 */
	private ImageView ballShape ;
	private Circle ball ;
	private int value;
	
	// For Coordinates
	private Random r = new Random();
	private int pos;
	private StackPane balls = new StackPane();
	
	// speed
	private double speed;
	
	/**
	 * @param speed: speed of the balls.
	 * @param y: the y coordinate of the ball.
	 * @throws FileNotFoundException
	 */
	public Balls( double speed, double y) throws FileNotFoundException {
		
		this.speed = speed;
		
		setBall(new Circle(12,Color.DARKTURQUOISE));
		//Image ballImg = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\ball.png"))		
		
		value = r.nextInt(4);
		
		balls.getChildren().addAll(ball,new Text(Integer.toString(value+1)));
		
	
		balls.setTranslateY(y);
		pos = r.nextInt(2);
		if (pos == 0) {
			
			balls.setTranslateX(r.nextInt(50) + 30);
		}
		else {
			
			balls.setTranslateX(r.nextInt(50) + 230);
		}
		
		
	}

	/**
	 * Translated the ball. 
	 */
	public void move()
	{
		balls.setTranslateY(balls.getTranslateY()+speed);
	}
	
	public void setValue(int v)
	{
		value=v;
	}

	public int getValue()
	{
		return value;
	}
	
	public void generate() {
		// TODO Auto-generated method stub
		
	}

	public ImageView getShape() {
		return ballShape;
	}
	
	public StackPane getBall() {
		return balls;
	}

	public void setBall(Circle ball) {
		this.ball = ball;
	}


}