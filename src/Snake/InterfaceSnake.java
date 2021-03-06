package Snake;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;

import cfranc.xxgames.model.GameCtrlInterface;
import cfranc.xxgames.model.OneDirection;
import menu.com.Menu;

public class InterfaceSnake  extends Thread implements KeyListener,ActionListener,GameCtrlInterface{
	Direction direction;
	JTextArea score;
	JTextArea classement;
	String classementString = "\n";
	static JFrame frame;
	JButton retourMenu;
	static JPanel[][] mapDessin ;
	public static int scoreInt = 0;
	public static int scoreDixieme = 0;
	public static boolean connexion;
	public InterfaceSnake(Option option) throws SQLException {
		 
		//Traitements...
		 
		
		if(Menu.getMode()) {
	    String driverName = "org.gjt.mm.mysql.Driver";
	    try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}

	    if(Connexion.connection != null) {
		    Statement st = Connexion.connection.createStatement();
		    String classement = "SELECT pseudo,score FROM utilisateur WHERE acceleration = "+option.isAccelerationNormal()+" AND enleverPoint="+option.isEnleverPointNormal()+" order by score desc";
		    
		    ResultSet rs = st.executeQuery(classement);
        	scoreDixieme = 0;
	        int cpt = 0;
	        while (rs.next() && cpt<10) {
	        	if(cpt ==9) {
	        		scoreDixieme = rs.getInt(2);
	        	}
	        	cpt ++;

	        	//System.out.println("Score dixieme : " + cpt);
	        	classementString+= rs.getString(1) + " : " + rs.getString(2) + "\n";
	        	connexion = true;
	        }
			
		}else {
			connexion = false;
			classementString+= " Mode hors ligne";
	        }
	    
	    

		}
		fenetre();
		this.direction = Direction.DROITE;
	}
	public void setDirection(Direction direction) {
		this.direction = direction;
	}
	public Direction getDirection() {
		return direction;
	}
	public void fenetre() {
		
		frame = new JFrame();
		frame.setBounds(100, 0, 900, 637);
		mapDessin = new JPanel[Map.map.length][Map.map.length];
		frame.getContentPane().setLayout(null);
		
		score = new JTextArea();
		score.setText("Score : "  + (Snake.taille-3) *100);
		score.setEditable(false);
		score.setFocusable(false);
		
		JPanel panScore = new JPanel();
		panScore.setBounds(600,0,300,140);
		panScore.setBackground(new Color(100,200,200));
		panScore.add(score);
		
		classement = new JTextArea();
		panScore.add(classement);
		if(Menu.getMode()) {
			classement.setText("Classement : " + classementString);
		}
		classement.setEditable(false);
		classement.setFocusable(false);

		JPanel panClassement = new JPanel();
		panClassement.setBounds(600,140,300,360);
		panClassement.add(classement);
		
		retourMenu = new JButton("Retour au menu");
		retourMenu.setBounds(600, 500, 300, 100);
		retourMenu.addActionListener(this);
		retourMenu.setFocusable(false);

		score.setBackground(null);
		classement.setBackground(null);
		JPanel panMap = new JPanel();
		panMap.setSize(600,600);
		panMap.setLayout(new GridLayout(Map.map.length, Map.map.length, 0, 0));
		for (int i = 0; i < Map.map.length; i++) {
			for (int j = 0; j < Map.map.length; j++) {
				mapDessin[i][j] = new JPanel();
				if (!(i == 0 || j == 0 || Map.map.length -1 == j || Map.map.length -1 == i)) {
				mapDessin[i][j].setBorder(BorderFactory.createLineBorder((Color.BLUE),2));
				
				mapDessin[i][j].setBackground(Color.BLUE);
				}
				panMap.add(mapDessin[i][j]);
			}
		}
		
		dessine();
		frame.add(panClassement);
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
					mapDessin[i][j] .setBackground(Color.YELLOW);
				}else if(Map.map[i][j].getType()==TypeCase.NOURITURE) {
					mapDessin[i][j] .setBackground(new Color(255,140,0));
				}else {
					mapDessin[i][j] .setBackground(Color.BLUE);
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
				
			case KeyEvent.VK_D:
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
				
			case KeyEvent.VK_Q:
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
			case KeyEvent.VK_Z:
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
			case KeyEvent.VK_S:
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
		try {
			new Menu();
		} catch (IOException e) {
			System.out.println("Probleme génération menu");
		}
		
		
	}
	@Override
	public boolean advance() {
		// TODO Auto-generated method stub
		return false;
	}
	@Override
	public void changeDirection(OneDirection newDirection) {
		if(Map.aJouer == false) {
			switch (newDirection)
			{
				case RIGHT:
					if(getDirection() != Direction.GAUCHE) {
						setDirection(Direction.DROITE);
						Map.aJouer = true;
					}
					break;
					
				case LEFT:
					if(getDirection() != Direction.DROITE) {
						setDirection(Direction.GAUCHE);
						Map.aJouer = true;

					}
					
					break;
					
				case UP:
					if(getDirection() != Direction.BAS) {
						setDirection(Direction.HAUT);
						Map.aJouer = true;

					}
					
					break;
					
				case DOWN:
					if(getDirection() != Direction.HAUT) {
						setDirection(Direction.BAS);
						Map.aJouer = true;
						
					}
					
					break;
			}
		}
		
	}
}

