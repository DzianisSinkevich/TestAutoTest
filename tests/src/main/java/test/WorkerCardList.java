package test;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class WorkerCardList {
	public static void main(String[] args) throws InterruptedException {

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// создаём опцию для хромдрайвера, где указываем размеры страницы
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1300,1000");
		WebDriver driver = new ChromeDriver(option);

		// открываем страницу логина, проходим http - авторизацию
		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		// вводим логин
		WebElement loginElement = driver.findElement(By.cssSelector(".js-login"));
		loginElement.sendKeys("autotest");

		// вводим пароль
		WebElement element = driver.findElement(By.cssSelector("[type='password']"));
		element.sendKeys("autotest");
		// нажимаем кнопку входа
		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);

		// driver.quit();
	}
}
