package test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class GPSEmulator {
	public static void main(String[] args) throws InterruptedException {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");
		int deley = 1800;
		List<UserData> uD = new ArrayList<UserData>();
//		uD.add(new UserData("emul1", "12345", "23.760", "53.630"));
//		uD.add(new UserData("emul2", "12345", "23.770", "53.640"));
//		uD.add(new UserData("emul3", "12345", "23.780", "53.645"));
//		uD.add(new UserData("emul4", "12345", "23.790", "53.652"));
//		uD.add(new UserData("emul5", "12345", "23.800", "53.657"));
//		uD.add(new UserData("emul6", "12345", "23.810", "53.661"));
//		uD.add(new UserData("emul7", "12345", "23.820", "53.664"));
//		uD.add(new UserData("emul8", "12345", "23.830", "53.670"));
//		uD.add(new UserData("emul9", "12345", "23.840", "53.680"));
//		uD.add(new UserData("emul11", "12345", "23.805", "53.650"));
//		uD.add(new UserData("emul12", "12345", "23.815", "53.660"));
//		uD.add(new UserData("emul14", "12345", "23.820", "53.675"));
//		uD.add(new UserData("emul15", "123456798", "23.815", "53.685"));
//		uD.add(new UserData("emul16", "12345", "23.805", "53.690"));
//		uD.add(new UserData("emul17", "12345", "23.820", "53.683"));
//		uD.add(new UserData("emul18", "12345", "23.782", "53.693"));
//		uD.add(new UserData("emul19", "12345", "23.790", "53.680"));
//		uD.add(new UserData("emul20", "12345", "23.800", "53.680"));

		uD.add(new UserData("vod41", "123456798", "23.940", "53.670"));
		uD.add(new UserData("vod42", "123456798", "23.930", "53.680"));
		uD.add(new UserData("vod43", "123456798", "23.920", "53.675"));
		uD.add(new UserData("vod44", "123456798", "23.650", "53.685"));
		uD.add(new UserData("vod45", "123456798", "23.900", "53.690"));
		uD.add(new UserData("vod46", "123456798", "23.890", "53.683"));
		uD.add(new UserData("vod47", "123456798", "23.880", "53.493"));
		uD.add(new UserData("vod48", "123456798", "23.870", "53.680"));
		uD.add(new UserData("vod49", "123456798", "23.860", "53.680"));
		uD.add(new UserData("vod50", "123456798", "23.850", "53.670"));
//		uD.add(new UserData("vod51", "123456798", "23.840", "53.680"));
//		uD.add(new UserData("vod52", "123456798", "23.830", "53.675"));
//		uD.add(new UserData("vod53", "123456798", "23.820", "53.685"));
//		uD.add(new UserData("vod54", "123456798", "23.810", "53.680"));
//		uD.add(new UserData("vod55", "123456798", "23.820", "53.714"));
//		uD.add(new UserData("vod56", "123456798", "23.782", "53.717"));
//		uD.add(new UserData("vod57", "123456798", "24.090", "53.721"));
//		uD.add(new UserData("vod58", "123456798", "23.782", "53.725"));
//		uD.add(new UserData("vod59", "123456798", "23.790", "53.730"));
//		uD.add(new UserData("vod60", "123456798", "23.800", "53.700"));
//		uD.add(new UserData("vod61", "123456798", "23.800", "53.740"));

//		 uD.add(new UserData("emul11", "12345", "23.825", "53.670"));
//		 uD.add(new UserData("emul12", "12345", "23.826", "53.671"));
//		 uD.add(new UserData("emul14", "12345", "23.827", "53.672"));
//		 uD.add(new UserData("emul15", "123", "23.828", "53.673"));
//		 uD.add(new UserData("emul16", "12345", "23.829", "53.674"));
//		 uD.add(new UserData("emul17", "12345", "23.820", "53.675"));
//		 uD.add(new UserData("emul18", "12345", "23.824", "53.669"));
//		 uD.add(new UserData("emul19", "12345", "23.823", "53.668"));
//		 uD.add(new UserData("emul20", "12345", "23.822", "53.667"));

		WebDriver driver;
		for (UserData user : uD) {
			ChromeOptions option = new ChromeOptions();
			option.addArguments("--window-size=1300,1300");
			driver = new ChromeDriver(option);
			driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/gpsTest?empty=true");

			Thread.sleep(deley);

			WebElement loginElement = driver.findElement(By.cssSelector(".js-login"));
			loginElement.sendKeys(user.getLogin());

			WebElement element = driver.findElement(By.cssSelector("[type='password']"));
			element.sendKeys(user.getPass());

			WebElement lonElement = driver.findElement(By.id("lon"));
			lonElement.click();
			lonElement.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
			lonElement.sendKeys(user.getLon());

			WebElement latElement = driver.findElement(By.id("lat"));
			latElement.click();
			latElement.sendKeys(Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE, Keys.BACK_SPACE);
			latElement.sendKeys(user.getLat());

			WebElement submitElement = driver.findElement(By.cssSelector("button"));
			submitElement.click();
		}
		// WebElement element = driver.findElement(By.name("q"));
		//
		// element.sendKeys("Cheese!");
		//
		// element.submit();
		//
		// System.out.println("Page title is: " + driver.getTitle());
		//
		//
		// (new WebDriverWait(driver, 10)).until(new ExpectedCondition<Boolean>() {
		// public Boolean apply(WebDriver d) {
		// return d.getTitle().toLowerCase().startsWith("cheese!");
		// }
		// });
		//
		// System.out.println("Page title is: " + driver.getTitle());

		// DesiredCapabilities capability = DesiredCapabilities.chrome();
		// capability.setCapability("chrome.switches", Arrays.asList("–disable-extensions"));
		// capability.setCapability("chrome.binary", "C:/Users/Dzianis_sinkevich/AppData/Local/Google/Chrome/Application/chrome.exe");
		// ChromeOptions options = new ChromeOptions();
		// options.addArguments("user-data-dir=C:/Users/Dzianis_sinkevich/AppData/Local/Google/Chrome/User Data/Default");
		//
		// ChromeDriver driver= new ChromeDriver(capability);

		// driver.quit();
	}
}