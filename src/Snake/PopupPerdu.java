package Snake;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JTextField;

import menu.com.Menu;

import java.awt.Font;
import java.awt.Graphics;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Component;

public class PopupPerdu implements ActionListener{

	private JFrame frame;
	private JTextField txtPerdu;
	JButton btnRetourAuMenu;
	JButton btnNewButton;

	/**
	 * Launch the application.
	 */


	/**
	 * Create the application.
	 */
	public PopupPerdu() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 280);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtPerdu = new JTextField();
		txtPerdu.setEditable(false);
		txtPerdu.setFont(new Font("Lucida Fax", Font.BOLD, 51));
		txtPerdu.setText("PERDU");
		txtPerdu.setBounds(129, 30, 215, 90);
		txtPerdu.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		frame.getContentPane().add(txtPerdu);
		txtPerdu.setColumns(10);
		
		btnNewButton = new JButton("Rejouer");
		btnNewButton.setBackground(Color.GREEN);
		btnNewButton.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnNewButton.setBounds(0, 151, frame.getWidth()/2, 93);
		btnNewButton.addActionListener(this);
		frame.getContentPane().add(btnNewButton);
		
		btnRetourAuMenu = new JButton("Retour au menu");
		btnRetourAuMenu.setBackground(Color.ORANGE);
		btnRetourAuMenu.setFont(new Font("Tahoma", Font.BOLD, 22));
		btnRetourAuMenu.setBounds(frame.getWidth()/2, 151, frame.getWidth()/2, 93);
		btnRetourAuMenu.addActionListener(this);
		frame.getContentPane().add(btnRetourAuMenu);
		
		frame.setVisible(true);
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == btnNewButton) {
			Menu.jouer=true;
			Map.perdu=false;
			Map.fermer = false;
			InterfaceSnake.frame.dispose();
			frame.dispose();
		}else {
			InterfaceSnake.frame.dispose();
			frame.dispose();
			try {
				new Menu();
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
		
	}

	/**
	 * Initialize the contents of the frame.
	 */
	
	
}
