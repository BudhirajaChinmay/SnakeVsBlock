package Game2;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Shields implements TimedTokens {

	private int timer;
	private Image shieldImg;
	private ImageView shield;
	public Shields() throws FileNotFoundException
	{
		setTimer(5);
		shieldImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\shield.png"));
		shield= new ImageView(shieldImg);
		shield.setFitHeight(25);
		shield.setFitWidth(25);
//		shield.setTranslateX(Math.random()*400);
//		shield.setTranslateY(100+(Math.random()*400));
	}
	
	public ImageView getShield()
	{
		return shield;
	}
	@Override
	public void setTimer(int t) {
		// TODO Auto
		timer=t;
	}

	@Override
	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}

}
