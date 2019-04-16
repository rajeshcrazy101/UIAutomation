package BaseObject;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

public class BaseClass {

    private Properties configFile=null;
    private String browser=null;
    public WebDriver driver;
    private String os=null;

    //Initiate browsers
    // Initaitate config files
    //Inititatiate db connections
    //Common util initiataions
    //Threadings

    BaseClass(WebDriver driver){
        this.driver=driver;
        initConfig();
        osSelection();
        browserSelection();
        initBrowser();


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

    public WebDriver initBrowser(){
        if (browser.equalsIgnoreCase("chrome")){
            driver=new ChromeDriver();
            driver.manage().window().maximize();
        }else if (browser.equalsIgnoreCase("fireFox")){
            driver=new FirefoxDriver();
            driver.manage().window().maximize();
        }else {
            System.out.println("Error on initialization driver");
        }
        return driver;
    }

}
