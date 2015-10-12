package Flesh;

import org.apache.log4j.Logger;

import com.adobe.genie.executor.GenieLocatorInfo;
import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieComponent;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieSprite;
import com.adobe.genie.genieCom.SWFApp;

public class HerbHarvest extends GenieScript {
	private static final Logger log = Logger.getLogger(HerbHarvest.class);

	public HerbHarvest() throws Exception {
		super();
	}

	@Override
	public void start() throws Exception {
		int misRes = 0;
		String forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
		int route = 2;
		String nameObject = "";
		int kolvo = 0;
		boolean appConnect = true;

		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();

		log.warn("START GENERAL FOR");
		for (int i = 0; i < 10000; i++) {
			misRes = 0;

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

				new GenieSprite(gc.getGenieID(), app1).click();
				// gc.click();
				nameObject = new GenieDisplayObject("SP^base:::FP^frame_corner_TR:::SE^TextField::PX^8::PTR^0::IX^5::ITR^0", app1).getValueOf("text");
				log.info("START IF RESOURCE.");
				// if (nameObject.equals("Мухомор")) {
				if ((nameObject.equals("Рябинник")) || (nameObject.equals("Крапива"))) {

					log.info("START FARMING.");
					(new GenieSprite("SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^2::PTR^0::IX^2::ITR^0", app1)).click();

					for (int t = 0; t < 25; t++) {
						Thread.sleep(500);
						try {
							SWFApp app2 = connectToApp("fight");
							appConnect = false;
							break;
						} catch (Exception e) {
						}
					}
					if (!appConnect) {
						break;
					}

					log.info("START WAIT END OF FARMING.");
					for (int k = 0; k < 35; k++) {
						try {
							SWFApp app2 = connectToApp("fight");
							appConnect = false;
							break;
						} catch (Exception e) {
							if ((new GenieDisplayObject("SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0", app1)).isPresent()) {

								(new GenieSprite("SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0", app1)).click();
								kolvo++;
								log.warn("HARVESTED!");
								log.info("END FARMING.");
								log.info("------------------------------------------------------------------------------------------------------------ COUNT - "
										+ kolvo);
								break;
							}
						}

						Thread.sleep(250);
					}
					if (!appConnect) {
						break;
					}
					// break;
				} else {
					misRes++;
				}
			}
			if (!appConnect) {
				break;
			}

			if (misRes >= childs.length) {
				misRes = 0;
				switch (route) {
				case 1: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
					route = 2;
					break;
				}
				case 2: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0", app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^0::ITR^0";
					route = 3;
					break;
				}
				case 3: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0", app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^1::ITR^0";
					route = 4;
					break;
				}
				case 4: {
					(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^7::PTR^0::IX^0::ITR^0", app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^2::ITR^0";
					route = 1;
					break;
				}
				}
			}
		}
	}
}
