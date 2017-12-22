import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;

import javax.crypto.SecretKeyFactorySpi;
import javax.swing.JFrame;

public class Main extends JFrame implements KeyListener{
	
	public static void main(String[] args) {
		new Menu();
		while(!Menu.jouer) {
			try {
				Thread.sleep (200);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		new Jeu();
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
