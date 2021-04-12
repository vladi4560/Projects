package election_system_model;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.Vector;

public class Party {
	private String name;
	private LocalDate partyBirth;

	public enum Faction {
		LEFT, RIGHT, MIDDLE
	};

	private Faction efaction;
	private Vector<Nominee> nominees;

	public Party(String name, Faction efaction, int year, int month, int day) {
		this.name = name;
		this.partyBirth = LocalDate.of(year, month, day);
		this.efaction = efaction;
		this.nominees = new Vector<Nominee>();
	}

	public void addNominee(Nominee nominee) {
		nominees.add(nominee);
		Collections.sort(nominees);
	}

	public String getName() {
		return name;
	}

	public LocalDate getPartyBirth() {
		return partyBirth;
	}

	public Faction getEfaction() {
		return efaction;
	}

	
	public boolean equals(Object other) {
		if (!(other instanceof Party)) {
			return false;
		}
		Party temp = (Party) other;
		return this.name.toLowerCase().equals(temp.name.toLowerCase());		
	}
	
	public String toString() {
		StringBuffer f=new StringBuffer( "Party Name: " + this.name + ", Faction: " + efaction + ", Founded date: " + partyBirth
				+ ", Nominees list:\n");
		for(Nominee nominee: nominees) {
			f.append(nominee.toString()+"\n");
		}
		return f.toString();
	}
}
