package org.scriptRunner.Magben;

import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

public class AmazonScroll {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		WebDriver driver=new ChromeDriver();
		driver.get("https://www.amazon.in/");
		driver.manage().window().maximize();
		JavascriptExecutor js=(JavascriptExecutor)driver;
		WebElement ele = driver.findElement(By.xpath("//a[.='Sell on Amazon']"));
		
		int xaxis = ele.getLocation().getX();
		int yaxis=ele.getLocation().getY();
		js.executeScript("window.scrollBy("+xaxis+","+yaxis+")");

	}

}
