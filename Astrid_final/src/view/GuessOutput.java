package view;
import java.awt.Dimension;
import java.awt.FlowLayout;

import javax.swing.JPanel;

import model.GuessHandler;
import model.interfaces.GuessListener;
/** 
* Objekt som skapar en Array av GuessJPanel-objekt. (Varje GuessJPanel är en JPanel med tomma rutor 
* för de ord som är möjliga att skapa utifrån den givna bokstavkombinationen.)
* 
* @author Lotten Wester
* 
* @version 1.0
*/
@SuppressWarnings("serial")
public class GuessOutput extends JPanel implements GuessListener {
	private GuessJPanel[] guessJPanel;

	public GuessOutput(GuessHandler guessHandler){
		guessHandler.addGuessListener(this);
		String[] anagrams = guessHandler.getAnagrams();
		setPreferredSize(new Dimension(500, 300));
		setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		
		guessJPanel = new GuessJPanel[anagrams.length];
		for(int i = 0; i < anagrams.length; i++){
			guessJPanel[i] = new GuessJPanel(anagrams[i]);
			add(guessJPanel[i]);
		}
	}

	/**
	 * @param whereInArray Var i Arrayen ordet befinner sig.
	 * @param winWord Ifall ordet räknas som ett ord som kvalificerar spelaren till nästa omgång.
	 * Funktion som aktiverar rätt GuessJPanel till att visa bokstäverna (ifall detta ord gissats rätt på)
	 */
	@Override
	public void guessCorrect(int whereInArray, boolean winWord) {
		if(winWord){
			guessJPanel[whereInArray].setCorrectWin();
		}
		if(!winWord){
			guessJPanel[whereInArray].setCorrect();
		}
	}	
}
