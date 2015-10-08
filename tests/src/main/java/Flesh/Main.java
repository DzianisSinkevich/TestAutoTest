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
		driver.manage().window().setSize(new Dimension(1200, 1000));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-name")).sendKeys("sidco93@mail.ru");
		driver.findElement(By.id("edit-pass")).sendKeys("adept13a");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(3000);
		doScript("OhotaMenuClick");
		Thread.sleep(4000);
		doScript("SouthClick");
		Thread.sleep(2000);

		for (int i = 0; i <= 20; i++) {
		}

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
//		String path = "F:\\JAVA\\GIT\\tests\\target\\classes\\Flesh\\";
		String path = "D:\\GIT\\TestAutoTest\\tests\\target\\classes\\Flesh\\";
		GenieScriptsExecutor.ExecuteScript(path + className + ".class");
	}
}
