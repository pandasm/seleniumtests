package coretests;

import static org.testng.Assert.assertTrue;

import java.util.List;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

public class GoogleTest {
	
	private WebDriver driver;
	private WebDriverWait wait;
	
	@BeforeTest
	public void setup(){	
		System.setProperty("webdriver.chrome.driver", "/Users/soumyamukherjee/Downloads/chromedriver");
		driver = new ChromeDriver();
		wait = new WebDriverWait(driver, 10);
	}
	
	@Test
	public void googletest() throws Exception{
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		//Enter Automation and then verify the link if automation is present
		driver.findElement(By.name("q")).sendKeys("automation");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK")));
		driver.findElement(By.name("btnK")).click();
		
		
		List<WebElement> ele = driver.findElements(By.className("rc"));
		System.out.println("Element Size : " + ele.size());
		
		for (WebElement el : ele){
			String searchtext = el.getText().toLowerCase();
			if(searchtext.contains("automation")){
				assertTrue(true,"Automation word is present");
				break;
			}
			else
				assertTrue(false,"Automation word is not present");
		}
		
	}
	
	@AfterTest
	public void after(){
		driver.close();
	}
	
	
}
