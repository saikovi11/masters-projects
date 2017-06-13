import java.util.Hashtable;
import java.util.Enumeration;

public class NicknameList {
	private Hashtable<String, ServerSlave> list;
	
	NicknameList(){
		this.list = new Hashtable<String, ServerSlave>();
	}
	
	public synchronized void removeNickname(String username){
		this.list.remove(username);
	}
	
	public boolean isUser(String username){
		return this.list.containsKey(username);
	}
	
	public synchronized Enumeration<String> getListNickName(){
		 return this.list.keys();	
	}
	
	public synchronized boolean putNickname(String username, ServerSlave thread){
		if (!isUser(username)){
			this.list.put(username, thread);
			return true;
		}
		return false;
	}
	
	public synchronized ServerSlave getNickname(String username){
		return this.list.get(username);
	}
}
