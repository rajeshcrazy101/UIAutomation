package BaseClasses;

import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.log4testng.Logger;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;

/**
 * Initiating a driver and added common methods for test.
 **/
public class TestBase {

    private Properties configFile=null;
    private String browser=null;
    public RemoteWebDriver driver;
    private String os=null;
    public Properties testDataFile=null;

    public Logger logger=Logger.getLogger(TestBase.class);

    /**
     * Initiating config and selecting the browser
     * */
    @BeforeClass(alwaysRun = true)
    public void init(){
        initConfig();
        initTestDatafile();
        osSelection();
        browserSelection();
    }

    /**
     * Initiating the driver
     * */
    @BeforeMethod(alwaysRun = true)
    public void browserInit(){
        initDriver();
    }

    /**
    * Taking screenshot and quiting the driver.
    * */
    @AfterMethod(alwaysRun = true)
    public void tearDown(ITestResult result){

        if (result.getStatus()==ITestResult.FAILURE){
            takeScreenshot(result);
            driver.quit();
        }
        if(driver!=null) {
            driver.quit();
        }
    }

    private void initConfig(){
        configFile = new Properties();
        try {
            configFile.load(new FileReader("src/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void initTestDatafile(){
        testDataFile = new Properties();
        try {
            testDataFile.load(new FileReader("src/test/java/TestData.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String osSelection(){
        return System.getProperty("os.name");
    }

    private void browserSelection(){
        os=osSelection().split(" ")[0].trim();
        os=configFile.getProperty("os");
        if (os.equalsIgnoreCase("MAC") || os.equalsIgnoreCase("WINDOWS")) {
            browser = configFile.getProperty("browser");
            if (browser.equalsIgnoreCase("chrome")) {
                WebDriverManager.chromedriver().setup();
            } else if (browser.equalsIgnoreCase("firefox")) {
                WebDriverManager.firefoxdriver().setup();
            } else {
                System.out.println("Selected browser is not added on property file");
            }
        }else {
            System.out.println("Error on OS selection");
        }

    }
    
    private RemoteWebDriver initDriver() {
        DesiredCapabilities capabilities;
            if (browser.equalsIgnoreCase("chrome")) {
                capabilities = DesiredCapabilities.chrome();

            } else if (browser.equalsIgnoreCase("firefox")) {
                capabilities = DesiredCapabilities.firefox();

            } else {
                throw new RuntimeException("Driver not initiated");
            }
            try {
                driver = new RemoteWebDriver(new URL(configFile.getProperty("URL")),capabilities);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            }
        return driver;
    }

    public void openUrl(String url){
        driver.get(url);
        driver.manage().window().maximize();
    }

    public String getCurrentUrl(){
        return driver.getCurrentUrl();
    }

    public boolean waitForPageToLoad(String pageUrl){
        //driver.manage().timeouts().pageLoadTimeout(10, TimeUnit.SECONDS);
        JavascriptExecutor js=(JavascriptExecutor)driver;
        return driver.getCurrentUrl().contains(pageUrl) && js.executeScript("return document.readyState").equals("complete");

    }

    public void sleep(long mili){
        try {
            Thread.sleep(mili);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void setCookie(String name,String value){
        driver.manage().addCookie(new Cookie(name,value));
        driver.navigate().refresh();
    }

    public void waitTillUrlToBe(String url){
        WebDriverWait wait=new WebDriverWait(driver,10);
        wait.until(ExpectedConditions.urlToBe(url));
    }


   public void switchTo(int tab){
        List<String> windowList= new ArrayList<String>(driver.getWindowHandles());
        for (int i=0;i<windowList.size();i++){
            driver.switchTo().window(windowList.get(tab));
        }
    }

    public String getPageTitle(){
        return driver.getTitle();
    }

    public String getPageSource(){
        return driver.getPageSource();
    }

    public void acceptBrowserAlert(){
        driver.switchTo().alert().accept();
    }

    public void dismissBrowserAlert(){
        driver.switchTo().alert().dismiss();
    }

    public String getTextFromBrowserAlert(){
        return driver.switchTo().alert().getText();
    }

    public void takeScreenshot(ITestResult result){
        String timeStamp = new SimpleDateFormat("yyyyMMdd_HHmmss").format(Calendar.getInstance().getTime());
        System.out.println(timeStamp);
        String currentDir = System.getProperty("user.dir");
        Shutterbug.shootPage(driver).withName(result.getMethod().getMethodName()).save(currentDir + "/src/test/java/FailedTestScreeShot/" + timeStamp + ".png");
    }


}
