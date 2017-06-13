
public class White extends ObjectPiece{
		
		public White(int x, int y, Grid b){
			super("none", x, y, b);
			this.name = ".";
		}
		
		public Boolean isValidMovement(int x, int y){
			return false;
		}
		
		public Boolean isEmpty(){
			return true;
		}
}