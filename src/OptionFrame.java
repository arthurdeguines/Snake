import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.text.html.HTMLDocument.HTMLReader.IsindexAction;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

public class OptionFrame implements ActionListener{

	private JFrame frame;
	JToggleButton tglbtnModeNormal;
	JCheckBox chckbxAcceleration;
	JCheckBox chckbxNewCheckBox;
	JSpinner spinnerVitesse;
	JSpinner spinnerTaille;
	JPanel barreMilieu;
	JButton btnSauvegarder;
	JButton btnAnluler;
	private JTextField txtVitesse;
	private JTextField txtTaille;
	private JCheckBox enlevPoint2;
	private JCheckBox acceleration2;
	
	


	/**
	 * Initialize the contents of the frame.
	 */
	public OptionFrame() {
		frame = new JFrame();
		frame.setBounds(100, 100, 450, 300);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		tglbtnModeNormal = new JToggleButton("Mode normal");
		tglbtnModeNormal.addActionListener(this);
		tglbtnModeNormal.setBounds(127, 16, 163, 29);
		
		chckbxAcceleration = new JCheckBox("Acceleration");
		chckbxAcceleration.setHorizontalAlignment(SwingConstants.LEFT);
		chckbxAcceleration.setBounds(29, 87, 139, 29);
		chckbxAcceleration.setSelected(Main.option.isAccelerationNormal());
		frame.getContentPane().add(chckbxAcceleration);
		
		chckbxNewCheckBox = new JCheckBox("Enlever des points");
		chckbxNewCheckBox.setBounds(29, 124, 171, 29);
		chckbxNewCheckBox.setSelected(Main.option.isEnleverPointNormal());
		frame.getContentPane().add(chckbxNewCheckBox);
		
		
		
		frame.getContentPane().add(tglbtnModeNormal);
		
		spinnerVitesse = new JSpinner();
		spinnerVitesse.setBounds(117, 87, 49, 26);
		spinnerVitesse.setValue(Main.option.getVitesseFun());
		frame.getContentPane().add(spinnerVitesse);
		
		txtVitesse = new JTextField();
		txtVitesse.setForeground(Color.BLACK);
		txtVitesse.setEditable(false);
		txtVitesse.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtVitesse.setText("Vitesse : ");
		txtVitesse.setBounds(29, 87, 73, 26);
		frame.getContentPane().add(txtVitesse);
		txtVitesse.setColumns(10);
		
		spinnerTaille = new JSpinner();
		spinnerTaille.setBounds(117, 127, 49, 26);
		spinnerTaille.setValue(Main.option.getTailleMapFun());
		frame.getContentPane().add(spinnerTaille);
		
		txtTaille = new JTextField();
		txtTaille.setForeground(Color.BLACK);
		txtTaille.setEditable(false);
		txtTaille.setBorder(javax.swing.BorderFactory.createEmptyBorder());
		txtTaille.setText("Taille map : ");
		txtTaille.setBounds(29, 127, 73, 26);
		frame.getContentPane().add(txtTaille);
		txtVitesse.setColumns(10);
		
		btnSauvegarder = new JButton("Sauvegarder");
		btnSauvegarder.setBackground(new Color(0,255,0));
		btnSauvegarder.setBounds(29, 199, 147, 29);
		btnSauvegarder.addActionListener(this);
		frame.getContentPane().add(btnSauvegarder);
		
		btnAnluler = new JButton("Quitter");
		btnAnluler.setBackground(new Color(255,0,0));
		btnAnluler.setBounds(238, 199, 147, 29);
		btnAnluler.addActionListener(this);
		frame.getContentPane().add(btnAnluler);
		
		barreMilieu = new JPanel();
		barreMilieu.setBackground(Color.BLACK);
		barreMilieu.setBounds(211, 76, 2, 89);
		frame.getContentPane().add(barreMilieu);
		
		enlevPoint2 = new JCheckBox("Enlever des points");
		enlevPoint2.setBounds(238, 124, 171, 29);
		enlevPoint2.setSelected(Main.option.isEnleverPointFun());
		frame.getContentPane().add(enlevPoint2);
		
		acceleration2 = new JCheckBox("Acceleration");
		acceleration2.setHorizontalAlignment(SwingConstants.LEFT);
		acceleration2.setBounds(238, 87, 139, 29);
		acceleration2.setSelected(Main.option.isAccelerationFun());
		frame.getContentPane().add(acceleration2);
		frame.setVisible(true);
		repaint();
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == tglbtnModeNormal) {
			repaint();
		}else if(e.getSource() == btnSauvegarder) {
			Main.option.setAccelerationFun(acceleration2.isSelected());
			Main.option.setAccelerationNormal(chckbxAcceleration.isSelected());
			Main.option.setEnleverPointFun(enlevPoint2.isSelected());
			Main.option.setEnleverPointNormal(chckbxNewCheckBox.isSelected());
			Main.option.setTailleMapFun((int) spinnerTaille.getValue());
			Main.option.setVitesseFun((int) spinnerVitesse.getValue());
		}else if(e.getSource() == btnAnluler) {
			frame.dispose();
		}
		
	}

	

	private void repaint() {
		if(tglbtnModeNormal.isSelected() == false) {
			chckbxAcceleration.show();
			chckbxNewCheckBox.show();
			spinnerVitesse.hide();
			spinnerTaille.hide();
			txtVitesse.hide();
			barreMilieu.hide();
			acceleration2.hide();
			enlevPoint2.hide();
			tglbtnModeNormal.setText("Mode normal");
		}else {
			tglbtnModeNormal.setText("Mode fun");
			acceleration2.show();
			spinnerVitesse.show();
			spinnerTaille.show();
			txtVitesse.show();
			chckbxNewCheckBox.hide();
			chckbxAcceleration.hide();
			enlevPoint2.show();
			barreMilieu.show();
		}
		
	}
}
