package com.danco.botva;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;

public class Event_2 {
	static List<Enemy> valueEnemys = new ArrayList<Enemy>();
	static List<String> AllValueEnemys = new ArrayList<String>();
	static List<Integer> idLF = new ArrayList<Integer>();
	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<WebElement> enemys = new ArrayList<WebElement>();

	public static void EventRun(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='event_2']")).click();
		// driver.findElement(By.xpath(".//*[@class='accordion_bgr m1 f_s11 border p5'][1]")).click();
		Thread.sleep(2000);
		if (driver
				.findElement(
						By.cssSelector("[class='cmd_all cmd_small_sl cabbage arena_search cmd_asmall_sl cabbage arena_search  fl_r mt2 can_disable']"))
				.isDisplayed()) {
			driver.findElement(
					By.cssSelector("[class='cmd_all cmd_small_sl cabbage arena_search cmd_asmall_sl cabbage arena_search  fl_r mt2 can_disable']"))
					.click();
		}
		SerchMinEnemy(driver);
	}

	public static Integer SerchMinEnemy(WebDriver driver) throws InterruptedException {
		int idEnemy = 0;
		new Actions(driver).moveToElement(driver.findElement(By.id("arena_enemies")));
		elements = driver.findElements(By.id("arena_enemies"));
		enemys = driver.findElements(By.xpath(".//*[@class='arena_enemy ']"));
		// System.out.println("Kol enemys :" + enemys.size());
		// System.out.println("Value 1 ---:" + elements.get(0).getText());
		Thread.sleep(2000);
		for (WebElement element : elements) {
			AllValueEnemys.add(element.getText());
			break;
		}
		for (int j = 0; j < AllValueEnemys.get(0).length(); j++) {
			if (getCharCode(AllValueEnemys.get(0).charAt(j)) == 10) {
				idLF.add(j);
			}
		}
		String text = elements.get(0).getText();
		for (int i = 0; i < 4; i++) {
			String str = text.substring(idLF.get(i * 3) + 1, idLF.get(i * 3 + 1));
			str = str.replaceAll("\\.", "");
			valueEnemys.add(new Enemy(i + 1, Float.parseFloat(str)));
		}
		Collections.sort(valueEnemys, new ComparatorEnemy());
		driver.findElement(By.xpath(".//*[@class='arena_enemy '][" + valueEnemys.get(0).getId() + "]")).click();
		// System.out.println(valueEnemys.get(0).getId());

		return idEnemy;
	}

	public static int getCharCode(char ch) {
		return (int) ch;
	}
}
