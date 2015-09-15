package test;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.interactions.Actions;

public class AddCompletedOrder {

	static String adressFrom1 = "";

	// переменные для записи вводимых при создании заказа данных
	static String phone1 = "";
	static String lastName1 = "";
	static String firstName1 = "";
	static String middleName1 = "";
	static String city1 = "";
	static String streetFrom1 = "";
	static String houseFrom1 = "";
	static String apartment1 = "";
	static String noteFrom1 = "";
	static String streetTo1 = "";
	static String houseTo1 = "";
	static String noteOrder1 = "";
	static String carType1 = "";
	static String bonus1 = "";
	static String adressTo1 = "";
	static String driver1 = "";
	static String operator1 = "";
	static String distance1 = "";
	static String cost1 = "";

	// переменные для данных, считанных на странице Статус заказа
	static String phone2 = "";
	static String lastName2 = "";
	static String firstName2 = "";
	static String middleName2 = "";
	static String city2 = "";
	static String streetFrom2 = "";
	static String houseFrom2 = "";
	static String apartment2 = "";
	static String noteFrom2 = "";
	static String streetTo2 = "";
	static String houseTo2 = "";
	static String noteOrder2 = "";
	static String carType2 = "";
	static String bonus2 = "";
	static String adressTo2 = "";
	static String driver2 = "";
	static String operator2 = "";
	static String distance2 = "";
	static String cost2 = "";

	// переменные для записи данных из окна редактирвания созданного заказа
	static String phone3 = "";
	static String lastName3 = "";
	static String firstName3 = "";
	static String middleName3 = "";
	static String city3 = "";
	static String streetFrom3 = "";
	static String houseFrom3 = "";
	static String apartment3 = "";
	static String noteFrom3 = "";
	static String streetTo3 = "";
	static String houseTo3 = "";
	static String noteOrder3 = "";
	static String carType3 = "";
	static String bonus3 = "";
	static String driver3 = "";
	static String operator3 = "";
	static String distance3 = "";
	static String cost3 = "";

	static List<String> streetArr = new ArrayList<String>();
	static List<String> noteArr = new ArrayList<String>();
	static List<String> hasDriveArr = new ArrayList<String>();
	static List<String> testArr = new ArrayList<String>();

	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<WebElement> errorNotify = new ArrayList<WebElement>();

