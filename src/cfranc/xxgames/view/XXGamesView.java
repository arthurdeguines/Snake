package cfranc.xxgames.view;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JPanel;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.JSeparator;
import javax.swing.JTextField;
import javax.swing.SwingConstants;

import cfranc.xxgames.ctrl.XXGameCtrl;
import cfranc.xxgames.model.Block;
import cfranc.xxgames.model.DefaultGameModel;
import java.awt.FlowLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;

public class XXGamesView extends Observable{
	
	private GameTableModel gameTableModel;

	public JFrame frmXxGames;
	private JPanel playGround;
	private final Action actionGameChoiceTetris = new SwingActionTetris();
	private final Action actionEnd = new SwingActionEnd();
	private final Action actionStart = new SwingActionStart();
	private final Action actionPause = new SwingActionPause();
	private final Action actionScoring = new SwingActionScoring();
	private final Action actionGameChoiceSnake = new SwingActionSnake();
	private final ButtonGroup buttonGroupGame = new ButtonGroup();
	private JTextField scoreTextField;
	private JPanel infoPanel;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {		
		buildAndInvoke();
	}

	private static void buildAndInvoke() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					XXGamesView window = new XXGamesView();
					window.frmXxGames.setSize(400,400);
					window.frmXxGames.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public static XXGamesView buildAndInvoke(final DefaultGameModel gameModel) {
		XXGamesView window = new XXGamesView(gameModel);
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {					
					window.frmXxGames.pack();
					window.frmXxGames.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		return window;
	}
	
	public void changeModel(DefaultGameModel model){
		this.gameTableModel=new GameTableModel(model);
	}

	/**
	 * Create the application.
	 */
	public XXGamesView() {
		this(new GameTableModel());
	}

	/**
	 * Create the application.
	 */
	public XXGamesView(DefaultGameModel gameModel) {		
		this(new GameTableModel(gameModel));
	}

	public XXGamesView(GameTableModel gameTableModel) {		
		this.gameTableModel=gameTableModel;
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frmXxGames = new JFrame();
		frmXxGames.setTitle("XX games");
		frmXxGames.setBounds(100, 100, 450, 300);
		frmXxGames.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		JMenuBar menuBar = new JMenuBar();
		frmXxGames.setJMenuBar(menuBar);
		
		JMenu mnGame = new JMenu("Game");
		menuBar.add(mnGame);
		
		JRadioButtonMenuItem rdbtnmntmTetris = new JRadioButtonMenuItem("Tetris");
		buttonGroupGame.add(rdbtnmntmTetris);
		rdbtnmntmTetris.setAction(actionGameChoiceTetris);
		mnGame.add(rdbtnmntmTetris);
		
		JRadioButtonMenuItem rdbtnmntmSnake = new JRadioButtonMenuItem("Snake");
		buttonGroupGame.add(rdbtnmntmSnake);
		rdbtnmntmSnake.setAction(actionGameChoiceSnake);
		mnGame.add(rdbtnmntmSnake);
		
		JSeparator separator = new JSeparator();
		mnGame.add(separator);
		
		JMenuItem mntmQuit = new JMenuItem("Quit");
		mntmQuit.setAction(new AbstractAction("Quit") {
			public void actionPerformed(ActionEvent e) {
				setChanged();
				notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_PAUSE);
				XXGamesView.this.frmXxGames.dispose();
			}
		});
		mnGame.add(mntmQuit);
		
		JMenu mnPlay = new JMenu("Play");
		menuBar.add(mnPlay);
		
		JMenuItem mnStart = new JMenuItem("Start");
		mnStart.setAction(actionStart);
		mnPlay.add(mnStart);
		
		JMenuItem mntmPause = new JMenuItem("Pause");
		mntmPause.setAction(actionPause);
		mnPlay.add(mntmPause);
		
		JMenuItem mntmEnd = new JMenuItem("End");
		mntmEnd.setAction(actionEnd);
		mnPlay.add(mntmEnd);
		
		JMenu mnHelp = new JMenu("Help");
		menuBar.add(mnHelp);
		
		JMenuItem mntmScoring = new JMenuItem("Scoring");
		mntmScoring.setAction(actionScoring);
		mnHelp.add(mntmScoring);
		
		JSeparator separator_1 = new JSeparator();
		mnHelp.add(separator_1);
		
		JMenuItem mntmAbout = new JMenuItem("About");
		mnHelp.add(mntmAbout);
		JPanel scorePanel=new JPanel();
		scorePanel.setFocusable(false);
		frmXxGames.getContentPane().add(scorePanel,BorderLayout.WEST);
		
		JPanel scorePanel2 = new JPanel();
		scoreTextField = new JTextField();
		scoreTextField.setHorizontalAlignment(SwingConstants.CENTER);
		scoreTextField.setColumns(10);
		scoreTextField.setEditable(false);
		scoreTextField.setFocusable(false);
		scoreTextField.setPreferredSize(new Dimension(50, 50));
		JLabel label = new JLabel("Score");
		label.setAlignmentX(Component.CENTER_ALIGNMENT);
		infoPanel=new JPanel();
		infoPanel.setPreferredSize(new Dimension(50, 50));
		infoPanel.setMinimumSize(new Dimension(50, 50));
		GroupLayout gl_scorePanel = new GroupLayout(scorePanel);
		gl_scorePanel.setHorizontalGroup(
			gl_scorePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scorePanel.createSequentialGroup()
					.addComponent(scorePanel2, GroupLayout.PREFERRED_SIZE, 169, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(24, Short.MAX_VALUE))
		);
		gl_scorePanel.setVerticalGroup(
			gl_scorePanel.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scorePanel.createSequentialGroup()
					.addComponent(scorePanel2, GroupLayout.PREFERRED_SIZE, 240, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
		);
		GroupLayout gl_scorePanel2 = new GroupLayout(scorePanel2);
		gl_scorePanel2.setHorizontalGroup(
			gl_scorePanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scorePanel2.createSequentialGroup()
					.addGroup(gl_scorePanel2.createParallelGroup(Alignment.LEADING)
						.addGroup(gl_scorePanel2.createSequentialGroup()
							.addGap(79)
							.addComponent(label))
						.addGroup(gl_scorePanel2.createSequentialGroup()
							.addGap(50)
							.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE))
						.addGroup(gl_scorePanel2.createSequentialGroup()
							.addGap(68)
							.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)))
					.addContainerGap(57, Short.MAX_VALUE))
		);
		gl_scorePanel2.setVerticalGroup(
			gl_scorePanel2.createParallelGroup(Alignment.LEADING)
				.addGroup(gl_scorePanel2.createSequentialGroup()
					.addGap(9)
					.addComponent(infoPanel, GroupLayout.PREFERRED_SIZE, GroupLayout.DEFAULT_SIZE, GroupLayout.PREFERRED_SIZE)
					.addPreferredGap(ComponentPlacement.UNRELATED)
					.addComponent(label)
					.addPreferredGap(ComponentPlacement.RELATED)
					.addComponent(scoreTextField, GroupLayout.PREFERRED_SIZE, 29, GroupLayout.PREFERRED_SIZE)
					.addContainerGap(121, Short.MAX_VALUE))
		);
		scorePanel2.setLayout(gl_scorePanel2);
		scorePanel.setLayout(gl_scorePanel);
		playGround=new JPanel();
		playGround.setFocusable(false);
		frmXxGames.getContentPane().add(playGround,BorderLayout.CENTER);
		playGround.setLayout(gameTableModel.gridLayout);
		for (int i = 0; i < gameTableModel.getRowCount(); i++) {
			for (int j = 0; j < gameTableModel.getColumnCount(); j++) {
				playGround.add(gameTableModel.panels[i][j]);
			}
		}
		frmXxGames.requestFocus();
	}
	
	public JPanel getPlayGround() {
		return playGround;
	}
	
	public void gameOver(){
		scoreTextField.setText("GAME OVER");
	}
	
	private class SwingActionTetris extends AbstractAction {
		public SwingActionTetris() {
			putValue(NAME, "Tetris");
			putValue(SHORT_DESCRIPTION, "Tetris");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_TETRIS);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_TETRIS);
		}
	}
	private class SwingActionEnd extends AbstractAction {
		public SwingActionEnd() {
			putValue(NAME, "End");
			putValue(SHORT_DESCRIPTION, "End of Game");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_END);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_END);
		}
	}
	private class SwingActionStart extends AbstractAction {
		public SwingActionStart() {
			putValue(SHORT_DESCRIPTION, "(Re)Start the Game");
			putValue(NAME, "Start");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_START);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_START);
		}
	}
	private class SwingActionPause extends AbstractAction {
		public SwingActionPause() {
			putValue(NAME, "Pause");
			putValue(SHORT_DESCRIPTION, "Pause");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_PAUSE);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_PAUSE);
		}
	}
	private class SwingActionScoring extends AbstractAction {
		public SwingActionScoring() {
			putValue(NAME, "Scoring");
			putValue(SHORT_DESCRIPTION, "Scoring");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_SCORING);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_SCORING);
		}
	}
	private class SwingActionSnake extends AbstractAction {
		public SwingActionSnake() {
			putValue(NAME, "Snake");
			putValue(SHORT_DESCRIPTION, "Some short description");
			putValue(ACTION_COMMAND_KEY, XXGameCtrl.ACTION_COMMAND_KEY_SNAKE);
		}
		public void actionPerformed(ActionEvent e) {
			setChanged();
			notifyObservers(XXGameCtrl.ACTION_COMMAND_KEY_SNAKE);
		}
	}
}

