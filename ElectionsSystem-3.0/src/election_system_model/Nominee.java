package election_system_model;

import java.time.LocalDate;
import java.util.Comparator;

public class Nominee extends Citizen implements Comparable<Nominee> {
	private String partyName;
	private int primaryNum;

	public Nominee(String name, int id, int yearBirth, int serialBallotNum, boolean isolation, String partyName,
			int primaryNum,LocalDate electionDate) throws Exception {
		super(name, id, yearBirth, serialBallotNum, isolation, electionDate);
		this.partyName = partyName;
		this.primaryNum = primaryNum;
	}

	public int compareTo(Nominee o) {
		if (this.primaryNum < o.primaryNum) {
			return -1;
		} else if (this.primaryNum > o.primaryNum) {
			return 1;
		} else
			return 0;
	}

	public int getPrimaryNum() {
		return primaryNum;
	}

	public String getPartyName() {
		return partyName;
	}

	public boolean equals(Object other) {
		if (!(other instanceof Nominee)) {
			return false;
		}
		if (!super.equals(other)) {
			return false;
		}
		return true;
	}

	@Override
	public String toString() {
		return super.toString() + ", Party Name: " + this.partyName + ", Primary Number: " + this.primaryNum+"\n";
	}

}
