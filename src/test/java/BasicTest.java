import BaseObject.TestBase;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;


public class BasicTest {
    WebDriver driver;

    @Test
    public void test(){
        TestBase testBase=new TestBase(driver);
        testBase.openUrl("https://www.google.com");
    }

}
