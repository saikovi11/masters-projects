/*
** Class: StopAndWaitInterface
** It is the GUI of the program
*/

import java.awt.event.ActionListener;
import javax.swing.JOptionPane;

import javax.swing.JRadioButton;

import javax.swing.SwingUtilities;
import java.awt.Frame;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import javax.swing.ButtonGroup;
import javax.swing.JTextField;
import java.awt.event.KeyEvent;

import javax.swing.JFrame;
import java.awt.Button;
import javax.swing.JLabel;


class StopAndWaitInterface extends JFrame {

	private JLabel time;
	private JLabel unit;
	private JLabel losses;
	private JTextField serverAddress;
	private JTextField  serverPort;
	private JTextField  packetLosses;
	
	

	public StopAndWaitInterface(){
		super("Stop-And-Wait Error Control Protocol");
		setLayout(null);

		JRadioButton serverButton = new JRadioButton("Server");
	    serverButton.setMnemonic(KeyEvent.VK_S);
	    serverButton.setActionCommand("server");
	    serverButton.setSelected(true);
	    serverButton.setBounds(20, 10, 100, 20);
	    add(serverButton);

	    JRadioButton clientButton = new JRadioButton("Client");
	    clientButton.setMnemonic(KeyEvent.VK_C);
	    clientButton.setActionCommand("client");
	    clientButton.setBounds(150, 10, 130, 20);
	    add(clientButton);

	    ButtonGroup group = new ButtonGroup();
    	group.add(serverButton);
    	group.add(clientButton);
		
		JLabel serverAddressLabel = new JLabel("Server Ip Address:");
		serverAddressLabel.setBounds(20, 40, 150, 20);
		add(serverAddressLabel);
		serverAddress = new JTextField("");
		serverAddress.setBounds(160, 40, 130, 20);
		serverAddress.setEditable(false);
		add(serverAddress);
		
		JLabel serverPortLabel = new JLabel("Server Port:");
		serverPortLabel.setBounds(20, 70, 100, 20);
		add(serverPortLabel);
		serverPort = new JTextField("");
		serverPort.setBounds(110, 70, 55, 20);
		add(serverPort);
		
		JLabel packetLossesLabel = new JLabel("% Packet Losses:");
		packetLossesLabel.setBounds(20, 100, 180, 20);
		add(packetLossesLabel);
		packetLosses = new JTextField("");
		packetLosses.setBounds(160, 100, 40, 20);
		add(packetLosses);

		
		Button buttonStart = new Button("Start");
		buttonStart.setBounds(220,130,80,30);
		
		add(buttonStart);
		
		unit = new JLabel("");
		unit.setBounds(20, 180, 300, 20);
		add(unit);
		
		losses = new JLabel("");
		losses.setBounds(20, 210, 300, 20);
		add(losses);

		time = new JLabel("");
		time.setBounds(20, 240, 400, 20);
		add(time);
		
		setSize(550,310);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);


		serverButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		serverAddress.setEditable(false);
	    	}
	    });
	    
		clientButton.addActionListener(new ActionListener() {
	    	public void actionPerformed(ActionEvent e) {
	    		serverAddress.setEditable(true);
	    	}
	    });

	    buttonStart.addActionListener(new ActionListener() {

		    public void actionPerformed(ActionEvent e) {

		    	if (serverPort.getText().equals("") || packetLosses.getText().equals("")){
			    	JOptionPane.showMessageDialog(null,
						    "The Port and % Packet Losses fields can not be empty",
						    "Error", JOptionPane.ERROR_MESSAGE);
			    	
		    	}else {

		    		try {
		    			int port = Integer.parseInt(serverPort.getText());
			    		int lossesP = Integer.parseInt(packetLosses.getText());
			    		String address = serverAddress.getText();

			    		if (port < 1024 || port > 49151){
				    		JOptionPane.showMessageDialog(null,
								    "Invalid port. It must be an integer between 1024 and 49151",
								    "Error",JOptionPane.ERROR_MESSAGE);
				    		
				    	}else if (lossesP < 0 || lossesP > 99){
				    		JOptionPane.showMessageDialog(null,
								    "Invalid % Packet Losses. It must be an integer between 0 and 99",
								    "Error",JOptionPane.ERROR_MESSAGE);

				    	}else{

				    		SenderSAW sender = new SenderSAW(address,port,lossesP,serverButton.isSelected());

					    	//Show Statictic
							unit.setText(sender.getSentPackets());
							losses.setText(sender.getLostPackets());
							time.setText(sender.getTime());
				    	}

			    	} catch (NumberFormatException excep) {
			    		JOptionPane.showMessageDialog(null,
							    "Invalid port or invalid % packet losses. Port must be an integer between 1024 and 49151 and packet losses must be an integer between 0 and 99",
							    "Error", JOptionPane.ERROR_MESSAGE);
			    	}

		    	}
		    }
		});		
	}

	public static void main(String[] args){
		new StopAndWaitInterface();
	}
	
}
	
