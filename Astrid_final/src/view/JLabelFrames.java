package view;

import java.awt.BorderLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
/**  
* Skapar en JPanel som genom en BorderLayout innehåller en JPanel med JLabels runt om på alla kanter.
* 
* @author Lotten Wester
* 
* @version 1.0
*/
@SuppressWarnings("serial")
public class JLabelFrames extends JPanel {
	
	/**
	 * 
	 * @param dude Själva JPaneln som ska omslutas av JLabels
	 * @param x Placering av objektet på x-axeln
	 * @param y Placering av objektet på y-axeln
	 */
    public JLabelFrames(JPanel dude, int x, int y) {
        setLayout(new BorderLayout(x, y));
        add(new JLabel(), BorderLayout.WEST);
        add(new JLabel(), BorderLayout.EAST);
        add(new JLabel(), BorderLayout.NORTH);
        add(new JLabel(), BorderLayout.SOUTH);
        add(dude, BorderLayout.CENTER);
    }

}
