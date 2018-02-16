package menu.com;
import java.awt.Color;
import java.awt.Desktop;
import java.awt.Graphics2D;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.net.URISyntaxException;

import javax.imageio.ImageIO;
import javax.swing.ButtonGroup;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JTextArea;
import javax.swing.text.View;

import Snake.OptionFrame;

public class Menu implements ActionListener{
	JButton jeu;
	JButton option;
	JButton classement;
	JButton quitter;
	JPanel menu;
	JFrame frame;
	JMenuBar barMenu;
	JMenu jeuMenu, playMenu, optionMenu, aideMenu;
	ButtonGroup buttonGroupGame;
	JRadioButtonMenuItem jeuMenuSnake,jeuMenuTetris;
	JMenuItem jeuMenuQuitter;
	JMenuItem playMenuPlay,playMenuPause,playMenuReset;
	JMenuItem optionMenuSnake,optionMenuTetris;
	JMenuItem aideMenuAbout,aideMenuClassement;
	
	JRadioButton rdbtnModeFun;
	static JRadioButton rdbtnModeNormal;
	
	BufferedImage in;
	
	public static boolean jouer = false;
	public Menu() throws IOException {
		frame = new JFrame();
		frame.setTitle("Menu - Snake");
		frame.setBounds(100, 0, 500, 650);
		frame.getContentPane().setLayout(null);
		createBarMenu();
		frame.setJMenuBar(barMenu);
		
		
		in =ImageIO.read(new File("./src/imgSnake.png"));
		
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
		jeu.setBounds(frame.getWidth()/4 +15 ,60,200,50);
		
		option = new JButton();
		option.addActionListener(this);
		option.setText("Option");
		option.setBounds(30,jeu.getHeight()+70,200,50);
		
		quitter = new JButton();
		quitter.addActionListener(this);
		quitter.setText("Quitter");
		quitter.setBounds(frame.getWidth()/2 ,jeu.getHeight()+70,200,50);
		
		menu.add(rdbtnModeFun);
		menu.add(rdbtnModeNormal);
		menu.add(quitter);
		menu.add(jeu);
		menu.add(option);
		frame.add(menu);
		frame.setResizable(false);
		frame.setVisible(true);
		frame.setDefaultCloseOperation(frame.EXIT_ON_CLOSE);
	}
	private void createBarMenu() {
		/*
		JMenuBar barMenu;
		JMenu jeuMenu, playMenu, optionMenu, aideMenu;
		JMenuItem jeuMenuSnake,jeuMenuTetris,jeuMenuQuitter;
		JMenuItem playMenuPlay,playMenuPause,PlayMenuStop;
		JMenuItem optionMenuSnake,optionMenuTetris;
		JMenuItem aideMenuAbout,aideMenuClassement;
		*/
		
		barMenu = new JMenuBar();
		
		jeuMenu = new JMenu("Jeu");
		playMenu = new JMenu("Play");
		optionMenu = new JMenu("Option");
		aideMenu = new JMenu("Aide");
		
		barMenu.add(jeuMenu);
		barMenu.add(playMenu);
		barMenu.add(optionMenu);
		barMenu.add(aideMenu);
		
		buttonGroupGame = new ButtonGroup();
		jeuMenuSnake = new JRadioButtonMenuItem("Snake");
		jeuMenuSnake.setSelected(true);
		buttonGroupGame.add(jeuMenuSnake);
		jeuMenuTetris = new JRadioButtonMenuItem("Tetris");
		buttonGroupGame.add(jeuMenuTetris);
		jeuMenuQuitter = new JMenuItem("Quitter");
		
		jeuMenu.add(jeuMenuSnake);
		jeuMenu.add(jeuMenuTetris);
		jeuMenu.add(jeuMenuQuitter);
		
		playMenuPlay = new JMenuItem("Play");
		playMenuPause = new JMenuItem("Pause");
		playMenuReset = new JMenuItem("Reset");
		
		playMenu.add(playMenuPlay);
		playMenu.add(playMenuPause);
		playMenu.add(playMenuReset);
		
		optionMenuSnake = new JMenuItem("Option Snake");
		optionMenuSnake.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				new OptionFrame();
				
			}
		});
		optionMenuTetris = new JMenuItem("Option Tetris");
		
		optionMenu.add(optionMenuSnake);
		optionMenu.add(optionMenuTetris);
		
		aideMenuClassement = new JMenuItem("Classement");
		aideMenuAbout = new JMenuItem("A propos");
		aideMenuAbout.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent arg0) {
				// TODO Auto-generated method stub
				Desktop desktop = Desktop.getDesktop(); 
				try {
					desktop.browse(new java.net.URI("http://kdd.ics.uci.edu/databases/kddcup99/kddcup99.html"));
				} catch (IOException | URISyntaxException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				} 
			}
		});
		
		aideMenu.add(aideMenuClassement);
		aideMenu.add(aideMenuAbout);
		
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
