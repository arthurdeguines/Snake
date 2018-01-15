import java.awt.Color;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.text.View;

public class Menu implements ActionListener{
	JButton jeu;
	JButton option;
	JButton classement;
	JButton quitter;
	JPanel menu;
	JFrame frame;
	
	JRadioButton rdbtnModeFun;
	static JRadioButton rdbtnModeNormal;
	static boolean jouer = false;
	public Menu() throws IOException {
		frame = new JFrame();
		frame.setTitle("Lol");
		frame.setBounds(100, 0, 500, 630);
		frame.getContentPane().setLayout(null);
		
		
		BufferedImage in =ImageIO.read(new File("./src/img.jpg"));

		JLabel picLabel = new JLabel(new ImageIcon(in));
		picLabel.setBounds(0, 0, 500, 400);
		frame.add(picLabel);
		
		menu = new JPanel();
		menu.setBounds(0, 400, 500, 300);
		menu.setBackground(new Color(100,200,200));
		menu.setLayout(null);
		
		rdbtnModeNormal = new JRadioButton("Mode normal");
		rdbtnModeNormal.setSelected(true);
		rdbtnModeNormal.setBounds((menu.getWidth()-200)/2-10, 20, 110, 29);
		rdbtnModeNormal.addActionListener(this);
		rdbtnModeNormal.setBackground(new Color(100,200,200));
		
		rdbtnModeFun = new JRadioButton("Mode fun");
		rdbtnModeFun.addActionListener(this);
		rdbtnModeFun.setBackground(new Color(100,200,200));
		rdbtnModeFun.setBounds((menu.getWidth())/2+10, 20, 110, 29);
		
		jeu = new JButton();
		jeu.addActionListener(this);
		jeu.setText("Jouer");
		jeu.setBounds(30,60,200,50);
		
		option = new JButton();
		option.addActionListener(this);
		option.setText("Option");
		option.setBounds(30,jeu.getHeight()+70,200,50);
		
		classement = new JButton();
		classement.addActionListener(this);
		classement.setText("Classement");
		classement.setBounds(frame.getWidth()/2 ,60,200,50);
		
		quitter = new JButton();
		quitter.addActionListener(this);
		quitter.setText("Quitter");
		quitter.setBounds(frame.getWidth()/2 ,jeu.getHeight()+70,200,50);
		
		menu.add(rdbtnModeFun);
		menu.add(rdbtnModeNormal);
		menu.add(classement);
		menu.add(quitter);
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

	public static boolean getMode() {
		return rdbtnModeNormal.isSelected();
	}
	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == jeu) {
			jouer = true;
			frame.dispose();
		}else if(e.getSource() == option){
			new OptionFrame();
		}else if(e.getSource() == rdbtnModeFun){
			rdbtnModeNormal.setSelected(!rdbtnModeFun.isSelected());
		}else if(e.getSource() == rdbtnModeNormal){
			rdbtnModeFun.setSelected(!rdbtnModeNormal.isSelected());
		}else if(e.getSource() == classement){
			rdbtnModeFun.setSelected(!rdbtnModeNormal.isSelected());
		}else if(e.getSource() == quitter){
			frame.dispose();
		}
		
	} 
}
