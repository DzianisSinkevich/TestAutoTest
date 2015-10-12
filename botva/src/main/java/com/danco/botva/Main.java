package com.danco.botva;

import java.util.ArrayList;

import org.openqa.selenium.WebDriver;

public class Main {
	static Utils utils = new Utils();

	public static void main(String[] args) throws InterruptedException {
		WebDriver driver = utils.createDriver();
		utils.login(driver, "tarakan0va@mail.ru", "tarakan0va", 1);
		for (int i = 0; i < 10000; i++) {
			Thread.sleep(10000);
			ArrayList<String> typeEvent = CheckEvent.getListEvent(driver);
			for (String s : typeEvent) {
				switch (s) {
				case "2": {
					Event_2.EventRun(driver);
					break;
				}
				case "19": {
					Event_19.EventRun(driver);
					break;
				}
				case "25": {
					Event_25.EventRun(driver);
					break;
				}
				case "29": {
					Event_29.EventRun(driver);
					break;
				}
				case "": {
					break;
				}
				default:
					break;
				}
				driver.get("http://g1.botva.ru/index.php");
			}
			driver.get("http://g1.botva.ru/index.php");
		}
	}

}
