import BaseObject.LoginPage;
import org.testng.annotations.Test;


public class BasicTest extends TestBase{


    @Test
    public void test(){
        LoginPage loginPage=new LoginPage(driver);
        openUrl("https://www.flipKart.com");
        loginPage.enterUserName("8971404670");
        loginPage.enterPassword("sonu1961");
        sleep(2000);
        loginPage.clickLogin();
        setCookie("abRand","45");
        sleep(5000);
        waitForPageToLoad();
        loginPage.clickMajorCatrgoty();
        loginPage.clickSubCat();
        loginPage.scrollButtomToClickNext();
        String url=getCurrentUrl();
        System.out.println(url);



    }

}
