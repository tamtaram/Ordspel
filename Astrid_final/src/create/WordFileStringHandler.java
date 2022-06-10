package create;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Klass som skapar arrayer med strängar som följer det givna mönstret:
 * "altanen,antalen ale,aln,alt,ana,ann" där altanen och antalen är "vinnarord",
 * följt av övriga ord som innehåller samma bokstäver men som inte är vinnarord.
 * @author lottenwester1
 *
 */
public class WordFileStringHandler {
	private ArrayList<String> allWords = new ArrayList<String>();
	protected ArrayList<String> linesofPlayWords = new ArrayList<String>();
	private int maxLengthAnagrams = 7;
	private int minLengthAnagrams = 3;
	private int maxLengthWinWord = 7;
	private int minLengthWinWord = 7;
	private int minAmounthAnagrams = 20;

	/**
	 * Går igenom arrayen med alla ord och byter bort accenter och specialtecken: t ex ü = u, á=a
	 * @param allWords ArrayList<String> med alla ord
	 * Använder sen funktionen createLinesofPlayWord()
	 */
	public WordFileStringHandler(List<String> allWords){
		for(int i = 0; i < allWords.size(); i++){
			String s = allWords.get(i);
			if(s.length() <= maxLengthWinWord || s.length() >= minLengthWinWord){
				s = s.replaceAll("[áàâ]", "a");	
				s = s.replaceAll("[éèêê]", "e");	
				s = s.replaceAll("[íìîï]", "i");	
				s = s.replaceAll("[óòô]", "o");	
				s = s.replaceAll("[úùûü]", "u");
				this.allWords.add(s);
			}
		}
		createLinesofPlayWords();
	}	
	
	/**
	 * 
	 * @return en ArrayList<String> med textsträngar med vinnarord, sen spelord
	 */
	public ArrayList<String> getLinesofPlayWords(){
		return linesofPlayWords;
	}

	/**
	 * Anropar funktionen sortWinWords(), som returnerar en ArrayList<ArrayList<String>>.
	 * Varje sträng är ett ord, varje ArrayList<String> är alla ord som innehåller samma bokstäver.
	 * För varje 
	 */
	private void createLinesofPlayWords(){
		ArrayList<ArrayList<String>> winWordswithAna = sortWinWords();
		for(ArrayList<String> winWord : winWordswithAna){
			String writeString = createLine(winWord);
			if(!writeString.equals("")){
				linesofPlayWords.add(writeString);
			}
		}
	}
	/**
	 * 
	 * @param winWord En ArrayList<String> med vinnarord
	 * @return en sträng som innehåller ett korrekt vinnarord, 
	 * sen komma och eventuellt fler korrekta vinnarord som använder samma bokstäver, 
	 * sen mellanslag och alla andra ord som använder samma bokstäver som inte är vinnarord, separerade med komma.
	 * Använder sig av funktionen findAnagrams för det första av varje ord. 
	 */
	private String createLine(ArrayList<String> winWord){	
		String write = "";
		for(String winWordS : winWord){
			write += winWordS + ",";
		}
		write = write.substring(0, write.length()-1);
		write += " ";
		ArrayList<String> anagrams = findAnagrams(winWord.get(0));
		if(anagrams.size() >= minAmounthAnagrams){
		Collections.sort(anagrams, new WordComparator());
		for(String ana : anagrams){
			write += ana + ",";
		}
		write = write.substring(0, write.length()-1);
		return write;
		}
		else return "";
	}

