package view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Frame;
import java.awt.GridLayout;
import java.awt.KeyboardFocusManager;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.InputController;
import model.GuessHandler;
import model.PointsHandler;
import model.TimeHandler;
import model.TypeHandler;
/**
 * Själva spelfönstret
 * @author lottenwester1
 *
 */
@SuppressWarnings("serial")
public class GameOutput extends Frame  {
	
	public GameOutput(InputController input, TypeHandler typeHandler, 
			GuessHandler guessHandler, PointsHandler pointsHandler, TimeHandler timeHandler){
	//setResizable(false);
	setBackground(Color.WHITE);
	setVisible(true);
	addWindowListener(input);
	KeyboardFocusManager manager = KeyboardFocusManager.getCurrentKeyboardFocusManager();
    manager.addKeyEventDispatcher(input);
	
    JPanel everything = new JPanel(new BorderLayout(10, 10));
    everything.setBorder(BorderFactory.createLineBorder(Color.black));
    
	TypeOutput wordOutput = new TypeOutput(typeHandler);
	Box up = Box.createVerticalBox();
	up.add(wordOutput.getPrintPart());
	up.add(wordOutput.getWritePart());
	everything.add(up, BorderLayout.NORTH);
	
	GuessOutput anagramOutput = new GuessOutput(guessHandler);
	everything.add(anagramOutput, BorderLayout.CENTER);
	
	JPanel upLeft = new JPanel();
	JPanel gameEvents = new JPanel();
	gameEvents.setLayout(new GridLayout(5, 1, 1, 1));
	gameEvents.add(new PointsOutput(pointsHandler));
	TimeOutput time = new TimeOutput(timeHandler);
	gameEvents.add(time);
	JButton blanda = new JButton("Blanda");
	blanda.addActionListener(input);
	gameEvents.add(blanda);
	JButton gissa = new JButton("Gissa");
	gissa.addActionListener(input);
	gameEvents.add(gissa);
	JButton pausa = new JButton("Pausa");
	pausa.addActionListener(input);
	gameEvents.add(pausa);
	JPanel gameFrames = new JLabelFrames(gameEvents, 10, 10);
	upLeft.add(gameFrames, BorderLayout.CENTER);

	JPanel downLeft = new JPanel(new BorderLayout(10, 10));
	ImageIcon mumin = new ImageIcon("mumin.jpg");
	JLabel trollet = new JLabel(mumin);
	downLeft.add(trollet, BorderLayout.SOUTH);
	JPanel leftSide = new JPanel();
	leftSide.setLayout(new BorderLayout());
	leftSide.add(downLeft, BorderLayout.SOUTH);
	leftSide.add(upLeft, BorderLayout.CENTER);
	
	everything.add(leftSide, BorderLayout.WEST);

	JPanel everythingFrames = new JLabelFrames(everything, 10, 10);
	add(everythingFrames);

	pack();
	}


}
