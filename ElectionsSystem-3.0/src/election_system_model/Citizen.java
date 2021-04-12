package election_system_model;

import java.time.LocalDate;

import model_exceptions.IdException;
import model_exceptions.YearBirthException;

public class Citizen {
	protected String name;
	protected int id;
	protected int yearBirth;
	protected int serialBallotNum;
	protected boolean coronaSickness;

	public Citizen(String name, int id, int yearBirth, int serialBallotNum, boolean isolation,LocalDate electionDate) throws Exception {
		this.name = name;
		setYearBirth(yearBirth,electionDate);
		setId(id);
		this.serialBallotNum=serialBallotNum;
		this.coronaSickness = isolation;
	}

	public void setId(int id) throws Exception {
		if(id>=1000000000||id<=99999999) {
			throw new IdException();
		}
		this.id=id;
	}
	
	public void setYearBirth(int yearBirth, LocalDate electionDate) throws Exception{
		if((electionDate.getYear()-yearBirth)<18) {
			throw new YearBirthException();
		}
		this.yearBirth=yearBirth;
	}

	public String getName() {
		return name;
	}

	public int getYearBirth() {
		return yearBirth;
	}

	public boolean isIsolation() {
		return coronaSickness;
	}

	public int getSerialBallotNum() {
		return serialBallotNum;
	}

	public boolean setSerialBallotNum(int serialBallotNum) {
		this.serialBallotNum = serialBallotNum;
		return true;
	}
	
	public boolean equals(Object other) {
		if (!(other instanceof Citizen)) {
			return false;
		}
		Citizen temp = (Citizen) other;
		if (!(this.id == temp.id)) {
			return false;
		}
		return true;

	}

	public String toString() {
		return "Citizien name: " + this.name + ", id: " + this.id + ", year birth: " + this.yearBirth
				+ ", ballot number: " + this.serialBallotNum + ", Have Corona?: " + this.coronaSickness+"\n";
	}

}
