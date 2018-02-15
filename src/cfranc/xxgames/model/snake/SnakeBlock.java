package cfranc.xxgames.model.snake;

import java.awt.Color;

import cfranc.xxgames.model.Block;
import cfranc.xxgames.model.snake.SnakeModel.SnakeBlockType;

public class SnakeBlock extends Block {

	SnakeBlockType snakeBlockType=SnakeBlockType.FREE;

	public Color getColor() {
		Color res=DEFAULT_COLOR;
		switch (snakeBlockType) {
		case HEAD:
			res=Color.RED;
			break;
		case TAIL:
			res=Color.GREEN;
			break;
		case FOOD:
			res=Color.YELLOW;
			break;
		default:
			break;
		}
		return res;
	}

	public boolean isValide(){
		return ( (snakeBlockType.equals(SnakeBlockType.FREE))
				 ||
				 (snakeBlockType.equals(SnakeBlockType.FOOD)) );
	}

	public SnakeBlock(int iRow, int iCol, SnakeBlockType type) {
		super(iRow, iCol);
		snakeBlockType=type;
	}
	
	public boolean isFood(){
		return snakeBlockType.equals(SnakeBlockType.FOOD);
	}

	public void setSnakeBlockType(SnakeBlockType snakeBlockType) {
		this.snakeBlockType = snakeBlockType;
	}
}
