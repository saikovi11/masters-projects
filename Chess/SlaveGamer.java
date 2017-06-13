import java.io.IOException;
import java.io.BufferedReader;
import javax.swing.JOptionPane;

public class SlaveGamer extends Thread{
	private Gamer gamer;
	private boolean read = false;
	
	private String content = "";
	private BufferedReader reader;
	

	SlaveGamer(BufferedReader input, Gamer g){
		reader = input;
		this.gamer = g;
	}

	public synchronized String getInput(){
		while(!this.read){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.read = false;
		notifyAll();
		return this.content;
	}
	
	public void run(){
		String input;
		try {
			while ((input = reader.readLine()) != null) {
				String[] array = input.split(" ");
				if (array[0].equalsIgnoreCase("invitationFrom")){
					int response = JOptionPane.showConfirmDialog(null,array[1] + " has challenged you to play. Do you accept?", 
							"Challenge",JOptionPane.YES_NO_OPTION);
					if (response == 0){
						this.gamer.initChallege(array[1]);
					}else{
						this.gamer.dontPlay(array[1]);
					}
				}else if (array[0].equalsIgnoreCase("list")){
					String sms = "";
					if (array.length == 1)
						sms = "There is no other players";
					else
						for (int i = 1; i < array.length; i++)
							sms += array[i] + "\n";
						
					setText(sms);
					
				}else
					setText(input);
			    
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public synchronized void setText(String sms){
		while(this.read){
			try {
				wait();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		this.read = true;
		this.content = sms;
		notifyAll();
	}
}
