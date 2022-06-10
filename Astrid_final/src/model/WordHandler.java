package model;

import java.util.ArrayList;
import java.util.Random;
/** 
* Klass som utifrån rader inlästa från textfil skapar Word-objekt och sparar dem i en ArrayList<Word>
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
public class WordHandler {
	private ArrayList<String> wordLines = new ArrayList<String>();
	private ArrayList<Word> wordList = new ArrayList<Word>();

	
	public WordHandler(){
		ReadFiles readFiles = new ReadFiles();
		this.wordLines = readFiles.getAllLines();
		makeWords();
	}
	
	/**
	 * 
	 * @return Returnerar ett slumpmässigt Word-objekt utifrån sin ArrayList med alla Word-objekt
	 */
	public Word getWord(){
		Random random = new Random();
		int which = random.nextInt(wordList.size());
		return wordList.get(which);
	}
	
	/**
	 * Skapar alla Word-objekt utifrån rader inlästa stån textfil.
	 * Lägger till Word-objekten på sin ArrayList.
	 */
	private void makeWords(){
		for(String line : wordLines ){
			String[] lineparts = line.split(" ");
			String[] wordStr;
			if(lineparts[0].contains(",")){
				wordStr = lineparts[0].split(",");
			}
			else{
				wordStr = new String[1];
				wordStr[0] = lineparts[0];
			}
			String[] anagrams = lineparts[1].split(",");
			Word word = new Word(wordStr, anagrams);
			wordList.add(word);
		}
	}
}
