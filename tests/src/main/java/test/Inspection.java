package test;

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

public class Inspection {

	public static String tempU = "";
	public static String parName = "";
	public static String comment = "";
	public static String fullString = "";

	public static int balanceOld;
	public static float ratingOld;
	public static int kolvoReviewOld;

	public static int balanceCalculated;
	public static float ratingCalculated;

	public static int balanceNew;
	public static float ratingNew;
	public static int kolvoReviewNew;

	public static String dataLastReviewOld = "";
	public static String dataLastReviewFinal = "";
	public static float currentCarRating;

	// переменные для даты/времени последнего осмотра
	public static int year;
	public static int month;
	public static int day;
	public static int hour;
	public static int minutes;
	public static int dayZ;

	// переменные для текущих даты, времени и средних оценок
	public static int curYear;
	public static int curMonth;
	public static int curDay;
	public static int curHour;
	public static int curMinutes;

	static List<String> ratingString = new ArrayList<String>();
	static List<String> checkRatingString = new ArrayList<String>();
	static List<Integer> ratingA = new ArrayList<Integer>();
	static List<String> comments = new ArrayList<String>();

	public static void main(String[] args) throws InterruptedException {

		comments.add("Коммент0");
		comments.add("Коммент1");
		comments.add("Коммент2");
		comments.add("Коммент3");
		comments.add("Коммент4");
		comments.add("Коммент5");
		comments.add("Коммент6");
		comments.add("Коммент7");
		comments.add("Коммент8");
		comments.add("Коммент9");

		System.setProperty("webdriver.chrome.driver", "C:\\chromedriver.exe");

		// создаём опцию для хромдрайвера, где указываем размеры страницы
		ChromeOptions option = new ChromeOptions();
		option.addArguments("--window-size=1300,1000");
		WebDriver driver = new ChromeDriver(option);

		// открываем страницу логина, проходим http - авторизацию
		driver.get("http://qa:bfh365Dkjfh@qa.taxi.dancosoft.com/site/login");

		Thread.sleep(1500);

		// вводим логин
		WebElement loginElement = driver.findElement(By
				.cssSelector(".js-login"));
		loginElement.sendKeys("autotest");

		// вводим пароль
		WebElement element = driver.findElement(By
				.cssSelector("[type='password']"));
		element.sendKeys("autotest");
		// нажимаем кнопку входа
		WebElement submitElement = driver.findElement(By.cssSelector("button"));
		submitElement.click();

		Thread.sleep(3000);
		addInspection(driver, "emul1");
		// addInspection(driver, "emul2");
		// addInspection(driver, "emul3");
		// addInspection(driver, "emul4");
		// addInspection(driver, "emul5");
		// addInspection(driver, "emul6");
		// addInspection(driver, "emul7");
		// addInspection(driver, "emul8");
		// addInspection(driver, "emul9");
		// addInspection(driver, "emul10");
		// addInspection(driver, "emul11");
		// addInspection(driver, "emul12");
		// addInspection(driver, "emul13");
		// addInspection(driver, "emul14");
		// addInspection(driver, "emul15");
		// addInspection(driver, "emul16");
		// addInspection(driver, "emul17");
		// addInspection(driver, "emul18");
		// addInspection(driver, "emul19");
		// addInspection(driver, "emul20");

		// addInspection(driver, "v41");
		// addInspection(driver, "v42");
		// addInspection(driver, "v43");
		// addInspection(driver, "v44");
		// addInspection(driver, "v45");
		// addInspection(driver, "v46");
		// addInspection(driver, "v47");
		// addInspection(driver, "v48");
		// addInspection(driver, "v49");
		// addInspection(driver, "v50");
		// addInspection(driver, "v51");
		// addInspection(driver, "v52");
		// addInspection(driver, "v53");
		// addInspection(driver, "v54");
		// addInspection(driver, "v55");
		// addInspection(driver, "v56");
		// addInspection(driver, "v57");
		// addInspection(driver, "v58");
		// addInspection(driver, "v59");
		// addInspection(driver, "v60");
		// addInspection(driver, "v61");

	}

