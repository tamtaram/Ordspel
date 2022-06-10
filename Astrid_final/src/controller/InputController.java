package controller;

import java.awt.KeyEventDispatcher;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;

/** 
* Controllerklass för hantering av spelarens inputs.
*  
* 
* @author Lotten Wester
* 
* @version 1.0
*/
public class InputController extends WindowAdapter implements ActionListener, KeyEventDispatcher {

	private ArrayList<InputListener> listeners = new ArrayList<InputListener>();

	/** 
	* @param listener Objekt med interfacet InputListener som tar emot händelser.
	* 
	*/
	public InputController(InputListener listener){
		listeners.add(listener);
	}
	
	/** 
	 * Lägger till objekt som den ska skicka händelser till.
	* @param listener Objekt med interfacet InputListener
	* 
	*/
	public void addInputListener(InputListener listener) {
		listeners.add(listener);
	}

	/** 
	 * Plockar upp tangentbordhändelser för att slänga dom. Ifall händelsen är enter skickas fireSendGuess ut till lyssnarna, 
	 * ifall händelsen är mellanslag skickas fireBackSpace ut till lyssnarna och i fall händelsen är en bokstavstangent
	 * skickas skickas fireLetterInput ut till lyssnarna med bokstaven i versalform.
	* @param e Tangentbordshändelsen
	* @return Returnerar alltid false (så att tangentbordhändelsen slängs bort)
	*/
	@Override
	public boolean dispatchKeyEvent(KeyEvent e) {
		int intLetter = e.getKeyCode();
		if (e.getID() == KeyEvent.KEY_PRESSED) {
			if(intLetter == KeyEvent.VK_ENTER){
				fireSendGuess();
			}
			else if(intLetter == KeyEvent.VK_BACK_SPACE){
				fireBackSpace();
			}
			else if(intLetter == KeyEvent.VK_SPACE){
				fireRequestMixWord();
			}
			else{
				char letter = Character.toUpperCase(e.getKeyChar());
				fireLetterInput(letter);
			}
		}
		return false;
	}
	
	/** 
	* @param event Händelsen som skett
	* Ifall händelsen är knappen Blanda skickas fireRequestMixWord till lyssnarna, 
	* ifall händelsen är Gissa skickas fireSendGuess till lyssnarna.
	* 
	*/

	@Override
	public void actionPerformed(ActionEvent event) {
		if (event.getActionCommand().equals("Blanda")){
			fireRequestMixWord();
		}
		if (event.getActionCommand().equals("Gissa")){
			fireSendGuess();
		}
	}
	
	/** 
	* @param arg0 Fönsterhändelsen
	* Ifall fönstret deaktiveras skickas firePauseGame ut så att spelet pausas
	* 
	*/
	@Override
	public void windowActivated(WindowEvent arg0) {
		firePauseGame(false);
	}
	
	/** 
	* @param arg0 Fönsterhändelsen
	* Ifall fönstret aktiveras efter att det deaktiverats skickas firePauseGame ut så att spelet inte pausas
	* 
	*/
	@Override
	public void windowDeactivated(WindowEvent arg0) {
		firePauseGame(true);
	}

	/** 
	* @param theLetter Bokstaven spelaren vill använda
	* Skickar bokstaven till lyssnarna
	* 
	*/
	private void fireLetterInput(char theLetter) {
		for (InputListener listener : listeners)
			listener.letterInput(theLetter);
	}
	
	/** 
	* Skickar backspace till lyssnarna
	* 
	*/
	private void fireBackSpace() {
		for (InputListener listener : listeners)
			listener.backspace();
	}
	
	/** 
	* Skickar senGuess till lyssnarna
	* 
	*/
	private void fireSendGuess() {
		for (InputListener listener : listeners)
			listener.sendGuess();
	}
	
	/** 
	* Skickar requestMixWord till lyssnarna
	* 
	*/
	private void fireRequestMixWord() {
		for (InputListener listener : listeners)
			listener.requestMixWord();
	}
	
	/** 
	 * @param pauseOn Sann ifall spelet ska pausas, falsk ifall spelet ska köra på
	* Skickar pauseOnOff till lyssnarna
	* 
	*/
	private void firePauseGame(boolean pauseOn) {
		for (InputListener listener : listeners)
			listener.pauseOnOff(pauseOn);
	}

}