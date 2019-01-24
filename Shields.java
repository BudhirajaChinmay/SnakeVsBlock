package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The Basic building unit of a Shield which prevent from block collision.
 * @author Dashmesh,Chinmay
 *
 */
public class Shields implements TimedTokens {

	/**
	 * Time for which the sheild is active. 
	 */
	private int timer;
	private static Image shieldImg;
	/**
	 * Node type object of the shield.
	 */
	private ImageView shield;
	private Pane s = new Pane();
	
	/**
	 * Random positions
	 */
	int pos;
	Random r = new Random();
	
	// Speed
	private double speed;
	
	/**
	 * @param speed: speed of the balls.
	 * @param y: the y coordinate of the ball.
	 * @throws FileNotFoundException
	 */
	public Shields(double speed, double y) throws FileNotFoundException
	{
		this.speed = speed;
		
		setTimer(5);
		shieldImg = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\shield.png"));
		shield= new ImageView(shieldImg);
		shield.setFitHeight(25);
		shield.setFitWidth(25);
		
		s.getChildren().add(shield);
		s.setTranslateX(y);
		
			s.setTranslateX(r.nextInt(50) + 130);

//		shield.setTranslateX(Math.random()*400);
//		shield.setTranslateY(100+(Math.random()*400));
	}
	
	/**
	 * Translates the Shield. 
	 */
	public void move()
	{
		s.setTranslateY(s.getTranslateY()+speed);
	}
	
	public ImageView getShieldShape()
	{
		return shield;
	}
	@Override
	public void setTimer(int t) {
		// TODO Auto
		timer=t;
	}

	@Override
	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}
	
	public Pane getShield() {
		return s;
	}
	 

}
