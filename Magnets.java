package Game2;

import java.util.Random;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

public class Magnets implements TimedTokens {

	private int timer;
	private Image magnetImg;
	private ImageView magnet;
	private Random randint = new Random();
	private int isvisible;
	
	
	public Magnets() throws FileNotFoundException
	{
		
		isvisible = randint.nextInt(2);
		
			setTimer(5);
			magnetImg = new Image(new FileInputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\FP\\magnet.png"));
			magnet= new ImageView(magnetImg);
			magnet.setFitHeight(25);
			magnet.setFitWidth(25);
		
			if (isvisible == 0) {
				magnet.setVisible(false);
			}
			else {
				magnet.setVisible(true);
			}
		
	}
	
	public ImageView getMagnet()
	{
		return magnet;
	}
	@Override
	public void setTimer(int t) {
		// TODO Auto-generated method stub
		timer=t;
	}

	@Override
	public int getTimer() {
		// TODO Auto-generated method stub
		return timer;
	}
	
	
}
