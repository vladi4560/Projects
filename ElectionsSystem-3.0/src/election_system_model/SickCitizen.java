package election_system_model;

import java.time.LocalDate;

public class SickCitizen extends Citizen implements Sickable {
	private int sickDays;

	public SickCitizen(String name, int id, int yearBirth, int serialBallotNum, boolean isolation,
			LocalDate electionDate, int sickDays) throws Exception {
		super(name, id, yearBirth, serialBallotNum, isolation, electionDate);
		this.sickDays = sickDays;

	}
	public int getSickDays() {
		return this.sickDays;
	}
	public String toString() {
		return super.toString() + ", sick days: " + sickDays + "\n";
	}
}
