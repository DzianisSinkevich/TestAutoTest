package Flesh;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class Main {

	static List<WebElement> elements = new ArrayList<WebElement>();

	public static void main(String[] args) throws Exception {
		WebDriver driver = new FirefoxDriver();

		driver.get("http://cosmics.net/");
		Thread.sleep(5000);
		driver.findElement(By.id("edit-name")).sendKeys("sidco93@mail.ru");
		driver.findElement(By.id("edit-pass")).sendKeys("adept13a");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(8000);
		doScript("OhotaMenuClick");
		Thread.sleep(7000);
		doScript("SouthClick");
		Thread.sleep(3000);

		for (int i = 0; i <= 20; i++) {
			// Thread.sleep(100);
			// elements = driver
			// .findElements(By
			// .id("SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^"
			// + i + ""));
			// if (elements.size() > 0) {
			// System.out.println("" + i + " is found");
			// } else {
			// System.out.println("" + i + " not found");
			// }
			System.out
					.println(isElementPresent(
							driver,
							By.id("SP^field_object:::FP^obj:::SE^sensor::PX^0::PTR^0::IX^1::ITR^"
									+ i + "")));
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
		String path = "F:\\JAVA\\GIT\\tests\\target\\classes\\Flesh\\";
		GenieScriptsExecutor.ExecuteScript(path + className + ".class");
	}
}
