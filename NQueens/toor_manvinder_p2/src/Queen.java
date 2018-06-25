
public class Queen {
	private int row;
	private int column;
	private int size = Test.size-1;
	
	public Queen(int r, int c) {
		row = r;
		column = c;
	}
	public boolean canAttack(Queen q) {
		boolean canAttack = false;
		if (row==q.getRow() || column==q.getColumn())
			canAttack = true;
		else if(Math.abs(column-q.getColumn()) == Math.abs(row-q.getRow()))
			canAttack = true;
		return canAttack;
	}
	public void moveDown(int spaces) {
		row +=spaces;
		if(row> (size) && (row%(size)) != 0)
			row = (row %(size))-1;
		else if(row > size && row %size ==0)
			row = size;
	}
	public int getRow() {
		return row;
	}
	public int getColumn() {
		return column;
	}
	public void setRow(int r) {
		row = r;
	}
	public void setColumn(int c) {
		column = c;
	}
	public String toString() {
		return "(" + row+", " + column+")";
	}
}
