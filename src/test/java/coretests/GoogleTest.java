package coretests;

import static org.testng.Assert.assertTrue;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleTest {
	
	private WebDriver driver;
	
	@BeforeTest
	public void setup(){	
		System.setProperty("webdriver.chrome.driver", "/Users/soumyamukherjee/Downloads/chromedriver");
		driver = new ChromeDriver();
	}
	
	@Test
	public void googletest() throws Exception{
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		//Enter Automation and then verify the link if automation is present
		driver.findElement(By.name("q")).sendKeys("automation");
		Thread.sleep(5000);
		driver.findElement(By.name("btnK")).click();
		
		Thread.sleep(5000);
		String searchtext = driver.findElement(By.xpath("//*[@id=\"rso\"]/div[1]/div/div/div/div[1]/a/h3")).getText();
		if(searchtext.toLowerCase().contains("automation"))
			assertTrue(true,"Automation word is present");
		else
			assertTrue(false,"Automation word is not present");
		
	}
	
	@AfterTest
	public void after(){
		driver.close();
	}
	
	
}
