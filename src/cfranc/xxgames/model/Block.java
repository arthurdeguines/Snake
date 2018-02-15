package cfranc.xxgames.model;

import java.awt.Color;

public class Block {
	public static final Color DEFAULT_COLOR=Color.GRAY;
	int iRow;
	int iCol;
	
	Block(int iRow, int iCol, Color color) {
		this(iRow,iCol);
		this.color = color;
	}
	
	protected Block(int iRow, int iCol) {
		super();
		this.iRow = iRow;
		this.iCol = iCol;
	}

	protected Color color=DEFAULT_COLOR;

	public int getIRow() {
		return iRow;
	}

	public void setIRow(int iRow) {
		this.iRow = iRow;
	}

	public int getICol() {
		return iCol;
	}

	public void setICol(int iCol) {
		this.iCol = iCol;
	}
	
	public Color getColor(){
		return color;
	}
}