	// определение id полей для оценок
	public static int getNumber(WebDriver driver, int kolOptions,
			ArrayList<Integer> opt) {
		// ищем id полей для оценок, заносим их в List opt
		for (int i = 0; i <= 100; i++) {
			try {
				driver.findElement(By
						.cssSelector("[data-rv-value='newReview.parameters."
								+ i + ".rating']"));
				kolOptions++;
				opt.add(i);
			} catch (Exception e) {
			}
		}
		return kolOptions;
	}

	// добавление осмотра
	public static void addInspection(WebDriver driver, String Owner)
			throws InterruptedException {

		// открываем страницу осмотров водителей
		driver.get("http://qa.taxi.dancosoft.com/site/carReview");
		Thread.sleep(2000);
		// выбираем поле "Позывной"
		WebElement searchElement = driver.findElement(By
				.cssSelector("[data-rv-source='sources.registrationNumbers']"));
		// вводим в поле позывной водителя
		searchElement.sendKeys(Owner);
		Thread.sleep(600);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(2500);

		// выбираем кнопку "Добавить" и жмём на неё
		Thread.sleep(500);
		WebElement submitElement = driver.findElement(By
				.cssSelector("[data-action='addReview']"));
		submitElement.click();
		Thread.sleep(1000);

		// создаём ArrayList opt для хранения id поля для оценки
		ArrayList<Integer> opt = new ArrayList<Integer>();
		int kolOptions = 0;

		// считываем кол-во параметров для оценки
		kolOptions = getNumber(driver, kolOptions, opt);

		// берём случайное число параметров, которые будем оценивать, заносим
		// его в kolRatingTemp
		int kolRating = 1 + (int) (Math.random() * (kolOptions - 1));
		int kolRatingTemp = kolRating + 1;

		// выставляем оценки параметрам, пока не обнулится счётчик неоцененных
		// параметров
		while (kolRating >= 0) {
			// выводим на кансоль размер массива с номерами параметров для
			// оценки и кол-вом оставшихся параметров для оценки
			System.out.print("Size - " + opt.size() + " " + "Kol - "
					+ kolOptions + " ");
			System.out.println();
			// генерируем оценку
			int Rating = (int) (Math.random() * 10);

			// заносим оценку в массив
			ratingA.add(Rating);
			// суммируем выставленные оценки
			ratingCalculated = ratingCalculated + Rating;

			// выставляем оценку
			driver.findElement(
					By.cssSelector("[data-rv-value='newReview.parameters."
							+ opt.get(kolRating) + ".rating']")).sendKeys(
					"" + Rating);
			// генерируем число 0 или 1 для решения об занесении комментария
			int commentFlag = (int) ((Math.random() * 2));
			System.out.println("commentFlag " + commentFlag);
			// если 1 - заносим случайный комментарий
			if (commentFlag == 1) {

				int commentNumber = 1 + (int) (Math.random() * (comments.size() - 1));
				System.out.println("commentNumber " + commentNumber);
				driver.findElement(
						By.cssSelector("[data-rv-value='newReview.parameters."
								+ opt.get(kolRating) + ".notes']")).sendKeys(
						"" + comments.get(commentNumber));
			}

			// выводим на консоль кол-во не выставленных оценок, оценка текущего
			// параметра, id поля
			System.out.print("kolRating - " + kolRating + " ");
			System.out.print("Rating - " + Rating + " ");
			System.out.println("Field id - " + opt.get(kolRating) + " ");
			opt.remove(kolRating);
			kolOptions--;
			kolRating--;
		}

		// определяем кол-во параметров
		int i = driver
				.findElements(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/*/div[@class='col-xs-3']/div[@class='input-group']/input[@class='form-control-chevron js-smart-dropdown form-control']"))
				.size();
		System.out.println("i - " + i);

		// по кол-ву параметров запускам цикл для формирования строк Имя
		// параметра - Оценка - Комментарий(только для оценённых параметров)
		for (int j = 1; j <= 12; j++) {
			String str = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/div[@class='row offset-top review-row']["
									+ j
									+ "]/div[@class='col-xs-3']/div[@class='input-group']/input[@class='form-control-chevron js-smart-dropdown form-control']"))
					.getAttribute("value");
			if (str.length() > 0) {
				System.out
						.println(driver
								.findElement(
										By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/div[@class='row offset-top review-row']["
												+ j
												+ "]/div[@class='col-xs-3']/div[@class='input-group']/input[@class='form-control-chevron js-smart-dropdown form-control']"))
								.getAttribute("value"));
				parName = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/div[@class='row offset-top review-row']["
										+ j
										+ "]/div[@class='col-xs-5']/input[@class='form-control']"))
						.getAttribute("value");
				tempU = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/div[@class='row offset-top review-row']["
										+ j
										+ "]/div[@class='col-xs-3']/div[@class='input-group']/input[@class='form-control-chevron js-smart-dropdown form-control']"))
						.getAttribute("value");
				comment = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='js-add-review-tab']/div[@class='block separator-bot offset-top js-modal-add-review']/div[@class='row offset-top review-row']["
										+ j
										+ "]/div[@class='col-xs-4']/input[@class='form-control']"))
						.getAttribute("value");
				fullString = parName + "-" + tempU + "-" + comment;
				System.out.println("fullString - " + fullString);
				ratingString.add(fullString);
			}
		}
		// считаем среднюю оценку
		ratingCalculated = ratingCalculated / kolRatingTemp;
		System.out.println("ratingCalculated - " + ratingCalculated);
		System.out.println(" ");
		WebElement addInsElement = driver.findElement(By
				.cssSelector("[data-action='AddReviewReal']"));
		addInsElement.click();
		// вызов процедуры сортировки
		sorting(driver);
		// вызов процедуры считывания данных по осмотру
		dataReading(driver);
		// определение текущей даты
		curData(driver);
		// 
		checkData(driver, Owner);
	}

	// сортируем осмотры по убыванию
	public static void sorting(WebDriver driver) throws InterruptedException {
		driver.findElement(By.cssSelector("[id$='createdAt']")).click();
		driver.findElement(By.cssSelector("[id$='createdAt']")).click();
		Thread.sleep(2000);
	}

	// ищем дату последнего осмотра
	public static void dataReading(WebDriver driver)
			throws InterruptedException {
		dataLastReviewOld = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@id='tab-reviews']/div[@class='content-bottom']/div[@class='content-outer offset-top']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0']"))
				.getText();

		// System.out.println("dataLastReviewOld " + dataLastReviewOld);
		year = Integer.parseInt(dataLastReviewOld.substring(0, 4));
		month = Integer.parseInt(dataLastReviewOld.substring(5, 7));
		day = Integer.parseInt(dataLastReviewOld.substring(8, 10));
		Thread.sleep(2000);
		switch (month) {
		case 1:
			dayZ = 0;
			break;
		case 2:
			dayZ = 31;
			break;
		case 3:
			dayZ = 59;
			break;
		case 4:
			dayZ = 90;
			break;
		case 5:
			dayZ = 120;
			break;
		case 6:
			dayZ = 151;
			break;
		case 7:
			dayZ = 181;
			break;
		case 8:
			dayZ = 212;
			break;
		case 9:
			dayZ = 243;
			break;
		case 10:
			dayZ = 273;
			break;
		case 11:
			dayZ = 304;
			break;
		case 12:
			dayZ = 334;
			break;
		}

		switch (year % 4) {
		case 1:
			break;
		case 2:
			break;
		case 3:
			break;
		case 0:
			dayZ = dayZ + 1;
			break;
		}

		dayZ = dayZ + day;
		// System.out.println("Day review - " + dayZ);
	}

	// определение текущей даты(номер дня в году)
	public static void curData(WebDriver driver) throws InterruptedException {
		Calendar calendar = Calendar.getInstance();

		curDay = calendar.get(Calendar.DAY_OF_YEAR);
		// System.out.println("Current day - " + curDay);
	}

	public static void dateParse(WebDriver driver) throws InterruptedException {

	}

	// сравнение данных осмотра с введёнными
	public static void checkData(WebDriver driver, String Owner)
			throws InterruptedException {

		System.out.println(" ");
		System.out.println("CHECK DATA");
		System.out.println(" ");

		driver.get("http://qa.taxi.dancosoft.com/site/status");
		Thread.sleep(1000);
		driver.get("http://qa.taxi.dancosoft.com/site/carReview");
		Thread.sleep(2000);

		// выбираем поле "Позывной"
		WebElement searchElement = driver.findElement(By
				.cssSelector("[data-rv-source='sources.registrationNumbers']"));
		// вводим в поле позывной водителя
		searchElement.sendKeys(Owner);
		Thread.sleep(600);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(1500);
		searchElement.sendKeys(Keys.ENTER);
		Thread.sleep(2500);

		checkRating(driver, Owner);
		checkDataReview(driver, Owner);
		checkRatingValue(driver, Owner);
		checkFullString(driver, Owner);
		// System.out.println("currentCarRating - " + currentCarRating);
		// System.out.println("roundCurrentRating - " + roundCurrentRating);

		// System.out.println("dataLastReviewFinal " + dataLastReviewFinal);
	}

	public static void checkRating(WebDriver driver, String Owner)
			throws InterruptedException {
		sorting(driver);

		// считываем значение текущего рейтинга из поля Текущий рейтинг
		String tempRating = "";
		WebElement CurRating = driver.findElement(By
				.cssSelector("[data-rv-value='currentCar.rating']"));
		tempRating = CurRating.getAttribute("value");

		// сравниваем рейтинг в поле Текущий рейтинг с вычисленным рейтингом по
		System.out.println("Culculate rating - " + ratingCalculated + " -- "
				+ tempRating + " - in field");
	}

	public static void checkDataReview(WebDriver driver, String Owner)
			throws InterruptedException {
		// считываем дату и время последнего осмотра
		dataLastReviewFinal = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@id='tab-reviews']/div[@class='content-bottom']/div[@class='content-outer offset-top']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0']"))
				.getText();

		// сравниваем даты последнего осмотра
		if ((dataLastReviewFinal.equals(dataLastReviewOld)) && (dayZ == curDay)) {
			System.out.println("right - Date Review");
		} else {
			System.out.println("ERROR- Date Review");
		}
	}

	public static void checkRatingValue(WebDriver driver, String Owner)
			throws InterruptedException {

		// кликаем на самый последний осмотр(самый верхний в таблице)
		driver.findElement(
				By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom tab-content']/div[@id='tab-reviews']/div[@class='content-bottom']/div[@class='content-outer offset-top']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0']"))
				.click();

		Thread.sleep(1000);
		// кликаем на поле Комментарий первого параметра
		WebElement Comment1 = driver
				.findElement(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even']/div[@class='slick-cell l2 r2 text-bold']"));
		comment = Comment1.getText();
		// считываем самую первую оценку
		String tempRV = driver
				.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l1 r1 text-bold']"))
				.getText();

		System.out.println(tempRV);

		// определяем первую запись в таблице оценок
		WebElement wParName = driver
				.findElement(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0 text-bold']"));
		// считываем имя параметра
		String parName1 = wParName.getText();

		// формируем строку Имя параметра - Оценка - Комментарий
		fullString = parName1 + "-" + tempRV + "-" + comment;
		System.out.println("fullString - " + fullString);

		// выделяем эту строку
		wParName.click();

		boolean stop = false;
		while (stop == false) {

			// переходим на строку вниз клавишей Arrow Down
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1 text-bold selected']")))
					.sendKeys(Keys.ARROW_DOWN, Keys.ARROW_LEFT).perform();
			// считываем оценку в текущей строке
			tempRV = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l1 r1 text-bold selected']"))
					.getText();
			// считываем название параметра в текущей строке
			parName = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 text-bold selected active']"))
					.getText();
			// считываем сомментарий в текущей строке
			comment = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='container-fluid']/div[@class='tab-content']/div[@class='tab-pane active tab-reviews']/div[@class='content-outer']/div[@class='content-bottom']/div[2]/div[@class='grid-outer offset-top']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l2 r2 text-bold selected']"))
					.getText();
			if (parName1.equals(parName)) {
				stop = true;
				break;
			}
			fullString = parName + "-" + tempRV + "-" + comment;
			System.out.println("fullString - " + fullString);
			parName1 = parName;
			checkRatingString.add(fullString);
		}

	}

	// проверка соответствия оценок и комментариев при просмтре проведённого
	// осмотра выставленным при добавлении осмотра
	public static void checkFullString(WebDriver driver, String Owner)
			throws InterruptedException {
		for (int j = 0; j < checkRatingString.size(); j++) {
			if (!ratingString.get(j).equals(checkRatingString.get(j))) {
				System.out.println("ERROR - Check rating conformity");
				System.out.println(ratingString.get(j));
				System.out.println(checkRatingString.get(j));
			}
		}
	}

}