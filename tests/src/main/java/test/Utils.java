package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {

	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// ������ ����� ��� ������������, ��� ��������� ������� ��������
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1300,1000");
		return new ChromeDriver(option);
	}

	public void login(WebDriver driver) throws InterruptedException {
		// ��������� �������� ������, �������� http - �����������
		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		// ������ �����
		WebElement loginElement = driver.findElement(By.cssSelector(".js-login"));
		loginElement.sendKeys("autotest");

		// ������ ������
		WebElement element = driver.findElement(By.cssSelector("[type='password']"));
		element.sendKeys("autotest");
		// �������� ������ �����
		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);
	}
}
