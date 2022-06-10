package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.GuessListener;

/** 
* Controllerklass som innehåller och hanterar model och view-objekt.
* Skickar förändringar till model genom en InputController.  
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
public class GuessHandler {
	private List<GuessListener> listeners =
			new ArrayList<GuessListener>();
	private String[] anagrams;
	private int winLength;
	private Boolean rightWord;	
	private String[] guesses;

	public GuessHandler(int wordLength, String[] anagrams){
		setUp(wordLength, anagrams);
	}
	
	   /** 
	    * Fyller objektet med information om hur lång ett rättgissat ord ska vara för att anse att det är ett vinnarord och 
	    * vilka gissningar som är korrekta
	    * @param wordLength längden på vinnarord
	    * @param anagram Riktiga gissningar
	   */
	public void setUp(int wordLength, String[] anagrams){
		this.winLength = wordLength;
		guesses = anagrams;
		this.anagrams = anagrams.clone();
		rightWord = false;
	}
	
	   /** 
	    * 
	    * @return Orden som är korrekta gissningar
	   */
	public String[] getAnagrams(){
		return anagrams;
	}
	
	   /** 
	    * 
	    * @return Ifall den senaste gissningen var korrekt
	   */
	public boolean rightWord(){
		return rightWord;
	}
	
	   /** 
	    * Kontrollerar ifall gissningen 1. är korrekt och 2. inte gissats förut
	    * @param guess Gissningen som ska kontrolleras
	    * @return Sant ifall gissningen är korrekt och inte gissats förut
	    * 
	   */
	public boolean rightUniqueGuess(String guess){
		for(int i = 0; i < guesses.length; i++){
			if(guesses[i].equals(guess.toLowerCase())){
				if(guesses[i].length() >= winLength) {
					rightWord = true;
				}
				else {
					rightWord = false;
				}
				fireGuessCorrect(i, rightWord);
				guesses[i] = "";
				return true;
			}
		}
		return false;
	}
	
	   /** 
	    * Lägger till en till lyssnare
	    * @param listener Objekt med interfacet GuessListener
	   */
	public void addGuessListener(GuessListener listener){
		listeners.add(listener);
	}
	
	   /** 
	    * Skickar händelsen att en riktig gissning gjorts till sina lyssnare
	    * @param whereInArray Var i gissningslistan den korrekta gissningen är
	    * @param superWord Sant ifall ordet är ett vinnarord, falskt annars
	   */
	private void fireGuessCorrect(int whereInArray, boolean superWord) {
		for (GuessListener listener : listeners)
			listener.guessCorrect(whereInArray, superWord);
	} 
}
