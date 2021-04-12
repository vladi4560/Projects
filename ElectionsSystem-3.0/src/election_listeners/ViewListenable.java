package election_listeners;

import java.time.LocalDate;
import java.util.Vector;

import election_system_model.Citizen;
import election_system_model.Party;
import election_system_model.Party.Faction;
import election_system_model.Set;
import model_exceptions.BallotNotExistException;
import model_exceptions.CitizenExistsException;
import model_exceptions.PartyExistException;
import model_exceptions.PartyNotExistException;
import model_exceptions.ProtectiveSuitException;

public interface ViewListenable {
	void AddBallotFromView(int i, String street, String type) throws Exception;

	void AddCitizenFromView(String CitizenName, int CitizenId, int yearBirth, int BallotId, boolean coronaSickness,
			int sickDays, boolean isSoldier, boolean carryWeapon)
			throws CitizenExistsException, BallotNotExistException, Exception;

	void AddNomnieFromView(String nameNominee, int idNominee, int yearBirth, int serialNum4, boolean isolation4,
			String partyName, int primaryNum) throws PartyNotExistException, CitizenExistsException, Exception;

	String showAllballotsInformation();

	String showAllCItizenInformation();

	String showAllPartiesInformation();

	void AddPartyFromView(String partyName, Faction faction, int partyYearBirth, int partyMonthBirth, int partyDayBirth)
			throws PartyExistException, Exception;

	String showResultsElectionFromView();

	Vector<Party> getPartiesToView();

	Set<Citizen> getCitizensToView();

	void makeVoteFromView(Citizen citizen, int citizenVote) throws BallotNotExistException;


}