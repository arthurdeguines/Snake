package cfranc.xxgames.model.snake;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import cfranc.xxgames.model.DefaultGameModel;
import cfranc.xxgames.model.OneDirection;

public class SnakeModel extends DefaultGameModel {
	Random random=new Random();
	SnakeBlock foodBlock;
	boolean updateFoodBlock=false;

	public final static int FOOD_STACK=4;
	private int food=FOOD_STACK;
	private List<SnakeBlock> tail=new LinkedList<SnakeBlock>();
	private SnakeBlock head=null;
	private SnakeBlock nextHead=null;
	private OneDirection oldSnakeDirection=OneDirection.RIGHT;
	private OneDirection newSnakeDirection=OneDirection.RIGHT;
	
	enum SnakeBlockType{
		HEAD,FOOD,WALL,TAIL,FREE;
	}
	
	@Override
	public void changeDirection(OneDirection newDirection){
		if( (newDirection.equals(OneDirection.UP))
			&& !oldSnakeDirection.equals(OneDirection.DOWN) ){
			newSnakeDirection=newDirection;
			oldSnakeDirection=newSnakeDirection;
		}
		else if ( (newDirection.equals(OneDirection.DOWN))
				  && !oldSnakeDirection.equals(OneDirection.UP) ){
			newSnakeDirection=newDirection;
			oldSnakeDirection=newSnakeDirection;
		}
		else if ( (newDirection.equals(OneDirection.LEFT))
				  && !oldSnakeDirection.equals(OneDirection.RIGHT) ){
			newSnakeDirection=newDirection;
			oldSnakeDirection=newSnakeDirection;
		}
		else if ( (newDirection.equals(OneDirection.RIGHT))
				  && !oldSnakeDirection.equals(OneDirection.LEFT) ){
			newSnakeDirection=newDirection;
			oldSnakeDirection=newSnakeDirection;
		}
	}

	public SnakeModel() {
		for (int i = 0; i < grid.length; i++) {
			for (int j = 0; j < grid[i].length; j++) {
				grid[i][j]=new SnakeBlock(i,j,SnakeBlockType.FREE);
			}
		}
		head=(SnakeBlock)grid[0][0];
		generateFoodBlock();
	}

	@Override
	public boolean advance() {
		boolean res=true;
		if(canAdvance()){
			_advance();
		}
		else{
			res=false;
		}
		return res;
	}
	
	private boolean canAdvance(){
		boolean res=false;
		int snakeHeadRowIndex=head.getIRow();
		int snakeHeadColumnIndex=head.getICol();

		//-Cancel back-move
		if(  !( (newSnakeDirection.equals(OneDirection.RIGHT) && !oldSnakeDirection.equals(OneDirection.LEFT))
			||
			(newSnakeDirection.equals(OneDirection.LEFT) && !oldSnakeDirection.equals(OneDirection.RIGHT))
			||
			(newSnakeDirection.equals(OneDirection.UP) && !oldSnakeDirection.equals(OneDirection.DOWN))
			||
			(newSnakeDirection.equals(OneDirection.DOWN) && !oldSnakeDirection.equals(OneDirection.UP)) )  ) {
			newSnakeDirection=oldSnakeDirection;
		}
		//-Cancel move out of the grid
		switch (newSnakeDirection) {
		case UP:
			if( (snakeHeadRowIndex-1>=0)
				&& 
				((SnakeBlock)grid[snakeHeadRowIndex-1][snakeHeadColumnIndex]).isValide() ){
				snakeHeadRowIndex=snakeHeadRowIndex-1;				
				res=true;
			}
			break;
		case DOWN:
			if( (snakeHeadRowIndex+1<getNbGridRows())
				&& 
				((SnakeBlock)grid[snakeHeadRowIndex+1][snakeHeadColumnIndex]).isValide() ){
				snakeHeadRowIndex=snakeHeadRowIndex+1;
				res=true;
			}
			break;
		case RIGHT:
			if( (snakeHeadColumnIndex+1<getNbGridColumns())
				&& 
				((SnakeBlock)grid[snakeHeadRowIndex][snakeHeadColumnIndex+1]).isValide() ){
				snakeHeadColumnIndex=snakeHeadColumnIndex+1;
				res=true;
			}
			break;
		case LEFT:
			if( (snakeHeadColumnIndex-1>=0)
				&& 
				((SnakeBlock)grid[snakeHeadRowIndex][snakeHeadColumnIndex-1]).isValide() ){
				snakeHeadColumnIndex=snakeHeadColumnIndex-1;
				res=true;
			}
			break;
		default:
			res=false;
			break;
		}
		if(res){
			nextHead=(SnakeBlock)grid[snakeHeadRowIndex][snakeHeadColumnIndex];
			if(tail.contains(nextHead)){
				res=false;
			}
			else{
				if(((SnakeBlock)grid[snakeHeadRowIndex][snakeHeadColumnIndex]).isFood()){
					generateFoodBlock();
				}
			}
		}
		return res;
	}

	private void generateFoodBlock() {
		food=food+FOOD_STACK;
		ArrayList<SnakeBlock> emptyBlocks=new ArrayList<SnakeBlock>();
		for (int i = 1; i < grid.length-1; i++) {
			for (int j = 1; j < grid[i].length-1; j++) {
				emptyBlocks.add((SnakeBlock)grid[i][j]);
			}
		}
		emptyBlocks.remove(head);
		emptyBlocks.removeAll(tail);
		foodBlock=(SnakeBlock)(emptyBlocks.get(random.nextInt(emptyBlocks.size())));
		foodBlock.setSnakeBlockType(SnakeBlockType.FOOD);
		updateFoodBlock=true;
	}
	
	private void _advance() {
		nextHead.setSnakeBlockType(SnakeBlockType.HEAD);
		setChanged();
		tail.add(0,head);
		head.setSnakeBlockType(SnakeBlockType.TAIL);
		notifyObservers(head);
		head=nextHead;
		setChanged();
		notifyObservers(head);
		
		if(food>0){
			food--;
			setChanged();
			notifyObservers((SnakeBlock)((LinkedList<SnakeBlock>)tail).getLast());
			if(updateFoodBlock){
				setChanged();
				notifyObservers(foodBlock);
			}
			updateFoodBlock=false;
		}
		else{
			((LinkedList<SnakeBlock>)tail).getLast().setSnakeBlockType(SnakeBlockType.FREE);
			setChanged();
			notifyObservers((SnakeBlock)((LinkedList<SnakeBlock>)tail).getLast());
			((LinkedList<SnakeBlock>)tail).removeLast();

		}
	}	
}
