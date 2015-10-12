package com.danco.botva;

import org.openqa.selenium.WebDriver;

public class BotvaTest {
	static Utils utils = new Utils();

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = utils.createDriver();
		utils.login(driver, "tarakan0va@mail.ru", "tarakan0va", 1);

		Event_25.purcheseSlaves(driver);

		Thread.sleep(10000);
		driver.close();
	}

}
