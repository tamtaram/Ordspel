package create;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Läser in en textfils alla ord och sparar dessa i en ArrayList<String>.
 * @author lottenwester1
 *
 */
public class WordFileReader {

	private int minWordLength = 3;
	private int maxWordLength = 10;
	private File wordFile;
	private ArrayList<String> allWords = new ArrayList<String>();

	/**
	 * Läser in orden och tillkallas setAllWords()
	 * @param minWordLength Hur få bokstäver ordet får vara som minst
	 * @param maxWordLength Hur många bokstäver ordet får vara som mest
	 * @param file Filen som läses ifrån
	 * @throws FileNotFoundException
	 */
	public WordFileReader(int minWordLength, 
			int maxWordLength, File file) throws FileNotFoundException{
		this.maxWordLength = maxWordLength;
		this.minWordLength = minWordLength;
		this.wordFile = file;	
		setAllWords();
	}
	
	/**
	 * 
	 * @return En ArrayList<String> med alla ord som filen innehöll som stämde in på kriterierna (ordlängd)
	 */
	public ArrayList<String> getAllWords(){
		return allWords;
	}

	/**
	 * Läser ord från fil, sparar dem i en ArrayList<String> ifall de stämmer in på längd-kriterierna
	 * @throws FileNotFoundException
	 */
	private void setAllWords() throws FileNotFoundException {
		BufferedReader br = null;
		br = new BufferedReader(new FileReader(wordFile));
		String sCurrentLine;
		try {
			int hepp = 0;
			while ((sCurrentLine = br.readLine()) != null && hepp != 100){
				if(sCurrentLine.length() >= minWordLength 
						&& sCurrentLine.length() <= maxWordLength
						&& sCurrentLine.matches("^[a-zA-ZåäöÅÄÖ]+$")){
					allWords.add(sCurrentLine);
					hepp++;
				}
			}
		} catch (IOException e) {
			System.out.print(e.getMessage());
			e.printStackTrace();
		} finally {
			try {
				if (br != null)br.close();
			} catch (IOException ex) {
				ex.printStackTrace();
			}
		}
	}

}
