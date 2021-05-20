package GameModel;

import java.util.Vector;

public class Tennis extends Game {
	private Vector<Integer> gameSetsP1;
	private Vector<Integer> gameSetsP2;

	public Tennis(String player1, String player2) {
		super(player1, player2);
		this.gameSetsP1 = new Vector<Integer>();
		this.gameSetsP2 = new Vector<Integer>();
	}



	public boolean fillInScore(int player1, int player2) {
		if(player1==player2) {
			return false;
		}
		if (player1 > player2) {
			gameSetsP1.add(1);
			gameSetsP2.add(0);
			return true;
		}
		gameSetsP1.add(0);
		gameSetsP2.add(1);
		return true;

	}

	public String GameWinner() {
		int totalP1 = 0;
		int totalP2 = 0;
		for (int i = 0; i < gameSetsP1.size(); i++) {
			totalP1 += gameSetsP1.get(i);
			totalP2 += gameSetsP2.get(i);
			if ((totalP1 == 3 && totalP2 == 0)) {
				return this.player1;
			} else if (totalP2 == 3 && totalP1 == 0) {
				return this.player2;
			} else if ((totalP1 == 4 && totalP2 == 1)) {
				return this.player1;
			} else if ((totalP1 == 1 && totalP2 == 4)) {
				return this.player2;
			} else if ((totalP1 == 3 && totalP2 == 2)) {
				return this.player1;
			} else if ((totalP1 == 2 && totalP2 == 3)) {
				return this.player2;
			}
		} return null;
	}
}
