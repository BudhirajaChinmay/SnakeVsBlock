package Game2;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;

public class LeaderBoard {
	private ObservableList<Player> player ; // players


	public LeaderBoard() {
		setPlayer(FXCollections.observableArrayList());
	}


	public ObservableList<Player> getPlayer() {
		return player;
	}


	public void setPlayer(ObservableList<Player> player) {
		this.player = player;
	}


}
