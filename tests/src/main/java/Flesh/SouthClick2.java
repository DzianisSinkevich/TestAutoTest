package Flesh;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieSprite;
import com.adobe.genie.genieCom.SWFApp;

/**
 * This is a sample Genie script.
 */
// Change name of the class
public class SouthClick2 extends GenieScript {

	public SouthClick2() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int flagSmen = 0;
		int tempJ = 0;
		int j = 0;
		boolean south = true;
		// Turn this on if you want script to exit
		// when a step fails
		EXIT_ON_FAILURE = false;

		// Turn this on if you want a screenshot
		// to be captured on a step failure
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		// click na South
		(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();

	}
}
