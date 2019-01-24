package Game2;

import java.util.Random;

import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.scene.text.Text;

/**
 * The Basic building unit of a Block.
 * @author Dashmesh,Chinmay
 *
 */
public class Blocks{

	/**
	 * The x coordinate of the block. 
	 */
	double x;

	double speed; //speed
	private Rectangle shape;
	private int value;
	private Random randint = new Random();
	/**
	 * visibility property.
	 */
	private int isvisible;
	/**
	 * Node object of the block. 
	 */
	private StackPane block;
	
	/**
	 * @param speed : Speed of the game
	 * @param xc : The x coordinate of the block.
	 */
	public Blocks(double speed,double xc) {
		
		this.speed= speed;
		this.x=xc;
		
		isvisible = randint.nextInt(2);
		
		//shape of block
		shape = new Rectangle(100,100);
		shape.setArcHeight(50);
		shape.setArcWidth(50);

		block= new StackPane();
		block.setTranslateX(x);
		
		if (isvisible == 0) {
			block.setVisible(false);
		}
		else{
			value = randint.nextInt(21);
			if(value==0)
			{
				block.setVisible(false);
			}
			else
			{
				if (value <= 5) {
					shape.setFill(Color.BLUE);
				}
				else if (value > 5 && value <= 10) {
					shape.setFill(Color.BROWN);
				}
				else if (value > 10 && value <= 15) {
					shape.setFill(Color.CHOCOLATE);
				}
				else {
					shape.setFill(Color.RED);
				}
			}
		}
		
		block.setTranslateY(-100);
		block.getChildren().addAll(shape,new Text(Integer.toString(value)));
		
	}

	/**
	 * Translates the block. 
	 */
	public void move()
	{
		//System.out.println(block.getTranslateY());
		block.setTranslateY( block.getTranslateY()+speed);
	}
	
	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
		this.block.getChildren().remove(0);
		this.block.getChildren().addAll(this.shape,new Text(Integer.toString(value)));
	}
	
	public Rectangle getShape() {
		return shape;
	}
	
	public StackPane getBlock()
	{
		return block;
	}
	
}
