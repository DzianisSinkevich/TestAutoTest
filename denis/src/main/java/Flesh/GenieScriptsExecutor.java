package Flesh;

import java.io.IOException;

import com.adobe.genie.executor.Executor;

public class GenieScriptsExecutor {
	public static void ExecuteScript(String path) {
		try {
			String[] path_arr = new String[] { path };
			Executor.startExecution(path_arr);
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	private void startGenieServer() {
		ExecuteBatch("cmd /c start runServer.bat");
	}

	private void stopGenieServer() {
		ExecuteBatch("cmd /c start stopServer.bat");
	}

	private void ExecuteBatch(String command) {
		try {
			Runtime.getRuntime().exec(command);
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
}
