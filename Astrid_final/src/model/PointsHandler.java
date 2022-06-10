package model;

import java.util.ArrayList;
import java.util.List;

import model.interfaces.PointsListener;

/** 
* Hanterar poäng till spelet 
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/

public class PointsHandler {
	private int points = 0;
	private List<PointsListener> listeners =
			new ArrayList<PointsListener>();
	
	public PointsHandler(){
		
	}
	
	
	/** 
	* Lägger till poäng och meddelar sina lyssnare den nya poängen
	* @param points Poängen som ska läggas till
	*/
	public void addPoints(int points){
		this.points += points;
		firePointsChanged(this.points);
	}

	/** 
	* Ändrar poängen till 0 och meddelar sina lyssnare detta
	*/
	public void reset(){
		points = 0;
		firePointsChanged(points);
	}


	/** 
	* Lägger till en lyssnare
	* @param listener Objekt med interfacet PointsListener
	*/
	public void addPointListener(PointsListener listener){
		listeners.add(listener);
	}
	

	/** 
	* Skickar poängförändringar till sina lyssnare
	* @param points Vad poängen är nu
	*/
	private void firePointsChanged(int points) {
		for (PointsListener listener : listeners)
			listener.pointsChanged(points);
	}
	

}
