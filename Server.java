import java.awt.List;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Date; 

public class Server {
	private ArrayList<Offer> offers;
	ServerSocket listener;
    
	public Server() {
		
		offers = new ArrayList<>();
	}
	
	public Boolean start() {
		try {
			listener = new ServerSocket(9090);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			
			return false;
		}
		return true;
	}
	
	public void end() {
		try {
			listener.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
}
