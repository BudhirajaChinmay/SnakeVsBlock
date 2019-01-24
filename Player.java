package Game2;

import java.io.Serializable;

/**
 * Contains details about the player currently playing the game.
 * @author Dashmesh,Chinmay
 *
 */
@SuppressWarnings("serial")
public class Player implements Comparable<Player>, Serializable{

	private String Name;
	private String score;
	private String date;
	
	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public String getScore() {
		return score;
	}


	public void setScore(String score) {
		this.score = score;
	}


	public String getDate() {
		return date;
	}


	public void setDate(String date) {
		this.date = date;
	}


	/* (non-Javadoc)
	 * compare function used for sorting.
	 * @see java.lang.Comparable#compareTo(java.lang.Object)
	 */
	@Override
	public int compareTo(Player arg0) {
		if(Integer.parseInt(this.score)<Integer.parseInt(arg0.score))
		{
			return 1;
		}
		else if(Integer.parseInt(this.score)>Integer.parseInt(arg0.score))
		{
			return -1;
		}
		return 0;
	}
}
