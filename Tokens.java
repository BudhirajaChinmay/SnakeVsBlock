package Game2;

import javafx.scene.shape.Shape;

public abstract class Tokens{

	private Shape figure;

	public Tokens(Shape s) {
		setFigure(s);
	}
	
	public Shape getFigure() {
		return figure;
	}
	
	public void setFigure(Shape f) {
		figure = f;
	}
	
}
