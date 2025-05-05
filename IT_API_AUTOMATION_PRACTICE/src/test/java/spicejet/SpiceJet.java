package spicejet;

import java.time.Duration;

import org.openqa.selenium.Alert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

public class SpiceJet {
	@Test(enabled=false)
	public void spiceJet()
	{
		//WebDriver driver=new ChromeDriver();
		
		ChromeOptions op=new ChromeOptions();
		op.addArguments("--disable-notifications");
		WebDriver driver=new ChromeDriver(op);
		driver.get("https://www.spicejet.com/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		/*
		 * Alert a=driver.switchTo().alert(); a.accept();
		 */
		//driver.findElement(By.xpath("//div[text()='To']")).click();
		
	}
	@Test
	public void redBus()
	{
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.redbus.in/");
		driver.manage().window().maximize();
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		WebElement ele = driver.findElement(By.xpath("//input[@id='src']"));
		ele.click();
		ele.sendKeys("Bengaluru");
		driver.findElement(By.xpath("//text[.='Bengaluru']")).click();
		
		
		WebElement ele2 = driver.findElement(By.xpath("//input[@id='dest']"));
		ele2.click();
		ele2.sendKeys("Hubli");
		driver.findElement(By.xpath("//text[.='Hubli']")).click();
		 
		
		driver.findElement(By.xpath("//div[@id='onwardCal']")).click();
		driver.findElement(By.xpath("//span[.='20']")).click();
		driver.findElement(By.xpath("//button[.='SEARCH BUSES']")).click();
	}

}
