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
		int route = 1;
		int kolvo = 0;
		String nameObject = "";
		// Turn this on if you want script to exit
		// when a step fails
		EXIT_ON_FAILURE = false;

		// Turn this on if you want a screenshot
		// to be captured on a step failure
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		// click na South
		(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();

		for (int i = 0; i < 100000; i++) {
			// click na object
			for (j = tempJ; j <= 12;) {
				try {
					System.out.println(" - " + j + " - ");
					System.out.println("Start IF");
					// if ((new
					// GenieDisplayObject("SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^"
					// + j + "", app1)).isPresent()) {
					(new GenieDisplayObject("SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^" + j + "", app1)).click();
					nameObject = new GenieDisplayObject("SP^base:::FP^frame_corner_TR:::SE^TextField::PX^8::PTR^0::IX^5::ITR^0", app1).getValueOf("text");
					// System.out.println(nameObject);
					if ((nameObject.equals("ÐšÑ€ÐµÐ¼Ð½Ð¸Ð¹")) || (nameObject.equals("Ð�Ð²Ð°Ð½Ñ‚ÑŽÑ€Ð¸Ð½"))) {

						(new GenieSprite("SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^3::PTR^0::IX^2::ITR^0", app1)).click();
						Thread.sleep(11000);
						for (int k = 0; k < 50; k++) {
							if ((new GenieDisplayObject("SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0", app1)).isPresent()) {
								(new GenieSprite("SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0", app1)).click();
								kolvo++;
								System.out.println("Count - " + kolvo);
								break;
							}
							Thread.sleep(100);
						}
					}

					// }
					if (j >= 8) {
						flagSmen++;
						break;
					}
				} catch (Exception e) {
					System.out.println("Exception");
				}
				j++;
			}
			if (flagSmen > 0) {
				switch (route) {
				case 1: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();
					route = 2;
					flagSmen = 0;
					tempJ = 0;
					break;
				}
				case 2: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0", app1)).click();
					route = 3;
					flagSmen = 0;
					tempJ = 0;
					break;
				}
				case 3: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0", app1)).click();
					route = 4;
					flagSmen = 0;
					tempJ = 0;
					break;
				}
				case 4: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^7::PTR^0::IX^0::ITR^0", app1)).click();
					route = 1;
					flagSmen = 0;
					tempJ = 0;
					break;
				}
				}
			}
		}
	}
}
