package com.danco.botva;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class Event_25 {
	static List<WebElement> elements2 = new ArrayList<WebElement>();
	static List<Integer> idLF = new ArrayList<Integer>();
	static String tempStr;
	static List<String> namePurchese = new ArrayList<String>();

	public static void EventRun(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@href='/castle.php?a=workshop_farm']")).click();
		Thread.sleep(2000);
		if (Integer.parseInt(driver.findElement(By.id("slaves_count")).getText()) > 0) {
			driver.findElement(By.cssSelector("[class='cmd_all green  cmd_row4 cmd_arow4 ']")).click();
		} else {
			purcheseSlaves(driver);
		}
	}

	public static void purcheseSlaves(WebDriver driver) throws InterruptedException {
		driver.findElement(By.cssSelector("[href='/clan_mod.php?m=bmarket']")).click();
		Thread.sleep(1000);

		List<WebElement> elements = new ArrayList<WebElement>();
		elements = driver.findElements(By.cssSelector("[class='profile_statistic bmarket_clan_buy_table']"));
		String elementsStr = "";
		elementsStr = elements.get(0).getText();
		// System.out.println("ALL ELEMENTS -:" + elementsStr);

		for (int j = 0; j < elementsStr.length(); j++) {
			if (getCharCode(elementsStr.charAt(j)) == 10) {
				idLF.add(j);
			}
		}
		System.out.println("KolElements 2 :" + elements2.size());

		for (int i = 0; i < 30; i++) {
			tempStr = elementsStr.substring(idLF.get(i) + 1, idLF.get(i + 1));
			System.out.println("tempTsr = " + tempStr);
			for (int j = 0; j < 7; j++) {
				tempStr = tempStr.substring(0, tempStr.lastIndexOf(" "));
			}
			tempStr = tempStr.substring(tempStr.lastIndexOf(" ") + 1, tempStr.length());
			if (!tempStr.equals("-")) {
				driver.findElement(
						By.xpath("/html[@class=' desktop portrait']/body[@class='lang_RU  old_bg2  race_1']/div[@id='container']/div[@id='content-container']/div[@id='content']/div[@id='body']/div[@class='round_block_round_border round_block_header_cont pt10 pb5 lh120']/table[@class='profile_statistic bmarket_clan_buy_table'][1]/tbody/tr["
								+ (i + 2) + "]/td[@class='name pt3 pb3']/a[@class='profile']")).click();
				Thread.sleep(1000);
				idLF.clear();
				elements = driver.findElements(By.id("want_to_buy"));
				elementsStr = elements.get(0).getText();
				System.out.println(elementsStr);
				for (int j = 0; j < elementsStr.length(); j++) {
					if (getCharCode(elementsStr.charAt(j)) == 10) {
						idLF.add(j);
					}
				}
				namePurchese.add(elementsStr.substring(0, idLF.get(0)));
				System.out.println(elementsStr);
				for (int k = 0; k + 1 < idLF.size(); k++) {
					namePurchese.add(elementsStr.substring(idLF.get(k) + 1, idLF.get(k + 1)));
				}
				System.out.println(namePurchese);

				for (int j = 0; j < namePurchese.size(); j++) {
					if (!namePurchese.get(j).contains("Раб людишко")) {
						driver.findElement(By.id("want_to_buy")).sendKeys(Keys.ARROW_DOWN);
					} else {
						driver.findElement(By.cssSelector("[class='cmd_all green  cmd_large cmd_alarge ']")).click();
						break;
					}
				}
				break;
			}
		}

	}

	public static int getCharCode(char ch) {
		return (int) ch;
	}
}
