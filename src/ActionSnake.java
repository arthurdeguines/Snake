import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import javafx.scene.text.Font;

public class ActionSnake implements KeyListener,ActionListener{
	Direction direction;
	JTextArea score;
	JFrame frame;
	JButton retourMenu;
	static JPanel[][] mapDessin ;
	static int scoreInt = 0;
	public ActionSnake() {
		fenetre();
		this.direction = Direction.DROITE;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void setScore(int taille) {
		score.setText("Score : "  + (Snake.taille-3) *100);
	}
	public void fenetre() {
		frame = new JFrame();
		frame.setBounds(100, 0, 900, 637);
		mapDessin = new JPanel[20][20];
		frame.getContentPane().setLayout(null);
		JPanel panScore = new JPanel();
		panScore.setBounds(600,0,300,140);
		panScore.setBackground(new Color(100,200,200));
		score = new JTextArea();
		panScore.add(score);
		score.setText("Score : "  + (Snake.taille-3) *100);
		score.setEditable(false);
		score.setFocusable(false);
		retourMenu = new JButton("Retour au menu");
		retourMenu.setBounds(600, 400, 300, 100);
		retourMenu.addActionListener(this);
		retourMenu.setFocusable(false);

		score.setBackground(null);
		JPanel panMap = new JPanel();
		panMap.setSize(600,600);
		panMap.setLayout(new GridLayout(20, 20, 0, 0));
		for (int i = 0; i < Map.map.length; i++) {
			for (int j = 0; j < Map.map.length; j++) {
			
				mapDessin[i][j] = new JPanel();
				if (!(i == 0 || j == 0 || Map.map.length -1 == j || Map.map.length -1 == i)) {
				mapDessin[i][j].setBorder(BorderFactory.createLineBorder(new Color(0,255,0),2));
				
				mapDessin[i][j].setBackground(Color.GREEN);
				}
				panMap.add(mapDessin[i][j]);
			}
		}
		
		dessine();
		frame.add(retourMenu);
		frame.add(panMap);
		frame.add(panScore);
		frame.setVisible(true);
		frame.addKeyListener(this);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
	}
	public static void dessine() {
		for (int i = 0; i < Map.map.length; i++) {
			for (int j = 0; j < Map.map.length; j++) {
				if(Map.map[i][j].getType()==TypeCase.MUR) {
					mapDessin[i][j] .setBackground(Color.RED);
				}else if(Map.map[i][j].getType()==TypeCase.TETE){
					mapDessin[i][j] .setBackground(Color.ORANGE);
				}else if(Map.map[i][j].getType()==TypeCase.SNAKE){
					mapDessin[i][j] .setBackground(Color.BLUE);
				}else if(Map.map[i][j].getType()==TypeCase.NOURITURE) {
					mapDessin[i][j] .setBackground(Color.BLACK);
				}else {
					mapDessin[i][j] .setBackground(Color.GREEN);
				}
				
			}
		}
	}
	public void keyPressed(KeyEvent e)
	{	
		if(Map.aJouer == false) {
		switch (e.getKeyCode())
		{
			case KeyEvent.VK_RIGHT:
				if(getDirection() != Direction.GAUCHE) {
					setDirection(Direction.DROITE);
					Map.aJouer = true;
				}
				break;
			case KeyEvent.VK_LEFT:
				if(getDirection() != Direction.DROITE) {
					setDirection(Direction.GAUCHE);
					Map.aJouer = true;

				}
				
				break;
			case KeyEvent.VK_UP:
				if(getDirection() != Direction.BAS) {
					setDirection(Direction.HAUT);
					Map.aJouer = true;

				}
				
				break;
			case KeyEvent.VK_DOWN:
				if(getDirection() != Direction.HAUT) {
					setDirection(Direction.BAS);
					Map.aJouer = true;
					
				}
				
				break;
		}
	}
	}	
	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
	public void addScore(int i) {
		this.scoreInt += i;
		score.setText("Score : "  + this.scoreInt);
		
	}
	@Override
	public void actionPerformed(ActionEvent arg0) {
		Map.fermer = true;
		Map.perdu = false;
		frame.dispose();
		new Menu();
		
		
	}
}

