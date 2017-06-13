import java.io.InputStreamReader;
import java.util.Iterator;
import java.awt.*;

import java.net.Socket;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.awt.event.ActionEvent;
import java.io.IOException;

import java.io.PrintWriter;
import java.net.UnknownHostException;
import javax.swing.*;

public class Gamer extends JFrame implements ActionListener {
	
	private long myTime, myEndTime;
	private JTextField jtextRow1, jtextCol1, jtextRow2, jtextColum2;
	
	private long otherTime, otherEndTime;
	private String nickname;
	private static int port;
	private static String color;
	private static String opponent;
	private JButton buttonConn, buttonNick, buttonChallenge, buttonPlay;
	private PrintWriter printer;
	private static String address;
	private JLabel lblMyTime, lblOpponentTime;
	private ArrayList<JLabel> chips = new ArrayList<JLabel>();

	private static Socket socket;
	private BufferedReader reader;
	private SlaveGamer slave;
	private ArrayList<Square> tiles = new ArrayList<Square>();
	private Boolean myTurn = false;

	private JTextField jtextAddres, jtextPort, jtextNickname;
	private JFrame window, windowNickname, windowGamers, windowGrid;
	private JList list;
	private JLabel lblTurn;
	private DefaultListModel model;
	
	
	Gamer(){
		this.opponent = "";
		this.color = "";
		displayWindow();
	}
	
	public void startOpponentTime(){
		this.otherTime = System.currentTimeMillis();
	}
	
	public void startMyTime(){
		this.myTime = System.currentTimeMillis();
	}
	
	public void endOpponentTime(){
		this.otherEndTime = System.currentTimeMillis();
		
	}
	
	public void endMyTime(){
		this.myEndTime = System.currentTimeMillis();
	}
	
	public String getOpponetTime(){
		long total = this.otherEndTime - this.otherTime;
		return convertTime(total);
	}
	
	public String getMyTime(){
		long total = this.myEndTime - this.myTime;
		return convertTime(total);
	}
	
	private String convertTime(long time){
		long milisec = time % 1000;
		long aux = time/1000;
		
		long sec = aux % 60;
		aux = aux / 60;
		
		long min = aux % 60;
		long hours = aux / 60;
		
		return hours + ":" + min +":" + sec;
	}
	
	private void displayGamers(){
        windowGamers = new JFrame();
        windowGamers.setTitle("Challenge");
		windowGamers.setLayout(null);
		windowGamers.setSize(200,370);
		windowGamers.setResizable(false);
	    
		model = new DefaultListModel();
		list = new JList(model);
		
		updateList();
			
		list.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		list.setLayoutOrientation(JList.VERTICAL);
		list.setVisibleRowCount(-1);
		
		JScrollPane listScroller = new JScrollPane(list);
		listScroller.setBounds(0, 0, 200, 250);
		windowGamers.add(listScroller);
	    
		JLabel lblPlayers = new JLabel("Challenge another user to play");
		lblPlayers.setBounds(10,255,200,20);
		windowGamers.add(lblPlayers);
		
	    buttonChallenge = new JButton("Challenge");
	    buttonChallenge.setBounds(90,295,90,25);
	    windowGamers.add(buttonChallenge);
	    buttonChallenge.addActionListener(this);
	    
	    ImageIcon imagen = new ImageIcon("img/chalenge.png");
		JLabel Img = new JLabel();
		Img.setBounds(-20, 270, 100,100);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(Img.getWidth(), Img.getHeight(), Image.SCALE_DEFAULT));
		Img.setIcon(icono);
		windowGamers.add(Img);
	    
