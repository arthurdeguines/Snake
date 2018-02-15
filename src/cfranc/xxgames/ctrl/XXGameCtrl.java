package cfranc.xxgames.ctrl;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.Observable;
import java.util.Observer;

import cfranc.xxgames.model.DefaultGameModel;
import cfranc.xxgames.model.GameCtrlInterface;
import cfranc.xxgames.model.OneDirection;
import cfranc.xxgames.model.snake.SnakeModel;
import cfranc.xxgames.model.tetris.TetrisModel;
import cfranc.xxgames.view.XXGamesView;

public class XXGameCtrl implements KeyListener,Observer{
	
	public static final int MAX_SPEED = 500;//50
	
	private static Object lock=new Object();
	
	enum XXGameCtrlState{
		RUN,PAUSE,END;
	}
	
	XXGameCtrlState state=XXGameCtrlState.PAUSE;
	
	public static final String ACTION_COMMAND_KEY_START="ACTION_COMMAND_KEY_START";
	public static final String ACTION_COMMAND_KEY_PAUSE="ACTION_COMMAND_KEY_PAUSE";
	public static final String ACTION_COMMAND_KEY_END="ACTION_COMMAND_KEY_END";
	public static final String ACTION_COMMAND_KEY_TETRIS="ACTION_COMMAND_KEY_TETRIS";
	public static final String ACTION_COMMAND_KEY_SNAKE="ACTION_COMMAND_KEY_SNAKE";
	public static final String ACTION_COMMAND_KEY_SCORING="ACTION_COMMAND_KEY_SCORING";
	
//	DefaultGameModel model=new SnakeModel();
	DefaultGameModel model=new TetrisModel();
	XXGamesView view;
	
	boolean canStop=false;

	public static void main(String[] args) throws InterruptedException {
		XXGameCtrl gameCtrl=new XXGameCtrl();
		gameCtrl.view=XXGamesView.buildAndInvoke(gameCtrl.model);
		gameCtrl.view.frmXxGames.addKeyListener(gameCtrl);
		gameCtrl.view.addObserver(gameCtrl);
		
		int speedDec=10;
		
		boolean gameOver=false;
		int speed=MAX_SPEED;
		int iCount=0;

		do {
			switch (gameCtrl.state) {
			case END:
				gameCtrl.canStop=true;
				break;
				
			case PAUSE:
				try {
					Thread.sleep(250);
				} catch (InterruptedException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				break;
	
			case RUN:
				if(gameOver){
					gameCtrl.view.gameOver();
				}
				else{
					synchronized (lock) {
						gameOver=!gameCtrl.model.advance();
					}
					speed=Math.max((speed-speedDec),MAX_SPEED);
					iCount++;
					try {
						Thread.sleep(speed);
					} catch (InterruptedException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
				break;
	
			default:
				break;
			}
		}
		while(!gameCtrl.canStop && iCount<1000);
	}
	
	//IMPLEMENT KeyListener
	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
	    int keyCode = e.getKeyCode();
	    synchronized (lock) {
		    switch( keyCode ) { 
	        case KeyEvent.VK_UP:
	            ((GameCtrlInterface)model).changeDirection(OneDirection.UP);
	            break;
	        case KeyEvent.VK_DOWN:
	            ((GameCtrlInterface)model).changeDirection(OneDirection.DOWN);
	            break;
	        case KeyEvent.VK_LEFT:
	            ((GameCtrlInterface)model).changeDirection(OneDirection.LEFT);
	            break;
	        case KeyEvent.VK_RIGHT :
	            ((GameCtrlInterface)model).changeDirection(OneDirection.RIGHT);
	            break;
		    }
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void update(Observable o, Object arg) {
		if(arg.equals(ACTION_COMMAND_KEY_END)){
			state=XXGameCtrlState.END;
		}
		else if(arg.equals(ACTION_COMMAND_KEY_PAUSE)){
			state=XXGameCtrlState.PAUSE;
		}
		else if(arg.equals(ACTION_COMMAND_KEY_SCORING)) {
			
		}
		else if(arg.equals(ACTION_COMMAND_KEY_SNAKE)){
			model=new SnakeModel();
			view.changeModel(model);			
		}
		else if(arg.equals(ACTION_COMMAND_KEY_TETRIS)){
			model=new DefaultGameModel();
			view.changeModel(model);			
		}
		else if(arg.equals(ACTION_COMMAND_KEY_START)) {
			state=XXGameCtrlState.RUN;			
		}
	}
}
