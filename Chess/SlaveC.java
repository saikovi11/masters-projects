public class SlaveC extends Thread{
	private SlaveGamer slave;
	private Gamer gamer;
	
	SlaveC(SlaveGamer s, Gamer cli){
		this.slave = s;
		this.gamer = cli;
	}
	
	public void run(){
		this.gamer.chooseColor();
	}
}
