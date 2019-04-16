package BaseObject;

import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;

public class TestBase extends BaseClass{

    public TestBase(WebDriver driver) {
        super(driver);
    }

    public void openUrl(String url){
        driver.get(url);
    }

    @AfterMethod(alwaysRun = true)
    public void quitdriver(){
        driver.quit();
    }
}