	public static void main(String[] args) throws InterruptedException {

		streetArr.add("Антонова");
		streetArr.add("Белуша");
		streetArr.add("Болдина");
		streetArr.add("Богдановича");
		streetArr.add("Курчатова");
		streetArr.add("Лиможа");
		streetArr.add("Тавлая");
		streetArr.add("Подольная");
		streetArr.add("Кирова");
		streetArr.add("Титова");
		streetArr.add("Максима Горького");

		noteArr.add("Стоянка");
		noteArr.add("Две женщины");
		noteArr.add("Подъезд слева");
		noteArr.add("Быстро");
		noteArr.add("Загородный");
		noteArr.add("Большой багаж");
		noteArr.add("На остановке");

		hasDriveArr.add("Через арку");
		hasDriveArr.add("Второй поворот налево");
		hasDriveArr.add("По грунтовой дороге");
		hasDriveArr.add("На остановку у магазина");

		testArr.add("ТЕСТ1");
		testArr.add("ТЕСТ2");
		testArr.add("ТЕСТ3");
		testArr.add("ТЕСТ4");
		testArr.add("ТЕСТ5");

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// создаём опцию для хромдрайвера, где указываем размеры страницы
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1300,1000");
		WebDriver driver = new ChromeDriver(option);

		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		driver.findElement(By.cssSelector(".js-login")).sendKeys("autotest");

		driver.findElement(By.cssSelector("[type='password']")).sendKeys("autotest");

		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);
		addOrder(driver);
	}

	public static void addOrder(WebDriver driver) throws InterruptedException {
		driver.get("http://qa.taxi.dancosoft.com/site/new?finished=true");

		// ввод данных при создании заказа
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[tabindex='1']")).sendKeys("445864574"); // номер телефона
		driver.findElement(By.cssSelector("[tabindex='1']")).sendKeys(Keys.ENTER);
		Thread.sleep(3000);
		driver.findElement(By.cssSelector("[tabindex='2']")).sendKeys("Гродно"); // город
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[tabindex='2']")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("[tabindex='3']")).sendKeys(streetArr.get((int) (Math.random() * 11))); // улица отправления
		driver.findElement(By.cssSelector("[tabindex='4']")).sendKeys(Integer.toString(1 + (int) (Math.random() * 30))); // дом отправления
		driver.findElement(By.cssSelector("[tabindex='5']")).sendKeys(Integer.toString(2 + (int) (Math.random() * 100))); // квартира
		driver.findElement(By.cssSelector("[tabindex='110']")).sendKeys(hasDriveArr.get((int) (Math.random() * 4))); // как подъехать
		driver.findElement(By.cssSelector("[tabindex='8']")).sendKeys(streetArr.get((int) (Math.random() * 11))); // улица назначения
		driver.findElement(By.cssSelector("[tabindex='9']")).sendKeys(Integer.toString(1 + (int) (Math.random() * 30))); // дом назначения
		driver.findElement(By.cssSelector("[tabindex='7']")).sendKeys(noteArr.get((int) (Math.random() * 7))); // примечание к заказу
		driver.findElement(By.cssSelector("[tabindex='153']")).sendKeys(Integer.toString((int) (Math.random() * 20))); // расстояние
		driver.findElement(By.cssSelector("[tabindex='154']")).sendKeys(Integer.toString(30000 + 1000 * (int) (Math.random() * 20))); // стоимость

		Thread.sleep(1500);

		carType(driver);

		// опеределяется, будут ли указаны опции т/с
		if (((int) (Math.random() * 3)) == 1) {
			carOptions(driver);
		}

		// выбор бонуса
		orderBonus(driver);

		Thread.sleep(7000);

		operatorsPick(driver);
		driverPick(driver);

		// чтение заполненных данных
		readingDataToCreate(driver);

		Thread.sleep(3000);

		driver.findElement(By.cssSelector("[tabindex='200']")).click(); // кнопка "принять"

		// ожидание появления диалогового окна при отсутствии тарифа для выбранного типа т/с и нажатие на кнопку "Принять"
		for (int i = 0; i < 20; i++) {
			Thread.sleep(100);
			elements = driver
					.findElements(By
							.xpath("/html/body[@class='modal-open']/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='bootbox-modal-body']"));
			if (elements.size() > 0) {
				driver.findElement(
						By.xpath("/html/body[@class='modal-open']/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='bootbox modal fade in']/div[@class='modal-dialog']/div[@class='modal-content']/div[@class='modal-footer']/button[@class='btn btn-primary']"))
						.click();
				break;
			}
		}

		// проверка на появление уведомления об успешном добавлении заказа
		// if (checkCreateOrder(driver)) {
		// }

		Thread.sleep(2000);
		driver.get("http://qa.taxi.dancosoft.com/site/status");

		// выбор созданного заказа на странице Статус заказа
		pickCreatedOrder(driver);

		// чтение данных по заказу со страницы Статус заказа
		readingDataAfterCreate(driver);

		// сравнение данных
		compareData(driver);

		Thread.sleep(1500);
	}

	// выбор диспетчеров на смене
	public static void operatorsPick(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[tabindex='155']")).click();

		for (int j = 0; j < 1 + (int) (Math.random() * 10); j++) {
			driver.findElement(By.cssSelector("[tabindex='155']")).sendKeys(Keys.ARROW_DOWN);
		}

		Thread.sleep(500);
		driver.findElement(By.cssSelector("[tabindex='155']")).sendKeys(Keys.ENTER);
	}

	// выбор назначенного водителя
	public static void driverPick(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[tabindex='100']")).click();
		for (int j = 0; j < (int) (Math.random() * 100); j++) {
			Thread.sleep(50);
			driver.findElement(By.cssSelector("[tabindex='100']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[tabindex='100']")).sendKeys(Keys.ENTER);
	}

	// выбор типа т/с
	public static void carType(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		for (int j = 0; j < (int) (Math.random() * 10); j++) {
			driver.findElement(By.cssSelector("[data-rv-source='sources.carKinds']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carKinds']")).sendKeys(Keys.ENTER);
	}

	// выбор опций
	public static void carOptions(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		int kolOptions = (int) (Math.random() * 3);
		driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).click();

		for (int i = 0; i < kolOptions; i++) {
			for (int j = 0; j < (int) (Math.random() * 10); j++) {
				driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.ARROW_DOWN);
			}
			driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.SPACE);
		}
		driver.findElement(By.cssSelector("[data-rv-source='sources.carOptions']")).sendKeys(Keys.ENTER);
	}

	// выбор бонуса
	public static void orderBonus(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);

		for (int j = 0; j < 1 + (int) (Math.random() * 7); j++) {
			driver.findElement(By.cssSelector("[tabindex='7']")).sendKeys(Keys.F6);
		}
	}

	public static boolean checkCreateOrder(WebDriver driver) throws InterruptedException {
		boolean results = false;
		// 100 раз по 100 мс ждём на предмет появления нотификации об успешном
		// добавлении заказа либо нотификации об ошибке
		for (int i = 0; i < 100; i++) {
			Thread.sleep(100);
			elements = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-success ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Order is created.");
				results = true;
				return results;
			}
			errorNotify = driver
					.findElements(By
							.xpath("/html/body/div[@class='ui-pnotify stack-bottomright']/div[@class='alert ui-pnotify-container alert-danger ui-pnotify-shadow']"));
			if (elements.size() > 0) {
				System.out.println("Order is not created.");
				results = false;
				return results;
			}
		}
		results = false;
		return results;
	}

	// чтение внесённых данных со страницы Новый заказ
	public static void readingDataToCreate(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		String bonusStr = "";

		phone1 = driver.findElement(By.cssSelector("[tabindex='1']")).getAttribute("value");
		lastName1 = driver.findElement(By.cssSelector("[data-rv-value='newOrder.lastName']")).getAttribute("value");
		firstName1 = driver.findElement(By.cssSelector("[data-rv-value='newOrder.firstName']")).getAttribute("value");
		middleName1 = driver.findElement(By.cssSelector("[data-rv-value='newOrder.middleName']")).getAttribute("value");
		city1 = driver.findElement(By.cssSelector("[tabindex='2']")).getAttribute("value");
		streetFrom1 = driver.findElement(By.cssSelector("[tabindex='3']")).getAttribute("value");
		houseFrom1 = driver.findElement(By.cssSelector("[tabindex='4']")).getAttribute("value");
		apartment1 = driver.findElement(By.cssSelector("[data-rv-value='newOrder.addressFromApartmentNumber']")).getAttribute("value");
		noteFrom1 = driver.findElement(By.cssSelector("[data-rv-value='newOrder.addressFromNote']")).getAttribute("value");
		streetTo1 = driver.findElement(By.cssSelector("[tabindex='8']")).getAttribute("value");
		houseTo1 = driver.findElement(By.cssSelector("[tabindex='9']")).getAttribute("value");
		noteOrder1 = driver.findElement(By.cssSelector("[tabindex='7']")).getAttribute("value");
		carType1 = driver.findElement(By.cssSelector("[tabindex='109']")).getAttribute("value");
		driver1 = driver.findElement(By.cssSelector("[tabindex='100']")).getAttribute("value");
		distance1 = driver.findElement(By.cssSelector("[tabindex='153']")).getAttribute("value");
		cost1 = driver.findElement(By.cssSelector("[tabindex='154']")).getAttribute("value");

		// считывание суммы бонуса определением выделенного элемента - весь бонус или другой
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-top']/div[@class='container-fluid']/div[@class='separator-bot']/div[@class='row']/div[@class='col-xs-12']/div[@class='row']/div[@class='col-xs-6 separator-lt-over offset-bot']/div[@class='block']/div[@class='row offset-top js-view-bonus hide-weak']/div[@class='col-xs-9']/div[@class='btn-group btn-group-justified bonus-outer js-bonus-btn-group']/label[@class='btn btn-warning active']"));
		if (elements.size() > 0) {
			bonusStr = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-top']/div[@class='container-fluid']/div[@class='separator-bot']/div[@class='row']/div[@class='col-xs-12']/div[@class='row']/div[@class='col-xs-6 separator-lt-over offset-bot']/div[@class='block']/div[@class='row offset-top js-view-bonus hide-weak']/div[@class='col-xs-9']/div[@class='btn-group btn-group-justified bonus-outer js-bonus-btn-group']/label[@class='btn btn-warning active']"))
					.getText();
			// int a1 = bonusStr.indexOf('n') + 1;
			bonus1 = bonusStr.substring(11);
		} else {
			bonus1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-top']/div[@class='container-fluid']/div[@class='separator-bot']/div[@class='row']/div[@class='col-xs-12']/div[@class='row']/div[@class='col-xs-6 separator-lt-over offset-bot']/div[@class='block']/div[@class='row offset-top js-view-bonus hide-weak']/div[@class='col-xs-9']/div[@class='btn-group btn-group-justified bonus-outer js-bonus-btn-group']/label[@class='btn btn-primary js-bonus-btn active']"))
					.getText();
		}

		// формирование адреса назначения в одну строку
		adressTo1 = streetTo1 + " " + houseTo1;
		// формирование адреса подачи в одну строку
		adressFrom1 = streetFrom1 + " " + houseFrom1 + " кв. " + apartment1;
	}

	public static void pickCreatedOrder(WebDriver driver) throws InterruptedException {

		driver.get("http://qa.taxi.dancosoft.com/site/status");
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[data-action='search']")).click();

		boolean flag = true;
		String insidePhone1 = "";
		String insidePhone2 = "";
		String callsign1 = "";

		// сортировка заказов в таблице поиска заказов по полю время последнего действия 2 раза
		Thread.sleep(2000);
		driver.findElement(By.cssSelector("[id$='lastActionAt']")).click();
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[id$='lastActionAt']")).click();

		Thread.sleep(2500);
		// заносим в массив все элементы из таблицы
		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1 text-font-big text-bold']"));

		// проверяем, пустой ли массив элементов
		if (elements.size() != 0) {

			// если массив не пустой, то выделяем элемент в таблице
			Thread.sleep(200);
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1 text-font-big text-bold'][1]")))
					.click().perform();

			Thread.sleep(200);

			// жмём стрелку вверх на клавиатуре, пока не достигнем верха таблицы
			while (flag == true) {
				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5 text-font-big text-bold selected']")))
						.sendKeys(Keys.ARROW_UP).perform();

				// читаем адрес подачи в выделенной строке
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5 text-font-big text-bold selected']"))
						.getText();
				// сравниваем считанные адреcа на прошлом шаге и текущем. Если
				// совпадают - выходим из while - верх таблицы достигнут
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}
				insidePhone1 = insidePhone2;
				Thread.sleep(100);
				callsign1 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5 text-font-big text-bold selected']"))
						.getText();
			}

			callsign1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l5 r5 text-font-big text-bold selected']"))
					.getText();

			if (callsign1.equals(adressFrom1)) {
				System.out.println("Sought-for order is found.");
			}
		}
	}

	// чтение данных заказа с инфо-панели со страницы поиска заказов
	public static void readingDataAfterCreate(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);

		phone2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[3]/div[@class='block offset-top'][2]/div[@class='container-fluid tab-content']/div[@id='tab-additionally']/div[@class='block offset-top separator-bot'][1]/div[@class='row'][1]/div[@class='col-xs-6']/span"))
				.getText(); // телефон
		noteFrom2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[@class='block offset-top tab-content']/div[@class='container-fluid tab-pane widened-tab active tab-order']/div/div[@class='block offset-top']/div[@class='row'][5]/div[@class='col-xs-6']/span"))
				.getText(); // примечание к месту подачи
		adressTo2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[@class='block offset-top tab-content']/div[@class='container-fluid tab-pane widened-tab active tab-order']/div/div[@class='block offset-bot separator-bot']/div[@class='row']/div[@class='col-xs-8']/div[@class='row'][2]/div[@class='col-xs-12']/h4[@class='js-currentOrder-addressTo']"))
				.getText(); // адрес назначения
		noteOrder2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[@class='block offset-top tab-content']/div[@class='container-fluid tab-pane widened-tab active tab-order']/div/div[@class='block offset-top']/div[@class='row'][6]/div[@class='col-xs-6']/span"))
				.getText(); // примечание к заказу
		carType2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[@class='block offset-top tab-content']/div[@class='container-fluid tab-pane widened-tab active tab-order']/div/div[@class='block offset-top']/div[@class='row'][3]/div[@class='col-xs-6']/span"))
				.getText(); // тип т\с
		bonus2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[3]/div[@class='block offset-top'][2]/div[@class='container-fluid tab-content']/div[@id='tab-additionally']/div[@class='block offset-top separator-bot'][2]/div[@class='row'][8]/div[@class='col-xs-6']/span"))
				.getText(); // бонус
		city2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l8 r8 selected']"))
				.getText(); // город
		driver2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='js-search-active hide-weak full-height-weak']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2 text-center text-font-big text-bold selected']"))
				.getText(); // позывной водителя
		cost2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[3]/div[@class='block offset-top'][2]/div[@class='container-fluid tab-content']/div[@id='tab-additionally']/div[@class='block offset-top separator-bot'][2]/div[@class='row'][7]/div[@class='col-xs-6']/span"))
				.getText();
		distance2 = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='js-infopanel-block js-infopanel-order']/div[@class='container-fluid']/div[3]/div[@class='block offset-top'][2]/div[@class='container-fluid tab-content']/div[@id='tab-additionally']/div[@class='block offset-top separator-bot'][2]/div[@class='row'][4]/div[@class='col-xs-6']/span"))
				.getText();
	}

	// сравнение данных, считанных во время создания заказа, с инфо-панели страницы Статус заказа и со страницы редактирования заказа
	public static void compareData(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);

		if (!phone1.equals(phone2)) {
			System.out.println("ERROR - phone on page does not match");
			System.out.println(phone1 + " - " + phone2);
		}
		if (!adressTo1.equals(adressTo2)) {
			System.out.println("ERROR - adress on page does not match");
			System.out.println(adressTo1 + " - " + adressTo2);
		}
		if (!noteOrder1.equals(noteOrder2)) {
			System.out.println("ERROR - note order on page does not match");
			System.out.println(noteOrder1 + " - " + noteOrder2);
		}
		if (!carType1.equals(carType2)) {
			System.out.println("ERROR - car type on page does not match");
			System.out.println(carType1 + " - " + carType2);
		}
		if (!phone1.equals(phone2)) {
			System.out.println("ERROR - phone on page does not match");
			System.out.println(phone1 + " - " + phone2);
		}
		if (!city1.equals(city2)) {
			System.out.println("ERROR - city on page does not match");
			System.out.println(city1 + " - " + city2);
		}
		if (!driver1.equals(driver2)) {
			System.out.println("ERROR - driver on page does not match");
			System.out.println(driver1 + " - " + driver2);
		}
		if (!cost1.equals(cost2)) {
			System.out.println("ERROR - cost on page does not match");
			System.out.println(cost1 + " - " + cost2);
		}
		if (!distance1.equals(distance2)) {
			System.out.println("ERROR - distance on page does not match");
			System.out.println(distance1 + " - " + distance2);
		}
		if (!bonus1.equals(bonus2)) {
			System.out.println("ERROR - bonus on page does not match");
			System.out.println(bonus1 + " - " + bonus2);
		}
	}
}
