package com.danco.botva;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class Utils {

	public WebDriver createDriver() {
		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// ������ ����� ��� ������������, ��� ��������� ������� ��������
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1250,950");
		return new ChromeDriver(option);
	}

	public void login(WebDriver driver, String log, String psw, Integer serv) throws InterruptedException {
		// ��������� �������� ������
		driver.get("http://www.botva.ru/");

		Thread.sleep(1500);
		driver.findElement(By.name("server")).click();
		for (int i = 0; i < serv - 1; i++) {
			driver.findElement(By.name("server")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.name("server")).sendKeys(Keys.ENTER);

		// ������ �����
		driver.findElement(By.name("email")).sendKeys(log);

		// ������ ������
		driver.findElement(By.name("password")).sendKeys(psw);
		// �������� ������ �����
		driver.findElement(By.cssSelector("[type='submit']")).click();

		Thread.sleep(3000);
	}
}