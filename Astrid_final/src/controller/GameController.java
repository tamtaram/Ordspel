package controller;

import view.GameOutput;
import model.GuessHandler;
import model.PointsHandler;
import model.TimeHandler;
import model.TypeHandler;
import model.Word;
import model.WordHandler;

/** 
* Controllerklass som innehåller och hanterar model och view-objekt.
* Skickar förändringar till model genom en InputController.  
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/

public class GameController implements InputListener {
	private GuessHandler guessHandler;
	private TypeHandler typeHandler;
	private PointsHandler pointsHandler;
	private TimeHandler timeHandler;
	private Word wordToPlayWith;
	private boolean isRunning;
	
	/** 
	* Konstruktor. Skapar en inputController där den själv är en lyssnare, samt model-, och viewobjekt.
	* 
	*/

	public GameController(){
		InputController inputController = new InputController(this);
		isRunning = true;
		
		//model
		WordHandler wordHandler = new WordHandler();
		wordToPlayWith = wordHandler.getWord();
		System.out.println(wordToPlayWith.getWord()[0]);
		guessHandler = new GuessHandler(wordToPlayWith.getWord()[0].length(), wordToPlayWith.getAna());
		typeHandler = new TypeHandler(wordToPlayWith.getWord()[0]);
		pointsHandler = new PointsHandler();
		timeHandler = new TimeHandler();

		//view
		GameOutput game = new GameOutput(inputController, typeHandler, guessHandler, pointsHandler, timeHandler);
		
		timeHandler.start();
	}
	
	   /** 
	    * Skickar en bokstav till typeHandler när spelaren trycker på en bokstavstangent
	    * @param letter Bokstaven spelaren tryckt på på tangentbordet
	   */
	@Override
	public void letterInput(char letter) {
		if(isRunning){
			typeHandler.inputLetter(letter);
		}
	}

	   /** 
	    * Aktiverar typeHandlerns backSpace-metod när spelaren trycker på mellanslagstangenten.
	   */
	@Override
	public void backspace() {
		if(isRunning){
		typeHandler.backSpace();
		}
	}

	   /** 
	    * Hämtar spelarens gissning från typeHandler och tar bort gissningen.
	    * Skickar vidare spelarens gissning till pointsHandler.
	   */
	
	@Override
	public void sendGuess() {
		if(isRunning){
		String guessS = typeHandler.sendAndResetGuess();
		if(guessHandler.rightUniqueGuess(guessS)){
			if(guessHandler.rightWord()){
				pointsHandler.addPoints(50);
			}
			else pointsHandler.addPoints(10);
		}
		}
	}

	   /** 
	    * Aktiverar typeHandlers metod newMixedWord vilket innebär att bokstäverna spelaren har att gissa på blandas om.
	   */
	
	@Override
	public void requestMixWord() {
		if(isRunning){
		typeHandler.newMixedWord();
		}
	}

	   /** 
	    * @param pauseOn
	    * Ska pausa tiden hos TimeHandler ifall pauseOn är true och sätter igång tiden ifall pauseOn är falsk. 
	    * Metoden fungerar inte i TimeHandler-objektet så just nu gör metoden ingenting.
	   */
	@Override
	public void pauseOnOff(boolean pauseOn) {
		if(pauseOn){
			isRunning = false;
			//time.pause();
		}
		if(!pauseOn){
			isRunning = true;
			//time.start();
		}
	}

	   /** 
	    * Ändrar statusen på sin egen variabel isRunning till true
	   */
	
	@Override
	public void startGame() {
		isRunning = true;		
	}


	   /** 
	    * Ändrar statusen på sin egen variabel isRunning till false
	   */
	@Override
	public void endGame() {
		isRunning = false;		
	}

}