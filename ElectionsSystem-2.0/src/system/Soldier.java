package system;

import java.time.LocalDate;

public class Soldier extends Citizen {
	private boolean carryWeapon;

	public Soldier(String name, int id, int yearBirth, int serialBallotNum, boolean isolation,LocalDate electionDate,boolean carryWeapon) throws Exception {
		super(name, id, yearBirth, serialBallotNum, isolation, electionDate);
		this.carryWeapon=carryWeapon;
	}
	
	public String toString() {
		return super.toString()+ ", carry weapon: "+carryWeapon+"\n";
	}

}
