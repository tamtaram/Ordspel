package model;

import java.security.InvalidParameterException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import model.interfaces.TypeListener;
/**
 * Hanterare av inmatade bokstäver från spelaren
 * 
 * @author lottenwester1
 *
 */
public class TypeHandler {
	private List<TypeListener> listeners =
			new ArrayList<TypeListener>();

	private String longestWordstr;
	private int longestWordint;

	private List<String> LetterArrayList = new ArrayList<String>();
	private String mixWord = "";

	private char[] guessedLetters;
	private String guess;
	private int pointer;
	private int[] orderCorrectInputLetter;

	/**
	 * 
	 * @param word Det längsta ordet som går att skapa med bokstäverna (dvs alla bokstäver)
	 */
	public TypeHandler(String word) {
		setUp(word);
	}
	/**
	 * Matar in det gällande gissningsordet i objektet
	 * @param word Gissningsordet
	 */
	public void setUp(String word){
		longestWordstr = word.toUpperCase();
		longestWordint = word.length();
		LetterArrayList.clear();
		for(int i = 0; i < word.length(); i++){
			LetterArrayList.add(word.substring(i, i+1));
		}
		newMixedWord();
		resetGuess();
		pointer = 0;
		orderCorrectInputLetter = new int[word.length()];
	}

	/**
	 * Blandar om ordningen på de bokstäver som spelaren gissar utifrån
	 */
	public void newMixedWord(){
		mixWord = "";
		Collections.shuffle(LetterArrayList);
		for(String letter : LetterArrayList){
			mixWord = mixWord + letter;
		}
		mixWord = mixWord.toUpperCase();
		sendStringPrint(mixWord);
	}

	
	/**
	 * Meddelar sina lyssnare vilken sträng som ska stå där där spelaren ser vad den gissat på
	 * @param send Strängen som ska skrivas ut
	 */
	private void sendStringWrite(String send){
		if(send.length() > longestWordint){
			throw new InvalidParameterException("String cannot be longer than the longest word, "
					+ longestWordint + " characters: " + send);
		}
		char[] sendCharArray = send.toCharArray();
		for(int i = 0; i < longestWordint; i++){
			if(i < send.length()){
				fireWriteLetterChanged(i, sendCharArray[i]);
			}
			else fireWriteLetterChanged(i, ' ');
		}
	}
	
	/**
	 * Meddelar sina lyssnare vilken sträng som ska stå där där spelaren ser vilka bokstäver den har att gissa på
	 * @param send Strängen som ska skrivas ut
	 */
	private void sendStringPrint(String send){
		if(send.length() > longestWordint){
			throw new InvalidParameterException("String cannot be longer than the longest word, "
					+ longestWordint + " characters: " + send);
		}
		char[] sendCharArray = send.toCharArray();
		for(int i = 0; i < longestWordint; i++){
			if(i < send.length()){
				firePrintLetterChanged(i, sendCharArray[i]);
			}
			else firePrintLetterChanged(i, ' ');
		}
	}

	/**
	 * Kontrollerar om en bokstav 1. finns emd i gissningsordet och 2. inte redan tidigare använts i gissningen.
	 * Ifall detta stämmer meddelar den sina lyssnare att en giltig bokstav gissats på.
	 * @param c Bokstaven som ska kontrolleras
	 */
	public void inputLetter(char c){
		c = Character.toUpperCase(c);
		/*
		if(c == '[') c = '���';
		if(c == '?') c = '���';
		if(c == ';') c = '���';*/
		for(int i = 0; i < guessedLetters.length && guess.length() < longestWordint; i++){
			if(c == guessedLetters[i]){
				guess += c;
				guessedLetters[i] = ' ';
				orderCorrectInputLetter[pointer] = i;
				fireWriteLetterChanged(pointer, c);
				firePrintLetterUsed(i, true);
				pointer++;
				break;

			}
		}
	}

	/**
	 * 
	 * @return En sträng med bokstäverna i blandad ordnign
	 */
	public String getMixWord(){
		return mixWord;
	}
	
	/**
	 * Tar bort den tidigare sparade gissningen
	 */
	public void resetGuess(){
		guess="";
		pointer = 0;
		guessedLetters = mixWord.toCharArray();
		sendStringWrite("");
		for(int i = 0; i < longestWordint; i++){
			firePrintLetterUsed(i, false);
		}
	}
	
	/**
	 * Tar bort den tidigare sparade gissningen och returnerar gissningen
	 * @return Själva gissningen
	 */
	public String sendAndResetGuess(){
		String send = new String(guess);
		resetGuess();
		return send;
	}

	/**
	 * Tar bort den senast gissade bokstäven och meddelar sina lyssnare detta
	 */
	public void backSpace(){
		if(pointer > 0){
			pointer--;
			int where = orderCorrectInputLetter[pointer];
			guessedLetters[where] = mixWord.toCharArray()[where];
			guess = guess.substring(0, pointer);
			firePrintLetterUsed(where, false);
		}
		fireWriteLetterChanged(pointer, ' ');
	}

	/**
	 * Tömmer all information hos sina lyssnare och sig själv om gissningar 
	 * och meddelar att det korrekta ordet som använder alla bokstäver ska skrivas ut
	 */
	public void endGame(){
		resetGuess();
		sendStringWrite("");
		sendStringPrint(longestWordstr);
	}

	/**
	 * Lägger till en lyssnare
	 * @param listener Ett objekt med interfacet TypeListener
	 */
	public void addTypeListener(TypeListener listener){
		listeners.add(listener);
	}

	/**
	 * Meddelar sina lyssnare att en bokstav ska skrivas ut i gissningsfältet
	 * @param where På vilken plats, ex tredje bokstaven
	 * @param changeTo Till vilken bokstav
	 */
	private void fireWriteLetterChanged(int where, char changeTo) {
		for (TypeListener listener : listeners)
			listener.writeLetterChanged(where, changeTo);
	}

	/**
	 * Meddelar sina lyssnare att en bokstav ska skrivas ut i fältet där man kan se vilka bokstäver man har att gissa på
	 * @param where På vilken plats
	 * @param changeTo Till vilken bokstav
	 */
	private void firePrintLetterChanged(int where, char changeTo) {
		for (TypeListener listener : listeners)
			listener.printLetterChanged(where, changeTo);
	}

	/**
	 * Meddelar sina lyssnare att en av bokstäverna man har att gissa bytt status från använd till oanvänd eller tvärtom 
	 * (använda bokstäver kan inte användas igen i samma gissning)
	 * av spelaren
	 * @param where På vilken plats
	 * @param isUsed Sant ifall bokstaven ska ändra status oanvänd->använd, falskt ifall bokstaven ska få statusen använd->oanvänd
	 */
	private void firePrintLetterUsed(int where, boolean isUsed) {
		for (TypeListener listener : listeners)
			listener.printLetterUsed(where, isUsed);
	}
}