	/**
	 * Skapar först en ArrayList<String> med alla vinnarord med hjälp av funktionen findWinWords().
	 * Går sedan igenom alla vinnarord och tar bort kopior med hjälp av funktionen winWordsareAnagrams().
	 * På så vis finns det inte en sträng som t ex innehåller [altanen, antalen] 
	 * och sedan en sträng som innehåller [antalen, altanen], utan bara en av dem.
	 * @return en ArrayList<ArrayList<String>> med vinnarord 
	 * Alla ord i samma ArrayList<String> använder exakt samma bokstäver, 
	 * och samma bokstavskombination förekommer endast en gång i den
	 */
	@SuppressWarnings("unchecked")
	private ArrayList<ArrayList<String>> sortWinWords(){
		ArrayList<String> winWords = findWinWords();			
		ArrayList<ArrayList<String>> winWordsWiththeirAna = new ArrayList<ArrayList<String>>();		
		ArrayList<String> tmp = new ArrayList<String>();
		ArrayList<String> currentWinWords = new ArrayList<String>();

		for(String winWord1 : winWords){
			if(!currentWinWords.contains(winWord1)){
				tmp.clear();
				tmp.add(winWord1);
				for(String winWord2 : winWords){
					if(winWordsareAnagrams(winWord1, winWord2)){
						tmp.add(winWord2);
						currentWinWords.add(winWord2);
					}
				}
			}
			winWordsWiththeirAna.add((ArrayList<String>) tmp.clone());
		}
		return winWordsWiththeirAna;
	}

	/**
	 * Går igenom alla ord, och om de har en godkänd längd läggs de till på en ArrayList<String>
	 * @return ArrayList<String> med alla godkända ord
	 */
	private ArrayList<String> findWinWords(){
		ArrayList<String> winWords = new ArrayList<String>();
		for(String sourceS : allWords){
			if(sourceS.length() <= maxLengthWinWord 
					&& sourceS.length() >= minLengthWinWord){
				winWords.add(sourceS);
			}
		}
		return winWords;
	}

	/**
	 * Jämför två ord för att se om de innehåller samma bokstäver
	 * @param winWord1
	 * @param winWord2
	 * @return true ifall de två orden innehåller samma bokstäver, false ifall de inte gör det
	 */
	private boolean winWordsareAnagrams(String winWord1, String winWord2){
		if(winWord1.equals(winWord2)) return false;
		char[] winChar1 = winWord1.toCharArray();
		Arrays.sort(winChar1);
		char[] winChar2 = winWord2.toCharArray();
		Arrays.sort(winChar2);
		for(int i = 0; i < winChar1.length; i++){
			if(winChar1[i] != winChar2[i]) break;
			if(i == winChar1.length-1) {
				//System.out.println(winWord1 + " " + winWord2);
				return true;
			}
		}
		return false;
	}

	/**
	 * Tar ett ord, och letar igenom ArrayList<String> allWords efter vilka ord som går att skapa med de givna bokstäverna
	 * @param sourceS Sträng med det ord som ska testas
	 * @return en ArrayList<String> med alla möjliga ord som går att skapa med de givna bokstäverna
	 */
	private ArrayList<String> findAnagrams(String sourceS){
		ArrayList<String> anagrams = new ArrayList<String>();		

		char[] sourceChar = sourceS.toCharArray();
		Arrays.sort(sourceChar);		
		for(int i = 0; i < allWords.size(); i ++){
			String testifAnagram = allWords.get(i);
			if(testifAnagram.length() <= maxLengthAnagrams && testifAnagram.length() >= minLengthAnagrams){
				char[] testifAnaChar = testifAnagram.toCharArray();
				Arrays.sort(testifAnaChar);

				int sourceLetter = 0;
				int testAnaLetter = 0;	

				loop:
					while(sourceChar[sourceLetter] == testifAnaChar[testAnaLetter] || 
					sourceChar[sourceLetter] < testifAnaChar[testAnaLetter]){
						while(sourceChar[sourceLetter] == testifAnaChar[testAnaLetter]){
							sourceLetter++;
							testAnaLetter++;					
							if(testifAnagram.length() == testAnaLetter){
								anagrams.add(testifAnagram);
								break loop;
							}
							if(sourceLetter == sourceS.length()){
								break loop;
							}

						}
						while(sourceChar[sourceLetter] < testifAnaChar[testAnaLetter]){
							sourceLetter++;
							if(sourceLetter == sourceS.length()){
								break loop;
							}
						}

					}
			}
		}
		return anagrams;
	}
}
