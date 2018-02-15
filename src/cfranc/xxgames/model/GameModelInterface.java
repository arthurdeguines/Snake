package cfranc.xxgames.model;

public interface GameModelInterface {	
	
	public int getNbGridColumns();
	public int getNbGridRows();
	public Block getBlock(int rowIndex, int columnIndex);
	int cellSize();

}
