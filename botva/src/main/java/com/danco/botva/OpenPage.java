package com.danco.botva;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class OpenPage {
	
	public void openPageTab(WebDriver driver, String page, String tab) throws InterruptedException{
		Thread.sleep(500);
		driver.get(page);
		Thread.sleep(1500);
		driver.findElement(By.cssSelector(tab)).click();
		Thread.sleep(2000);
	}
	
	public void openPage(WebDriver driver, String page) throws InterruptedException{
		Thread.sleep(500);
		driver.get(page);
		Thread.sleep(1500);
	}
}
