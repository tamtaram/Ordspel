package create;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;


public class Main {

	/**
	 * @param args
	 * @throws FileNotFoundException 
	 */
	public static void main(String[] args) throws FileNotFoundException {
	      		WordFileReader reader = new WordFileReader(3, 7, new File("dsso.txt"));
	    		ArrayList<String> readLines = reader.getAllWords();
	    		WordFileStringHandler handler = new WordFileStringHandler(readLines);	    		
	    		WordFileCreator printer = new WordFileCreator(handler.getLinesofPlayWords());

	}
}
