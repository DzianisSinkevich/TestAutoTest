package Flesh;

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
public class SouthClick3 extends GenieScript {

	public SouthClick3() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		int misRes = 0;
		String forChild = "SP^base:::FP^field:::SE^Sprite::PX^3::PTR^0::IX^1::ITR^0";
		int route = 2;
		String strGID = "";
		String nameObject = "";
		int kolvo = 0;
		boolean sensorPresent = false;

		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("[object init]");
		(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0", app1)).click();

		for (int i = 0; i < 10000; i++) {
			misRes = 0;

			GenieLocatorInfo info = new GenieLocatorInfo();
			info.propertyValueTable.put("numChildren", "1");
			GenieDisplayObject root = new GenieDisplayObject(forChild, app1);
			root.getValueOf("numChildren");
			GenieComponent[] childs = root.getChildren(info, true);

			for (GenieComponent gc : childs) {
				strGID = gc.getGenieID();
				sensorPresent = strGID.contains("sensor");
				if (sensorPresent) {
					gc.click();
					nameObject = new GenieDisplayObject("SP^base:::FP^frame_corner_TR:::SE^TextField::PX^8::PTR^0::IX^5::ITR^0",
							app1).getValueOf("text");
					if ((nameObject.equals("РљСЂРµРјРЅРёР№")) || (nameObject.equals("РђРІР°РЅС‚СЋСЂРёРЅ"))) {

						(new GenieSprite("SP^frame_corner_TL:::FP^frame_corner_TL_btn:::SE^Sprite::PX^3::PTR^0::IX^2::ITR^0",
								app1)).click();
						Thread.sleep(11000);

						for (int k = 0; k < 35; k++) {
							if ((new GenieDisplayObject("SP^base:::FP^farming_popup:::SE^TextField::PX^3::PTR^0::IX^0::ITR^0",
									app1)).isPresent()) {
								(new GenieSprite("SP^base:::FP^farming_popup:::SE^Sprite::PX^3::PTR^0::IX^3::ITR^0", app1))
										.click();
								kolvo++;
								System.out.println("Count - " + kolvo);
								break;
							}
							Thread.sleep(250);
						}
						break;
					} else {
						misRes++;
					}
				} else {
					misRes++;
				}

				if ((misRes + 1) >= ((childs.length) / 3)) {
					misRes = 0;
					switch (route) {
					case 1: {
						(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^8::PTR^0::IX^0::ITR^0",
								app1)).click();
						forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^3::ITR^0";
						route = 2;
						break;
					}
					case 2: {
						(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^5::PTR^0::IX^0::ITR^0",
								app1)).click();
						forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^0::ITR^0";
						route = 3;
						break;
					}
					case 3: {
						(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^6::PTR^0::IX^0::ITR^0",
								app1)).click();
						forChild = "FP^base:::SE^field:::CH^field::PX^0::PTR^0::IX^1::ITR^0";
						route = 4;
						break;
					}
					case 4: {
						(new GenieDisplayObject("SP^frame_bor_T:::FP^frame_bor_T_btn:::SE^map_hlight::PX^7::PTR^0::IX^0::ITR^0",
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
}
