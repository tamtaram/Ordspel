package view;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import javax.swing.border.Border;
/** 
* Klass som skapar JLabels med vissa inställningar gällande ramar, typsnitt och storlek.
* 
* 
* @author Lotten Wester
* 
* @version 1.0
*/
@SuppressWarnings("serial")
public class JLabelSettings extends JLabel{
    private Border b = BorderFactory.createLineBorder(Color.black);
    private Border b2 = BorderFactory.createLineBorder(Color.blue);
    private Border b3 = BorderFactory.createLineBorder(Color.green);
    private Font bigText = new Font("Arial", Font.BOLD, 40);
    public Font smallText = new Font("Arial", Font.BOLD, 12);
    private Dimension bigD = new Dimension(50, 50);
    public Dimension smallD = new Dimension(18, 18);
    
    
    JLabelSettings(){
    	standard();
    }
    
    /*
     * Lägger på text, ram och placering
     */
    JLabelSettings(String s){
    	super(s);
    	standard();
    }

    /*
     * Lägger på ram och placering
     */
    public void standard(){
    	setBorder(b);
		setHorizontalAlignment(SwingConstants.CENTER);
		setVerticalAlignment(SwingConstants.CENTER);
 
    }
    
    /*
     * Lägger på font och storleksinställningar för JLabels, i de rutorna som 
     * är avsett för visning av bokstäverna spelaren
     * har att gissa utifrån och de rutor där spelarens inmatade bokstäver syns
     */
    public void setTypeOutput(){
    	setFont(bigText);
    	setPreferredSize(bigD);
   }
    
    /*
     * Ändrar rutorna ifall de är använda (bokstäver som används i gissningen)
     */
    public void setDisabled(){
    	setForeground(Color.LIGHT_GRAY);
    }
    
    /*
     * Ändrar rutorna ifall de är oanvända (bokstäver som inte används i gissningen)
     */
    public void setEnabled(){
    	setForeground(Color.black);
    }
    
    /*
     * Lägger på typsnitt och storleksinställningar för JLabels i de rutorna som
     * är avsedda för visning av möjliga ord spelaren kan gissa på
     */
    public void setGuessOutput(){
    	setFont(smallText);
    	setPreferredSize(smallD);
    }
    
    /*
     * Ändrar ramen utefter ifall ordet är korrekt eller inte
     * @param correct Sant för att ändra till ett korrekt ord, falskt för att ändra till ett icke-korrekt
     */
	public void setCorrect(boolean correct){
		if(correct){
			setBorder(b2);
		}
		if(!correct){
			setBorder(b);
		}
	}
	public void setWordCorrect(){
		setBorder(b3);		
	}
	
	public void setInfoOutput(){
		setFont(smallText);
		setPreferredSize(new Dimension(70, 20));
	}

	
}
