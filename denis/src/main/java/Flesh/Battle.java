package Flesh;

import org.apache.log4j.Logger;
import org.seleniumhq.jetty7.util.log.Log;

import com.adobe.genie.executor.GenieScript;
import com.adobe.genie.executor.components.GenieDisplayObject;
import com.adobe.genie.genieCom.SWFApp;

public class Battle extends GenieScript {
	private static final Logger log = Logger.getLogger(StoneHarvest.class);

	public Battle() throws Exception {
		super();

	}

	@Override
	public void start() throws Exception {
		EXIT_ON_FAILURE = false;
		CAPTURE_SCREENSHOT_ON_FAILURE = false;

		SWFApp app1 = connectToApp("fight");
		log.info("START GENERAL WHILE");
		while (!(new GenieDisplayObject("SP^base:::FP^HPMP:::SE^TextField::PX^5::PTR^0::IX^4::ITR^0", app1).getValueOf("text")).substring(0, 1).equals("0")){
			log.info("BOT's HP IS NOT EQUAL 0. START IF");
			if ((new GenieDisplayObject("SP^cCenter:::FP^cCenter_attack:::SE^cCenter_attack_sector::PX^3::PTR^0::IX^9::ITR^0", app1).isPresent())){
				log.info("MY STEP");
				new GenieDisplayObject("SP^cCenter:::FP^cCenter_attack:::SE^cCenter_attack_sector::PX^3::PTR^0::IX^8::ITR^0", app1).click();
				Thread.sleep(2000);
			}
			Thread.sleep(200);
		}
		Log.warn("BOT IS DEATH");
		Thread.sleep(3000);
		
	}
}