class GameTableModel implements Observer {
	
	Color BACKGROUND_COLOR=Block.DEFAULT_COLOR;
	
	GridLayout gridLayout;
	JPanel[][] panels;
	JTextField scroreTextField;

	private DefaultGameModel gameModel;	
	
	public int getRowCount() {
		// TODO Auto-generated method stub
		return this.gameModel.getNbGridRows();
	}
	
	public int getColumnCount() {
		// TODO Auto-generated method stub
		return this.gameModel.getNbGridColumns();
	}

	public GameTableModel(DefaultGameModel gameModel) {
		super();
		this.gridLayout=new GridLayout(gameModel.getNbGridRows(), gameModel.getNbGridColumns(), 0, 0);
		this.gameModel = gameModel;
		this.panels=new JPanel[gridLayout.getRows()][gridLayout.getColumns()];
		this.scroreTextField=new JTextField();
		for (int i = 0; i < panels.length; i++) {
			for (int j = 0; j < panels[i].length; j++) {
				this.panels[i][j]=new JPanel();
				this.panels[i][j].setOpaque(true);
				this.panels[i][j].setBackground(BACKGROUND_COLOR);
			}
		}
		this.gameModel.addObserver(this);
	}

	public GameTableModel() {
		this(new DefaultGameModel());		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg instanceof Block){
			updateBlock((Block)arg);
		}
		else if(arg instanceof List<?>){
			List<Block> list = (List<Block>)arg;
			Iterator<Block> it=list.iterator();
			while (it.hasNext()){
				updateBlock(it.next());
			}
		}
		else{
			this.scroreTextField.setText(this.gameModel.getScore());
		}
	}
	
	private void updateBlock(Block arg) {
		int iRow = ((Block)arg).getIRow();
		int iCol = ((Block)arg).getICol();
		Color c = ((Block)arg).getColor();
		this.panels[iRow][iCol].setBackground(c);		
	}

}

