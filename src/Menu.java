import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.text.View;

public class Menu implements ActionListener{
	JButton jeu;
	JButton option;
	JPanel menu;
	JFrame frame;
	static boolean jouer = false;
	public Menu() {
		frame = new JFrame();
		frame.setTitle("Lol");
		frame.setBounds(100, 0, 500, 700);
		frame.getContentPane().setLayout(null);
		menu = new JPanel();
		menu.setBounds(0, 400, 500, 300);
		menu.setBackground(new Color(100,200,200));
		menu.setLayout(null);
		jeu = new JButton();
		
		jeu.addActionListener(this);
		  
		jeu.setText("Jouer");
		jeu.setBounds((menu.getWidth()-200)/2,50,200,50);
		option = new JButton();
		option.addActionListener(this);
		option.setText("Option");
		option.setBounds((menu.getWidth()-200)/2,jeu.getHeight()+100,200,50);
		menu.add(jeu);
		menu.add(option);
		frame.add(menu);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	public boolean getJouer() {
		return this.jouer;
	}

	private void lancerJeu() {
		new Jeu();
		
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jeu) {
			jouer = true;
		}else {
			new Option();
		}
		
	} 
}
