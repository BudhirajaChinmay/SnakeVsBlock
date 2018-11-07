package Game2;

import java.util.Date;

public class Player {

	private String Name;
	private int score;
	private Date date;
	
	
	public Player(String n, int s) {
		setName(n);
		setScore(s);
		setDate(new Date());
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}


	public int getScore() {
		return score;
	}


	public void setScore(int score) {
		this.score = score;
	}


	public Date getDate() {
		return date;
	}


	public void setDate(Date date) {
		this.date = date;
	}
}
