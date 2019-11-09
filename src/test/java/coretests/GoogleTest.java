package coretests;

import org.testng.annotations.Test;
import static org.testng.Assert.assertTrue;

import java.net.URL;
import java.util.List;
import org.openqa.selenium.By;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

public class GoogleTest {
	
	private WebDriver driver;
	private WebDriverWait wait; //****
	
	@Parameters("browser")
	@BeforeTest
	public void setup(String browsername) throws Exception{
		
		DesiredCapabilities capabilities = null;
		
		switch(browsername){
			case "firefox":
				
				/** Without GRID **/
				//System.setProperty("webdriver.gecko.driver", "/Users/soumyamukherjee/Downloads/geckodriver");
				//driver = new FirefoxDriver();	
				
				
				/*** GRID CODE Section ****/
				capabilities = DesiredCapabilities.firefox();
				capabilities.setBrowserName("firefox");
				capabilities.setPlatform(Platform.MAC);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				/*** GRID CODE Section ****/
				
				break;
			case "chrome":
				
				/** Without GRID **/
				//System.setProperty("webdriver.chrome.driver", "/Users/soumyamukherjee/Downloads/chromedriver");
				//driver = new ChromeDriver();
				
				/*** GRID CODE Section ****/
				capabilities = DesiredCapabilities.chrome();
				capabilities.setBrowserName("chrome");
				capabilities.setPlatform(Platform.MAC);
				driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), capabilities);
				/*** GRID CODE Section ****/
				
				break;
		
		}
		
		wait = new WebDriverWait(driver, 10); //*** This is an example of Fluent Wait ****
		
	}
	
	@Test
	public void googletest() throws Exception{
		driver.get("https://www.google.com");
		driver.manage().window().maximize();
		
		//Enter Automation and then verify the link if automation is present
		driver.findElement(By.name("q")).sendKeys("automation");
		
		wait.until(ExpectedConditions.elementToBeClickable(By.name("btnK"))); //***
		driver.findElement(By.name("btnK")).click();
		
		
		List<WebElement> ele = driver.findElements(By.className("rc")); //***
		System.out.println("Element Size : " + ele.size());
		
		for (WebElement el : ele){
			String searchtext = el.getText().toLowerCase();
			if(searchtext.contains("automation")){
				Assert.assertTrue(true,"Automation word is present");
				break;
			}
			else
				Assert.assertTrue(false,"Automation word is not present");
		}
		
	}
	
	@AfterTest
	public void after(){
		driver.close();
	}
	
	
}
