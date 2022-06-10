package view;
import java.awt.FlowLayout;

import javax.swing.JPanel;
/**
 * En panel med gissningsdelen, alltså både de bokstäver som skrivs ut när spelaren gissar och de bokstäver som spelaren har att gissa på
 * @author lottenwester1
 *
 */
@SuppressWarnings("serial")
public class GuessJPanel extends JPanel {
	private String theAnagram;
	private JLabelSettings[] letters;

	public GuessJPanel(String s){
		theAnagram = s.toUpperCase();
		letters = new JLabelSettings[s.length()];
		JPanel wordBox = new JPanel();
		wordBox.setLayout(new FlowLayout(FlowLayout.LEFT, 0, 0));
		for(int i = 0; i < s.length(); i++){			
			letters[i] = new JLabelSettings();
			letters[i].setGuessOutput();
			wordBox.add(letters[i]);
		}
		add(wordBox);
	}
	
	/**
	 * Ändrar de blandade bokstäver man har att gissa på till ordet de kan bilda
	 */
	public void setCorrect(){
		for(int i = 0; i < letters.length; i++){			
			letters[i].setText(theAnagram.substring(i, i+1));
			letters[i].setCorrect(true);
		}
	}
	
	/**
	 * Ändrar de blandade bokstäver man har att gissa på till ordet de kan bilda
	 */
	public void setCorrectWin(){
		for(int i = 0; i < letters.length; i++){			
			letters[i].setText(theAnagram.substring(i, i+1));
			letters[i].setWordCorrect();
		}
	}
		
}
