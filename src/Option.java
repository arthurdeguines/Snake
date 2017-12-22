import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class Option {
	JButton jeu;
	JButton option;
	JPanel menu;
	JFrame frame;
	public Option() {
		frame = new JFrame();
		frame.setTitle("Lol");
		frame.setBounds(100, 0, 500, 700);
		frame.getContentPane().setLayout(null);
		
		//ACCELERATION
		JPanel panAcceleration = new JPanel();
		JCheckBox acceleration = new JCheckBox("Acceleration");
		panAcceleration.add(acceleration);
		panAcceleration.setBounds(100,100,200,50);
		
		//ENLEVER LES POINTS
		JPanel panEnlevePoints = new JPanel();
		JCheckBox enlevPoints = new JCheckBox("Enlever des points");
		panEnlevePoints.add(enlevPoints);
		panEnlevePoints.setBounds(100,200,200,50);
		
		//VITESSE
		JPanel panVitesse = new JPanel();
		JTextField vitesse = new JTextField("50");
		panVitesse.setBounds(100,300,200,50);
		panVitesse.add(vitesse);
		
		frame.add(panVitesse);
		frame.add(panEnlevePoints);
		frame.add(panAcceleration);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
	}
}
