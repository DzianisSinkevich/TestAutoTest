package test.Cars.Fleet;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class Sorting {

	static String[] sortingStr = new String[] {};

	static List<String> notSorting = new ArrayList<String>();
	static List<String> sorting = new ArrayList<String>();

	static List<WebElement> elements = new ArrayList<WebElement>();

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

		openCars(driver);
		valueAmmountEmployee(driver);
		sortingArray(driver);

		openCars(driver);
		sortingCars(driver, "registrationNumber");
		valueAmmountEmployee(driver);
		sortingArray(driver);
	}

	// открытие страницы Сотрудники - Сотрудники
	public static void openCars(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/cars");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("[data-target='.tab-cars']")).click();
		Thread.sleep(5000);
	}

	// сортировка по столбцу
	public static void sortingCars(WebDriver driver, String column) throws InterruptedException {
		driver.findElement(By.cssSelector("[id$='" + column + "']")).click();
		Thread.sleep(500);
	}

	public static void valueAmmountEmployee(WebDriver driver) throws InterruptedException {
		boolean flag = true;
		String regNum1 = "";
		String regNum2 = "";
		String readEl = "";

		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8'][1]"));

		if (elements.size() != 0) {

			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l10 r10'][1]")))
					.click().perform();

			Thread.sleep(200);
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				regNum2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				if (regNum1.equals(regNum2)) {
					break;
				}
				regNum1 = regNum2;
			}

			regNum1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();
			readEl = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();
			notSorting.add(readEl);

			while (flag == true) {
				Thread.sleep(5);

				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				readEl = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				regNum2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();

				if (regNum1.equals(regNum2)) {
					break;
				}
				notSorting.add(readEl);

				regNum1 = regNum2;
			}
		}
	}

	// сортировка по столбцу
	public static void sortingArray(WebDriver driver) throws InterruptedException {
		sorting.clear();
		sorting = notSorting;
		Collections.sort(sorting);
		System.out.println(sorting);
	}
}
