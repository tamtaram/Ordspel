package create;

/**
 * Skriver en textfil
 */
import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class WordFileCreator extends WordFileStringHandler {
	
	/**
	 * 
	 * @param allWords en List<String> med alla ord som ska skrivas i textfilen
	 */
	public WordFileCreator(List<String> allWords) {
		super(allWords);
	}
	
	/**
	 * Skriver till filen
	 */
	public void writeToFile(){
		BufferedWriter bw = null;
		try {
			int hepp = 0;
			bw = new BufferedWriter(new FileWriter("conjugated_7.txt"));
			for(String s : linesofPlayWords){
				System.out.println(hepp + " :" + linesofPlayWords.size());
				bw.write(s);
				bw.newLine();
			}		    
		} catch (IOException ex) {
			System.out.println("error");
			ex.printStackTrace();
		} finally {
			try {
				if (bw != null) {
					bw.flush();
					bw.close();
				}
			} catch (IOException ex) {
				System.out.println("error");
				ex.printStackTrace();
			}
		}
	}

}
