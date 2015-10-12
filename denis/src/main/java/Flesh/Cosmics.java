package Flesh;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class Cosmics {
	static List<WebElement> elements = new ArrayList<WebElement>();
	static ArrayList<String> textMsg = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://cosmics.net/");
		driver.manage().window().setSize(new Dimension(1200, 1000));
		Thread.sleep(1000);
		driver.findElement(By.id("edit-name")).sendKeys("denis13th@mail.ru");
		driver.findElement(By.id("edit-pass")).sendKeys("adept13a");
		driver.findElement(By.id("edit-submit")).click();
		Thread.sleep(4000);
		driver.switchTo().frame("chat");
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[onclick='chatToggleChannel(1);']")).click();
		checkAttack(driver);
		Thread.sleep(15000);
		driver.quit();
	}

	public static void checkAttack(WebDriver driver) throws InterruptedException {
		Thread.sleep(60000);
		driver.switchTo().frame("if1");
		elements = driver.findElements(By.cssSelector("[id^='msg']"));
		for (WebElement element : elements) {
			System.out.println(element.getText());
			if (element.getText().contains("На Вас совершено нападение")){
				
			}				
		}
	}
}
