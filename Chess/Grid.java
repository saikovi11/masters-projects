
public class Grid {
	private ObjectPiece[][] grid;
	private King whiteKing;
	private King blackKing;
	
	Grid(){
		this.grid = new ObjectPiece[8][8];
		buildGrid();
	}
	
	private void buildGrid(){
		this.grid[0][0] = new Rook("black", 0, 0, this);
		this.grid[0][7] = new Rook("black", 0, 7, this);
		this.grid[7][0] = new Rook("white", 7, 0, this);
		this.grid[7][7] = new Rook("white", 7, 7, this);
		
		this.grid[0][1] = new Knight("black", 0, 1, this);
		this.grid[0][6] = new Knight("black", 0, 6, this);
		this.grid[7][1] = new Knight("white", 7, 1, this);
		this.grid[7][6] = new Knight("white", 7, 6, this);
		
		this.grid[0][2] = new Bishop("black", 0, 2, this);
		this.grid[0][5] = new Bishop("black", 0, 5, this);
		this.grid[7][2] = new Bishop("white", 7, 2, this);
		this.grid[7][5] = new Bishop("white", 7, 5, this);
		
		this.grid[0][3] = new Queen("black", 0, 3, this);
		this.grid[7][3] = new Queen("white", 7, 3, this);
		
		this.blackKing = new King("black", 0, 4, this);
		this.grid[0][4] = this.blackKing;
		this.whiteKing = new King("white", 7, 4, this);
		this.grid[7][4] = this.whiteKing;
		
		for (int i = 0; i < 8 ; i++){
			this.grid[1][i] = new Pawn("black", 1, i, this);
			this.grid[6][i] = new Pawn("white", 6, i, this);
		}
		
		for (int i = 2; i < 6; i ++)
			for (int j = 0; j < 8; j++)
				this.grid[i][j] = new White(i,j, this); 
		
	}
	
	public Boolean isValidMovement(int x1, int y1, int x2, int y2, String playerColor){
		if (!this.grid[x1][y1].getColor().equalsIgnoreCase(playerColor)){
			System.out.println("No es tu pieza");
			return false;
		}
		
		return this.grid[x1][y1].isValidMovement(x2, y2);
	}
	
	public void move(int x1, int y1, int x2, int y2){	
		ObjectPiece p = this.grid[x1][y1];
		p.move(x2, y2);
		this.grid[x2][y2] = p;
		this.grid[x1][y1] = new White(x1, y1, this); 
	}
	
	public String getGameState(String color){
		King k;
		String result = "";
		if (color.equalsIgnoreCase("white"))
			k = this.blackKing;
		else
			k = this.whiteKing;
		
		if (!k.canMove() && k.isBeingAttack())
			result = "youwin";
		else
			result = "continue";
			
		return result;
		
	}
	
	public ObjectPiece getPiece(int x, int y){
		if ( x < 0 || x > 7 || y < 0 || y > 7 )
			return null;
		
		return grid[x][y];
	}
	
	public void removePiece(int x, int y){
		grid[x][y] = new White(x, y, this);
	}
	
	public void putPiece(int x, int y, ObjectPiece p){
		grid[x][y] = p;
		
	}
	
	public String toString(String color){
		String result;
		if (color.equalsIgnoreCase("white")){
			result = "  a b c d e f g h-";
			
			for (int i = 0; i < 8; i++){
				result += (8 - i) + " ";
				for (int j = 0; j < 8; j++)
					result += this.grid[i][j].toString() + " ";
				
				result += (8 - i) + "-";
				
			} 
			result += "  a b c d e f g h";
		}else{
			result = "  h g f e d c b a-";
			for (int i = 7; i >= 0; i--){
				result += (8 - i) + " ";
				for (int j = 7; j >= 0; j--)
					result += this.grid[i][j].toString() + " ";
				
				result += (8 - i) + "-";
			}
			result += "  h g f e d c b a";
		}
		return result;
	}
}
