package Game;

import javafx.application.Application;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.stage.*;

public class MainPage extends Application {
	Button startGame;
	Button resumeGame;
	Button leaderboard;
	Button exit;
	
//	private PlayGame gameObj;
//	private ResumeGame previousGame;
//	private PlayGame previousPlayGame;
//	private Leaderboard leaderboardObj;
//	private Long lastGameScore;
	
	public void displayLastGameScore()
	{
		
	}
	
	public void resumeLastGame()
	{
		
	}

	@Override
	public void start(Stage arg0) throws Exception {
		// TODO Auto-generated method stub
		
		startGame = new Button("Start Game");

		resumeGame = new Button("Reaseme Game");

		leaderboard = new Button("Leader board");

		exit = new Button("Exit");
		
		Group group = new Group();
		group.getChildren().add(startGame);
		group.getChildren().add(resumeGame);
		group.getChildren().add(leaderboard);
		group.getChildren().add(exit);
		
		Scene scene = new Scene(group,400,400); 
		arg0.setScene(scene);
		arg0.show();
	}
	
	
	public static void main(String[] argd) {
		Application.launch(argd);
	}
}
