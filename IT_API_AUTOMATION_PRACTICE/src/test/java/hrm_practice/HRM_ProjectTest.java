package hrm_practice;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.Duration;
import java.util.Random;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.Select;
import org.testng.annotations.Test;

import com.it.api.genericutility.DataBaseUtility;
import com.mysql.jdbc.Driver;

import static io.restassured.RestAssured.*;

public class HRM_ProjectTest {
	String proj;
	String projectId;

	@Test
	public void endToEndProjectTest() {
		WebDriver driver = new ChromeDriver();
		driver.manage().window().maximize();
		driver.get("http://49.249.28.218:8091/");
		driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(20));
		// login
		driver.findElement(By.id("username")).sendKeys("rmgyantra");
		driver.findElement(By.id("inputPassword")).sendKeys("rmgy@9999");
		driver.findElement(By.xpath("//button[text()='Sign in']")).click();

		// click on project module
		driver.findElement(By.xpath("//a[text()='Projects']")).click();

		// click on create project button
		driver.findElement(By.xpath("//span[text()='Create Project']")).click();

		// random number
		Random rnum = new Random();
		int num = rnum.nextInt(5000);
		proj = "Cyber_Punk" + num;
		// System.out.println(proj);
		// Enter details
		driver.findElement(By.xpath("//input[@name='projectName']")).sendKeys(proj);
		driver.findElement(By.xpath("//input[@name='createdBy']")).sendKeys("Sunil_k");

		WebElement dropdown = driver.findElement(By.xpath("(//select[@name='status'])[2]"));

		Select s = new Select(dropdown);
		s.selectByIndex(1);

		driver.findElement(By.xpath("//input[@type='submit']")).click();

		// fetch project id from frontend

		WebElement iddrop = driver.findElement(By.xpath("(//select[@class='form-control'])[1]"));
		Select s1 = new Select(iddrop);
		s1.selectByIndex(1);

		driver.findElement(By.xpath("//input[@placeholder=Search by Project Name']")).sendKeys(proj);

		WebElement ele = driver.findElement(By.xpath("//tr/td"));
		projectId = ele.getText();
		System.out.println(projectId);
		driver.quit();

	}

	@Test
	public void getProjectTest() {
		// fetch the project details from backend
		get("http://49.249.28.218:8091/project/" + projectId).then().assertThat().statusCode(200).log().all();

	}

	@Test
	public void validateDataBase() throws SQLException {

		Driver d = new Driver();
		DriverManager.registerDriver(d);
		Connection conn = DriverManager.getConnection("jdbc:mysql://49.249.28.218:3333/ninza_hrm", "root", "root");
		Statement stat = conn.createStatement();
		
		
		
		ResultSet result = stat.executeQuery("select project_id from project where project_name='" + proj + "'");
		
		//String actId = result.getString("project_id");
		
		//String actId = result.getString("project_id");
		  while(result.next())
		  {
			  String actId = result.getString("project_id");
			  if(actId.equals(projectId)) {
		  System.out.println(proj+"data verified in database table");
		  
		  }else { System.out.println(proj+"data not verified in database table");
		  
		  }
		  }
		conn.close();

	}
}
