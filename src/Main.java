import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.crypto.SecretKeyFactorySpi;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener{
	static Option option;
	
	public static void main(String[] args) {
		boolean modeNormal;
		option = new Option();
		new Menu();
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
		new Jeu(Menu.getMode());
		
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
