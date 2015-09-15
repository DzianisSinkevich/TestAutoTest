package test;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class WorkerCard {
	static ArrayList<String> post = new ArrayList<String>();

	// переменные для записи новых данных
	static int afterBalans = 0;
	static int afterInSaldo = 0;
	static int afterOutSaldo = 0;
	static int afterFinRes = 0;

	// переменные для записи старых данных
	static int beforeBalans = 0;
	static int beforeInSaldo = 0;
	static int beforeOutSaldo = 0;
	static int beforeFinRes = 0;

	// переменная для записи вводимой сумы
	static int inputSum = 0;

	// переменная для записи города работы сотрудника
	static String workCity = "";

	// переменная для записи роли сотрудника
	static String workerRole = "";

	// переменные для записи стоимости СМС
	static int costCMCForAll = 0;
	static int costCMCForCity = 0;
	static int costCMCFinal = 0;

	static List<WebElement> elements = new ArrayList<WebElement>();

	private static Logger LOG = Logger.getLogger(WorkerCard.class);

	public static void main(String[] args) throws InterruptedException {

		post.add("Ð?Ð´Ð¼Ð¸Ð½Ð¸Ñ?Ñ‚Ñ€Ð°Ñ‚Ð¾Ñ€");
		post.add("Ð‘ÑƒÑ…Ð³Ð°Ð»Ñ‚ÐµÑ€");
		post.add("Ð”Ð¸Ñ?Ð¿ÐµÑ‚Ñ‡ÐµÑ€");
		post.add("Ð’Ð¾Ð´Ð¸Ñ‚ÐµÐ»ÑŒ");
		post.add("Ð‘Ñ€Ð¸Ð³Ð°Ð´Ð¸Ñ€");

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

		allOperation(driver, "emul13");
		Thread.sleep(1000);

		// driver.quit();
	}

	// открытие карточки сотрудника по введённому позывному
	public static void openCard(WebDriver driver, String callsign) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/employeeCard");
		Thread.sleep(3000);
		// ввод позывного в поле поиска
		driver.findElement(By.cssSelector("[data-rv-source='sources.employees']")).sendKeys(callsign);
		Thread.sleep(3000);
		// нажатие клавиши Enter
		driver.findElement(By.cssSelector("[data-rv-source='sources.employees']")).sendKeys(Keys.ENTER);
		Thread.sleep(1000);
	}

	// чтение старых данных бухгалтерии
	public static void dataReadBefore(WebDriver driver) throws InterruptedException {
		Thread.sleep(2000);
		beforeBalans = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.balance']")).getAttribute("value"));
		beforeInSaldo = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.incomingBalance']")).getAttribute("value"));
		beforeOutSaldo = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.outgoingBalance']")).getAttribute("value"));
		beforeFinRes = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.financialResult']")).getAttribute("value"));
		printBalance(beforeBalans, beforeInSaldo, beforeOutSaldo, beforeFinRes, beforeFinRes, afterInSaldo, afterOutSaldo, afterFinRes, true);
	}

	// печать бухгалтерских данных
	private static void printBalance(int beforeBalans, int beforeInSaldo, int beforeOutSaldo, int beforeFinRes, int afterBalans, int afterInSaldo,
			int afterOutSaldo, int afterFinRes, boolean isBefore) {

		if (isBefore) {
			LOG.info("\nBefore balans: " + beforeBalans + "\nBefore InSaldo: " + beforeInSaldo + "\nBefore OutSaldo: " + beforeOutSaldo
					+ "\nBefore finance results: " + beforeFinRes);
		} else {
			LOG.info("\nAfter balans: " + afterBalans + "\nAfter InSaldo: " + afterInSaldo + "\nAfter OutSaldo: " + afterOutSaldo
					+ "\nAfter finance results: " + afterFinRes);
		}
	}

	// чтение новых данных бухгалтерии
	public static void dataReadAfter(WebDriver driver) throws InterruptedException {
		Thread.sleep(3000);
		afterBalans = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.balance']")).getAttribute("value"));
		afterInSaldo = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.incomingBalance']")).getAttribute("value"));
		afterOutSaldo = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.outgoingBalance']")).getAttribute("value"));
		afterFinRes = Integer.parseInt(driver.findElement(By.cssSelector("[data-rv-value='currEmployee.financialResult']")).getAttribute("value"));
		printBalance(beforeBalans, beforeInSaldo, beforeOutSaldo, beforeFinRes, afterBalans, afterInSaldo, afterOutSaldo, afterFinRes, false);
	}

	// внесение денег в кассу
	public static void inBooking(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		operationSelect(driver, 1);
		inputSum = sumRandom();

		LOG.info("Input maney in cashbox -- " + inputSum + " -- ");

		// ввод суммы операции, нажатие Enter
		driver.findElement(By.cssSelector("[data-rv-value='accountingOperation.amount']")).sendKeys("" + inputSum + Keys.ENTER);
		Thread.sleep(300);
		// нажатие кнопки Выполнить операцию
		driver.findElement(By.cssSelector("[data-action='executeOperation']")).click();
	}

	// выдача денег из кассы
	public static void outBooking(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		operationSelect(driver, 2);
		inputSum = sumRandom();

		LOG.info("Withdrawal of money -- " + inputSum + " -- ");

		// ввод суммы операции, нажатие Enter
		driver.findElement(By.cssSelector("[data-rv-value='accountingOperation.amount']")).sendKeys("" + inputSum + Keys.ENTER);
		Thread.sleep(300);
		// нажатие кнопки Выполнить операцию
		driver.findElement(By.cssSelector("[data-action='executeOperation']")).click();
	}

	// начисление бонуса
	public static void award(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		operationSelect(driver, 3);
		inputSum = sumRandom();

		LOG.info("Add bonuse -- " + inputSum + " --");

		// ввод суммы операции, нажатие Enter
		driver.findElement(By.cssSelector("[data-rv-value='accountingOperation.amount']")).sendKeys("" + inputSum + Keys.ENTER);
		Thread.sleep(300);
		// нажатие кнопки Выполнить операцию
		driver.findElement(By.cssSelector("[data-action='executeOperation']")).click();
	}

	// начисление штрафа
	public static void fine(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		operationSelect(driver, 4);
		inputSum = sumRandom();

		LOG.info("Add penalty -- " + inputSum + " -- ");

		// ввод суммы операции, нажатие Enter
		driver.findElement(By.cssSelector("[data-rv-value='accountingOperation.amount']")).sendKeys("" + inputSum + Keys.ENTER);
		Thread.sleep(300);
		// нажатие кнопки Выполнить операцию
		driver.findElement(By.cssSelector("[data-action='executeOperation']")).click();
	}

	// рандомное вычисление суммы операции от 10000 до 40000
	public static int sumRandom() {
		int sum = (int) (10 + Math.random() * 40);
		return sum * 1000;
	}

	// вывод сообщения в случае несовпадения расчётных итоговых значений с
	// отображаемыми
	public static void processingAdd(String comment) {
		if (beforeOutSaldo - beforeInSaldo != beforeFinRes) {
			LOG.error("---ERROR- " + comment + " - After finance results.");
		}
		if (beforeOutSaldo != beforeBalans) {
			LOG.error("---ERROR- " + comment + " - beforeOutSaldo != beforeBalans.");
		}
		if ((afterBalans + costCMCFinal) - beforeBalans != inputSum) {
			LOG.error("---ERROR- " + comment + " - afterBalans - beforeBalans != inputSum.");
		}
		if (afterOutSaldo - afterInSaldo != afterFinRes) {
			LOG.error("---ERROR- " + comment + " - afterOutSaldo - afterInSaldo != afterFinRes.");
		}
		if (afterOutSaldo != afterBalans) {
			LOG.error("---ERROR- " + comment + " - afterOutSaldo != afterBalans.");
		}
	}

	public static void processingRemove(String comment) {
		if (beforeOutSaldo - beforeInSaldo != beforeFinRes) {
			LOG.error("---ERROR- " + comment + " - After finance results.");
		}
		if (beforeOutSaldo != beforeBalans) {
			LOG.error("---ERROR- " + comment + " - beforeOutSaldo != beforeBalans.");
		}
		if (beforeBalans - inputSum - costCMCFinal != afterBalans) {
			LOG.error("---ERROR- " + comment + " - afterBalans - beforeBalans != inputSum.");
		}
		if (afterOutSaldo - afterInSaldo != afterFinRes) {
			LOG.error("---ERROR- " + comment + " - afterOutSaldo - afterInSaldo != afterFinRes.");
		}
		if (afterOutSaldo != afterBalans) {
			LOG.error("---ERROR- " + comment + " - afterOutSaldo != afterBalans.");
		}
	}

	// выбор операции
	public static void operationSelect(WebDriver driver, int operationNumber) throws InterruptedException {
		// установка фокуса ввода в поле Операция
		driver.findElement(By.cssSelector("[data-rv-key='accountingOperation.operation']")).click();
		Thread.sleep(300);
		// нажатие клавиши вниз
		for (int i = 0; i < operationNumber; i++) {
			driver.findElement(By.cssSelector("[data-rv-key='accountingOperation.operation']")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(300);
		}
		// нажатие Enter для подтверждения выбора операции
		driver.findElement(By.cssSelector("[data-rv-key='accountingOperation.operation']")).sendKeys(Keys.ENTER);
		Thread.sleep(300);
	}

	// последовательное проведение всех операций с оценкой всех данных
	public static void allOperation(WebDriver driver, String worker) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/employeeCard");

		// опрееделение города работы сотрудника
		// в зависимости от роли(водитель или нет) опеределение стоимости СМС
		checkWorkerCity(driver, worker);
		if (checkWorkerRole(driver, worker)) {
			serchAllCost(driver, worker, true);
		} else {
			serchAllCost(driver, worker, false);
		}

		if (checkWorkerRole(driver, worker)) {
			searchCityCost(driver, workCity, true);
		} else {
			searchCityCost(driver, workCity, false);
		}

		Thread.sleep(2000);

		// открытие карточки сотрудника
		openCard(driver, worker);

		// определение цены СМС для данного сотрудника
		pricingCMCCost(driver);

		// считывание данных до проведения операции
		dataReadBefore(driver);
		// внесение денег в кассу
		inBooking(driver);
		// считывание данных после проведения операции
		dataReadAfter(driver);
		// проверка соответствия данных
		processingAdd("Enter money");

		dataReadBefore(driver);
		outBooking(driver);
		dataReadAfter(driver);
		processingRemove("Withdrawal money");

		dataReadBefore(driver);
		award(driver);
		dataReadAfter(driver);
		processingAdd("Award");

		dataReadBefore(driver);
		fine(driver);
		dataReadAfter(driver);
		processingRemove("Fine");
	}

	// определение города сотрудника
	public static void checkWorkerCity(WebDriver driver, String worker) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/employee");
		Thread.sleep(3000);

		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

		Thread.sleep(1500);
		// ставим фокус ввода в поле Позывной, вводим позывной
		// сотрудника, нажимаем Поиск
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).sendKeys(worker);
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
				// пользователя. Если совпадают - занесение названия города в
				// переменную и выход из цикла
				if (callsign1.equals(worker)) {
					Thread.sleep(1000);
					workCity = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8 selected']"))
							.getText();
					System.out.println(workCity);
					break;
				}
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

	// определение роли сотрудника
	public static boolean checkWorkerRole(WebDriver driver, String worker) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/employee");
		Thread.sleep(3000);

		// ставим фокус ввода в поле Город, вводим название города, жмём ENTER
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);

		Thread.sleep(1500);
		// ставим фокус ввода в поле Позывной, вводим позывной
		// сотрудника, нажимаем Поиск
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='currentEmployeeFilter.callsign']")).sendKeys(worker);
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
				// сравниваем считанный позывной на текущем шаге с позывным пользователя. Если совпадают - считываем роль сотруднкиа и
				// заносим в переменную. Выходим из while.
				// и выходим из while
				if (callsign1.equals(worker)) {
					Thread.sleep(1000);
					workerRole = driver
							.findElement(
									By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected']"))
							.getText();
					System.out.println(workCity);
					break;
				}
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
		if ((workerRole.equals("Водитель")) || (workerRole.equals("Бригадир"))) {
			return true;
		}
		return false;
	}

	// ОПРЕДЕЛЕНИЕ ЦЕНЫ СМС
	// открытие настроек для всех городов
	public static void settingsAll(WebDriver driver) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/settings");
		// установка фокуса ввода в поле Город
		driver.findElement(By.cssSelector("[data-rv-source='sources.citiesFormatted']")).click();
		Thread.sleep(3000);
		// выбор пункта "Пусто" и подтверждение ввода
		driver.findElement(By.cssSelector("[data-rv-source='sources.citiesFormatted']")).sendKeys(Keys.ARROW_DOWN, Keys.ENTER);
		Thread.sleep(1000);
	}

	// поиск стоимости СМС для всех городов
	public static void serchAllCost(WebDriver driver, String worker, Boolean roleDriver) throws InterruptedException {
		Thread.sleep(500);
		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";
		String settingName = "";

		Thread.sleep(2500);

		settingsAll(driver);

		// заносим в массив все элементы из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1'][1]")))
					.click().perform();

			Thread.sleep(200);

			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				// читаем позывной в выделенной строке
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']"))
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
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				settingName = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']"))
						.getText();
				insidePhone2 = settingName;

				// если сотрудник - водитель или бригадир, ищестся параметр стоимости СМС для водителей
				if (roleDriver) {
					if (settingName.equals("Стоимость СМС сообщения для водителей")) {
						Thread.sleep(1000);
						costCMCForAll = Integer
								.parseInt(driver
										.findElement(
												By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
										.getText());
						System.out.println("Setting is detected - " + costCMCForAll);
						break;
					}
					// если сотрудник - НЕ водитель или бригадир, ищестся параметр стоимости СМС для всех
				} else {
					if (settingName.equals("Стоимость отправки одного СМС сообщения")) {
						Thread.sleep(1000);
						costCMCForAll = Integer
								.parseInt(driver
										.findElement(
												By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
										.getText());
						System.out.println("Setting is detected - " + costCMCForAll);
						break;
					}
				}

				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (insidePhone1.equals(insidePhone2)) {
					System.out.println("Setting is NOT detected");
					break;
				}

				insidePhone1 = insidePhone2;
			}
		}
	}

	// открытие настроек для города сотрудника
	public static void settingsCity(WebDriver driver, String workCity) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/settings");
		// установка фокуса ввода в поле Город
		driver.findElement(By.cssSelector("[data-rv-source='sources.citiesFormatted']")).click();
		Thread.sleep(3000);

		// /////////
		// выбор города и подтверждение ввода
		driver.findElement(By.cssSelector("[data-rv-source='sources.citiesFormatted']")).sendKeys(Keys.ARROW_DOWN, Keys.ARROW_DOWN, Keys.ARROW_DOWN,
				Keys.ENTER);
		Thread.sleep(1000);
	}

	// поиск стоимости СМС для города сотрудника
	public static void searchCityCost(WebDriver driver, String city, Boolean roleDriver) throws InterruptedException {
		Thread.sleep(500);
		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";
		String settingName = "";

		Thread.sleep(2500);

		settingsCity(driver, city);

		// заносим в массив все элементы из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1'][1]")))
					.click().perform();

			Thread.sleep(200);

			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2 selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				// читаем позывной в выделенной строке
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']"))
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
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				settingName = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l3 r3 selected']"))
						.getText();
				insidePhone2 = settingName;

				// если сотрудник - водитель или бригадир, ищестся параметр стоимости СМС для водителей
				if (roleDriver) {
					if (settingName.equals("Стоимость СМС сообщения для водителей")) {
						Thread.sleep(1000);
						costCMCForAll = Integer
								.parseInt(driver
										.findElement(
												By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
										.getText());
						System.out.println("Setting is detected - " + costCMCForAll);
						break;
					}
					// если сотрудник - НЕ водитель или бригадир, ищестся параметр стоимости СМС для всех
				} else {
					if (settingName.equals("Стоимость отправки одного СМС сообщения")) {
						Thread.sleep(1000);
						costCMCForCity = Integer
								.parseInt(driver
										.findElement(
												By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
										.getText());
						System.out.println("Setting is detected - " + costCMCForCity);
						break;
					}
				}

				// сравниваем считанные позывные на прошлом шаге и текущем. Если
				// совпадают - выводим сообщение об отсутствии искомого
				// позывного и выходим из while - низ таблицы достигнут
				if (insidePhone1.equals(insidePhone2)) {
					System.out.println("Setting is NOT detected");
					break;
				}

				insidePhone1 = insidePhone2;
			}
		}
	}

	// определение стоимости СМС для сотрудника
	public static void pricingCMCCost(WebDriver driver) throws InterruptedException {
		// если не выставленна цена СМС для города сторудника, то берётся стоимость для всех городов
		if (costCMCForCity == 0) {
			costCMCFinal = costCMCForAll;
		} else {
			costCMCFinal = costCMCForCity;
		}
	}
}