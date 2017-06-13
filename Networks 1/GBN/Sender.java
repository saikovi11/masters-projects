/*
** Class: Sender
** Send the content of COSC635_P2_DataSent.txt file using GBN protocol
*/

import java.util.Random;
import java.io.InputStream;
import java.io.FileNotFoundException;

import java.io.IOException;
import java.io.FileInputStream;
import java.io.FileWriter;

import java.io.PrintWriter;
import java.nio.ByteBuffer;

import javax.swing.JOptionPane;
import java.io.File;
import java.net.*;

public class Sender{

	private int n = 8;							// Window size
	private int m = 528;						// Maximum payload size = 528bytes
	private InetAddress sourceAdd;		// Receiver address
	private int receiverPort=0;					// Receiver port 
	private int portInit=0 ;						// My port
	private DatagramSocket datagramSocket;
	private int packetLosses;					// Percentage of packet losses
	private int timeout = 1;					// Timeout
	private long endTime, initTime;
	private int count ;
	private int losses = 0;
	private int headerSize = 48;

	Sender(String address, int port, int losses, boolean isServer){
		if (isServer){
			portInit = port;
			packetLosses = losses;
			sendFile();

		}else{
			try {
				sourceAdd = InetAddress.getByName(address);
				receiverPort = port;
				packetLosses = losses;
				sendFile();
			} catch (UnknownHostException exc) {
				JOptionPane.showMessageDialog(null,
			    	"Invalid address",
			    	"Error",
			    	JOptionPane.ERROR_MESSAGE);
			}
		}
	}

