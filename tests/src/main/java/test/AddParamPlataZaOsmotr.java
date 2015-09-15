package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.WebDriverWait;

public class AddParamPlataZaOsmotr {

	public static int summmaZaOsmotr;

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1300,1000");
		WebDriver driver = new ChromeDriver(option);
		WebDriverWait wait = new WebDriverWait(driver, 10);

		openOptionsPage(driver, wait);
	}

	public static void openOptionsPage(WebDriver driver, WebDriverWait wait) throws InterruptedException {

		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		WebElement loginElement = driver.findElement(By.cssSelector(".js-login"));
		loginElement.sendKeys("autotest");

		WebElement element = driver.findElement(By.cssSelector("[type='password']"));
		element.sendKeys("autotest");

		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);
		driver.get("http://qa.taxi.dancosoft.com/site/settings");

		selectCity(driver);
		addParam(driver);
		Thread.sleep(10000);

		driver.close();
	}

	public static void selectCity(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("[data-rv-source='sources.citiesEnabled']")).click();

		WebElement element = driver.findElement(By.cssSelector("[data-rv-source='sources.citiesEnabled']"));
		element.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.SPACE);

	}

	public static void addParam(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[data-action='addSetting']")).click();
		Thread.sleep(500);
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='content-outer']/div[@class='content-top js-setting-panel']/div[@class='container-fluid']/div[@class='block offset-top-bot']/div[@class='row'][2]/div[@class='col-xs-4'][2]/div[@class='input-group has-error']/input[@class='js-configuration-input form-control-chevron js-smart-dropdown form-control']"))
				.sendKeys("Настройки осмотров");
		WebElement element = driver
				.findElement(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='content-outer']/div[@class='content-top js-setting-panel']/div[@class='container-fluid']/div[@class='block offset-top-bot']/div[@class='row'][2]/div[@class='col-xs-4'][2]/div[@class='input-group has-error']/input[@class='js-configuration-input form-control-chevron js-smart-dropdown form-control']"));
		element.sendKeys(Keys.ENTER);
		Thread.sleep(3000);

		driver.findElement(By.cssSelector("[data-rv-source='sources.keys']")).sendKeys("Плата водителя за осмотр");
		element = driver.findElement(By.cssSelector("[data-rv-source='sources.keys']"));
		Thread.sleep(500);
		element.sendKeys(Keys.ENTER);
		Thread.sleep(500);

		int Rating = (int) (Math.random() * 10);
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='content-outer']/div[@class='content-top js-setting-panel']/div[@class='container-fluid']/div[3]/div[2]/div[@class='block'][1]/div[@class='row offset-top']/div[@class='col-xs-6']/div[@class='has-error']/input[@class='form-control']"))
				.sendKeys("" + Rating * 1000);
		summmaZaOsmotr = Rating * 1000;

		driver.findElement(By.cssSelector("[data-rv-checked='setting.startAtNow']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-action='saveSettingReal']")).click();
	}
}
