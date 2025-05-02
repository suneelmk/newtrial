package org.scriptRunner.Magben;

import java.io.File;
import java.io.IOException;

import org.apache.commons.io.FileUtils;
import org.openqa.selenium.OutputType;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.events.EventFiringWebDriver;
import org.testng.annotations.Test;

import io.github.bonigarcia.wdm.WebDriverManager;

public class Screenshot {
  
	@Test
	public void takeScreenShot() throws IOException
	{
		 WebDriverManager.chromedriver().setup();
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		
		//Step 1 create object to EventfiringwebDriver
		
		EventFiringWebDriver  edriver=new EventFiringWebDriver(driver);
		//step 2 use getScreenShotAs method to get file type of screenshot
		File src=edriver.getScreenshotAs(OutputType.FILE);
		//step 3 store screenshot in local driver
		FileUtils.copyFile(src, new File("./screenshot/test.png"));
		
	
	}
	
	
}
