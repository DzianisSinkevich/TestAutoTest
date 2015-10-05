package Flesh;

import java.util.concurrent.Executor;

public class GenieScriptsExecutor {
    public static void ExecuteScript(String path)  {
       try {
          String[] path_arr = new String[]{path};
          Executor.startExecution(path_arr);
       }
       catch (ClassNotFoundException e) {
          e.printStackTrace();
       }
    }
}
