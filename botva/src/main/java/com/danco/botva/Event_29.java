package com.danco.botva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Event_29 {
	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<String> typesElements = new ArrayList<String>();
	static List<Integer> idLF = new ArrayList<Integer>();
	static List<Enemy> valueEnemys = new ArrayList<Enemy>();

	public static void EventRun(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='event_29']")).click();
		Thread.sleep(2000);
		elements = driver.findElements(By.id("selector"));
		for (WebElement element : elements) {
			typesElements.add(element.getText());
			System.out.println("elements: " + typesElements);
			break;
		}
		for (int j = 0; j < typesElements.get(0).length(); j++) {
			if (getCharCode(typesElements.get(0).charAt(j)) == 10) {
				idLF.add(j);
			}
		}
		valueEnemys.add(new Enemy(7, Float.parseFloat(typesElements.get(0).substring(0, idLF.get(0) + 1))));
		for (int i = 0; i < 6; i++) {
			valueEnemys.add(new Enemy(i + 1, Float.parseFloat(typesElements.get(0).substring(idLF.get(i), idLF.get(i + 1)))));
		}
		valueEnemys.add(new Enemy(7, Float.parseFloat(typesElements.get(0).substring(idLF.get(5), idLF.get(6) + 1))));
		valueEnemys
				.add(new Enemy(8, Float.parseFloat(typesElements.get(0).substring(idLF.get(6), typesElements.get(0).length()))));
		System.out.println(valueEnemys);

		Collections.sort(valueEnemys, new ComparatorEnemy());
		System.out.println("getID: " + valueEnemys.get(0).getId());
		driver.findElement(By.xpath(".//*[@class='skills skill_54" + valueEnemys.get(0).getId() + "']")).click();
		Thread.sleep(1000);
		driver.findElement(By.cssSelector("[class='cmd_all green  cmd_large cmd_alarge ']")).click();
	}

	public static int getCharCode(char ch) {
		return (int) ch;
	}
}
