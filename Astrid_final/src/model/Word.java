package model;

/** 
* Ord-objektet. Innehåller själva ordet, anagram på ordet och också vilka kortare ord som kan skapas med bokstavskombinationen.
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
public class Word {

	private String[] word;
	private String[] anagrams;
	
	public Word(String[] word, String[] anagrams){
		this.word = word;
		this.anagrams = anagrams;
	}
	
	/**
	 * @return De ord som går att bilda på bokstavskombinationen
	 */
	public String[] getWord(){
		return word;
	}
	
	/**
	 * 
	 * @return De kortare ord som går att bilda på bokstavskombinationen
	 */
	public String[] getAna(){
		return anagrams;
	}
}
