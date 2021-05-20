package starter;

import GameModel.Champioship;
import GameView.View;
import GameView.ViewPoints;
import controller.ChampionshipController;
import javafx.application.Application;
import javafx.stage.Stage;
//שמות המגישים:דורין דורסמן ,ולדי קרסיוב
public class Program extends Application{
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		Champioship theModel= new Champioship();
		ViewPoints theViewPoints = new ViewPoints();
		View theView = new View(primaryStage, theViewPoints);
		ChampionshipController theController = new ChampionshipController(theModel, theView, theViewPoints);
		//for check
		for (int i = 0 ; i < 8; i++) {
			theController.FireAddParticipant(Integer.toString(i));
		}
		
		
	}

}
