package Flesh;

import java.io.IOException;

public class Utils {

	private void startGenieServer() {
	       ExecuteBatch("cmd /d start runServer.bat");
	}
	      
	private void stopGenieServer() {
	       ExecuteBatch("cmd /d start stopServer.bat");
	}
	      
	private void ExecuteBatch(String command){
	       try {
	             Runtime.getRuntime().exec(command);
	       } catch (IOException e) {
	             e.printStackTrace();
	       }
	            
	}
}