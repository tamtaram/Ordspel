package controller;

public interface InputListener {
	public void letterInput(char letter);

	public void backspace();

	public void sendGuess();

	public void requestMixWord();

	public void pauseOnOff(boolean pauseOn);

	public void startGame();

	public void endGame();

}