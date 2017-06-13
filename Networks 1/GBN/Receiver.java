/*
** Class: Receiver
** Put the packets received in the DataRecieved.txt file
*/


import java.nio.ByteBuffer;

import java.io.IOException;
import java.net.*;

import java.io.FileWriter;
import java.io.PrintWriter;

public class Receiver extends Thread{

		private DatagramSocket datagramSocket;
		private int ack;				// Sequence number of the next packet
		private int m = 528;			// Maximum payload size
		private boolean endFile = false;
		private boolean transmissionFinished = false;
		private int myPort=0;			//port
		private InetAddress incomingAddress;	//address
		private int incomingPort=0;			//port
		private int headerSize = 48;

		Receiver(DatagramSocket socket){
			this.datagramSocket = socket;
			this.ack = 0;
		}
		
		public void run() {
			PrintWriter outputFile = null;
			int nextPacket = 0;
			
			try {
				//Received data put into a file named DataRecieved.txt
				outputFile = new PrintWriter("DataRecieved.txt","UTF-8");
				
			} catch (IOException e1) {
				e1.printStackTrace();
				System.out.println(e1);
				System.exit(1);
			}
			
			byte[] buffer = new byte[headerSize+m];
			DatagramPacket incomingDatagram = new DatagramPacket(buffer, buffer.length);
		
			
			while(!transmissionFinished || !endFile) {
				
					try {
						// listen for incoming datagram packet
						datagramSocket.receive(incomingDatagram);
						
						byte[] packet = incomingDatagram.getData();

						byte[] sourceAdd = new byte[16];
						byte[] destAdd = new byte[16];
						byte[] sourcePort = new byte[4];
						byte[] destinationPort = new byte[4];
						byte[] numSequence = new byte[4];
						byte[] packetType = new byte[4];
						byte[] data = new byte[m];

						//get the source Port
						for(int j = 0; j< 16; j++)
							sourceAdd[j] = packet[j];

						//get the destination Port
						for(int j = 0; j< 16; j++)
							destAdd[j] = packet[j+16];
						

						//get the source Port
						for(int j = 0; j< 4; j++)
							sourcePort[j] = packet[j+32];

						//get the destination Port
						for(int j = 0; j< 4; j++)
							destinationPort[j] = packet[j+36];
						
						//get sequence number
						for(int j = 0; j< 4; j++)
							numSequence[j] = packet[j+40];
						
						
						//get type of packet
						for(int j = 0; j< 4; j++)
							packetType[j] = packet[j+44];

						String v1 = new String(sourceAdd);
						String v2 = new String(destAdd);


						int seq = ByteBuffer.wrap(numSequence).getInt();
						int type = ByteBuffer.wrap(packetType).getInt();
						
						//The incomming message contain data
						if ( type == 1 && !endFile){
							
							
							//Transmission finished
							if (seq==-2){
								nextPacket = 0;
								endFile = true;

								byte[] sourceAddress = ByteBuffer.allocate(16).put(InetAddress.getLocalHost().getHostAddress().getBytes()).array();
								byte[] destinationAddresss = ByteBuffer.allocate(16).put(incomingDatagram.getAddress().getHostAddress().getBytes()).array();
								byte[] sourceP = ByteBuffer.allocate(4).putInt(datagramSocket.getLocalPort()).array();
								byte[] destinationP = ByteBuffer.allocate(4).putInt(incomingDatagram.getPort()).array();
								byte[] arrayNext = ByteBuffer.allocate(4).putInt(-2).array();
								byte[] arrayType = ByteBuffer.allocate(4).putInt(2).array();
								byte[] answer = new byte[headerSize];

								//Add the source Address
								for (int i = 0; i<16; i++)
									answer[i] = sourceAddress[i];
								

								//Add the destination Address
								for (int i = 0; i<16; i++)
									answer[i+16] = destinationAddresss[i];

								//Add the source Port
								for (int i = 0; i < 4; i++)
									answer[i+32] = sourceP[i];

								//Add the destination Port
								for (int i = 0; i < 4; i++)
									answer[i+36] = destinationP[i];
								

								//Add the number of sequence to the packet -2
								for (int i = 0; i < 4; i++)
									answer[i+40] = arrayNext[i];
								
								
								//Add the type of packet = 2
								for (int i = 0; i < 4; i++)
									answer[i+44] = arrayType[i];

								
								DatagramPacket answerDatagram = new DatagramPacket(answer, answer.length, incomingDatagram.getAddress(),incomingDatagram.getPort());
								datagramSocket.send(answerDatagram);
								
							//Expected packet
							}else if (seq == nextPacket){
								setIncomingPort(incomingDatagram.getPort());
								setIncomingAddress(incomingDatagram.getAddress());
								
								for (int i = headerSize ; i < m+headerSize ; i++)
									data[i-headerSize] = packet[i];
								
								String message = new String(data);
								outputFile.print(message.replaceAll("\00", ""));
								nextPacket = nextPacket + m;


								byte[] sourceAddress = ByteBuffer.allocate(16).put(InetAddress.getLocalHost().getHostAddress().getBytes()).array();
								byte[] destinationAddresss = ByteBuffer.allocate(16).put(incomingDatagram.getAddress().getHostAddress().getBytes()).array();
								byte[] sourceP = ByteBuffer.allocate(4).putInt(datagramSocket.getLocalPort()).array();
								byte[] destinationP = ByteBuffer.allocate(4).putInt(incomingDatagram.getPort()).array();
								byte[] arrayNext = ByteBuffer.allocate(4).putInt(nextPacket).array();
								byte[] arrayType = ByteBuffer.allocate(4).putInt(2).array();
								byte[] answer = new byte[headerSize];


								//Add the source Address
								for (int i = 0; i<16; i++)
									answer[i] = sourceAddress[i];
								

								//Add the destination Address
								for (int i = 0; i<16; i++)
									answer[i+16] = destinationAddresss[i];

								//Add the source Port
								for (int i = 0; i < 4; i++)
									answer[i+32] = sourceP[i];

								//Add the destination Port
								for (int i = 0; i < 4; i++)
									answer[i+36] = destinationP[i];
								

								//Add the number of sequence to the next expected packet
								for (int i = 0; i < 4; i++)
									answer[i+40] = arrayNext[i];
								
								
								//Add the type of packet = 2
								for (int i = 0; i < 4; i++)
									answer[i+44] = arrayType[i];


								DatagramPacket answerDatagram = new DatagramPacket(answer, answer.length, incomingDatagram.getAddress(),incomingDatagram.getPort());
								datagramSocket.send(answerDatagram);
								

							}else{
								
								byte[] sourceAddress = ByteBuffer.allocate(16).put(InetAddress.getLocalHost().getHostAddress().getBytes()).array();
								byte[] destinationAddresss = ByteBuffer.allocate(16).put(incomingDatagram.getAddress().getHostAddress().getBytes()).array();
								byte[] sourceP = ByteBuffer.allocate(4).putInt(datagramSocket.getLocalPort()).array();
								byte[] destinationP = ByteBuffer.allocate(4).putInt(incomingDatagram.getPort()).array();
								byte[] arrayNext = ByteBuffer.allocate(4).putInt(nextPacket).array();
								byte[] arrayType = ByteBuffer.allocate(4).putInt(2).array();
								byte[] answer = new byte[headerSize];


								//Add the source Address
								for (int i = 0; i<16; i++)
									answer[i] = sourceAddress[i];
								

								//Add the destination Address
								for (int i = 0; i<16; i++)
									answer[i+16] = destinationAddresss[i];

								//Add the source Port
								for (int i = 0; i < 4; i++)
									answer[i+32] = sourceP[i];

								//Add the destination Port
								for (int i = 0; i < 4; i++)
									answer[i+36] = destinationP[i];
								

								//Add the number of sequence to the next expected packet
								for (int i = 0; i < 4; i++)
									answer[i+40] = arrayNext[i];
								
								
								//Add the type of packet = 2
								for (int i = 0; i < 4; i++)
									answer[i+44] = arrayType[i];
								
								DatagramPacket answerDatagram = new DatagramPacket(answer, answer.length, incomingDatagram.getAddress(),incomingDatagram.getPort());
								datagramSocket.send(answerDatagram);

							}
							
							
							
							
						//The incomming message is an ack
						}else if ( type == 2 && !transmissionFinished){
							

							//System.out.println("Receiver: ACK "+seq);
							if ( seq == -2){
								transmissionFinished = true;
								this.ack = seq;
							}
							if ((seq > this.ack) && (seq % m == 0))
								this.ack = seq;
						}
						
					} catch (IOException e) {
						e.printStackTrace();
						System.out.println("Error in receive incoming packet");
					}
					
			}
			outputFile.close();
			//System.out.println("Receiver: end");

		}


		public synchronized void setIncomingAddress(InetAddress address){
			this.incomingAddress = address;
			notify();
		}
		
		public synchronized InetAddress getIncomingAddress(){
			try {
				wait();
			}catch(InterruptedException ex){
				ex.printStackTrace();
	        }
			return incomingAddress;
		}
		
		public void setIncomingPort(int port){
			this.incomingPort = port;
		}
		
		public int getIncomingPort(){
			return incomingPort;
		}
		
		public int getACK(){
			return this.ack;
		}
		
	
}
