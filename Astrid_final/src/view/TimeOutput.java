package view;

import model.TimeHandler;
import model.interfaces.TimerListener;
/** 
*Visar tiden för spelomgången.
* 
* @author Lotten Wester
* 
* @version 1.0
*/
@SuppressWarnings("serial")
public class TimeOutput extends JLabelSettings implements TimerListener{

	/**
	 * 
	 * @param timeHandler Model-objektet som hanterar tidsförändringar
	 */
	public TimeOutput(TimeHandler timeHandler){
		timeHandler.addTimeListener(this);
		setInfoOutput();
		setText("   seconds");
	}

	/**
	 * Ändrar den visade tiden
	 * @param time Tiden som visas
	 */
	@Override
	public void timerChanged(int time) {
		setText(" " + Integer.toString(time) + " seconds");
		
	}
}
