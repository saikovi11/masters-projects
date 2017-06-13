import javax.swing.JFrame;
import java.net.Socket;
import java.awt.event.ActionEvent;
import java.io.IOException;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import javax.swing.JLabel;
import java.net.ServerSocket;
import javax.swing.JTextField;

public class ChessServer extends JFrame implements ActionListener {
	private JTextField jtextPort;
	private int port;
	private JButton btnStart;
	private NicknameList list;
	private JFrame window;
	private ServerSocket socket;
	
	ChessServer(){
		this.list = new NicknameList();
		showGUI();
	}
	
	private void showGUI(){
        window = new JFrame();
        window.setTitle("");
		window.setLayout(null);
		window.setSize(140,150);
		window.setResizable(false);
		
	    JLabel label = new JLabel("Port");
	    label.setBounds(30,10,130,20);
		window.add(label);
	    
		jtextPort = new JTextField("");
	    jtextPort.setBounds(30,30,60,20);
	    window.add(jtextPort);
	    
	    btnStart = new JButton("Init");
	    btnStart.setBounds(30,70,70,25);
	    window.add(btnStart);
	    btnStart.addActionListener(this);
	    
	    window.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	  	window.setLocationRelativeTo(null);
	  	window.setVisible(true);
	}
	
	public void actionPerformed(ActionEvent e) {
		if (e.getSource() == btnStart){
			if ( jtextPort.getText().length() == 0 || 
				 jtextPort.getText().replaceAll(" ", "").length() == 0) {
				JOptionPane.showMessageDialog(null,"You must fill the port", 
						"Warnning",JOptionPane.WARNING_MESSAGE);
			}else{
				try {
					port = Integer.parseInt(jtextPort.getText());
					socket = new ServerSocket(port);
					Socket clientSocket;
					window.setVisible(false);
					while ( (clientSocket = socket.accept()) != null)
						(new ServerSlave(clientSocket, list)).start();
					
				}catch (IOException e1) {
					JOptionPane.showMessageDialog(null,"Socket error", 
							"Warnning",JOptionPane.WARNING_MESSAGE);
					e1.printStackTrace();
				}
			}
		}
	}
	
	public static void main(String[] args) {
		ChessServer server = new ChessServer();
	    
	}

}
