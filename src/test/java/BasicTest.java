import BaseClasses.TestBase;
import BaseClasses.TestRetry;
import BaseObject.LoginPage;
import org.testng.Assert;
import org.testng.annotations.Test;
import org.testng.log4testng.Logger;


public class BasicTest extends TestBase {

    private Logger logger=Logger.getLogger(BasicTest.class);

    @Test(groups = "sanity",description = "Login Test")
    public void test(){

        LoginPage loginPage=new LoginPage(driver);

        logger.info("Open flipkart");
        openUrl("https://www.flipKart.com");
        logger.info("Enter user mobile number");
        loginPage.enterUserName("8971404670");
        logger.info("Enter password");
        loginPage.enterPassword("sonu1961");
        logger.info("Click on login button");
        loginPage.clickLogin();
        logger.info("Verify did user logged in successfully");
        Assert.assertTrue(loginPage.isLoginPopupClosed(),"login popup not closed");
        logger.info("Setting a cooke value");
        setCookie("abRand","45");
        sleep(3000);
        logger.info("Click on main category from home page");
        loginPage.clickMajorCatrgoty();
        sleep(2000);
        logger.info("Click on sub-cat");
        loginPage.clickSubCategory("Realme");
        sleep(2000);
        loginPage.scrollButtomToClickNext();
        String url=getCurrentUrl();
        System.out.println(url);



    }

}
