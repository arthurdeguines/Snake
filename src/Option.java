import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JFrame;
import javax.swing.JCheckBox;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import javax.swing.JSpinner;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.Color;
import javax.swing.DropMode;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.JPanel;

public class Option {
	boolean accelerationNormal;
	boolean enleverPointNormal;
	boolean accelerationFun;
	boolean enleverPointFun;
	int vitesseFun;
	int tailleMapFun;
	
	public Option() {
		this.accelerationNormal = true;
		this.enleverPointNormal = true;
		this.accelerationFun = true;
		this.enleverPointFun = true;
		this.vitesseFun = 20;
		this.tailleMapFun = 30;
	}

	public boolean isAccelerationNormal() {
		return accelerationNormal;
	}

	public void setAccelerationNormal(boolean accelerationNormal) {
		this.accelerationNormal = accelerationNormal;
	}

	public boolean isEnleverPointNormal() {
		return enleverPointNormal;
	}

	public void setEnleverPointNormal(boolean enleverPointNormal) {
		this.enleverPointNormal = enleverPointNormal;
	}

	public boolean isAccelerationFun() {
		return accelerationFun;
	}

	public void setAccelerationFun(boolean accelerationFun) {
		this.accelerationFun = accelerationFun;
	}

	public boolean isEnleverPointFun() {
		return enleverPointFun;
	}

	public void setEnleverPointFun(boolean enleverPointFun) {
		this.enleverPointFun = enleverPointFun;
	}

	public int getVitesseFun() {
		return vitesseFun;
	}

	public void setVitesseFun(int vitesseFun) {
		this.vitesseFun = vitesseFun;
	}

	public int getTailleMapFun() {
		return tailleMapFun;
	}

	public void setTailleMapFun(int tailleMapFun) {
		this.tailleMapFun = tailleMapFun;
	}
}
