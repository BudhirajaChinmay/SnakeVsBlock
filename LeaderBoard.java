package Game2;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collections;

/**
 * The class which stores the records of all players playing the game.
 * Used to retrieve the top 10 players. 
 * @author Dashmesh,Chinmay
 *
 */
@SuppressWarnings("serial")
public class LeaderBoard implements Serializable {
	/**
	 * The List of the players playing the game, sorted in descending order of score.
	 */
	private ArrayList<Player> player = new ArrayList<Player>();; // players
	/**
	 * Input and output streams for storing data
	 */
	private ObjectOutputStream out1;
	private ObjectInputStream in1;
	private ArrayList<Player> templist;

	/**
	 * Serialize the dtaa
	 */
	public void Serialize() {
		
		try {
			out1 = new ObjectOutputStream(new FileOutputStream("D:\\eclipse-workspace\\SnakvsBlock\\src\\Game2\\LeaderBoard.dat"));
			out1.writeObject(player);
			out1.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * DeSerialize the dtaa
	 */
	@SuppressWarnings("unchecked")
	public void DeSerialize() {
		try {
			in1 = new ObjectInputStream(new FileInputStream("D:\\\\eclipse-workspace\\\\SnakvsBlock\\\\src\\\\Game2\\\\LeaderBoard.dat"));
			templist = (ArrayList<Player>) in1.readObject();
			System.out.println(templist.size());
			in1.close();
		}
		catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Constructor initializes the variables.
	 */
	public LeaderBoard() {
		player= new ArrayList<Player>();

	}
	
	public ArrayList<Player> getPlayer() {
		DeSerialize();
		if (templist == null) {
			return player;
		}
		player = templist;
		return player;
	}

	/**
	 * A function which is used for debugging while class generation.
	 */
	@SuppressWarnings("unused")
	private void print()
	{
		for(int i=0;i<player.size();i++)
		{
			System.out.print(i+1 + " "+ player.get(i).getName()+" "+player.get(i).getScore());
		}
		System.out.println();
	}
	
	/**
	 * Adds the player and then sorts the list according to the score.
	 * @param Curplayer : The player which is being added to the list.
	 */
	public void addPlayer(Player Curplayer) 
	{
		player.add(Curplayer);
		Collections.sort(player);
				
		//print();
	}


}
