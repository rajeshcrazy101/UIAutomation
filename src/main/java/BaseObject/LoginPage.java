package BaseObject;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage extends PageBase {

    public LoginPage(WebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    @FindBy(xpath = "//div[@class='Km0IJL col col-3-5']//input[@type='text']")
    private WebElement userName;

    public void enterUserName(String user){
        userName.click();
        userName.clear();
        userName.sendKeys(user);
        //findElement("xpth","//div[@class='Km0IJL col col-3-5']//input[@type='text']").sendKeys(user);
    }

    @FindBy(xpath = "//div[@class='Km0IJL col col-3-5']//input[@type='password']")
    private WebElement passWord;

    public void enterPassword(String pass){
        passWord.click();
        passWord.clear();
        passWord.sendKeys(pass);
        //findElement("xpath","//div[@class='Km0IJL col col-3-5']//input[@type='password']").sendKeys(pass);
    }

    @FindBy(xpath = "//div[@class='Km0IJL col col-3-5']//button[@type='submit']")
    private WebElement login;

    public void clickLogin(){
        waitForElementToClickable("xpath","//div[@class='Km0IJL col col-3-5']//button[@type='submit']");
        login.click();
        //clickButton("xpath","//div[@class='Km0IJL col col-3-5']//button[@type='submit']");
    }

}
