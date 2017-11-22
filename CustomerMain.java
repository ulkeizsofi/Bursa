import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

import javax.swing.JOptionPane;

public class CustomerMain {
	
	
	public static void main(String[] args) throws IOException {
        
		
     Customer c = new Customer();
     if (c.connectToServer()) {
    	 String answer = c.processInput(0);
    	 int opt;
    	 while(answer != null) {
    		 try {
	    	  
		    	 opt = Integer.parseInt(JOptionPane.showInputDialog(answer));
		    	 answer = c.processInput(opt);
	    	 }
    		 catch(NumberFormatException e) {
    			 answer = "INSERT NUMBER\n" + c.processInput(0);
    			 opt = Integer.parseInt(JOptionPane.showInputDialog(answer));
		    	 answer = c.processInput(opt);
    		 }
    	 }
     }
     else {
    	 System.out.println("Cannot connect");
     }
     System.exit(0);
    }
}
