import java.awt.event.KeyEvent;

import java.awt.event.KeyListener;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.crypto.SecretKeyFactorySpi;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener{
	static Option option;
	public static void main(String[] args) throws SQLException {
		new Connexion();
		option = new Option();
		try {
			new Menu();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	while (1==1) {
		while(!Menu.jouer ) {
			try {
				Thread.sleep (200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		Menu.jouer=false;
		Map.perdu=false;
		Map.fermer = false;
		ActionSnake.scoreInt = 0;
		
		new Jeu(Menu.getMode(),option);
		System.out.println(Map.perdu && !Map.fermer && Menu.getMode() && ActionSnake.scoreDixieme< ActionSnake.scoreInt && ActionSnake.connexion);
		System.out.println("Mode : " + Menu.getMode());
		System.out.println(ActionSnake.scoreDixieme< ActionSnake.scoreInt );
		if(Map.perdu && !Map.fermer && Menu.getMode() && ActionSnake.scoreDixieme< ActionSnake.scoreInt && ActionSnake.connexion) {
			
			new PopuEnregistrer(option);
		}else if(Map.perdu){
			new PopupPerdu();
		}

	    
	    
		}
	}

	@Override
	public void keyPressed(KeyEvent e) {


		
	}

	@Override
	public void keyReleased(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void keyTyped(KeyEvent arg0) {
		// TODO Auto-generated method stub
		
	}
}
