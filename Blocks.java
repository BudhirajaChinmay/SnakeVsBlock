package Game2;

import java.util.Random;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Blocks{

	private Rectangle shape;
	private int value;
	private Random randint = new Random();
	private int isvisible;
	
	public Blocks() {
		
		isvisible = randint.nextInt(2);
		
		shape = new Rectangle(100,100);
		shape.setArcHeight(50);
		shape.setArcWidth(50);
		
		if (isvisible == 0) {
			shape.setVisible(false);
		}
		else{
			value = randint.nextInt(21);
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

	public int getValue() {
		return value;
	}

	public void setValue(int value) {
		this.value = value;
	}
	
	public Rectangle getShape() {
		return shape;
	}

}
