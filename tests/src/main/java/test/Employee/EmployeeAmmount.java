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

public class EmployeeAmmount {
	public static int adminRole;
	public static int brigadirRole;
	public static int voditelRole;
	public static int dispetcherRole;
	public static int buhgalterRole;
	public static int neregRole;

	public static int checkSummAllEmployee;

	public static Boolean ammountEmployeeSelectRole;

	public static String ammountEmployeeSelectRoleAdmin;
	public static String ammountEmployeeSelectRoleVoditel;
	public static String ammountEmployeeSelectRoleDispetcher;
	public static String ammountEmployeeSelectRoleBuhgalter;
	public static String ammountEmployeeSelectRoleBrigadir;
	public static String ammountEmployeeSelectRoleNereg;

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

		openEmployee(driver);
		selectCity(driver, 0);
		checkAllAmountEmployee(driver);
		openEmployee(driver);
		selectCity(driver, 2);
		ammountRole(driver);

		openEmployee(driver);
		selectCity(driver, 2);

		checkAmmountRole(driver, 1);
		checkAmmountRole(driver, 2);
		checkAmmountRole(driver, 3);
		checkAmmountRole(driver, 4);
		checkAmmountRole(driver, 5);
		checkAmmountRole(driver, 6);
		checkAmmountRole(driver, 7);
	}

	public static void openEmployee(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.get("http://qa.taxi.dancosoft.com/site/employee");
		Thread.sleep(1500);
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

	// проверка, соответствует ли кол-во сотрудников в таблице и в заголовке
	// таблицы
	public static void checkAllAmountEmployee(WebDriver driver) throws InterruptedException {
		if (checkAmmountEmployee(driver, 0) == valueAmmountEmployee(driver, 0)) {
			System.out.println("rigth - check all ammount employee");
		}
	}

	// подчёт сотрудников каждой роли считыванием записей из таблицы построчно
	public static void ammountRole(WebDriver driver) throws InterruptedException {
		boolean flag = true;
		nullVariable();
		String insidePhone1 = "";
		String insidePhone2 = "";
		String role = "";

		Thread.sleep(2500);

		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]"));

		if (elements.size() != 0) {

			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l2 r2']")))
					.click().sendKeys(Keys.ARROW_UP, Keys.ARROW_UP).perform();
			insidePhone1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();
			while (flag == true) {
				Thread.sleep(5);

				role = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l0 r0 selected']"))
						.getText();

				// System.out.println("role - " + role + ".");

				if (role.equals("Администратор")) {
					adminRole++;
				} else if (role.equals("Диспетчер")) {
					dispetcherRole++;
				} else if (role.equals("Бухгалтер")) {
					buhgalterRole++;
				} else if (role.equals("Водитель")) {
					voditelRole++;
				} else if (role.equals("Бригадир")) {
					brigadirRole++;
				} else if (role.equals("Незарегистрированный водитель")) {
					neregRole++;
				} else {
					System.out.println("ERROR - Incorrect role employee.");
				}

				new Actions(driver)
						.moveToElement(
								driver.findElement(By
										.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']")))
						.sendKeys(Keys.ARROW_DOWN).perform();
				insidePhone2 = driver
						.findElement(
								By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
						.getText();

				// System.out.println("insidePhone2 - " + insidePhone2);
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}

				insidePhone1 = insidePhone2;
			}
		}
		checkSummAllEmployee = adminRole + dispetcherRole + buhgalterRole + voditelRole + brigadirRole + neregRole;
		System.out.println("adminRole - " + adminRole);
		System.out.println("dispetcherRole - " + dispetcherRole);
		System.out.println("buhgalterRole - " + buhgalterRole);
		System.out.println("voditelRole - " + voditelRole);
		System.out.println("brigadirRole - " + brigadirRole);
		System.out.println("neregRole - " + neregRole);
	}

	// считывание кол-ва сотрудников по ролям из заголовка таблицы при выборе их
	// в фильтре
	public static void checkAmmountRole(WebDriver driver, int numberRole) throws InterruptedException {
		String insidePhone1 = "*";
		String insidePhone2 = "%";
		int ammount = 0;

		Thread.sleep(500);

		driver.findElement(By.cssSelector("[data-rv-key='currentEmployeeFilter.role']")).click();

		for (int i = 0; i < numberRole; i++) {
			driver.findElement(By.cssSelector("[data-rv-key='currentEmployeeFilter.role']")).sendKeys(Keys.ARROW_DOWN);
			Thread.sleep(5);
		}
		driver.findElement(By.cssSelector("[data-rv-key='currentEmployeeFilter.role']")).sendKeys(Keys.ENTER);
		driver.findElement(By.cssSelector("[data-action='search']")).click();
		Thread.sleep(1000);

		elements = driver
				.findElements(By
						.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l0 r0'][1]"));

		if (elements.size() != 0) {
			new Actions(driver)
					.moveToElement(
							driver.findElement(By
									.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/div[@class='slick-grid-content slick-row even'][1]/div[@class='slick-cell l2 r2']")))
					.click().sendKeys(Keys.ARROW_UP, Keys.ARROW_UP).perform();
			insidePhone1 = driver
					.findElement(
							By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='page-content js-scrollable']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='content-outer']/div[@class='content-bottom']/div[@class='grid-outer']/div[@class='grid-inner']/*/div[@class='slick-viewport']/div[@class='grid-canvas']/*/div[@class='slick-cell l4 r4 selected']"))
					.getText();

			boolean flag = true;
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
				// проверям совпадение предыдущего и текузего считанного
				// позывного
				// для определения конца таблицы
				if (insidePhone1.equals(insidePhone2)) {
					break;
				}

				insidePhone1 = insidePhone2;
			}
		}
		if (Integer.parseInt(driver.findElement(By.cssSelector("[class='badge pull-left js-employee-grid-count']")).getText()) != ammount) {
			System.out.println("ERROR - ammount employeers");
			System.out.println(Integer.parseInt(driver.findElement(By.cssSelector("[class='badge pull-left js-employee-grid-count']")).getText())
					+ " - " + ammount);
		} else {
			System.out.println("rigth - ammount employeers");
		}

	}

	public static void nullVariable() {
		adminRole = 0;
		dispetcherRole = 0;
		buhgalterRole = 0;
		voditelRole = 0;
		brigadirRole = 0;
		neregRole = 0;
	}

}
