import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class Customer {
	private Socket socket;
	private String MENU = new String("\n1. List\n2. Subscribe\n3. Exit\n");  
	
	public Customer() {
		
	}
	
	public Boolean connectToServer() {
		String serverAddress = JOptionPane.showInputDialog(
	            "Enter IP Address of a machine that is\n" +
	            "running the date service on port 9090:");
	     try {
			socket = new Socket(serverAddress, 9090);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			return false;
		}
	    
	     return true;
	}
	
	public String processInput(int option) {
		switch(option) {
			case 0:  
				return MENU;
			case 1:
				return getAlreadySubscribedList() + MENU;
			case 2:
				return getNewOffersList();
			case 3:
				return null;
			default:
				System.out.println("Cannot process the option\n");
			return "wrong answer";
		}
	}
	
	
	
	private String getAlreadySubscribedList() {
		return "ALREADY LIST";
	}
	
	private String getNewOffersList() {
		return "NEW OFFERS";
	}
	
	public void doSomething() throws IOException {
		if (socket == null) {
			JOptionPane.showMessageDialog(null, "NOT CONNECTED");
			return;
		}
	   BufferedReader input =
	            new BufferedReader(new InputStreamReader(socket.getInputStream()));
//	    PrintWriter out =
//	            new PrintWriter(socket.getOutputStream(), true);
	    String answer = input.readLine();
	    
}
}
