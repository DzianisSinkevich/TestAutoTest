package test.Employee;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class AddDeleteEmployee {

	static String callsign = "";
	static String jobCity = "";

	static int dayBithday;
	static int monatBithday;
	static int yearBithday;

	static int dayOfMonatInt;
	static String dayOfMonatStr;

	static int monatInt;
	static String monatStr;

	static Calendar calendar = Calendar.getInstance();

	static List<String> тameAddEmployee = new ArrayList<String>();
	static List<String> secondNameAddEmployee = new ArrayList<String>();
	static List<String> patronymicAddEmployee = new ArrayList<String>();
	static List<String> cityAddEmployee = new ArrayList<String>();
	static List<String> streetAddEmployee = new ArrayList<String>();
	static List<String> mobileAddEmployee = new ArrayList<String>();
	static List<String> mobile2AddEmployee = new ArrayList<String>();
	static List<String> homePhoneAddEmployee = new ArrayList<String>();

	static String nameMapping1;
	static String secondNameMapping1;
	static String patronymicMapping1;
	static String cityMapping1;
	static String streetMapping1;
	static String homeMapping1;
	static String apartmentMapping1;
	static String mobile1Mapping1;
	static String mobile2Mapping1;
	static String homePhoneMapping1;
	static String roleMapping1;
	static String cityJobMapping1;
	static String dataBrMapping1;
	static String dataAtMapping1;
	static String callsignMapping1;

	static String nameMapping2;
	static String secondNameMapping2;
	static String patronymicMapping2;
	static String cityMapping2;
	static String streetMapping2;
	static String homeMapping2;
	static String apartmentMapping2;
	static String mobile1Mapping2;
	static String mobile2Mapping2;
	static String homePhoneMapping2;
	static String roleMapping2;
	static String cityJobMapping2;
	static String dataBrMapping2;
	static String dataAtMapping2;
	static String callsignMapping2;

	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<WebElement> errorNotify = new ArrayList<WebElement>();

	public static void main(String[] args) throws InterruptedException {

		тameAddEmployee.add("Name0");
		тameAddEmployee.add("Name1");
		тameAddEmployee.add("Name2");
		тameAddEmployee.add("Name3");
		тameAddEmployee.add("Name4");

		secondNameAddEmployee.add("SecondName0");
		secondNameAddEmployee.add("SecondName1");
		secondNameAddEmployee.add("SecondName2");
		secondNameAddEmployee.add("SecondName3");
		secondNameAddEmployee.add("SecondName4");

		patronymicAddEmployee.add("Patronymic0");
		patronymicAddEmployee.add("Patronymic1");
		patronymicAddEmployee.add("Patronymic2");
		patronymicAddEmployee.add("Patronymic3");
		patronymicAddEmployee.add("Patronymic4");

		cityAddEmployee.add("City0");
		cityAddEmployee.add("City1");
		cityAddEmployee.add("City2");
		cityAddEmployee.add("City3");
		cityAddEmployee.add("City4");

		streetAddEmployee.add("Street0");
		streetAddEmployee.add("Street1");
		streetAddEmployee.add("Street2");
		streetAddEmployee.add("Street3");
		streetAddEmployee.add("Street4");

		mobileAddEmployee.add("445864574");
		mobileAddEmployee.add("+375445864574");
		mobileAddEmployee.add("80445864574");
		mobileAddEmployee.add("445864574");
		mobileAddEmployee.add("292849577");

		mobile2AddEmployee.add("445864574");
		mobile2AddEmployee.add("+375445864574");
		mobile2AddEmployee.add("80445864574");
		mobile2AddEmployee.add("445864574");
		mobile2AddEmployee.add("292849577");

		homePhoneAddEmployee.add("152690835");
		homePhoneAddEmployee.add("+375152690835");
		homePhoneAddEmployee.add("80152690835");

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
		// открываем страницу сотрудников
		openEmployee(driver);
		// добавлеяем нового сотрудника
		addNew(driver);
		// проверяем, добавлен ли сотрудник.
		if (checkAddedEmployee(driver)) {
			// Если добавлен, то проверяем работу
			// поиска по его позывному для города работы, для всех городов и
			// соотвтествие данных
			System.out.println("CheckAddedEmployee +");
			editEmployee(driver);
			// searchEmployee(driver, callsign, jobCity);
			// searchEmployee(driver, callsign, "");
			// checkDataMapping(driver);
			// удаляем сотрудника
			checkDataMapping(driver);
			deleteEmployee(driver, callsign);
			// проверяем наличие удалённого сотрудника в таблице
			// searchEmployee(driver, callsign, jobCity);
		} else {
			System.out.println("Employee is not added.");
		}
	}

	// открытие страницы Сотрудники - Сотрудники
	public static void openEmployee(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/employee");
		Thread.sleep(5000);
	}

	// добавление нового сотрудника
	public static void addNew(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[class='btn-success js-btn-add btn']")).click();
		Thread.sleep(1500);
		// вводим имя
		driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).sendKeys(тameAddEmployee.get((int) (Math.random() * 5)));
		nameMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).getAttribute("value");
		// вводим фамилию
		driver.findElement(By.cssSelector("[data-rv-value='model.firstName']")).sendKeys(secondNameAddEmployee.get((int) (Math.random() * 5)));
		secondNameMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.firstName']")).getAttribute("value");
		// вводим отчество
		driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).sendKeys(patronymicAddEmployee.get((int) (Math.random() * 5)));
		patronymicMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).getAttribute("value");
		// вводим пароль
		driver.findElement(By.cssSelector("[data-rv-value='model.password']")).sendKeys("123456798");
		// вводим подтверждение пароля
		driver.findElement(By.cssSelector("[data-rv-value='model.password2']")).sendKeys("123456798");
		// вводим город
		driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).sendKeys(cityAddEmployee.get((int) (Math.random() * 5)));
		cityMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).getAttribute("value");
		// вводим улицу
		driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']")).sendKeys(streetAddEmployee.get((int) (Math.random() * 5)));
		streetMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']")).getAttribute("value");
		// вводим дом
		driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).sendKeys(Integer.toString((int) (Math.random() * 30)));
		homeMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).getAttribute("value");
		// вводим квартиру
		driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']")).sendKeys(Integer.toString((int) (Math.random() * 300)));
		apartmentMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']")).getAttribute("value");
		// вводим мобильный 1
		driver.findElement(By.cssSelector("[data-rv-value='model.primaryPhone']")).sendKeys(mobileAddEmployee.get((int) (Math.random() * 5)));
		mobile1Mapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.primaryPhone']")).getAttribute("value");
		// вводим мобильный 2
		driver.findElement(By.cssSelector("[data-rv-value='model.secondaryPhone']")).sendKeys(mobile2AddEmployee.get((int) (Math.random() * 5)));
		mobile2Mapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.secondaryPhone']")).getAttribute("value");
		// вводим домашний телефон
		driver.findElement(By.cssSelector("[data-rv-value='model.homePhone']")).sendKeys(homePhoneAddEmployee.get((int) (Math.random() * 2)));
		homePhoneMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.homePhone']")).getAttribute("value");
		// выбираем роль
		driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).click();
		Thread.sleep(300);
		for (int i = 1; i < (2 + (int) (Math.random() * 5)); i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).sendKeys(Keys.ARROW_DOWN);
			;
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).sendKeys(Keys.ENTER);
		roleMapping1 = driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).getAttribute("value");
		// выбираем город работы
		driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).click();
		Thread.sleep(300);
		for (int i = 1; i < (2 + (int) (Math.random() * 5)); i++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ARROW_DOWN);
			;
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ENTER);
		cityJobMapping1 = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");

		// выбираем дату рождения
		dayBithday = (int) (Math.random() * 28);
		monatBithday = (int) (Math.random() * 12);
		yearBithday = 1980 + (int) (Math.random() * 34);
		if (dayBithday < 10) {
			dayOfMonatStr = "0" + Integer.toString(dayBithday);
		} else
			dayOfMonatStr = Integer.toString(dayBithday);
		if (monatBithday < 10) {
			monatStr = "0" + Integer.toString(monatBithday);
		} else
			monatStr = "0" + Integer.toString(monatBithday);
		driver.findElement(By.cssSelector("[data-rv-value='model.dateOfBirth']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='model.dateOfBirth']")).sendKeys(dayOfMonatStr + monatStr + Integer.toString(yearBithday),
				Keys.ENTER);
		dataBrMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.dateOfBirth']")).getAttribute("value");

		// выбираем дату приёма
		dayOfMonatInt = calendar.get(Calendar.DAY_OF_MONTH);
		if (dayOfMonatInt < 10) {
			dayOfMonatStr = "0" + Integer.toString(dayOfMonatInt);
		} else
			dayOfMonatStr = Integer.toString(dayOfMonatInt);

		monatInt = 1 + calendar.get(Calendar.MONTH);
		if (monatInt < 10) {
			monatStr = "0" + Integer.toString(monatInt);
		} else
			monatStr = Integer.toString(monatInt);

		driver.findElement(By.cssSelector("[data-rv-value='model.employedAt']")).sendKeys(
				dayOfMonatStr + monatStr + Integer.toString(calendar.get(Calendar.YEAR)));
		dataAtMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.employedAt']")).getAttribute("value");

		callsign = driver.findElement(By.cssSelector("[data-rv-source='sources.freeInsidePhones']")).getAttribute("value");
		callsignMapping1 = callsign;
		System.out.println("callsign - " + callsign);
		jobCity = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");
		System.out.println("jobCity - " + jobCity);

		// нажимаем кнопку "Сохранить"
		driver.findElement(By.cssSelector("[data-action='saveEmployee']")).click();

		// checkAddedEmployee(driver);
	}

	// поиск сотрудника по позывному
	public static void searchEmployee(WebDriver driver, String callsign, String city) throws InterruptedException {
		openEmployee(driver);
		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		Thread.sleep(500);
		if (city.equals("")) {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		} else {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(city, Keys.ENTER);
		}
		Thread.sleep(1500);
		// ставим фокус ввода в поле Позывной, вводим позывной добавленного
		// сотрудника, нажимаем Поиск
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).sendKeys(callsign);
		driver.findElement(By.cssSelector("[data-action='search']")).click();

		Thread.sleep(500);
		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";
		String callsign1 = "";

		Thread.sleep(2500);

		// заносим в массив все элементы из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2'][1]")))
					.click().perform();

			Thread.sleep(200);

			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				// читаем позывной в выделенной строке
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}
				insidePhone1 = insidePhone2;
			}

			// жмём стрелку вниз на клавиатуре, пока не достигнем низа таблицы
			while (flag == true) {
				Thread.sleep(5);

				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				callsign1 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();
				insidePhone2 = callsign1;
				// сравниваем считанный позывной на текущем шаге с позывным
				// добавленного пользователя. Если совпадают - выводим сообщение
				// и выходим из while
				if (callsign1.equals(callsign)) {
					System.out.println("Sought-for employee is found.");
					break;
				}
				// System.out.println("insidePhone2 - " + insidePhone2);
				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (insidePhone1.equals(insidePhone2)) {
					System.out.println("Sought-for employee is not found.");
					break;
				}

				insidePhone1 = insidePhone2;
			}
		}

	}

	// удаление созданного юзера
	public static void deleteEmployee(WebDriver driver, String callsign) throws InterruptedException {
		openEmployee(driver);

		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(500);

		// ставим фокус ввода в поле Позывной, вводим позывной добавленного
		// сотрудника, нажимаем Поиск
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).sendKeys(callsign);
		driver.findElement(By.cssSelector("[data-action='search']")).click();

		// сортируем таблицу по полю Роль 2 раза
		driver.findElement(By.cssSelector("[id$='roleString']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[id$='roleString']")).click();
		Thread.sleep(500);

		// нажимаем на первый элемент таблицы
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even']/div[@class='slick-cell l0 r0']"))
				.click();

		Thread.sleep(1000);

		// нажимаем кнопку Удалить
		driver.findElement(By.cssSelector("[data-action='removeUser']")).click();
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
				System.out.println("Employee successfully removed.");
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
	}

	// проверка добавления юзера
	public static boolean checkAddedEmployee(WebDriver driver) throws InterruptedException {
		boolean results = false;
		// 300 раз по 100 мс ждём на предмет появления нотификации об успешном
		// добавлении юзера либо нотификации об ошибке
		for (int i = 0; i < 300; i++) {
			Thread.sleep(100);
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Employee is added.");
				results = true;
				return results;
			}
			errorNotify = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-danger ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Employee is not added.");
				results = false;
				return results;
			}
		}
		results = false;
		return results;
	}

	// проверка соответствия данных юзера при создании и после сохранения и
	// рефреша страницы
	public static void checkDataMapping(WebDriver driver) throws InterruptedException {
		searchEmployee(driver, callsign, jobCity);

		// читаем имя
		nameMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).getAttribute("value");
		// читаем фамилию
		secondNameMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.firstName']")).getAttribute("value");
		// читаем отчество
		patronymicMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).getAttribute("value");
		// читаем город
		cityMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).getAttribute("value");
		// читаем улицу
		streetMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']")).getAttribute("value");
		// читаем дом
		homeMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).getAttribute("value");
		// читаем квартиру
		apartmentMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']")).getAttribute("value");
		// читаем мобильный 1
		mobile1Mapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.primaryPhone']")).getAttribute("value");
		// читаем мобильный 2
		mobile2Mapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.secondaryPhone']")).getAttribute("value");
		// читаем домашний телефон
		homePhoneMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.homePhone']")).getAttribute("value");
		// читаем роль
		roleMapping2 = driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).getAttribute("value");
		// читаем город работы
		cityJobMapping2 = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");
		// читаем дату рождения
		dataBrMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.dateOfBirth']")).getAttribute("value");
		// читаем дату приёма
		dataAtMapping2 = driver.findElement(By.cssSelector("[data-rv-value='model.employedAt']")).getAttribute("value");
		// читаем позывной
		callsignMapping2 = driver.findElement(By.cssSelector("[data-rv-source='sources.freeInsidePhones']")).getAttribute("value");

		if (!nameMapping1.equals(nameMapping2)) {
			System.out.println("ERROR in nameMapping");
		}
		if (!secondNameMapping1.equals(secondNameMapping2)) {
			System.out.println("ERROR in secondNameMapping");
		}
		if (!patronymicMapping1.equals(patronymicMapping2)) {
			System.out.println("ERROR in patronymicMapping2");
		}
		if (!cityMapping1.equals(cityMapping2)) {
			System.out.println("ERROR in cityMapping");
		}
		if (!streetMapping1.equals(streetMapping2)) {
			System.out.println("ERROR in streetMapping");
		}
		if (!homeMapping1.equals(homeMapping2)) {
			System.out.println("ERROR in homeMapping");
		}
		if (!apartmentMapping1.equals(apartmentMapping2)) {
			System.out.println("ERROR in apartmentMapping");
		}
		if (!mobile1Mapping1.equals(mobile1Mapping2)) {
			System.out.println("ERROR in mobile1Mapping");
		}
		if (!mobile2Mapping1.equals(mobile2Mapping2)) {
			System.out.println("ERROR in mobile2Mapping");
		}
		if (!roleMapping1.equals(roleMapping2)) {
			System.out.println("ERROR in roleMapping");
		}
		if (!cityJobMapping1.equals(cityJobMapping2)) {
			System.out.println("ERROR in cityJobMapping");
		}
		if (!dataBrMapping1.equals(dataBrMapping2)) {
			System.out.println("ERROR in dataBrMapping");
		}
		if (!dataAtMapping1.equals(dataAtMapping2)) {
			System.out.println("ERROR in dataAtMapping");
		}
		if (!callsignMapping1.equals(callsignMapping2)) {
			System.out.println("ERROR in callsignMapping1");
		}
	}

	// редактирование добавленного юзера
	public static void editEmployee(WebDriver driver) throws InterruptedException {
		openEmployee(driver);
		searchEmployee(driver, callsign, "");

		int forFor = (3 + (int) (Math.random() * 2));
		for (int i = 0; i < forFor; i++) {
			int forCase = (int) (Math.random() * 9);
			switch (forCase) {
			case 1: {
				// вводим имя
				driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).sendKeys(тameAddEmployee.get((int) (Math.random() * 5)));
				nameMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.lastName']")).getAttribute("value");
			}
			case 2: {
				// вводим фамилию
				driver.findElement(By.cssSelector("[data-rv-value='model.firstName']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.firstName']"))
						.sendKeys(secondNameAddEmployee.get((int) (Math.random() * 5)));
				secondNameMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.firstName']")).getAttribute("value");
			}
			case 3: {
				// вводим отчество
				driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).sendKeys(
						patronymicAddEmployee.get((int) (Math.random() * 5)));
				patronymicMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.middleName']")).getAttribute("value");
			}
			case 4: {
				// вводим город
				driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).sendKeys(cityAddEmployee.get((int) (Math.random() * 5)));
				cityMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressCity']")).getAttribute("value");
			}
			case 5: {
				// вводим улицу
				driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']"))
						.sendKeys(streetAddEmployee.get((int) (Math.random() * 5)));
				streetMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressStreet']")).getAttribute("value");
			}
			case 6: {
				// вводим дом
				driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).sendKeys(Integer.toString((int) (Math.random() * 30)));
				homeMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressHouse']")).getAttribute("value");
			}
			case 7: {
				// вводим квартиру
				driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']")).clear();
				driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']"))
						.sendKeys(Integer.toString((int) (Math.random() * 300)));
				apartmentMapping1 = driver.findElement(By.cssSelector("[data-rv-value='model.addressApartment']")).getAttribute("value");
			}
			case 8: {
				// выбираем роль
				driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).click();
				Thread.sleep(300);
				for (int j = 1; j < (2 + (int) (Math.random() * 5)); j++) {
					driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).sendKeys(Keys.ARROW_DOWN);
					;
				}
				driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).sendKeys(Keys.ENTER);
				roleMapping1 = driver.findElement(By.cssSelector("[data-rv-source='sources.userRoles']")).getAttribute("value");
			}
			case 9: {
				// выбираем город работы
				driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).click();
				Thread.sleep(300);
				for (int j = 1; j < (2 + (int) (Math.random() * 5)); j++) {
					driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ARROW_DOWN);
					;
				}
				driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).sendKeys(Keys.ENTER);
				cityJobMapping1 = driver.findElement(By.cssSelector("[data-rv-source='sources.cities']")).getAttribute("value");
			}
			}
		}
		// нажимаем кнопку "Сохранить"
		driver.findElement(By.cssSelector("[data-action='saveEmployee']")).click();
	}
}
