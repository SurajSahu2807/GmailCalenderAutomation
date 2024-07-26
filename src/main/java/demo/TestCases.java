package demo;

import java.time.Duration;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.devtools.v116.indexeddb.model.Key;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.logging.LogType;
import org.openqa.selenium.logging.LoggingPreferences;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.logging.Level;
import io.github.bonigarcia.wdm.WebDriverManager;


public class TestCases {
    WebDriver driver;
    
    public TestCases()
    {
        System.out.println("Constructor: TestCases");

        // WebDriverManager.chromedriver().timeout(30).setup();
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
        driver.manage().timeouts().pageLoadTimeout(Duration.ofSeconds(30));
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));
        
        // Set browser to maximize and wait9
        driver.manage().window().maximize();
    }

    public void endTest()
    {
        System.out.println("End Test: TestCases");
        driver.close();
        driver.quit();
    }

    
    public  void testCase01(){
        System.out.println("Start Test case: testCase01");
        // Navigating to url
        driver.get("https://calendar.google.com/calendar/u/0/r?pli=1");
        String Currenturl = driver.getCurrentUrl();
        // Verifying that the current url contains "calender"
        if(Currenturl.contains("calendar")){
            System.out.println("The URL of the Calendar homepage contains \"calendar\"");
        }else{
            System.out.println("The URL of the Calendar homepage doesn't contains \"calendar\".");
        }
        System.out.println("end Test case: testCase01");
    }

    public void testCase02() throws InterruptedException{
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
            System.out.println("Start Test case: testCase02");
            // Clicking on the switcher menu dropdown
            WebElement SwitcherDropDown = driver.findElement(By.xpath("//button[contains(@class,'VfPpkd-LgbsSe-OWXEXe-INsAgc') and @aria-haspopup='menu']"));
            SwitcherDropDown.click();
            // Waiting until the Popup is  visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'VfPpkd-xl07Ob-XxIAqe VfPpkd-xl07Ob')]")));
            // Switch to the Day view
            WebElement DayView = driver.findElement(By.xpath("//span[@jsname='K4r5Ff' and text()='Day']"));
            DayView.click();
            By AfterSwitchText1 = By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Day']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(AfterSwitchText1));
            driver.findElement(By.xpath("//button[contains(@class,'VfPpkd-LgbsSe-OWXEXe-INsAgc') and @aria-haspopup='menu']")).click();
            wait.until(ExpectedConditions.visibilityOfElementLocated(By.xpath("//div[contains(@class,'VfPpkd-xl07Ob-XxIAqe VfPpkd-xl07Ob')]")));
            WebElement MonthVeiw = driver.findElement(By.xpath("//span[@jsname='K4r5Ff' and text()='Month']"));
            MonthVeiw.click();
            // Verify wheather we switched to the month view successfully or not
            By AfterSwitchText2 = By.xpath("//span[@class='VfPpkd-vQzf8d' and text()='Month']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(AfterSwitchText2));
            if(driver.findElement(AfterSwitchText2).getText().equals("Month")){
                System.out.println("switching to the month view");
            }
            // Clicking on the CreateDropDown button
            WebElement CreateDropDownEle = driver.findElement(By.xpath("//div[@aria-label='Create' and @role='button']"));
            Actions action = new Actions(driver);
            action.moveToElement(CreateDropDownEle).click().build().perform();
            // Waiting until the popup is visible
            By CreateDropDownPopup = By.xpath("//span[@jsname='j7LFlb']");
            wait.until(ExpectedConditions.elementToBeClickable(CreateDropDownPopup));
            List<WebElement> AlEle = driver.findElements(By.xpath("//div[@class='jO7h3c']"));
            for(WebElement ele : AlEle){
                if(ele.getText().equals("Task")){
                    ele.click();
                }
            }
            By CreatetaskPopup = By.xpath("//div[@class='jO7h3c']");
            // waiting until the Popup is visible
            wait.until(ExpectedConditions.visibilityOfElementLocated(CreatetaskPopup));
            // Adding title for the task
            WebElement AddTaskTitle = driver.findElement(By.xpath("//input[@placeholder='Add title']"));
            AddTaskTitle.sendKeys("Crio INTV Task Automation");
            // Adding description for the task
            WebElement AddDescriptionEle = driver.findElement(By.xpath("//*[@placeholder='Add description']"));
            AddDescriptionEle.sendKeys("Crio INTV Calendar Task Automation");
            // Click on the Save button
            WebElement SaveBtnEle = driver.findElement(By.xpath("//button[@jsname='x8hlje']"));
            SaveBtnEle.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text() = 'Task Created']")));
            System.out.println("end Test case: testCase02");
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public void testCase03(){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(15));
            System.out.println("Start Test case: testCase03");
            // Clicking on the Existing task
            WebElement ExistingTaskDescEle = driver.findElement(By.xpath(
                    "//div[@class='KF4T6b smECzc jKgTF QGRmIf' and @role='button']"));
            ExistingTaskDescEle.click();
            By existingTaskPopup = By.xpath("//div[@class='FzsTU']");
            // waiting for the visibility of the existing task
            wait.until(ExpectedConditions.visibilityOfElementLocated(existingTaskPopup));
            // Click on the EditTask Button
            WebElement EditTaskEle = driver.findElement(By.xpath("//button[@aria-label='Edit task']"));
            EditTaskEle.click();
            WebElement AddDescriptionEle = driver.findElement(By.xpath("//*[@placeholder='Add description']"));
            // Updating the task description
            Actions action = new Actions(driver);
            action.moveToElement(AddDescriptionEle).click().keyDown(Keys.CONTROL).sendKeys("A").keyUp(Keys.CONTROL).build().perform();
            AddDescriptionEle.sendKeys(
                    "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application");
            WebElement SaveBtnEle = driver.findElement(By.xpath("//button[@jsname='x8hlje']"));
            // clicking on the save button
            SaveBtnEle.click();
            wait.until(ExpectedConditions.invisibilityOfElementLocated(By.xpath("//*[text() = 'Task Updated']")));
            ExistingTaskDescEle.click();
            By ExistingTaskDetailsEle = By.xpath("//div[@class='ecHOgf RDlrG Inn9w iWO5td']");
            wait.until(ExpectedConditions.visibilityOfElementLocated(ExistingTaskDetailsEle));
            WebElement ModifiedDescripEle = driver.findElement(By.xpath("//div[@id='xDetDlgDesc']"));            
            String ModifiedDescription = ModifiedDescripEle.getText();
            String ExpectedDescription = "Crio INTV Task Automation is a test suite designed for automating various tasks on the Google Calendar web application";
            if(ModifiedDescription.contains(ExpectedDescription)){
                System.out.println("The task description is successfully updated and displayed.");
            }
            System.out.println("end Test case: testCase03");
        }catch(Exception e){
            e.printStackTrace();
        }   
    }

    public void testCase04(){
        try{
            WebDriverWait wait = new WebDriverWait(driver,Duration.ofSeconds(5));
            System.out.println("Start Test case: testCase04");
            WebElement descriptionEle = driver.findElement(By.xpath("//span[@role='heading' and @id='rAECCd']"));
            String ActualText = descriptionEle.getText();
            String ExpectedText = "Crio INTV Task Automation";
            WebElement DeleteBtnEle = driver.findElement(By.xpath(
                    "//button[@class='VfPpkd-Bz112c-LgbsSe yHy1rc eT1oJ mN1ivc m2yD4b HfYfLe' and @aria-label='Delete task']"));
            // Verifying whather the ExpectedTaskTitle meets the actualTaskTitle
            if(ActualText.equals(ExpectedText)){
                DeleteBtnEle.click();
                WebElement AlertTaskEle = driver.findElement(By.xpath("//div[@class='VYTiVb']"));
                // ToCheckWheatherTaskGetsDeleted(AlertTaskEle,"Task deleted", "the alert text is equal to Task deleted");
                //The task is successfully deleted, and the confirmation message indicates \"Task deleted\"
                    if(AlertTaskEle.getText().contains("Task deleted")){
                        System.out.println("Task deleted");
                    }
            }
            System.out.println("end Test case: testCase04");
        }catch(Exception e){    
            e.printStackTrace();
        }   

       
        
    }

    // public static void ToCheckWheatherTaskGetsDeleted(WebElement ss , String tt, String printStatement){
    // if(ss.isDisplayed()){
    //     System.out.println("the alert Task deleted has been displayed");
    //     if(ss.getText().equals(tt)){
    //         System.out.println(printStatement);
    //     }
    //  }
    // }
}
