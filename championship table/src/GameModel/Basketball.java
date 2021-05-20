package GameModel;

import java.util.Vector;

public class Basketball extends Game {
	private Vector<Integer> gameSetsP1;
	private Vector<Integer> gameSetsP2;

	public Basketball(String player1, String player2) {
		super(player1, player2);
		this.gameSetsP1 = new Vector<Integer>();
		this.gameSetsP2 = new Vector<Integer>();

	}



	@Override
	public boolean fillInScore(int player1, int player2) {
			gameSetsP1.add(player1);
			gameSetsP2.add(player2);
			return true;
	}

	@Override
	public String GameWinner() {
		int totalP1 = 0;
		int totalP2 = 0;
		for (int i = 0; i < gameSetsP1.size(); i++) {
			totalP1 += gameSetsP1.get(i);
			totalP2 += gameSetsP2.get(i);
		}
		if (totalP1 > totalP2) {
			return this.player1;
		} else
			return this.player2;
	}
}
