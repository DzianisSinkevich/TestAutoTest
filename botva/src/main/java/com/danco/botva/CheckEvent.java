package com.danco.botva;

import java.util.ArrayList;
import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class CheckEvent {
	static List<WebElement> elements = new ArrayList<WebElement>();
	static List<String> typesElements = new ArrayList<String>();

	public static void getEvents(WebDriver driver) throws InterruptedException {
		elements.clear();
		elements = driver
				.findElements(By
						.xpath("/html[@class=' desktop portrait']/body[@class='lang_RU  old_bg2  race_1']/div[@id='container']/div[@id='content-container']/div[@id='right']/div[@id='rmenu2']/div[@id='accordion']/div[@class='tab_right']/div[@class='scroll']/div[@class='jspContainer']/div[@class='jspPane']/div[@id='events_scroll']/*"));
	}

	public static ArrayList<String> getListEvent(WebDriver driver) throws InterruptedException {
		getEvents(driver);
		ArrayList<String> typeEvent = new ArrayList<String>();
		for (WebElement element : elements) {
			typeEvent.add(element.getAttribute("id").substring(6));
		}
		return typeEvent;
	}
}
