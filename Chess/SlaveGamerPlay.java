
public class SlaveGamerPlay extends Thread{
	private SlaveGamer slave;
	private Gamer gamer;
	
	SlaveGamerPlay(SlaveGamer listen, Gamer clt){
		this.slave = listen;
		this.gamer = clt;
	}
	
	public void run(){
		String rsp = slave.getInput();
		this.gamer.endOpponentTime();
		this.gamer.refreshOpponentTime();
		if (rsp.equalsIgnoreCase("youlose")){
			this.gamer.stopGrid();
			
			this.gamer.lost();
			
			rsp = slave.getInput();
			this.gamer.Grid(this.gamer.transformGrid(rsp));
			this.gamer.close();
		
		}else{
			this.gamer.Grid(this.gamer.transformGrid(rsp));
			this.gamer.goGrid();
		}
		this.gamer.startMyTime();
	}
}
