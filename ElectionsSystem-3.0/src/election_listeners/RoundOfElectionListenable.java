package election_listeners;

public interface RoundOfElectionListenable {
	void failedAddingBallotToModelEvent();
	void failedAddingCitizenToModelEvent();
	void SuccessAddingCitizenToModelEvent();
	void SucceededAddingBallotToModelEvent();
	void SuccessAddingNomnieeToModelEvent();
	void SuccessAddingPartyToModelEvent();
	void fireElectionSuccessfullyEnd();
}