	public void sendFile(){
		
		try {
		
			//Read from a file named COSC635_P2_DataSent.txt
			File inputFile = new File("COSC635_P2_DataSent.txt");
			InputStream input = new FileInputStream(inputFile);
			int inputfileLength = (int) inputFile.length();

			
			//Server
			if(portInit!=0){
				datagramSocket = new DatagramSocket(portInit);
			
			//Client
			}else{
				datagramSocket = new DatagramSocket();
			}
				
			byte[][] packetArray =  new byte [n][m]; //Window with packets
			
			int nextPacket = 0;
			count = 0;
			int windowInit = 0;
			int next = 0;
			
			//Receiver of incoming messages
			Receiver receiver;
			receiver = new Receiver(datagramSocket);
			receiver.start();
			
			initTime = System.currentTimeMillis();
			while (nextPacket < inputfileLength){
				if (next >= nextPacket) {
					nextPacket = next;
					int numPacket = n;
					int end = 0;
					int leftover = 0;

					//Move packages that were not sent in the previous window
					if ( nextPacket != windowInit && ((nextPacket - windowInit)/m != n)){
						int aux = (nextPacket - windowInit)/m;
						end = n - aux;
						for (int i = 0 ; i < end ; i ++){
							packetArray[i] = packetArray[aux];
							aux++;
							leftover++;
						}
					}

					//Copy packages from the file to the window
					end = n - end;
					if ( (nextPacket == 0 && count == 0) || nextPacket != windowInit ){
						for (int i = 0 ; i < end ; i ++){
							byte[] buffer = new byte[m];
							int read = 0;
							if ((read = input.read(buffer)) != -1 ){
								packetArray[leftover] = buffer;
								leftover++;
							}else{
								if (leftover < n)
									packetArray[leftover] = null;
								numPacket = leftover;
								break;
							}
						}
					}

					
					windowInit = nextPacket;
					int numSequence = windowInit;
					
					if (receiverPort == 0){
						sourceAdd= receiver.getIncomingAddress();
						receiverPort = receiver.getIncomingPort();
						
					}
					
					Random generator = new Random(System.currentTimeMillis());
					int randomInt = generator.nextInt(100);
					
					//If the random number generated is less than the user input number
					// then the current protocol data unit wont be sent to simulate a packet loss.
					if ( randomInt >= packetLosses){
						
						//Send packages 
						for (int i = 0 ; i < numPacket ; i ++ ){
							if (packetArray[i] == null){
								break;
							}else{
								byte[] sourceAddress = ByteBuffer.allocate(16).put(InetAddress.getLocalHost().getHostAddress().getBytes()).array();
								byte[] destinationAddresss = ByteBuffer.allocate(16).put(sourceAdd.getHostAddress().getBytes()).array();
								byte[] sourcePort = ByteBuffer.allocate(4).putInt(datagramSocket.getLocalPort()).array();
								byte[] destinationPort = ByteBuffer.allocate(4).putInt(receiverPort).array();
								byte[] arrayNumSeq = ByteBuffer.allocate(4).putInt(numSequence).array();
								byte[] packetType = ByteBuffer.allocate(4).putInt(1).array();
								byte[] packet = new byte[headerSize+m];

								//Add the source Address
								for (int j = 0; j<16; j++)
									packet[j] = sourceAddress[j];
								

								//Add the destination Address
								for (int j = 0; j<16; j++)
									packet[j+16] = destinationAddresss[j];

								//Add the source Port
								for (int j = 0; j < 4; j++)
									packet[j+32] = sourcePort[j];

								//Add the destination Port
								for (int j = 0; j < 4; j++)
									packet[j+36] = destinationPort[j];
								

								//Add the number of sequence to the packet
								for (int j = 0; j < 4; j++)
									packet[j+40] = arrayNumSeq[j];
								
								
								//Add the type of packet = 1
								for (int j = 0; j < 4; j++)
									packet[j+44] = packetType[j];
								

								//Add the data to the packet
								for(int j = headerSize; j < m+headerSize ; j++)
									packet[j] = packetArray[i][j-headerSize];

								
								//System.out.println("Sender: Packet "+i+" Number of sequence"+numSequence);
								String p = new String(packetArray[i]);
								//System.out.println("Sender: Data of packet= "+p+"\n");

								
								DatagramPacket datagramPacket = new DatagramPacket(packet, packet.length, sourceAdd,receiverPort);
								datagramSocket.send(datagramPacket);

								numSequence = numSequence + m;
							}
						}
					}else
						losses++;
				}
				
				// Wait for the ack
				try{
				    Thread.sleep(timeout*1000);
				
				}catch(InterruptedException e){
					e.printStackTrace();
					System.out.println("Error with Thread.sleep");
					System.exit(1);
				}

				next = receiver.getACK();
				count++;
				
			}
			
			//System.out.println("Sender: Transmission finished");
			endTime = System.currentTimeMillis();
			
			//Send Transmission finished
			while(receiver.getACK() != -2){
				byte[] sourceAddress = ByteBuffer.allocate(16).put(InetAddress.getLocalHost().getHostAddress().getBytes()).array();
				byte[] destinationAddresss = ByteBuffer.allocate(16).put(sourceAdd.getHostAddress().getBytes()).array();
				byte[] sourcePort = ByteBuffer.allocate(4).putInt(datagramSocket.getLocalPort()).array();
				byte[] destinationPort = ByteBuffer.allocate(4).putInt(receiverPort).array();
				byte[] arrayNumSeq = ByteBuffer.allocate(4).putInt(-2).array();
				byte[] packetType = ByteBuffer.allocate(4).putInt(1).array();
				byte[] packet = new byte[headerSize+m];

				//Add the source Address
				for (int i = 0; i<16; i++)
					packet[i] = sourceAddress[i];
				

				//Add the destination Address
				for (int i = 0; i<16; i++)
					packet[i+16] = destinationAddresss[i];

				//Add the source Port
				for (int i = 0; i < 4; i++)
					packet[i+32] = sourcePort[i];

				//Add the destination Port
				for (int i = 0; i < 4; i++)
					packet[i+36] = destinationPort[i];
				

				//Add the number of sequence to the packet -2
				for (int i = 0; i < 4; i++)
					packet[i+40] = arrayNumSeq[i];
				
				
				//Add the type of packet = 1
				for (int i = 0; i < 4; i++)
					packet[i+44] = packetType[i];
				
				DatagramPacket answer = new DatagramPacket(packet, packet.length,sourceAdd,receiverPort);
				datagramSocket.send(answer);
				
				// Wait for the ack
				try{
				    Thread.sleep(timeout*1000);
				
				}catch(InterruptedException e){
					e.printStackTrace();
					System.out.println("Error with Thread.sleep");
					System.exit(1);
				}
			}
			
			//Close files
			input.close();
			
			
		} catch (SocketException e) {
			e.printStackTrace();
			System.out.println("Error with DatagramSocket");

			JOptionPane.showMessageDialog(null,"Error with DatagramSocket", "Error", JOptionPane.ERROR_MESSAGE);

			System.exit(1);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("Error open file: DataSent.txt");
			JOptionPane.showMessageDialog(null, "Error open file: DataSent.txt", "Error", JOptionPane.ERROR_MESSAGE);

			System.exit(1);
		} catch (IOException e) {
			e.printStackTrace();
			System.out.println(e);
			System.exit(1);
		}
		

	}

	String getTime(){
		int time = ((int)(endTime - initTime)/1000);
		int days = time / 86400;
		int res = time % 86400;
		int hours = res / 3600;
		res = res % 3600;
		int min = res / 60;
		int sec = res % 60;
		String result = "Transmission time = "+days+"days "+hours+"hours "+min+"minutes "+sec+"seconds";
		return result;
	}

	String getSentPackets(){
		return count * n + " sent packets";
	}

	String getLostPackets(){
		return losses * n + " lost packets";
	}

	
}