package view;

import model.PointsHandler;
import model.interfaces.PointsListener;
/** 
* Visar poängen. 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
@SuppressWarnings("serial")
public class PointsOutput extends JLabelSettings implements PointsListener {
	private int points = 0;
	
	/**
	 * @param pointHandler Model-objektet som hanterar poängförändringar.
	 */
	public PointsOutput(PointsHandler pointHandler){
		pointHandler.addPointListener(this);
		setInfoOutput();
		setText(" " + Integer.toString(points) + " poäng");
	}

	/**
	 * Ändrar poängen
	 * @param points Den poäng som ska visas
	 */
	@Override
	public void pointsChanged(int points) {
		setText(" " + Integer.toString(points) + " poäng");
		
	}

}
