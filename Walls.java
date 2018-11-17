package Game2;

import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;

public class Walls {

	private Rectangle shape;
	
	public Walls() {
		setShape(new Rectangle(5,200,Color.WHITE));
		shape.setLayoutX(Math.random()*480);
		shape.setLayoutY(130);
	}

	public Rectangle getShape() {
		return shape;
	}

	public void setShape(Rectangle shape) {
		this.shape = shape;
	}
}
