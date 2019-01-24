package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.Random;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Pane;

/**
 * The Basic building unit of a Bomb.
 * @author Dashmesh,Chinmay
 *
 */
public class DestryBlocks {
	
	private static Image bombimg;
	/**
	 * Node type object of the bomb.
	 */
	private ImageView b;
	private Pane bomb = new Pane();
	
	// Random pos
	private Random r = new Random();
	
	// speed
	private double speed;
	
	/**
	 * @param speed: speed of the bombs.
	 * @param y : the y coordinate of the bombs.
	 * @throws FileNotFoundException
	 */
	public DestryBlocks(double speed, double y) throws FileNotFoundException
	{
	
		this.speed = speed;
		
			bombimg = new Image(new FileInputStream("C:\\Users\\Chinmay\\Desktop\\FP\\bomb.png"));
			b= new ImageView(bombimg);
			b.setFitHeight(25);
			b.setFitWidth(25);
		
			bomb.getChildren().add(b);
			bomb.setTranslateY(y);
			
				bomb.setTranslateX(r.nextInt(50) + 430);
	}
	
	/**
	 * Translated the ball. 
	 */
	public void move()
	{
		bomb.setTranslateY(bomb.getTranslateY()+speed);
	}
	
	public ImageView getBombShape()
	{
		return b;
	}
	
	public Pane getBomb() {
		return bomb;
	}
}
