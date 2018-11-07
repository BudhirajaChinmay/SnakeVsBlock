package Game2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Circle;
import javafx.scene.shape.Shape;

public class Balls {

	private Circle Shape ;
	private int value;
	public Balls() {
		
		setShape(new Circle(Math.random()*400,120,5,Color.YELLOW));
		// TODO Auto-generated constructor stub
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

	public Circle getShape() {
		return Shape;
	}

	public void setShape(Circle shape) {
		Shape = shape;
	}

}
