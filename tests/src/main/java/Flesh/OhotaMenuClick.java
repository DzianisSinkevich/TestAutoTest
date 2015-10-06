package Flesh;

import java.util.List;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.genieCom.SWFApp;

/**
 * This is a sample Genie script.
 */
// Change name of the class
public class OhotaMenuClick extends GenieScript {

	public OhotaMenuClick() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		// Turn this on if you want script to exit
		// when a step fails
		EXIT_ON_FAILURE = false;

		// Turn this on if you want a screenshot
		// to be captured on a step failure
		CAPTURE_SCREENSHOT_ON_FAILURE = false;
		SWFApp app1 = connectToApp("[object menu]");
		(new GenieDisplayObject(
				"FP^MenuInit:::SE^Item:::CH^Bitmap::PX^0::PTR^0::IX^4::ITR^0",
				app1)).click(25, 18, 257, 34, 530, 78, 3, false);
	}
}