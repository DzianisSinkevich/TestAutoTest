package com.danco.botva;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class Event_19 {
	public static void EventRun(WebDriver driver) throws InterruptedException {
		driver.findElement(By.xpath(".//*[@id='event_19']")).click();
		Thread.sleep(2000);
		driver.findElement(By.xpath(".//*[@class='cmd_all cmd_small_sl cmd_asmall_sl ']")).click();
	}
}
