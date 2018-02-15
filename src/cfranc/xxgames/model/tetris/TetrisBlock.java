package cfranc.xxgames.model.tetris;

import java.awt.Color;

import cfranc.xxgames.model.Block;

public class TetrisBlock extends Block{
	
	public TetrisBlock(int iRow, int iCol, Color color) {
		super(iRow, iCol);
		if(color==null){
			color=DEFAULT_COLOR;
		}
		else{
			this.color=color;
		}
	}		
}
