import java.util.Enumeration;
import java.io.PrintWriter;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.Hashtable;
import java.net.Socket;
import java.io.IOException;



public class ServerSlave extends Thread{
	private Hashtable<String, ServerSlave> challengesList;
	private boolean colorSended;
	private Socket socket;
	private String nickname = "";
	private boolean startGame;
	private PrintWriter printer;
	private boolean waiting;
	private String color;
	private BufferedReader reader;
	
	private NicknameList listOfOpponents;
	private Grid gameGrid;
	private boolean otherAccept;
	private ServerSlave other;
	
	ServerSlave(Socket clientSocket, NicknameList list){
		this.colorSended = false;
		this.otherAccept = false;
		this.other = null;
		this.gameGrid = null;
		this.challengesList = new Hashtable<String, ServerSlave>();
		
		this.listOfOpponents = list;
		this.socket = clientSocket;
		
		
		try {
			this.waiting = false; 
			this.startGame = false;
			this.color = "";
			printer = new PrintWriter(clientSocket.getOutputStream(), true);
			reader = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public synchronized boolean challengeAccepted(String username){
		if (!this.startGame && this.other != null &&
				this.other.getNickname().equalsIgnoreCase(username)){
			this.otherAccept = true;
			printer.println("accept " + username);
			this.other.prove(this.nickname);
			this.listOfOpponents.removeNickname(this.nickname);
			this.waiting = false;
			return true;
		}
		return false;
	}
	
	public void changePlayer(String state){
		
		if (state.equalsIgnoreCase("youlose")){
			printer.println(state);
			printer.println(this.gameGrid.toString(this.color));
			try {
				reader.close();
				printer.close();
				socket.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else
			printer.println(this.gameGrid.toString(this.color));
		
	}
	
	public synchronized void challengeRefused(String username){
		if (!this.startGame && this.other != null &&
				this.other.getNickname().equalsIgnoreCase(username)){
			this.other = null;
			printer.println("refuse "+ username);
			this.waiting = false;
		}
	}
	
	private void login(String username){
		String answer;
		if (listOfOpponents.putNickname(username, this)){
			answer = "successful registration";
			this.nickname = username; 
		}else
			answer = "invalid username";
	    
		printer.println(answer);
	}
	
	
	private synchronized void challenge(String username){
		if (username.equalsIgnoreCase(this.nickname))
			printer.println("invalid username");
		
		else if ((this.other = this.listOfOpponents.getNickname(username)) != null){
			this.other.sendChanllenge(this.nickname, this);
			this.waiting = true;
			//Wait one minute
			(new Stoper(this, username)).start();
		}else
			printer.println("invalid username");
	}
	
	private synchronized void accept(String username){
		if (username.equalsIgnoreCase(this.nickname))
			printer.println("invalid username");
		
		else if ((this.other = this.challengesList.get(username)) != null){
			if (!this.other.challengeAccepted(this.nickname))
				printer.println("timeprinter "+username);
		
		}else
			printer.println("invalid username");
	}
	
	private void getPlayers(){
		String answer = "list";
		
		for (Enumeration<String> e = listOfOpponents.getListNickName(); e.hasMoreElements();){
			String next = e.nextElement();
			if (!next.equalsIgnoreCase(this.nickname))
				answer += " " + next;
		}
		printer.println(answer);
	}
	
	
	public synchronized void sendChanllenge(String username, ServerSlave otherServer){
		if (!this.waiting && !this.startGame){
			this.challengesList.put(otherServer.getNickname(), otherServer);
			printer.println("invitationFrom " + otherServer.getNickname());
		}
	}
	
	public String getNickname(){
		return this.nickname;
	}
	
	
	private synchronized void prove(String username){
		if (!this.startGame && this.other != null &&
				this.other.getNickname().equalsIgnoreCase(username)){
			this.listOfOpponents.removeNickname(this.nickname);
			printer.println("confirm");
		}
	}
	
	private void changecolor(String color){
		this.color = color;
		this.colorSended = false;
		this.other.selectedColor(color);
	}
	
	private synchronized void denny(String username){
		if (username.equalsIgnoreCase(this.nickname))
			printer.println("invalid username");
		
		else if ((this.other = this.challengesList.get(username)) != null){
			this.other.challengeRefused(this.nickname);
			printer.println("ok");
		
		}else
			printer.println("invalid username");
	}

	public void selectedColor(String opponentColor){
		String aux = "white";
		while(this.color.equals("")){
			try {
				Thread.sleep(1000);
				aux = "black";
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		if (opponentColor.equalsIgnoreCase("whatever") && this.color.equalsIgnoreCase("whatever")){
			printer.println(aux);
			this.color = aux;
		}else if (opponentColor.equalsIgnoreCase(this.color)){
			printer.println("the other player choosed the same color");
			this.color = "";
		}else if (opponentColor.equalsIgnoreCase("white") && this.color.equalsIgnoreCase("whatever")){
			printer.println("black");
			this.color = "black";
		}else if (opponentColor.equalsIgnoreCase("black") && this.color.equalsIgnoreCase("whatever")){
			printer.println("white");
			this.color = "white";
		}else if (opponentColor.equalsIgnoreCase("whatever")){
			printer.println(this.color);
		}else
			printer.println(this.color);
		
		this.colorSended = true;
		if (this.color.equalsIgnoreCase("white")){
			this.gameGrid = new Grid();
			printer.println(this.gameGrid.toString(this.color));
			this.other.setGrid(this.gameGrid);
		}
	}

	private class Stoper extends Thread {
		private ServerSlave server;
		private String username;
		
		Stoper(ServerSlave server, String user){
			this.server = server;
			this.username = user;
		}

		public void run() {
			try {
				Thread.sleep(60000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (!this.server.otherAccept()){
				this.server.challengeRefused(this.username);
				this.server.restartOpponent();
			}
			this.server.go();
    	}

	}
	
	private void play(String x1, String y1, String x2, String y2){
		int row1, row2, col1, col2;
		row1 = Integer.parseInt(x1);
		col1 = Integer.parseInt(y1);
		row2 = Integer.parseInt(x2);
		col2 = Integer.parseInt(y2);
		
		if (this.gameGrid.isValidMovement(row1, col1, row2, col2, this.color)){
			this.gameGrid.move(row1, col1, row2, col2);
			String state = this.gameGrid.getGameState(this.color);
			
			if (state.equalsIgnoreCase("youwin")){
				printer.println(state);
				printer.println(this.gameGrid.toString(this.color));
				try {
					reader.close();
					printer.close();
					socket.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				this.other.changePlayer("youlose");
			}else{
				printer.println(this.gameGrid.toString(this.color));
				this.other.changePlayer(state);
			}
				
		}else
			printer.println("invalid movement");
	}
	
	public void restartOpponent(){
		this.other = null;
	}
	
	public void setGrid(Grid b){
		this.gameGrid = b;
		while(!this.colorSended){
			try {
				Thread.sleep(2000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		printer.println(this.gameGrid.toString(this.color));
	}
	
	public boolean otherAccept(){
		return this.otherAccept;
	}
	
	public void go(){
		this.waiting = false;
	}
	
	public void run() {
		String inputLine, outputLine;
        
        try {
			while ((inputLine = reader.readLine()) != null) {
			    String[] input = inputLine.split(" ");
			    if (input[0].equalsIgnoreCase("username"))
			    	login(input[1]);
			    
			    else if (input[0].equalsIgnoreCase("getPlayers"))
			    	getPlayers();
				
			    else if (input[0].equalsIgnoreCase("invite"))
			    	challenge(input[1]);
			    
			    else if (input[0].equalsIgnoreCase("accept"))
			    	 accept(input[1]);
			    
			    else if (input[0].equalsIgnoreCase("refuse"))
			    	denny(input[1]);
			    
			    else if (input[0].equalsIgnoreCase("color"))
			    	changecolor(input[1]);
			    
			    else if (input[0].equalsIgnoreCase("move"))
			    	play(input[1], input[2], input[3], input[4]);
			    
			    else
			    	System.out.println("Invalid: "+ inputLine);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
    }
}


