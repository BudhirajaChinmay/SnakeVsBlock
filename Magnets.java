package Game2;

import java.util.Random;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The Basic building unit of a Magnet.
 * @author Dashmesh,Chinmay
 *
 */
public class Magnets implements TimedTokens {

	/**
	 * Time for which the magnet is active. 
	 */
	private int timer;
	private static Image magnetImg;
	/**
	 * Node type object of the ball.
	 */
	private ImageView magnet;
	private Pane m = new Pane();
	
	/**
	 * Random positions
	 */
	private Random r = new Random();
	
	// speed
	private double speed;
	
	/**
	 * @param speed: speed of the balls.
	 * @param y: the y coordinate of the ball.
	 * @throws FileNotFoundException
	 */
	public Magnets(double speed, double y) throws FileNotFoundException
	{
	
		this.speed = speed;
		
			setTimer(5);
			magnetImg = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\magnet.png"));
			magnet= new ImageView(magnetImg);
			magnet.setFitHeight(25);
			magnet.setFitWidth(25);
		
			m.getChildren().add(magnet);
			m.setTranslateY(y);
			
				m.setTranslateX(r.nextInt(50) + 330);

	}
	
	/**
	 * Translates the Magnet. 
	 */
	public void move()
	{
		m.setTranslateY(m.getTranslateY()+speed);
	}
	
	public ImageView getMagnetShape()
	{
		return magnet;
	}
	
	public Pane getMagnet() {
		return m;
	}
	@Override
	public void setTimer(int t) {
		// TODO Auto-generated method stub
		timer=t;
	}

	@Override
	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}
	
	
}
