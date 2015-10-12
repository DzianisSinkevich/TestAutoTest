package test;

import org.openqa.selenium.By;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class Botva {

	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		WebDriver driver = new ChromeDriver();

		driver.get("http://www.botva.ru/");

		driver.manage().window().setSize(new Dimension(1200, 1000));
		Thread.sleep(1000);
		driver.findElement(By.name("email")).sendKeys("tarakan0va@mail.ru");;
		driver.findElement(By.name("password")).sendKeys("tarakan0va");
		driver.findElement(By.cssSelector("[type='submit']")).click();

		Thread.sleep(15000);
		driver.quit();
	}
}
