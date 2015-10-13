package Flesh;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	static List<WebElement> elements = new ArrayList<WebElement>();

	public static void main(String[] args) throws Exception {

		WebDriver driver = new FirefoxDriver();

		driver.get("http://cosmics.net/");
		driver.manage().window().setSize(new Dimension(1200, 950));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-name")).sendKeys("sidco93@mail.ru");
		driver.findElement(By.id("edit-pass")).sendKeys("adept13a");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(4000);
		driver.switchTo().frame("chat");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[onclick='chatToggleChannel(1);']")).click();
		driver.switchTo().defaultContent();

		// doScript("FishHarvest");
		for (int i = 0; i < 100; i++) {
			doScript("OhotaMenuClick");
			Thread.sleep(4000);
			doScript("FishHarvest");
			checkAttack(driver);
		}
		Thread.sleep(2000);

		Thread.sleep(15000);
		driver.quit();
	}

	public static boolean isElementPresent(WebDriver driver, By locator) {
		if (driver.findElements(locator).size() > 0) {
			return true;
		}
		return false;
	}

	private static void doScript(String className) {
		 String path = "F:\\JAVA\\GIT\\Denis\\target\\classes\\Flesh\\";
//		String path = "D:\\GIT\\TestAutoTest\\Denis\\target\\classes\\Flesh\\";
		// String path = "E:\\git\\TestAutoTest\\denis\\target\\classes\\Flesh\\";
		GenieScriptsExecutor.ExecuteScript(path + className + ".class");
	}

	public static void checkAttack(WebDriver driver) throws InterruptedException {
		driver.switchTo().frame("chat");
		driver.switchTo().frame("if1");
		elements = driver.findElements(By.cssSelector("[id^='msg']"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
			doScript("Battle");
			break;
		}
		driver.switchTo().defaultContent();
	}
}
