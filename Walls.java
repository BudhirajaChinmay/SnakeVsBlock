package Game2;

import java.util.Random;

import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

/**
 * @author Dashmesh, Chinmay
 *
 */
public class Walls {

	double speed; //speed
	private int isvisible;
	private Random randint = new Random();
	private Rectangle shape;
	private Pane wall = new Pane();
	private double posx;
	private double posy;
	
	/**
	 * @param speed : Speed of the walls.
	 * @param posx : x coordinate.
	 * @param posy : y coordinate.
	 */
	public Walls(double speed,  double posx, double posy) {
		setShape(new Rectangle(5,154,Color.WHITE));
		this.posx = posx;
		this.posy = posy;
		
		isvisible = randint.nextInt(100);
		this.speed = speed;
		wall.getChildren().add(shape);
		wall.setTranslateX(posx);
		wall.setTranslateY(posy);
		
		if (isvisible >= 20) {
			wall.setVisible(false);
		}

	}
	
	public void move()
	{
//		/System.out.println(wall.getTranslateY());
		wall.setTranslateY(wall.getTranslateY()+speed);
	}


	public Pane getWall() {
		return wall;
	}
	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
		this.shape = shape;
	}
	
	public boolean visibleproperty() {
		return this.shape.isVisible();
	}

	public double getPosy() {
		return posy;
	}

	public void setPosy(double posy) {
		this.posy = posy;
	}

	public double getPosx() {
		return posx;
	}

	public void setPosx(double posx) {
		this.posx = posx;
	}
}
