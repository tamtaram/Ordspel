package model.interfaces;

public interface TypeListener {
	
	public void writeLetterChanged(int where, char changeTo);
	
	public void printLetterChanged(int where, char changeTo);
	
	public void printLetterUsed(int where, boolean isUsed);

}
