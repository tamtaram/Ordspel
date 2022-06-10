package model;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

import model.interfaces.TimerListener;

/** 
* Hanterare av tidsnedräkningen i spelet
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/

public class TimeHandler {
	Timer timer;
	int seconds = 60;
	private List<TimerListener> listeners =
			new ArrayList<TimerListener>();
	Counter c;
	
	public TimeHandler(){
		timer = new Timer();
	}
	
	/**
	 * Startar nedräkningen
	 */
	public void start(){
		c = new Counter();
	    timer.schedule(c, 0, 1000);
	}
	
	/**
	 * Pausar nedräkningen
	 */
	public void pause(){
		c.cancel();
	}
	
	/**
	 * Innerklass med timer
	 * @author lottenwester1
	 *
	 */
	class Counter extends TimerTask {
		public void run() {
			if (seconds > 0) {
				seconds--;
				fireTimeChanged();
			} else {
				this.cancel();
			}
		}
	}
	
	/**
	 * Returnerar den nuvarande tiden som är kvar
	 * @return Antal sekunder kvar
	 */
	public int getTime(){
		return seconds;
	}

	/**
	 * Lägger till en lyssnare
	 * @param listener Objekt med interfacet TimerListener
	 */
	public void addTimeListener(TimerListener listener){
		listeners.add(listener);
	}
	
	/**
	 * Meddelar sina lyssnare att tiden har ändrats och vad tiden ändrats till
	 */
	private void fireTimeChanged() {
		for (TimerListener listener : listeners) 
			listener.timerChanged(seconds);
		}
}
