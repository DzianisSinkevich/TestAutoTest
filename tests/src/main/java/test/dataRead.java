package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class dataRead {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		WebElement loginElement = driver.findElement(By
				.cssSelector(".js-login"));
		loginElement.sendKeys("autotest");

		WebElement element = driver.findElement(By
				.cssSelector("[type='password']"));
		element.sendKeys("autotest");

		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);

		readder(driver, "emul10");
		// readder(driver, "emul11");
		// readder(driver, "emul12");
		// readder(driver, "emul13");
		// readder(driver, "emul14");
		// readder(driver, "emul15");
		// readder(driver, "emul16");
		// readder(driver, "emul17");
		// readder(driver, "emul18");
		// readder(driver, "emul19");
		// readder(driver, "emul20");
		
	}

	public static void readder(WebDriver driver, String owner)
			throws InterruptedException {
		
		driver.get("http://qa.taxi.dancosoft.com/site/carReview");
		Thread.sleep(1000);
		WebElement searchElement = driver.findElement(By
				.cssSelector("[data-rv-source='sources.registrationNumbers']"));
		searchElement.sendKeys(owner);
		Thread.sleep(200);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(1000);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		WebElement columElement = driver.findElement(By
				.cssSelector("[id$='createdAt']"));
		columElement.click();
		columElement.click();

		Thread.sleep(1500);
		String textV = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@id='tab-reviews']/div[@class='content-bottom']/div[@class='content-outer offset-top']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0']"))
				.getText();
		System.out.println(owner + " " + textV);

		driver.quit();
	}
}
