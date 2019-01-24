package Game2;

import javafx.scene.shape.Shape;

/**
 * The parent class of all the tokens.
 * Is used for referencing and provides the basic shape object.
 * @author Dashmesh,Chinmay
 *
 */
public abstract class Tokens{

	/**
	 * The shape of the token. 
	 */
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
