package test.Cars.Fleet;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class AddDeleteCars {

	static String registrationNumber1;
	static String modelCars1;
	static String cityCars1;
	static String yearCars1;
	static String kindCars1;
	static String brandsCars1;
	static String colorCars1;

	static String registrationNumber2;
	static String modelCars2;
	static String cityCars2;
	static String yearCars2;
	static String kindCars2;
	static String brandsCars2;
	static String colorCars2;

	static List<String> modelCars = new ArrayList<String>();

	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<WebElement> errorNotify = new ArrayList<WebElement>();

	public static void main(String[] args) throws InterruptedException {

		modelCars.add("model0");
		modelCars.add("model1");
		modelCars.add("model2");
		modelCars.add("model3");
		modelCars.add("model4");

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
		// открываем страницу Машины - Парк Авто
		openCars(driver);
		addNew(driver);
		checkAddedCars(driver);
		searchCar(driver, registrationNumber1, cityCars1);
		checkDataMapping(driver);
		deleteCar(driver);
		searchCar(driver, registrationNumber1, cityCars1);
	}

	// открытие страницы Сотрудники - Сотрудники
	public static void openCars(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/cars");
		Thread.sleep(1500);
		driver.findElement(By.cssSelector("[data-target='.tab-cars']")).click();
		Thread.sleep(5000);
	}

	// добавление нового т/с
	public static void addNew(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[class='btn-success js-btn-add btn']")).click();
		Thread.sleep(1500);

		// вводим регистрационный номер
		driver.findElement(By.cssSelector("[data-rv-value='model.registrationNumber']")).sendKeys(
				Integer.toString(100000 + (int) (Math.random() * 100000)));
		registrationNumber1 = driver.findElement(By.cssSelector("[data-rv-value='model.registrationNumber']")).getAttribute("value");
		// вводим модель
		driver.findElement(By.cssSelector("[data-rv-value='model.model']")).sendKeys(modelCars.get((int) (Math.random() * 5)));
		modelCars1 = driver.findElement(By.cssSelector("[data-rv-value='model.model']")).getAttribute("value");
		// выбираем город работы
		driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).click();
		Thread.sleep(1000);
		for (int i = 0; i < (1 + (int) (Math.random() * 5)); i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ENTER);
		cityCars1 = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");
		// вводим год т/с
		driver.findElement(By.cssSelector("[data-rv-source='sources.years']")).sendKeys(Integer.toString(1920 + (int) (Math.random() * 95)));
		yearCars1 = driver.findElement(By.cssSelector("[data-rv-source='sources.years']")).getAttribute("value");
		// выбираем тип т/с
		driver.findElement(By.cssSelector("[data-rv-key='model.mainCarKindId']")).click();
		Thread.sleep(1000);
		for (int i = 0; i < (1 + (int) (Math.random() * 8)); i++) {
			driver.findElement(By.cssSelector("[data-rv-key='model.mainCarKindId']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carKinds']")).sendKeys(Keys.ENTER);
		kindCars1 = driver.findElement(By.cssSelector("[data-rv-source='sources.carKinds']")).getAttribute("value");
		// выбираем марку
		driver.findElement(By.cssSelector("[data-rv-source='sources.carBrands']")).click();
		Thread.sleep(1000);
		for (int i = 0; i < (1 + (int) (Math.random() * 30)); i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.carBrands']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carBrands']")).sendKeys(Keys.ENTER);
		brandsCars1 = driver.findElement(By.cssSelector("[data-rv-source='sources.carBrands']")).getAttribute("value");
		// выбираем цвет
		driver.findElement(By.cssSelector("[data-rv-source='sources.carColors']")).click();
		Thread.sleep(1000);
		for (int i = 0; i < (1 + (int) (Math.random() * 30)); i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.carColors']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carColors']")).sendKeys(Keys.ENTER);
		colorCars1 = driver.findElement(By.cssSelector("[data-rv-source='sources.carColors']")).getAttribute("value");

		// выбираем доп тип т/с
		driver.findElement(By.cssSelector("[data-rv-key-list='model.carKindsIds']")).click();
		Thread.sleep(1000);
		for (int i = 0; i < 9; i++) {
			driver.findElement(By.cssSelector("[data-rv-key-list='model.carKindsIds']")).sendKeys(Keys.ARROW_DOWN);
			if (((int) (Math.random() * 4)) == 1) {
				driver.findElement(By.cssSelector("[data-rv-key-list='model.carKindsIds']")).sendKeys(Keys.SPACE);
			}
		}
		driver.findElement(By.cssSelector("[data-rv-key-list='model.carKindsIds']")).sendKeys(Keys.ENTER);

		// выбираем опции
		driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).click();
		Thread.sleep(1000);
		for (int i = 0; i <= 9; i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.ARROW_DOWN);
			if (((int) (Math.random() * 4)) == 1) {
				driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.SPACE);
			}
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.ENTER);

		// нажимаем кнопку "Сохранить"
		driver.findElement(By.cssSelector("[data-action='save']")).click();
	}

	// проверка добавления т/с
	public static boolean checkAddedCars(WebDriver driver) throws InterruptedException {
		boolean results = false;
		// 300 раз по 100 мс ждём на предмет появления нотификации об успешном
		// добавлении т/с либо нотификации об ошибке
		for (int i = 0; i < 300; i++) {
			Thread.sleep(100);
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Car is added.");
				results = true;
				return results;
			}
			errorNotify = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-danger ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Car is not added.");
				results = false;
				return results;
			}
		}
		results = false;
		return results;
	}

	// поиск машины по регистрационному номеру
	public static void searchCar(WebDriver driver, String callsign, String city) throws InterruptedException {
		openCars(driver);
		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		Thread.sleep(500);
		if (city.equals("")) {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		} else {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(city, Keys.ENTER);
		}
		Thread.sleep(1500);
		// ставим фокус ввода в поле Регистрационный номер, вводим
		// регистарционный номер добавленного т/с, нажимаем Поиск
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-top']/div[@class='container-fluid']/div[@class='row offset-top-bot']/div[@class='col-xs-2 js-registrationNumber-filter']/input[@class='form-control']"))
				.click();
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-top']/div[@class='container-fluid']/div[@class='row offset-top-bot']/div[@class='col-xs-2 js-registrationNumber-filter']/input[@class='form-control']"))
				.sendKeys(registrationNumber1);
		driver.findElement(By.cssSelector("[data-action='search']")).click();

		Thread.sleep(500);
		boolean flag = true;
		String regNum1 = "";
		String regNum2 = "";
		String checkNum = "";

		Thread.sleep(2500);

		// заносим в массив все элементы из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5']")))
					.click().perform();

			Thread.sleep(200);

			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				// читаем позывной в выделенной строке
				regNum2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (regNum1.equals(regNum2)) {
					break;
				}
				regNum1 = regNum2;
			}

			// жмём стрелку вниз на клавиатуре, пока не достигнем низа таблицы
			while (flag == true) {
				Thread.sleep(5);

				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				checkNum = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				regNum2 = checkNum;
				// сравниваем считанный позывной на текущем шаге с позывным
				// добавленного пользователя. Если совпадают - выводим сообщение
				// и выходим из while
				if (registrationNumber1.equals(checkNum)) {
					System.out.println("Sought-for car is found.");
					break;
				}
				// System.out.println("insidePhone2 - " + insidePhone2);
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (regNum1.equals(regNum2)) {
					System.out.println("Sought-for car is not found.");
					break;
				}

				regNum1 = regNum2;
			}
		} else {
			System.out.println("Table is empty.");
		}

	}

	// проверка соответствия данных машины при создании и после сохранения и
	// рефреша страницы
	public static void checkDataMapping(WebDriver driver) throws InterruptedException {
		searchCar(driver, registrationNumber1, cityCars1);

		// читаем регистрационный номер
		registrationNumber2 = driver.findElement(By.cssSelector("[data-rv-value='model.registrationNumber']")).getAttribute("value");
		// читаем модель
		modelCars2 = driver.findElement(By.cssSelector("[data-rv-value='model.model']")).getAttribute("value");
		// читаем город
		cityCars2 = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");
		// читаем год
		yearCars2 = driver.findElement(By.cssSelector("[data-rv-source='sources.years']")).getAttribute("value");
		// читаем тип т/с
		kindCars2 = driver.findElement(By.cssSelector("[data-rv-key='model.mainCarKindId']")).getAttribute("value");
		// читаем марку
		brandsCars2 = driver.findElement(By.cssSelector("[data-rv-source='sources.carBrands']")).getAttribute("value");
		// читаем цвет
		colorCars2 = driver.findElement(By.cssSelector("[data-rv-source='sources.carColors']")).getAttribute("value");

		if (!registrationNumber1.equals(registrationNumber2)) {
			System.out.println("ERROR in registrationNumber");
		} else {
			System.out.println("RegistrationNumber - good");
		}
		if (!modelCars1.equals(modelCars2)) {
			System.out.println("ERROR in modelCars");
		} else {
			System.out.println("ModelCars - good");
		}
		if (!cityCars1.equals(cityCars2)) {
			System.out.println("ERROR in cityCars");
		} else {
			System.out.println("CityCars - good");
		}
		if (!yearCars1.equals(yearCars2)) {
			System.out.println("ERROR in yearCars");
		} else {
			System.out.println("YearCars - good");
		}
		if (!kindCars1.equals(kindCars2)) {
			System.out.println("ERROR in kindCars");
		} else {
			System.out.println("KindCars - good");
		}
		if (!brandsCars1.equals(brandsCars2)) {
			System.out.println("ERROR in brandsCars");
		} else {
			System.out.println("BrandsCars - good");
		}
		if (!colorCars1.equals(colorCars2)) {
			System.out.println("ERROR in colorCars");
		} else {
			System.out.println("ColorCars - good");
		}
	}

	// удаление созданного т/с
	public static void deleteCar(WebDriver driver) throws InterruptedException {
		openCars(driver);

		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(500);

		// ставим фокус ввода в поле Регистрационный номер, вводим
		// регистарционный номер добавленного т/с, нажимаем Поиск
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-top']/div[@class='container-fluid']/div[@class='row offset-top-bot']/div[@class='col-xs-2 js-registrationNumber-filter']/input[@class='form-control']"))
				.click();
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-top']/div[@class='container-fluid']/div[@class='row offset-top-bot']/div[@class='col-xs-2 js-registrationNumber-filter']/input[@class='form-control']"))
				.sendKeys(registrationNumber1);
		driver.findElement(By.cssSelector("[data-action='search']")).click();

		// сортируем таблицу по полю Водитель 2 раза
		driver.findElement(By.cssSelector("[id$='userDriver']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[id$='userDriver']")).click();
		Thread.sleep(500);

		// нажимаем на первый элемент таблицы
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@class='content-outer tab-pane active tab-cars']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5']"))
				.click();

		Thread.sleep(1000);

		// нажимаем кнопку Удалить
		driver.findElement(By.cssSelector("[data-action='delete']")).click();
		Thread.sleep(500);
		// нажимаем на кнопку Применить в появившемся диалоговом окне
		driver.findElement(
				By.xpath("/html/body[@class='modal-open']/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary']"))
				.click();
		// ждём 30 раз по 100 мс появления либо очередного диалогового окна либо
		// нотификацию об успешном удалении
		for (int j = 0; j < 30; j++) {
			// заносим в массив все элементы-нотификации
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
			// если массив не пустой(нотификация об удалении юзера появилась) -
			// выводим сообщение об успешном удалении юзера и выходим из цикла
			// For
			if (elements.size() > 0) {
				System.out.println("Car successfully removed.");
				break;
			}
		}
		if (elements.size() == 0) {
			System.out.println("Car is not removed.");
		}
	}
}
