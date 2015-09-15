package test.Employee;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class EmployeeFilterCity {

	static String tempStr = "";
	static int bracketPozition;

	static List<WebElement> elements = new ArrayList<WebElement>();
	static String cityInField = "";
	static List<String> cityInTable = new ArrayList<String>();

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

		openEmployee(driver);

		selectCity(driver, 1);
		sortingCity(driver);
		checkCityInTable(driver, true);
		openEmployee(driver);
		// selectCity(driver, 2);
		// checkCityInTable(driver, false);

		for (int i = 0; i < cityInTable.size(); i++) {
			selectCityName(driver, cityInTable.get(i));
			checkCityInTable(driver, false);
		}

		for (int i = 2; i < 6; i++) {
			selectCity(driver, i);
			checkCityInTable(driver, false);
		}

	}

	// открытие страницы Сотрудники - Сотрудники
	public static void openEmployee(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/employee");
		Thread.sleep(1500);
	}

	// сортировка по полю Город
	public static void sortingCity(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[id$='city']")).click();
		Thread.sleep(2500);

	}

	// выбор города с помощью клавиатуры
	public static void selectCity(WebDriver driver, Integer number) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.DELETE);

		for (int i = 0; i < number; i++) {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ENTER);
		Thread.sleep(1500);
	}

	// выбор города вводом его названия
	public static void selectCityName(WebDriver driver, String cityName) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		Thread.sleep(200);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.DELETE);
		Thread.sleep(200);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(cityName);
		Thread.sleep(200);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ENTER);
	}

	public static void checkCityInTable(WebDriver driver, boolean all) throws InterruptedException {
		Thread.sleep(500);
		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";
		String city1 = "";
		String city2 = "";

		Thread.sleep(2500);

		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]"));

		if (elements.size() != 0) {

			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2'][1]")))
					.click().perform();

			Thread.sleep(200);
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}
				insidePhone1 = insidePhone2;
			}

			insidePhone1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();
			city1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8 selected']"))
					.getText();
			cityInTable.add(city1);

			while (flag == true) {
				Thread.sleep(5);

				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				city2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8 selected']"))
						.getText();
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();

				if (!city2.equals(city1)) {
					if (all == true) {
						cityInTable.add(city2);
						System.out.println(cityInTable);
					} else
						System.out.println("ERROR - In list of select city present other city.");
				}

				// System.out.println("insidePhone2 - " + insidePhone2);
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}

				city1 = city2;
				insidePhone1 = insidePhone2;
			}
			// System.out.println(cityInTable);
		}
	}

	// проверка, соответствует ли кол-во сотрудников в таблице и в заголовке
	// таблицы нулю
	public static void checkAllAmountEmployee(WebDriver driver) throws InterruptedException {
		if (checkAmmountEmployee(driver, 0) == 0 && valueAmmountEmployee(driver, 0) == 0) {
			System.out.println("rigth - check all ammount employee");
		} else {
			System.out.println("ERROR - check all ammount employee");
		}
	}

	// считывание кол-ва сотрудников в таблице из заголовка таблицы
	public static int checkAmmountEmployee(WebDriver driver, int ammount) throws InterruptedException {
		Thread.sleep(500);
		ammount = Integer.parseInt(driver.findElement(By.cssSelector("[class='badge pull-left js-employee-grid-count']")).getText());
		return ammount;
	}

	// подсчёт кол-ва сотрудников в таблице
	public static int valueAmmountEmployee(WebDriver driver, int ammount) throws InterruptedException {
		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";

		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]"));

		if (elements.size() != 0) {

			// выбор первого сотрудника в таблице
			Thread.sleep(2500);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]")))
					.click().sendKeys(Keys.ARROW_UP, Keys.ARROW_UP).perform();
			// считывание позывного выбранного сотрудника
			insidePhone1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();
			while (flag == true) {
				// увеличиваем число сотрудников на 1
				ammount++;
				Thread.sleep(5);
				// переходим на строку вниз клавишей Arrow Down
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				// считываем позывной выбранного водителя
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				// System.out.println("insidePhone2 - " + insidePhone2);

				// проверям совпадение предыдущего и текузего считанного
				// позывного
				// для определения конца таблицы
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}

				insidePhone1 = insidePhone2;

			}
		}
		return ammount;
	}
}
