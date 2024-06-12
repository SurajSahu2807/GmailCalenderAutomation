package demo;

import java.util.List;
import java.util.concurrent.TimeUnit;

import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.Select;

import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    ChromeDriver driver;
    
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        WebDriverManager.chromedriver().timeout(30).setup();
        ChromeOptions options = new ChromeOptions();
        LoggingPreferences logs = new LoggingPreferences();

        // Set log level and type
        logs.enable(LogType.BROWSER, Level.INFO);
        logs.enable(LogType.DRIVER, Level.ALL);
        options.setCapability("goog:loggingPrefs", logs);

        // Connect to the chrome-window running on debugging port
        options.setExperimentalOption("debuggerAddress", "127.0.0.1:9222");

        // Set path for log file
        System.setProperty(ChromeDriverService.CHROME_DRIVER_LOG_PROPERTY, "chromedriver.log");

        driver = new ChromeDriver(options);

        // Set browser to maximize and wait
        // driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();

    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        driver.get("https://calendar.google.com/calendar/u/0/r?pli=1");
        String Currenturl = driver.getCurrentUrl();
        if(Currenturl.contains("calendar")){
            System.out.println("The URL of the Calendar homepage contains \"calendar\"");
        }else{
            System.out.println("The URL of the Calendar homepage doesn't contains \"calendar\".");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        try{
            System.out.println("Start Test case: testCase02");
            WebElement DropDownBox = driver.findElement(By.xpath("//span[text()='Day' and @class='VfPpkd-vQzf8d']"));
            DropDownBox.click();
            WebElement MonthViewOpt = driver.findElement(By.xpath("//span[text()='Month']"));
            MonthViewOpt.click();
            // WebElement TodayDate = driver.findElement(By.xpath("//h2[@class='w48V4c ubOFEd F262Ye']//parent::div"));
            // TodayDate.click();
            String CurrentDate = "13";
            List<WebElement> Alldates = driver.findElements(By.xpath("//h2[@class='w48V4c ubOFEd']"));
            for(WebElement e : Alldates){
                if(e.getText().equals(CurrentDate)){
                    e.click();
                    Thread.sleep(2000);
                }
            }
            System.out.println("end Test case: testCase02");
        }catch(Exception e){
            e.printStackTrace();
        }   
    }


}
