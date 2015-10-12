package Flesh;

import java.io.IOException;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.genieCom.SWFApp;

public class TestScrypt extends GenieScript {
	// private static final Logger log = Logger.getLogger(StoneHarvest.class);

	public TestScrypt() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int i = 0;
		boolean appConnect = true;
		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");

		while (i == 0) {
			for (int t = 0; t < 20; t++) {
				Thread.sleep(2000);
				try {
					SWFApp app2 = connectToApp("fight");
					appConnect = false;
					break;
				} catch (Exception e) {
				}
				System.out.println("appConnect - " + appConnect);
			}
			if (!appConnect) {
				break;
			}
		}

	}
}
