package Flesh;

import org.apache.log4j.Logger;

import com.adobe.genie.executor.GenieLocatorInfo;
import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieComponent;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieSprite;
import com.adobe.genie.genieCom.SWFApp;

/**
 * This is a sample Genie script.
 */
// Change name of the class
public class FishHarvest extends GenieScript {
	private static final Logger log = Logger.getLogger(FishHarvest.class);

	public FishHarvest() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int misRes = 0;
		String forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
		int route = 2;

		String nameObject = "";
		String nameObject1 = "";
		int kolvo = 0;

		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();

		log.warn("START GENERAL FOR");
		for (int i = 0; i < 10000; i++) {
			misRes = 0;
			log.info("------------------------------------------------------------------------------------------------------------ COUNT - "
					+ kolvo);
			log.info("START SEARCH OF CHILDREN");
			GenieLocatorInfo info = new GenieLocatorInfo();
			info.propertyValueTable.put("name", "sensor");
			GenieDisplayObject root = new GenieDisplayObject(forChild, app1);
			root.getValueOf("numChildren");
			log.info("ADD CHILDREN IN ARRAY");
			GenieComponent[] childs = root.getChildren(info, true);
			log.info("END SEARCH OF CHILDREN.");

			log.warn("START FOR");
			for (GenieComponent gc : childs) {
				log.info("------------------------------------------------------------------------------------------------------------ COUNT - "
						+ kolvo);

				// nameObject1 = new GenieDisplayObject(gc.getGenieID(), app1).getValueOf("type");
				nameObject = gc.getValueOf("type");
				log.info("START IF FISH.");
				if (nameObject.equals("fish_blue_r_fla::sensor_4")) {
					gc.click();
					log.info("START FARMING.");
					(new GenieSprite("SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^3::PTR^0::IX^2::ITR^0", app1))
							.click();
					Thread.sleep(11000);

					log.info("START WAIT END OF FARMING.");
					for (int k = 0; k < 35; k++) {
						if ((new GenieDisplayObject("SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0", app1))
								.isPresent()) {
							(new GenieSprite("SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0", app1)).click();
							kolvo++;
							log.warn("HARVESTED!");
							log.info("END FARMING.");
							log.info("------------------------------------------------------------------------------------------------------------ COUNT - "
									+ kolvo);
							break;
						}
						Thread.sleep(250);
					}
					// break;
				} else {
					log.info("--TYPE NOT FISH!--");
					misRes++;
				}
			}

			if (misRes >= childs.length) {
				misRes = 0;
				switch (route) {
				// SOUTH
				case 1: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1))
							.click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
					route = 2;
					break;
				}
				// NORD
				// case 2: {
				// (new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0",
				// app1)).click();
				// forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^0::ITR^0";
				// route = 3;
				// break;
				// }
				// EAST
				case 2: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0", app1))
							.click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^1::ITR^0";
					route = 1;
					break;
				}
				// WEST
				// case 2: {
				// (new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^7::PTR^0::IX^0::ITR^0",
				// app1)).click();
				// forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^2::ITR^0";
				// route = 1;
				// break;
				// }
				}
			}

		}
	}
}

// for (int i = 0; i < 100000; i++) {
// for (j = tempJ; j <= 21;) {
// selectorFish = "";
// selectorFish =
// "SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^" + j;
// try {
// System.out.println(" - " + j + " - ");
// System.out.println("Start IF");
// if ((new GenieDisplayObject(selectorFish, app1)).isPresent()) {
// System.out.println("Start 2nt IF");
// if ((new GenieDisplayObject(selectorFish,
// app1)).getValueOf("type").equals("fish_blue_r_fla::sensor_4")) {
// System.out.println("Click on fish");
// (new GenieDisplayObject(selectorFish, app1)).click();
// (new
// GenieSprite("SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^2::PTR^0::IX^2::ITR^0",
// app1)).click();
// Thread.sleep(25000);
// for (int k = 0; k < 50; k++) {
// if ((new
// GenieDisplayObject("SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0",
// app1)).isPresent()) {
// (new
// GenieSprite("SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0",
// app1)).click();
// Kolvo++;
// System.out.println("Kolvo - " + Kolvo);
// break;
// }
// Thread.sleep(100);
// }
//
// tempJ = j + 1;
// break;
// }
// }
// if (j >= 19) {
// flagSmen++;
// break;
// }
// } catch (Exception e) {
// System.out.println("Exception");
// }
// j++;
// }
// if (flagSmen > 0) {
// if (!south) {
// (new
// GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
// app1)).click();
// south = true;
// flagSmen = 0;
// tempJ = 0;
// } else {
// (new
// GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0",
// app1)).click();
// south = false;
// flagSmen = 0;
// tempJ = 0;
// }
// }
// }
// }
// }
