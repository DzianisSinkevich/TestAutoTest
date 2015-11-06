package Flesh;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;

public class BattleMain {
	static List<WebElement> elements = new ArrayList<WebElement>();

	public static void main(String[] args) throws Exception {

		WebDriver driver = new FirefoxDriver();

		driver.get("http://cosmics.net/");
		driver.manage().window().setSize(new Dimension(1200, 600));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-name")).sendKeys("sidco92@mail.ru");
		driver.findElement(By.id("edit-pass")).sendKeys("adept13a");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(4000);
		driver.switchTo().frame("chat");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[onclick='chatToggleChannel(1);']"))
				.click();
		driver.switchTo().defaultContent();
		Thread.sleep(15000);
		doScript("TestScrypt");
		checkAttack(driver);
		System.out.println("END");

		Thread.sleep(15000);
		driver.quit();
	}

	private static void doScript(String className) {
		String path = "F:\\JAVA\\GIT\\Denis\\target\\classes\\Flesh\\";
		// String path =
		// "D:\\GIT\\TestAutoTest\\Denis\\target\\classes\\Flesh\\";
		GenieScriptsExecutor.ExecuteScript(path + className + ".class");
	}

	public static void checkAttack(WebDriver driver)
			throws InterruptedException {
		Thread.sleep(500);
		driver.switchTo().frame("chat");
		driver.switchTo().frame("if1");
		elements = driver.findElements(By.cssSelector("[id^='msg']"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
			if (element.getText().contains("Вы совершили нападение на ")) {
				doScript("Battle");
				break;
			}
		}
		driver.switchTo().defaultContent();
		System.out.println("Check attack end.");
	}
}
