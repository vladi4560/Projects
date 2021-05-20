package GameModel;

import java.util.Vector;

public abstract class Game {
	protected String player1;
	protected String player2;
	protected String winner=null;
	
	public Game(String player1 ,String player2) {
		this.player1= player1;
		this.player2= player2;	
		
	}
	public abstract boolean fillInScore(int player1, int player2);
	public abstract String GameWinner();
	
}
