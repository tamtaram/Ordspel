package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Ett objekt som läser in en .txt-fil och skapar en ArrayList<String> med alla .txt-filens rader.
 * @author lottenwester1
 *
 */

public class ReadFiles {

	final File conjugatedPreparedWordListLength7 = new File("conjugated_7.txt");
	final File basicPreparedWordListLength7 = new File("7.txt");

	private ArrayList<String> allLines = new ArrayList<String>();

	public ReadFiles(){
		setAllLines();
	}

	/**
	 * @return .txt-filens alla rader
	 */
	public ArrayList<String> getAllLines(){
		return allLines;
	}

	/**
	 * Metod som läser in alla rader med hjälp av en BufferedReader
	 */
	private void setAllLines(){
		BufferedReader br = null;
		try {
			String sCurrentLine;
			br = new BufferedReader(new FileReader(conjugatedPreparedWordListLength7));
			while ((sCurrentLine = br.readLine()) != null) {
				allLines.add(sCurrentLine);
			}

		} catch (IOException e) {
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
