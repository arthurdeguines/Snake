package Snake;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JRadioButton;
import javax.swing.JTextField;

import menu.com.Menu;

import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.InetAddress;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import java.awt.Color;

public class PopuEnregistrer implements ActionListener{

	private JFrame frame;
	private JTextField txtBravo;
	private JTextField txtEntrezVotreNom;
	private JTextField textField;
	private JTextField score;
	Option option;

	/**
	 * Launch the application.
	 */

	//System.out.println(System.getProperty("user.name"));
	

	/**
	 * Create the application.
	 */
	public PopuEnregistrer(Option option) {
		this.option = option;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 360);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		txtBravo = new JTextField();
		txtBravo.setFont(new Font("Tahoma", Font.BOLD, 44));
		txtBravo.setEditable(false);
		txtBravo.setText("NOUVEAU RECORD");
		txtBravo.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		txtBravo.setBounds(0, 0, 428, 99);
		frame.getContentPane().add(txtBravo);
		txtBravo.setColumns(10);
		
		txtEntrezVotreNom = new JTextField();
		txtEntrezVotreNom.setEditable(false);
		txtEntrezVotreNom.setText("Entrez votre nom :");
		txtEntrezVotreNom.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		txtEntrezVotreNom.setBounds(78, 115, 100, 26);
		frame.getContentPane().add(txtEntrezVotreNom);
		txtEntrezVotreNom.setColumns(10);
		
		textField = new JTextField();
		textField.setBounds(194, 115, 146, 26);
		frame.getContentPane().add(textField);
		textField.setColumns(10);
		
		JButton Valider = new JButton("Valider");
		Valider.setBackground(Color.GREEN);
		Valider.setBounds(56, 216, 275, 72);
		Valider.addActionListener(this);
		frame.getContentPane().add(Valider);
		
		score = new JTextField();
		score.setText("Score : " + InterfaceSnake.scoreInt);
		score.setEditable(false);
		score.setColumns(10);
		score.setBorder(BorderFactory.createLineBorder(Color.BLACK,0));
		score.setBounds(136, 157, 200, 26);
		frame.getContentPane().add(score);
		frame.setVisible(true);
		
	}
	public void enregistrer() throws SQLException {
		String driverName = "org.gjt.mm.mysql.Driver";
		try {
			Class.forName(driverName);
		} catch (ClassNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
	 
	    String nomJoueur = "";
	    System.out.println(textField.getText());
	    if(textField.getText().equals("")) {
	    	nomJoueur = "Undefined name";
	    }else {
	    	nomJoueur = textField.getText();
	    }
		String req = "INSERT INTO utilisateur ( pseudo , score,acceleration,enleverPoint,nomPc)  VALUES  ('"+ nomJoueur +"',"+InterfaceSnake.scoreInt+","+option.isAccelerationNormal()+","+option.isEnleverPointNormal()+",'"+System.getProperty("user.name")+"');";
		Statement st = null;
		try {
			st = Connexion.connection.createStatement();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	
			try {
				st.executeUpdate(req);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println(req);
	}

	@Override
	public void actionPerformed(ActionEvent arg0) {
		try {
			enregistrer();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Menu.jouer=true;
		Map.perdu=false;
		Map.fermer = false;
		InterfaceSnake.frame.dispose();
		frame.dispose();
		
	}
}
