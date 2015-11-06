package Flesh;

import java.io.IOException;

import org.apache.log4j.Logger;

import com.adobe.genie.executor.GenieLocatorInfo;
import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieComponent;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.executor.components.GenieSprite;
import com.adobe.genie.genieCom.SWFApp;

public class TestScrypt extends GenieScript {
	private static final Logger log = Logger.getLogger(StoneHarvest.class);

	public TestScrypt() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int misRes = 0;
		String forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^0::ITR^0";
		int route = 3;
		boolean appConnect = true;
		String getGenieID = "";

		String nameObject = "";
		String nameObject1 = "";
		int kolvo = 0;

		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		(new GenieDisplayObject(
				"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0",
				app1)).click();

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
			try {
				SWFApp app2 = connectToApp("fight");
				appConnect = false;
				break;
			} catch (Exception e) {
			}

			log.warn("START FOR");
			for (int j = 0; j < childs.length; j++) {
				getGenieID = childs[j].getGenieID();
				if (j == 0) {
					try {
						SWFApp app2 = connectToApp("fight");
						appConnect = false;
						break;
					} catch (Exception e) {
					}
				}

				log.info(getGenieID);

				new GenieSprite(getGenieID, app1).click();
				nameObject = new GenieDisplayObject(
						"SP^base:::FP^frame_corner_TR:::SE^TextField::PX^8::PTR^0::IX^5::ITR^0",
						app1).getValueOf("text");
				log.info("START IF RESOURCE.");
				if ((nameObject.equals("Учебный робот"))) {

					log.info("START FIGHT.");
					(new GenieSprite(
							"SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^4::PTR^0::IX^2::ITR^0",
							app1)).click();

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
				} else {
					misRes++;
				}

				if (!appConnect) {
					break;
				}
			}
			if (!appConnect) {
				break;
			}
			if (misRes >= childs.length) {
				misRes = 0;
				switch (route) {
				// SOUTH
				case 1: {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
							app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
					route = 2;
					break;
				}
				// NORD
				case 2: {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0",
							app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^0::ITR^0";
					route = 3;
					break;
				}
				// EAST
				case 3: {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0",
							app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^1::ITR^0";
					route = 4;
					break;
				}
				// WEST
				case 4: {
					(new GenieDisplayObject(
							"SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^7::PTR^0::IX^0::ITR^0",
							app1)).click();
					forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^2::ITR^0";
					route = 1;
					break;
				}
				}

			}
		}
	}
}
