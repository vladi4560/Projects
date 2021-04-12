package system;

import java.time.LocalDate;

public class SickSoldier extends Soldier implements Sickable{
	private int sickDays;
	
	public SickSoldier(String name, int id, int yearBirth, int serialBallotNum, boolean isolation,LocalDate electionDate,boolean carryWeapon, int sickDays) throws Exception {
		super(name, id, yearBirth, serialBallotNum, isolation, electionDate,carryWeapon);
		this.sickDays = sickDays;
	}
	public int getSickDays() {
		return this.sickDays;
	}
}
