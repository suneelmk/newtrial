package org.scriptRunner.Magben;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class MavenExample {
 @Test
 public void run()
 {
	 WebDriverManager.firefoxdriver().setup();
	 WebDriver driver=new FirefoxDriver();
	 driver.get("https://www.facebook.com/");
	 driver.close();
 }
}
