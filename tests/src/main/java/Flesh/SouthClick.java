package Flesh;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieSprite;
import com.adobe.genie.genieCom.SWFApp;

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
		int flagSmen = 0;
		int tempJ = 0;
		int j = 0;
		int Kolvo = 0;
		boolean south = true;
		String selectorFish = "";
		// Turn this on if you want script to exit
		// when a step fails
		EXIT_ON_FAILURE = false;

		// Turn this on if you want a screenshot
		// to be captured on a step failure
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		// click na South
		(new GenieDisplayObject(
				"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
				app1)).click();
		// click na scroll vverh
		(new GenieSprite(
				"FP^scroller_vertical:::SE^Sprite:::CH^Bitmap::PX^3::PTR^0::IX^1::ITR^0",
				app1)).click();

		for (int i = 0; i < 100000; i++) {
			// click na object
			for (j = tempJ; j <= 21; j++) {
				selectorFish = "";
				selectorFish = "SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^"
						+ j;
				try {
					System.out.println(" - " + j + " - ");
					System.out.println("Start IF");
					if ((new GenieDisplayObject(selectorFish, app1))
							.isPresent()) {
						System.out.println("Start 2nt IF");
						if ((new GenieDisplayObject(selectorFish, app1))
								.getValueOf("type").equals(
										"fish_blue_r_fla::sensor_4")) {
							System.out.println("Click on fish");
							(new GenieDisplayObject(selectorFish, app1))
									.click();
							// click na lovit'
							(new GenieSprite(
									"SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^2::PTR^0::IX^2::ITR^0",
									app1)).click();

							Thread.sleep(25000);
							// click na zakrit'
							for (int k = 0; k < 50; k++) {
								if ((new GenieDisplayObject(
										"SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0",
										app1)).isPresent()) {
									(new GenieSprite(
											"SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0",
											app1)).click();
									Kolvo++;
									System.out.println("Kolvo - " + Kolvo);
									break;
								}
								Thread.sleep(100);
							}

							tempJ = j + 1;
							break;
						}
					}
					if (j >= 19) {
						flagSmen++;
						break;
					}
				} catch (Exception e) {
					System.out.println("Exception");
				}
			}
			if (flagSmen > 0) {
				if (!south) {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
							app1)).click();
					south = true;
					flagSmen = 0;
					tempJ = 0;
				} else {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0",
							app1)).click();
					south = false;
					flagSmen = 0;
					tempJ = 0;
				}
			}

		}
	}
}
