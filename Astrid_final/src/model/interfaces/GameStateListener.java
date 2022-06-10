package model.interfaces;

public interface GameStateListener {

	public void pauseOnOff(boolean pauseOn);

	public void gameOver();

	public void newRound(); 
	
}
