package Flesh;

import org.apache.log4j.Logger;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.genieCom.SWFApp;

public class TestScrypt extends GenieScript {
	private static final Logger log = Logger.getLogger(StoneHarvest.class);

	public TestScrypt() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int i=0;
		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		while (i==0){
			if (!app1.isSwfConnected()){
				break;
			}
			Thread.sleep(500);
		}
		
	}
}

