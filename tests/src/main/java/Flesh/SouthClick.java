package Flesh;

import com.adobe.genie.genieCom.SWFApp;
import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.*;
import com.adobe.genie.executor.uiEvents.*;

import static com.adobe.genie.executor.GenieAssertion.*;

import com.adobe.genie.executor.enums.GenieLogEnums;

/**
 * This is a sample Genie script.
 */
// Change name of the class
public class SouthClick extends GenieScript {

	public SouthClick() throws Exception {
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

		SWFApp app1 = connectToApp("[object init]");
		(new GenieDisplayObject(
				"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
				app1)).click(18, 17, 976, 279, 1141, 600, 3, false);
		(new GenieSprite(
				"FP^scroller_vertical:::SE^Sprite:::CH^Bitmap::PX^3::PTR^0::IX^1::ITR^0",
				app1)).click();

		for (int i = 0; i < 10; i++) {
			(new GenieDisplayObject(
					"SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^0",
					app1)).click();

			System.out
					.println(" ! "
							+ (new GenieDisplayObject(
									"SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^0",
									app1)).getValueOf("text")+" !");
			(new GenieSprite(
					"SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^2::PTR^0::IX^2::ITR^0",
					app1)).click();
			Thread.sleep(34000);
			(new GenieSprite(
					"SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0",
					app1)).click();
		}
	}
}
