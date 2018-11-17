package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Balls {

	private ImageView Shape ;
	private int value;
	public Balls() throws FileNotFoundException {
		
		Image ballImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\ball.png"));
		Shape= new ImageView(ballImg);
		Shape.setFitHeight(20);
		Shape.setFitWidth(20);

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

	public ImageView getShape() {
		return Shape;
	}


}