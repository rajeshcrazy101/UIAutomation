import com.assertthat.selenium_shutterbug.core.Shutterbug;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.Cookie;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.Wait;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.ITestResult;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import java.io.FileReader;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Properties;


public class TestBase {

    private Properties configFile=null;
    private String browser=null;
    public WebDriver driver;
    private String os=null;

    @BeforeClass
    public void init(){
        initConfig();
        osSelection();
        browserSelection();
    }

    @BeforeMethod
    public void browserInit(){
        initBrowser();
    }

    @AfterMethod
    public void tearDown(ITestResult result){
        if (result.getStatus()==ITestResult.FAILURE){
            takeScreenshot(result);
        }
        if(driver!=null)
        driver.quit();
    }

    private void initConfig(){
        configFile = new Properties();
        try {
            configFile.load(new FileReader("src/config.properties"));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private String osSelection(){
        return System.getProperty("os.name");
    }

    private void browserSelection(){
        System.out.println(System.getProperty("os.name"));
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

    private WebDriver initBrowser(){
        if (driver==null) {
            if (browser.equalsIgnoreCase("chrome")) {
                driver = new ChromeDriver();
                driver.manage().window().maximize();
            } else if (browser.equalsIgnoreCase("fireFox")) {
                driver = new FirefoxDriver();
                driver.manage().window().maximize();
            } else {
                System.out.println("Error on initialization driver");
            }
        }
            return driver;
    }

    public void openUrl(String url){
        driver.get(url);
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
        List<String> stringList= new ArrayList<String>(driver.getWindowHandles());
        for (int i=0;i<stringList.size();i++){
            driver.switchTo().window(stringList.get(tab));
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
