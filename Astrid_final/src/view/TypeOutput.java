package view;

import javax.swing.JPanel;

import model.TypeHandler;
import model.interfaces.TypeListener;
/**  
* Visar bokstäverna spelaren har att gissa ifrån,
* och även bokstäverna spelaren använder att gissa med. 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
public class TypeOutput implements TypeListener {
	private String allLetters;
	private JLabelSettings[] print, write;
	private JPanel printPart, writePart;
	
	public TypeOutput(TypeHandler typeHandler){
		typeHandler.addTypeListener(this);
		//egentligen bara size, sen typehandler fixar he in ord
		allLetters = typeHandler.getMixWord();	
		setUp(allLetters);
	}
	
	/**
	 * Skapar två JLabelSettings-arrayer som är lika långa som antalet bokstäver som ska användas. 
	 * @param word De bokstäver som ska användas samlade i en textsträng
	 */
	private void setUp(String word){
		print = new JLabelSettings[word.length()];
		write = new JLabelSettings[word.length()];
		createPrintPart(word);
		createWritePart();
	}
	
	/**
	 * Fyller JLabelSettings-arrayen med bokstäver från bokstäverna som ska användas.
	 * Lägger till dessa i en JPanel.
	 * @param s De bokstäver som ska användas, samlade i en textsträng
	 */
	private void createPrintPart(String s){
		printPart = new JPanel();
		for (int i = 0; i < allLetters.length(); i++) {
			print[i] = new JLabelSettings(s.substring(i, i+1));
			print[i].setTypeOutput();
			printPart.add(print[i]);
		}
		
	}	

	/**
	 * Lägger till rätt antal tomma rutor att gissa bosktävern a med i en JPanel.
	 */
	private void createWritePart(){
		writePart = new JPanel();
		for (int i = 0; i < allLetters.length(); i++) {
			write[i] = new JLabelSettings();
			write[i].setTypeOutput();
			writePart.add(write[i]);
		}
	}

	/**
	 * 
	 * @return JPaneln som innehåller delen där bokstäverna skrivs ut
	 */
	public JPanel getPrintPart(){
		return printPart;
	}

	/**
	 * 
	 * @return JPaneln som innehåller delen där gissningen skrivs
	 */
	public JPanel getWritePart(){
		return writePart;
	}

	@Override
	public void writeLetterChanged(int where, char changeTo) {
		write[where].setText(Character.toString(changeTo));		
	}

	@Override
	public void printLetterChanged(int where, char changeTo) {
		print[where].setText(Character.toString(changeTo));		
	}

	@Override
	public void printLetterUsed(int where, boolean isUsed) {
		if(isUsed){
			print[where].setDisabled();
		}
		if(!isUsed){
			print[where].setEnabled();
		}
	}
}
