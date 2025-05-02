package org.scriptRunner.Magben;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;

public class DDTPract {
 public static void main(String[] args) throws IOException {
	 FileInputStream fis=new FileInputStream("‪vtiger.properties‪");
		Properties p=new Properties();
		p.load(fis);
		String browser=p.getProperty("Browser");
		String url=p.getProperty("Url");
		String username=p.getProperty("UN");
		String Password=p.getProperty("pwd");
		 WebDriver driver=null;
		 if(browser.equals("firefox"))
		 {
			 driver=new FirefoxDriver();
		 }
		 else if(browser.equals("chrome"))
		 {
			 driver=new ChromeDriver();
		 }
		 driver.get(url);
		 driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
		 driver.findElement(By.name("user_name")).sendKeys(username);
		 driver.findElement(By.name("user_password")).sendKeys(Password);
		 driver.findElement(By.id("submitButton")).click();
	}

	
}


