package test.Cars.Options;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class AddDeleteOptions {
	static boolean flag = true;

	static String optionsCars1;

	static List<String> optionsCars = new ArrayList<String>();

	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<WebElement> errorNotify = new ArrayList<WebElement>();

	public static void main(String[] args) throws InterruptedException {

		optionsCars.add("option0");
		optionsCars.add("option1");
		optionsCars.add("option2");
		optionsCars.add("option3");
		optionsCars.add("option4");

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
		checkAmmountBrand(driver);
		addNew(driver);
		if (checkAddedBrands(driver)) {
			deleteEmployee(driver, optionsCars1);
			checkAmmountBrand(driver);
		}
	}

	// открытие страницы Машины - Опции
	public static void openCars(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/cars");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("[data-target='.tab-options']")).click();
		Thread.sleep(2000);
	}

	// добавление новой марки
	public static void addNew(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='navbar navbar-inverse navbar-static-top toolbar']/div[@class='container-fluid']/div[@class='row']/div[@class='col-xs-11 tab-content']/div[@class='tab-pane tab-options active']/div[@class='navbar-form btn-group pull-left js-grid-buttons']/button[@class='btn-success js-btn-add btn']"))
				.click();
		Thread.sleep(1500);

		// вводим название марки
		driver.findElement(By.cssSelector("[data-rv-value$='model.name']")).sendKeys(optionsCars.get((int) (Math.random() * 5)));
		optionsCars1 = driver.findElement(By.cssSelector("[data-rv-value='model.name']")).getAttribute("value");
		driver.findElement(By.cssSelector("[data-rv-value='model.name']")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);

		// нажимаем кнопку "Сохранить"
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane tab-brands active']/div[2]/div[@class='block shift-top separator-top offset-bot']/div[@class='row']/div[@class='col-xs-3']/button[@class='btn-block btn-success btn']"))
				.click();
	}

	// проверка добавления марки
	public static boolean checkAddedBrands(WebDriver driver) throws InterruptedException {
		boolean results = false;
		// 300 раз по 100 мс ждём на предмет появления нотификации об успешном
		// добавлении марки либо нотификации об ошибке
		for (int i = 0; i < 300; i++) {
			Thread.sleep(100);
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Brands is added.");
				results = true;
				return results;
			}
			errorNotify = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-danger ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Brands is not added.");
				results = false;
				return results;
			}
		}
		results = false;
		return results;
	}

	// поиск добавленной марки в таблице
	public static void checkAmmountBrand(WebDriver driver) throws InterruptedException {
		openCars(driver);

		Thread.sleep(500);
		int ammount1 = 0;
		int ammount2 = 0;
		String brandName1 = "";
		String brandName2 = "";

		Thread.sleep(500);
		ammount1 = Integer
				.parseInt(driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-top']/div[@class='grid-toolbar']/div[@class='badge pull-left js-grid-count']"))
						.getText());

		// заносим в массив все строки марок из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0'][1]")))
					.click().perform();

			Thread.sleep(200);
			ammount2 = 1;
			elements.clear();
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"));
			if (elements.size() > 0) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
						.sendKeys(Keys.ARROW_UP).perform();
			} else {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
						.sendKeys(Keys.ARROW_UP).perform();
			}
			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				elements.clear();
				elements = driver
						.findElements(By
								.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
				if (elements.size() > 0) {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
							.sendKeys(Keys.ARROW_UP).perform();
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"))
							.getText();
				} else {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
							.sendKeys(Keys.ARROW_UP).perform();
					// читаем название марки в выделенной строке
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"))
							.getText();
				}

				// сравниваем считанные марки на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (brandName1.equals(brandName2)) {
					break;
				}
				brandName1 = brandName2;
			}
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
					.sendKeys(Keys.ARROW_DOWN).perform();
			// жмём стрелку вниз на клавиатуре, пока не достигнем низа таблицы
			while (flag == true) {
				Thread.sleep(5);
				elements.clear();
				elements = driver
						.findElements(By
								.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
				if (elements.size() > 0) {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
							.sendKeys(Keys.ARROW_DOWN).perform();
				} else {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
							.sendKeys(Keys.ARROW_DOWN).perform();
				}
				elements.clear();
				elements = driver
						.findElements(By
								.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
				if (elements.size() > 0) {
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"))
							.getText();
				} else {
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"))
							.getText();
				}
				ammount2++;
				// сравниваем считанные марки на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут

				if (brandName1.equals(optionsCars1)) {
					System.out.println("Sought-for employee is found.");
					break;
				}
				// System.out.println("insidePhone2 - " + insidePhone2);
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (brandName1.equals(brandName2)) {
					System.out.println("Sought-for brand is not found.");
					break;
				}
				brandName1 = brandName2;
			}
		}
		// if (ammount1 == ammount2) {
		// System.out.println("Brands ammount - " + ammount1);
		// } else {
		// System.out.println("Error in ammount brands.");
		// }
		if (ammount1 == ammount2) {
			System.out.println("Ammount OK");
		}
	}

	// удаление созданного т/с
	public static void deleteEmployee(WebDriver driver, String callsign) throws InterruptedException {
		openCars(driver);

		boolean brandIsDeletted = false;

		String brandName1 = "";
		String brandName2 = "";

		// заносим в массив все строки марок из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0'][1]")))
					.click().perform();

			Thread.sleep(200);
			elements.clear();
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"));
			if (elements.size() > 0) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
						.sendKeys(Keys.ARROW_UP).perform();
			} else {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
						.sendKeys(Keys.ARROW_UP).perform();
			}
			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag = true) {
				elements.clear();
				elements = driver
						.findElements(By
								.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
				if (elements.size() > 0) {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
							.sendKeys(Keys.ARROW_UP).perform();
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"))
							.getText();
				} else {
					new Actions(driver)
							.moveToElement(
									driver.findElement(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
							.sendKeys(Keys.ARROW_UP).perform();
					// читаем название марки в выделенной строке
					brandName2 = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"))
							.getText();
				}

				// сравниваем считанные марки на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (brandName1.equals(brandName2)) {
					break;
				}
				brandName1 = brandName2;
			}
		}
		// жмём стрелку вниз на клавиатуре, пока не достигнем низа таблицы
		while (flag == true) {
			Thread.sleep(5);

			if (brandName2.equals(optionsCars1)) {
				Thread.sleep(1000);

				// нажимаем кнопку Удалить
				driver.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='navbar navbar-inverse navbar-static-top toolbar']/div[@class='container-fluid']/div[@class='row']/div[@class='col-xs-11 tab-content']/div[@class='tab-pane tab-options active']/div[@class='navbar-form btn-group pull-left js-grid-buttons']/button[@class='btn-danger js-btn-del btn']"))
						.click();
				Thread.sleep(500);
				// нажимаем на кнопку Применить в появившемся диалоговом окне
				driver.findElement(
						By.xpath("/html/body[@class='modal-open']/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary']"))
						.click();
				// ждём 30 раз по 100 мс появления либо очередного диалогового
				// окна либо
				// нотификацию об успешном удалении
				for (int j = 0; j < 30; j++) {
					// заносим в массив все элементы-нотификации
					elements = driver
							.findElements(By
									.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
					// если массив не пустой(нотификация об удалении юзера
					// появилась) -
					// выводим сообщение об успешном удалении юзера и выходим из
					// цикла
					// For
					if (elements.size() > 0) {
						brandIsDeletted = true;
						System.out.println("Brand successfully removed.");
						break;
					}
					// если массив нотификаций пустой, то проверяем страницу на
					// появление диалогового окна
					else {
						for (int i = 0; i < 10; i++) {
							Thread.sleep(100);
							elements = driver
									.findElements(By
											.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='bootbox-modal-body']"));
							if (elements.size() > 0) {
								driver.findElement(
										By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary']"))
										.click();
							}
						}
					}
				}
				if (brandIsDeletted == true) {
					break;
				}

				// сравниваем считанные марки на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (brandName1.equals(optionsCars1)) {
					System.out.println("Sought-for employee is found.");
					break;
				}
				// System.out.println("insidePhone2 - " + insidePhone2);
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (brandName1.equals(brandName2)) {
					System.out.println("Sought-for brand is not found.");
					break;
				}
				brandName1 = brandName2;
			}

			elements.clear();
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
			if (elements.size() > 0) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
			} else {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
			}
			elements.clear();
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"));
			if (elements.size() > 0) {
				brandName2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected active']"))
						.getText();
			} else {
				brandName2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='offset-top content-outer tab-pane tab-options active']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 active selected']"))
						.getText();
			}
		}
	}

}