	    windowGamers.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	windowGamers.setLocationRelativeTo(null);
	  	windowGamers.setVisible(true);
	}
	
	
	private void chooseGamer(String opponent){
		String userInput;
		String answer;
				
		printer.println("invite " + opponent);
		answer = slave.getInput();
		
		if (answer.equalsIgnoreCase("invalid username") ||
				answer.equalsIgnoreCase("wait the answer of the other invitation")){
			JOptionPane.showMessageDialog(null,"Invalid input", 
				"Alert",JOptionPane.WARNING_MESSAGE);
			String[] array = slave.getInput().split("\n");
			updateList();

		}else {
			
			String[] arrayServer = answer.split(" ");
			if (arrayServer[0].equalsIgnoreCase("refuse")){
				JOptionPane.showMessageDialog(null,"Sorry! :( "+opponent+" don't"
						+ " want to play with you. Don't worry! challege another player :)", 
						"Challenge",JOptionPane.INFORMATION_MESSAGE);
				list.clearSelection();
				updateList();
				
			}else if (arrayServer[0].equalsIgnoreCase("accept")){
				JOptionPane.showMessageDialog(null,"Great! " + opponent + " accept your invitation :)", 
						"Challenge",JOptionPane.INFORMATION_MESSAGE);
				this.opponent = arrayServer[1];
				windowGamers.setVisible(false);
				chooseColor();
				
			}
		}
	}

	private JLabel drawImage(int i, int j, String url){
		
		ImageIcon imagen = new ImageIcon(url);
		JLabel Img = new JLabel();
		Img.setBounds(j*60+15 ,i*60+19,30,40);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(Img.getWidth(), Img.getHeight(), Image.SCALE_DEFAULT));
		Img.setIcon(icono);
		
		return Img;
	}
	
	private void displayGrid(String myColor, String brd){
        windowGrid = new JFrame();
        windowGrid.setTitle("Chess Game");
		windowGrid.setLayout(null);
		windowGrid.setSize(610,700);
		windowGrid.setResizable(false);
		String up;
		
		lblTurn = new JLabel("Wait your turn");
	    lblTurn.setFont(new Font(lblTurn.getName(), Font.PLAIN, 30));
	    lblTurn.setBounds(150,280, 600, 30);
	    lblTurn.setVisible(false);
	    windowGrid.add(lblTurn);
	    
		if (myColor.equalsIgnoreCase("white")){
			up = "a \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t b";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t c";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  d";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  e";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  f";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  g";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t  h";
		}else{
			up = "h \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t g";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t f";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t e";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t d";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t c";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t b";
			up += " \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t \t a";
		}
		
		JLabel lbl1 = new JLabel(up);
		lbl1.setBounds(90,30,500,20);
		windowGrid.add(lbl1);
		
		JLabel lbl2 = new JLabel(up);
		lbl2.setBounds(90,550,500,20);
		windowGrid.add(lbl2);
		
		for (int i = 1; i < 9; i ++){
			JLabel lbl3;
			if (myColor.equalsIgnoreCase("white"))
				lbl3 = new JLabel(String.valueOf(9-i));
			else
				lbl3 = new JLabel(String.valueOf(i));
			lbl3.setBounds(35,i*60+20,20,20);
			windowGrid.add(lbl3);
			
			JLabel lbl4;
			if (myColor.equalsIgnoreCase("white"))
				lbl4 = new JLabel(String.valueOf(9-i));
			else
				lbl4 = new JLabel(String.valueOf(i));
			lbl4.setBounds(560,i*60+20,20,20);
			windowGrid.add(lbl4);
		
		}
		
		String[] board = brd.split(" ");
		Color c = Color.white;
		for (int i = 1; i < 9; i++){
			for (int j = 1; j < 9; j++){
				
				Square d = new Square(c);
				d.setBounds(j * 60, i * 60, 60, 60);
				
				if (!board[(i-1)*8+j-1].equalsIgnoreCase(".")){
					JLabel Img = getPieceImage(i,j,board[(i-1)*8+j-1]);
					this.chips.add(Img);
					windowGrid.add(Img);
				}
			
				this.tiles.add(d);
				windowGrid.add(d);
				
				if (c.equals(Color.white))
					c = Color.lightGray;
				else
					c = Color.white;
			}
			if (c.equals(Color.white))
				c = Color.lightGray;
			else
				c = Color.white;
		}
		 
		JLabel lbl6 = new JLabel("Move piece at (row,column)");
		lbl6.setBounds(30,620,300,20);
		windowGrid.add(lbl6);
	    
	    jtextRow1 = new JTextField("");
	    jtextRow1.setBounds(190,620,20,20);
	    windowGrid.add(jtextRow1);
	    
	    jtextCol1 = new JTextField("");
	    jtextCol1.setBounds(220,620,20,20);
	    windowGrid.add(jtextCol1);
	    
	    JLabel lbl8 = new JLabel("to (row,column)");
		lbl8.setBounds(250,620,130,20);
		windowGrid.add(lbl8);
	    
	    jtextRow2 = new JTextField("");
	    jtextRow2.setBounds(350,620,20,20);
	    windowGrid.add(jtextRow2);
		
	    jtextColum2 = new JTextField("");
	    jtextColum2.setBounds(380,620,20,20);
	    windowGrid.add(jtextColum2);
	    
	    buttonPlay = new JButton("Play");
	    buttonPlay.setBounds(410,617,65,25);
	    windowGrid.add(buttonPlay);
	    buttonPlay.addActionListener(this);
	    
	    lblMyTime = new JLabel("Me 0:0:0");
	    lblMyTime.setBounds(270, 575, 200, 20);
	    windowGrid.add(lblMyTime);
	    
	    lblOpponentTime = new JLabel(this.opponent +" 0:0:0");
	    lblOpponentTime.setBounds(250, 10, 200, 20);
	    windowGrid.add(lblOpponentTime);
	    
	    if (!this.myTurn){
	    	jtextRow1.setEnabled(false);
	    	jtextCol1.setEnabled(false);
	    	jtextRow2.setEnabled(false);
	    	jtextColum2.setEnabled(false);
	    	lblTurn.setVisible(true);
	    	buttonPlay.setEnabled(false);
	    }
	    
		//Display the window.
	    windowGrid.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	windowGrid.setLocationRelativeTo(null);
	  	windowGrid.setVisible(true);
	}
	
	
	
	private void updateList(){
		printer.println("getPlayers");
		String answer = slave.getInput();
		model.removeAllElements();
		if(!answer.equalsIgnoreCase("There is no other players")){
			String[] array = answer.split("\n");
			for (int i = 0; i < array.length; i++)
				model.addElement(array[i]);
		}
	} 
	
	private JLabel getPieceImage(int i, int j, String piece){
		switch(piece){
			case "p":
				return drawImage(i,j,"img/blackPawn.png");
			case "P":
				return drawImage(i,j,"img/whitePawn.png");
			
			case "r":
				return drawImage(i,j,"img/blackRook.png");
			case "R":
				return drawImage(i,j,"img/whiteRook.png");
			
			case "n":
				return drawImage(i,j,"img/blackKnight.png");
			case "N":
				return drawImage(i,j,"img/whiteKnight.png");
				
			case "b":
				return drawImage(i,j,"img/blackBishop.png");
			case "B":
				return drawImage(i,j,"img/whiteBishop.png");
				
			case "q":
				return drawImage(i,j,"img/blackQueen.png");
			case "Q":
				return drawImage(i,j,"img/whitequeen.png");
				
			case "k":
				return drawImage(i,j,"img/blackKing.png");
			case "K":
				return drawImage(i,j,"img/whiteKing.png");
		}
		
		return null;
	}
	
	public void Grid(String brd){
		String[] board = brd.split(" ");
		
		Iterator<JLabel> iterator = this.chips.iterator();
		while (iterator.hasNext())
			windowGrid.remove(iterator.next());
		
		Iterator<Square> iter = this.tiles.iterator();
		while (iter.hasNext())
			windowGrid.remove(iter.next());
		
		this.chips.clear();
		this.tiles.clear();
		
		Color c = Color.white;
		for (int i = 1; i < 9; i++){
			for (int j = 1; j < 9; j++){
				Square d = new Square(c);
				d.setBounds(j * 60, i * 60, 60, 60);
				
				if (!board[(i-1)*8+j-1].equalsIgnoreCase(".")){
					JLabel Img = getPieceImage(i,j,board[(i-1)*8+j-1]);
					this.chips.add(Img);
					windowGrid.add(Img);
				}
				this.tiles.add(d);
				windowGrid.add(d);
				
				if (c.equals(Color.white))
					c = Color.lightGray;
				else
					c = Color.white;
			}
			if (c.equals(Color.white))
				c = Color.lightGray;
			else
				c = Color.white;
		}
		windowGrid.repaint(); 
	}
	
	public void stopGrid(){
		jtextRow1.setEnabled(false);
    	jtextCol1.setEnabled(false);
    	jtextRow2.setEnabled(false);
    	jtextColum2.setEnabled(false);
    	buttonPlay.setEnabled(false);
    	windowGrid.repaint();
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == buttonConn){
			if ( jtextAddres.getText().length() == 0 || 
				 jtextPort.getText().length() == 0 ||
						 jtextAddres.getText().replaceAll(" ", "").length() == 0 ||
				 jtextPort.getText().replaceAll(" ", "").length() == 0){
				JOptionPane.showMessageDialog(null,"Complete the fields", 
						"Alert",JOptionPane.WARNING_MESSAGE);
			}else{
				
				address = jtextAddres.getText().replaceAll(" ", "");
				port = Integer.parseInt(jtextPort.getText().replaceAll(" ", ""));
				try {
					socket = new Socket(address, port);
					printer = new PrintWriter(socket.getOutputStream(), true);
					reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
					window.setVisible(false);
					displayNickname();
				} catch (UnknownHostException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
					JOptionPane.showMessageDialog(null,"Invalid input", 
							"Alert",JOptionPane.WARNING_MESSAGE);
				} catch (IOException e1) {
					// TODO Auto-generated catch block
					JOptionPane.showMessageDialog(null,"Invalid input", 
							"Alert",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
				
			}
		}else if (e.getSource() == buttonNick){
			if ( jtextNickname.getText().length() == 0 || 
					jtextNickname.getText().replaceAll(" ", "").length() == 0 ){
					JOptionPane.showMessageDialog(null,"Complete the field", 
							"Alert",JOptionPane.WARNING_MESSAGE);
				}else{
					chooseNickname(jtextNickname.getText().replaceAll(" ", ""));
				}
		
		}else if (e.getSource() == buttonChallenge){
			if (list.getSelectedIndex() == -1){
				JOptionPane.showMessageDialog(null,"Challenge a player", 
						"Alert",JOptionPane.WARNING_MESSAGE);
			}else{
				chooseGamer(list.getSelectedValue().toString());
			}
		}else if (e.getSource() == buttonPlay){
			if (jtextRow1.getText().length() == 0 || jtextRow1.getText().replaceAll(" ", "").length() == 0 ||
				jtextCol1.getText().length() == 0 || jtextCol1.getText().replaceAll(" ", "").length() == 0 ||
				jtextRow2.getText().length() == 0 || jtextRow2.getText().replaceAll(" ", "").length() == 0 ||
				jtextColum2.getText().length() == 0 || jtextColum2.getText().replaceAll(" ", "").length() == 0 ){
						JOptionPane.showMessageDialog(null,"Complete the fields", 
								"Alert",JOptionPane.WARNING_MESSAGE);
			}else{
			
				int x1, y1, x2, y2;
				y1 = Y(jtextRow1.getText());
				x1 = X(jtextCol1.getText());
				y2 = Y(jtextRow2.getText());
				x2 = X(jtextColum2.getText());
				if ( x1 < 0 || x1 > 7 || y1 < 0 || y1 > 7 
					||  x2 < 0 || x2 > 7 || y2 < 0 || y2 > 7 )
					JOptionPane.showMessageDialog(null,"Invalid input", 
							"Alert",JOptionPane.WARNING_MESSAGE);
				else{
					jtextRow1.setText("");
					jtextCol1.setText("");
					jtextRow2.setText("");
					jtextColum2.setText("");
					
					
					printer.println("move " + x1 + " " + y1 + " " + x2 + " " + y2);
					String answer = slave.getInput();
					if (answer.equalsIgnoreCase("youwin")){
						endMyTime();
						refreshMyTime();
						startOpponentTime();
						stopGrid();
						
						lblTurn.setText("Congratulations! You win! :)");
						lblTurn.setVisible(true);
						
						answer = slave.getInput();
						Grid(transformGrid(answer));
						
						
						close();
					
					}else if (answer.equalsIgnoreCase("invalid movement")){
						JOptionPane.showMessageDialog(null,"Invalid movement", 
								"Warnning",JOptionPane.WARNING_MESSAGE);
					}else{
						endMyTime();
						refreshMyTime();
						startOpponentTime();
						Grid(transformGrid(answer));
						stopGrid();
						lblTurn.setVisible(true);
						new SlaveGamerPlay(slave, this).start();
					}
				}
			}
		}
	}
	
	public void close(){
		try {
			reader.close();
			printer.close();
			socket.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void goGrid(){
		jtextRow1.setEnabled(true);
    	jtextCol1.setEnabled(true);
    	jtextRow2.setEnabled(true);
    	jtextColum2.setEnabled(true);
    	lblTurn.setVisible(false);
    	buttonPlay.setEnabled(true);
    	windowGrid.repaint();
	}
	
	public void lost(){
		lblTurn.setText("You lost! Try again :)");
	}
	
	public void refreshMyTime(){
		lblMyTime.setText("Me "+getMyTime());
	}
	
	public void refreshOpponentTime(){
		lblOpponentTime.setText(this.opponent + " "+getOpponetTime());
	}
	
	private void chooseNickname(String userInput){
		String answer;
		this.nickname = userInput;
			
		try {
			printer.println("username "+userInput);
			answer = reader.readLine();
			if (answer.equalsIgnoreCase("invalid username")){
				JOptionPane.showMessageDialog(null,"Invalid input", 
						"Alert",JOptionPane.WARNING_MESSAGE);
			
			}else{
				windowNickname.setVisible(false);
				this.slave = new SlaveGamer(this.reader, this);
				this.slave.start();
				displayGamers();
			}
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
		
	public void initChallege(String user){
		printer.println("accept " + user);
		String answer;
		try {
			answer = reader.readLine();
			if (answer.equalsIgnoreCase("confirm")){
				this.opponent = user;
				windowGamers.setVisible(false);
				new SlaveC(this.slave, this).start();
			}else{
				JOptionPane.showMessageDialog(null, "The other player don't respond :(  Don't worry! challege another player :)", 
						"Challenge",JOptionPane.INFORMATION_MESSAGE);
				updateGamers();
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	private void updateGamers(){
		printer.println("getPlayers");
		try {
			String answer = reader.readLine();
			String[] array = answer.split(" ");
			model.removeAllElements();
			if (array[0].equalsIgnoreCase("list"))
				for (int i = 1; i < array.length; i++)
					model.addElement(array[i]);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	public void dontPlay(String user){
		printer.println("refuse " + user);
		String answer;
		try {
			answer = reader.readLine();
			updateGamers();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void chooseColor(){
		String userColor = "";
		
		Object[] options = {"Black", "White", "Random"};
		int n = JOptionPane.showOptionDialog(null, this.nickname + " before start the game please choose a color :)",
				"Color", JOptionPane.YES_NO_CANCEL_OPTION, JOptionPane.QUESTION_MESSAGE,
				null, options, 	options[2]);

		switch (n){
		case 0:
			userColor = "white";
			break;
		case 1:
			userColor = "black";
			break;
		case 2:
			userColor = "whatever";
			break;
		}
		
		this.printer.println("color " + userColor);
		String answer = slave.getInput();
		if (answer.equalsIgnoreCase("the other player choosed the same color")){
			JOptionPane.showMessageDialog(null,"That is fun, the other player choosed the same color :) Please choose again", 
					"Challenge "+this.nickname,JOptionPane.INFORMATION_MESSAGE);
			chooseColor();
		}else{
			this.color = answer;
			if (this.color.equalsIgnoreCase("white"))
				this.myTurn = true;
			
			answer = slave.getInput();
			displayGrid(this.color, transformGrid(answer));
			if (this.myTurn)
				startMyTime();
			else{
				startOpponentTime();
				new SlaveGamerPlay(this.slave, this).start();
			}
		}
	}
	
	private int Y(String column){
		switch(column){
			case "a":
			case "A":
				return 0;
			case "b":
			case "B":
				return 1;
			case "c":
			case "C":
				return 2;
			case "d":
			case "D":
				return 3;
			case "e":
			case "E":
				return 4;
			case "f":
			case "F":
				return 5;
			case "g":
			case "G":
				return 6;
			case "h":
			case "H":
				return 7;
			default:
				return -1;
		}
	}
	
	private void displayNickname(){
        windowNickname = new JFrame();
        windowNickname.setTitle("Register");
		windowNickname.setLayout(null);
		windowNickname.setSize(200,125);
		windowNickname.setResizable(false);
		
		JLabel lbluser = new JLabel("Nickname");
		lbluser.setBounds(20,10,130,20);
		windowNickname.add(lbluser);
	    
		jtextNickname = new JTextField("");
		jtextNickname.setBounds(20,30,100,20);
	    windowNickname.add(jtextNickname);
	    
	    ImageIcon imagen = new ImageIcon("img/username.png");
		JLabel Img = new JLabel();
		Img.setBounds(120 ,10,100,100);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(Img.getWidth(), Img.getHeight(), Image.SCALE_DEFAULT));
		Img.setIcon(icono);
		windowNickname.add(Img);
	    
	    buttonNick = new JButton("Register");
	    buttonNick.setBounds(20,60,90,25);
	    windowNickname.add(buttonNick);
	    buttonNick.addActionListener(this);
	    
	    windowNickname.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	windowNickname.setLocationRelativeTo(null);
	  	windowNickname.setVisible(true);
	}
	
	private int X(String row){
		switch(row){
			case "1":
				return 7;
			case "2":
				return 6;
			case "3":
				return 5;
			case "4":
				return 4;
			case "5":
				return 3;
			case "6":
				return 2;
			case "7":
				return 1;
			case "8":
				return 0;
			default:
				return -1;
		}
	}
		
	public String transformGrid(String answer){
		String result = "";
		String[] array = answer.split("-");
		for (int i = 1; i < array.length - 1 ; i++){
			result += array[i].substring(2, 18);
		}
		
		return result;
	}
	
	private void displayWindow(){
        window = new JFrame();
        window.setTitle("Connect");
		window.setLayout(null);
		window.setSize(250,180);
		window.setResizable(false);
		
		JLabel lblserverAddres = new JLabel("Address");
		lblserverAddres.setBounds(20,10,130,20);
		window.add(lblserverAddres);
	    
		jtextAddres = new JTextField("");
		jtextAddres.setBounds(20,30,130,20);
	    window.add(jtextAddres);
		
	    JLabel lblserverPort = new JLabel("Port");
	    lblserverPort.setBounds(20,60,130,20);
		window.add(lblserverPort);
	    
		jtextPort = new JTextField("");
	    jtextPort.setBounds(20,80,60,20);
	    window.add(jtextPort);
	    
	    buttonConn = new JButton("Init");
	    buttonConn.setBounds(20,110,90,25);
	    window.add(buttonConn);
	    buttonConn.addActionListener(this);
	    
	    ImageIcon imagen = new ImageIcon("img/server.png");
		JLabel Img = new JLabel();
		Img.setBounds(140,-5,120,160);
		Icon icono = new ImageIcon(imagen.getImage().getScaledInstance(Img.getWidth(), Img.getHeight(), Image.SCALE_DEFAULT));
		Img.setIcon(icono);
		window.add(Img);
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	window.setLocationRelativeTo(null);
	  	window.setVisible(true);
	}
	
	
	public static void main(String[] args) {
		Gamer g = new Gamer();
	}	
}
