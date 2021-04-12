package election_controller;

import election_system_model.Ballot;
import election_system_model.Citizen;
import election_system_model.Nominee;
import election_system_model.Party;
import election_system_model.Party.Faction;
import election_system_model.RoundOfElections;
import election_system_model.Set;
import election_view.View1;
import model_exceptions.BallotExistsException;
import model_exceptions.BallotNotExistException;
import model_exceptions.CitizenExistsException;
import model_exceptions.PartyExistException;
import model_exceptions.PartyNotExistException;
import model_exceptions.ProtectiveSuitException;

import java.time.LocalDate;
import java.util.Vector;

import com.sun.javafx.webkit.ThemeClientImpl;

import election_listeners.*;

public class Controller implements RoundOfElectionListenable,ViewListenable{
 private RoundOfElections theModel;
 private View1 theView;
	
	public Controller(RoundOfElections theModel, View1 theView) {
		this.theModel=theModel;
		this.theView=theView;
		
		theModel.registerListener(this);
		theView.registerListener(this);
	}

	@Override
	public void AddBallotFromView(int serialNum, String street, String type) throws Exception  {
		theModel.addBallot(theModel.SelectedBallotType(serialNum,street,type),type);
		
	}
	
	public void failedAddingBallotToModelEvent() {
		theView.BallotExitsMessage("The Ballot Already Exists");
	}
	
	public void AddCitizenFromView(String CitizenName, int CitizenId, int yearBirth, int BallotId, boolean coronaSickness,int sickdays,boolean ifSolider,boolean carryWeapon) throws CitizenExistsException, BallotNotExistException, Exception {
	LocalDate electionDate= this.theModel.getElectionDate();
	theModel.addCitizen(theModel.selectedCitizenType(CitizenName, CitizenId, yearBirth, BallotId, coronaSickness, electionDate, carryWeapon, sickdays,ifSolider));
	
	}

	@Override
	public void failedAddingCitizenToModelEvent() {
		theView.CitizenExistsMessage("The Citizen Already Exists");
		
	}

	@Override
	public void SuccessAddingCitizenToModelEvent() {
		theView.CitizenAddedMessage("Citizen successfully added");
		
	}

	@Override
	public void SucceededAddingBallotToModelEvent() {
		theView.SucceededAddingBallotMessage("Ballot successfully added");
		
	}

	@Override
	public void AddNomnieFromView(String nameNominee, int idNominee, int yearBirth, int serialNum4, boolean isolation4,
			String partyName, int primaryNum) throws PartyNotExistException, CitizenExistsException, Exception {
		theModel.addNominee(new Nominee( nameNominee,idNominee,yearBirth,serialNum4,isolation4,partyName,primaryNum,theModel.getElectionDate()));
	}

	
	public void SuccessAddingNomnieeToModelEvent() {
		theView.SucceededAddingNomnieeMessage("Nominee successfully added");		
	}

	public String showAllballotsInformation() {
		return theModel.printBallots();
	}

	
	public String showAllCItizenInformation() {
		return theModel.printCitizens();
	}

	
	public String showAllPartiesInformation() {
		return theModel.printParties();
	}
	public void AddPartyFromView(String partyName, Faction efaction, int partyYearBirth,int partyMonthBirth, int partyDayBirth) throws PartyExistException {
		theModel.addParty(new Party(partyName, efaction, partyYearBirth, partyMonthBirth, partyDayBirth));
	}

	public void SuccessAddingPartyToModelEvent() {
		theView.PartyAddedMessage("Party successfully added");
	}

	public void fireElectionSuccessfullyEnd() {
		theView.ElectionSuccessfullyEndMessage("The Election Ended Successfully");
	}

	@Override
	public String showResultsElectionFromView() {
		return theModel.showResults();
	}

	@Override
	public Vector<Party> getPartiesToView() {
		
		return theModel.getListOfParties();
	}

	@Override
	public Set<Citizen> getCitizensToView() {
		
		return theModel.getCitizens();
		}

	
	

	@Override
	public void makeVoteFromView(Citizen citizen, int citizenVote) throws BallotNotExistException   {
		theModel.makeVote(citizen, citizenVote);
		
	}


	
	
	
}
