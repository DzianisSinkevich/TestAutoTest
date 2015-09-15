package test;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;

public class AddDeleteSettings {

	static String settingsCity = "";

	static String parity = "";
	static String role = "";
	static int i = 0;

	static ArrayList<String> textOfOptions = new ArrayList<String>();

	static List<WebElement> elements = new ArrayList<WebElement>();

	public static void main(String[] args) throws InterruptedException {
		textOfOptions.add("Test text 1.");
		textOfOptions.add("Test text 2.");
		textOfOptions.add("Test text 3.");
		textOfOptions.add("Test text 4.");
		textOfOptions.add("Test text 5.");
		textOfOptions.add("Test text 6.");

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

		driver.get("http://qa.taxi.dancosoft.com/site/settings");

		addSettings(driver, 1, "Параметры водителей", "Плата за прием водителя на работу", 100000, 0);
		addSettings(driver, 1, "Параметры водителей", "Минимально допустимая сумма баланса", 50000, 0);
		addSettings(driver, 1, "Параметры водителей", "Минимальная сумма баланса для предупреждающей СМС", 100000, 0);
		addSettings(driver, 1, "Параметры водителей", "Стоимость СМС сообщения для водителей", 1000, 0);
		addSettings(driver, 1, "Параметры водителей", "Штраф за неподтвержденный предварительный заказ", 50000, 0);
		addSettings(driver, 3, "Параметры водителей", "Скрывать адрес назначения (при распределении)", 1, 0);
		addSettings(driver, 3, "Параметры водителей", "Скрывать номер дома в адресе подачи (при распределении)", 1, 0);
		addSettings(driver, 1, "Параметры водителей", "Паказывать стоянки в радиусе (км)", 100, 0);
		addSettings(driver, 2, "Параметры водителей", "Округление суммы на таксометре", 1, 100000);
		addSettings(driver, 3, "Параметры водителей", "Отдельная очередь для каждого типа т/с в водительском приложении", 1, 0);
		addSettings(driver, 1, "Параметры водителей", "Максимальное количество пропущенных заказов", 10, 0);
		addSettings(driver, 4, "Параметры водителей", "Текст СМС со ссылкой вод. приложения", 1, 0);
		addSettings(driver, 1, "Параметры водителей", "Средняя скорость по городу", 100, 0);

		Thread.sleep(1000);

		driver.quit();
	}

	// добавление параметра
	private static void addSettings(WebDriver driver, Integer valueType, String parType, String parName, Integer value1, Integer value2)
			throws InterruptedException {
		int randomValue;

		Thread.sleep(500);

		selectCity(driver);
		driver.findElement(By.cssSelector("[data-action='addSetting']")).click();
		driver.findElement(By.cssSelector("[data-rv-checked='setting.startAtNow']")).click();
		Thread.sleep(1000);

		selectObejctConfiguration(driver, parType);

		selectParametr(driver, parName);

		Thread.sleep(500);

		switch (valueType) {
		case 1: {
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Integer.toString((int) (Math.random() * value1)));
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ENTER);
			break;
		}
		case 2: {
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Integer.toString(value1 + (int) (Math.random() * value2)));
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ENTER);
			break;
		}
		case 3: {
			randomValue = (int) (Math.random() * 2);
			if (randomValue == 0) {
				driver.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='content-outer']/div[@class='content-top js-setting-panel']/div[@class='container-fluid']/div[3]/div[2]/div[@class='block'][3]/div[@class='row offset-top']/div[@class='col-xs-6 col-xs-offset-5']/label[@class='checkbox-inline']"))
						.click();
			} else {
				driver.findElement(
						By.xpath("/html/body/div[@class='application js-sidebar-min ']/div[@class='page-content-outer js-instance-change js-mass-sending js-app-root hide-weak js-loaded']/div[@class='infopanel-outer hide-weak js-infopanel js-scrollable ui-resizable']/div[@class='infopanel']/div[@class='content-outer']/div[@class='content-top js-setting-panel']/div[@class='container-fluid']/div[3]/div[2]/div[@class='block'][3]/div[@class='row offset-bot']/div[@class='col-xs-6 col-xs-offset-5']/label[@class='checkbox-inline']"))
						.click();
			}
			break;
		}
		case 4: {
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(textOfOptions.get(1 + (int) (Math.random() * 6)));
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ENTER);
			break;
		}
		case 5: {
			for (int i = 0; i < (int) (Math.random() * 10); i++) {
				driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ARROW_DOWN);
			}
			driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ENTER);
			break;
		}
		}

		Thread.sleep(2000);

		if (checkStatusButtonSave(driver)) {
			driver.findElement(By.cssSelector("[data-action='saveSettingReal']")).click();
		} else {
			System.out.println("ERROR - Button Save is not available");
		}
	}

	// проверка доступности кнопки Сохранить
	public static boolean checkStatusButtonSave(WebDriver driver) throws InterruptedException {
		Thread.sleep(1000);

		if (driver.findElement(By.cssSelector("[data-action='saveSettingReal']")).isEnabled()) {
			return true;
		} else {
			return false;
		}
	}

	// выбор города с помощью клавиатуры
	public static void selectCity(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).click();
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.DELETE);

		for (int i = 0; i < 1; i++) {
			driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ARROW_DOWN);
		}
		driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).sendKeys(Keys.ENTER);
		Thread.sleep(1500);

		settingsCity = driver.findElement(By.cssSelector("[data-rv-value='modelCityFilter.cityName']")).getAttribute("value");
		System.out.println(settingsCity);
	}

	// выбор объекта конфигурации с помощью клавиатуры
	public static void selectObejctConfiguration(WebDriver driver, String parType) throws InterruptedException {
		int tempK = 1;

		Thread.sleep(500);

		while (!driver.findElement(By.cssSelector("[data-rv-key='setting.category']")).getAttribute("value").equals(parType)) {
			for (int i = 0; i < tempK; i++) {
				driver.findElement(By.cssSelector("[data-rv-key='setting.category']")).sendKeys(Keys.DOWN);
			}
			driver.findElement(By.cssSelector("[data-rv-key='setting.category']")).sendKeys(Keys.ENTER);
			tempK++;
		}
	}

	// выбор параметра с помощью клавиатуры
	public static void selectParametr(WebDriver driver, String parName) throws InterruptedException {
		int tempK = 1;

		Thread.sleep(500);
		driver.findElement(By.cssSelector("[data-rv-source='sources.keys']")).click();
		while (!driver.findElement(By.cssSelector("[data-rv-source='sources.keys']")).getAttribute("value").equals(parName)) {
			for (int i = 0; i < tempK; i++) {
				driver.findElement(By.cssSelector("[data-rv-source='sources.keys']")).sendKeys(Keys.DOWN);
			}
			driver.findElement(By.cssSelector("[data-rv-source='sources.keys']")).sendKeys(Keys.ENTER);
			tempK++;
		}
	}

	// ввод значения параметра
	public static void inputValue(WebDriver driver) throws InterruptedException {
		Thread.sleep(500);

		driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Integer.toString((int) (Math.random() * 4000)));
		driver.findElement(By.cssSelector("[data-rv-value='setting.value']")).sendKeys(Keys.ENTER);
	}
}
