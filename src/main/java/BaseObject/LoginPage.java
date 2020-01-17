package BaseObject;

import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;
import org.testng.log4testng.Logger;

import java.util.List;

public class LoginPage extends PageBase {

    public LoginPage(RemoteWebDriver driver) {
        this.driver=driver;
        PageFactory.initElements(driver, this);
    }

    private Logger logger=Logger.getLogger(LoginPage.class);

    @FindBy(xpath = "//div[@class='Km0IJL col col-3-5']//input[@type='text']")
    private WebElement userName;

    public void enterUserName(String user){
        userName.click();
        userName.clear();
        userName.sendKeys(user);
        //findElement("xpath","//div[@class='Km0IJL col col-3-5']//input[@type='text']").sendKeys(user);
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

    @FindBy(xpath = "//div[@class='zi6sUf']//span[contains(text(),'Electronics')]")
    private WebElement majorCategory;

    public void clickMajorCatrgoty(){
        mouseHover(majorCategory);
    }

    public void clickSubCategory(String value){
        String xpathValue="//div[@class='zi6sUf']//ul[@class='_3GtRpC']//a[@title='$']";
        WebElement element=findAndReplace("xpath",xpathValue,value);
        element.click();

    }

    @FindBy(xpath = "//nav[@class='_1ypTlJ']//span")
    private WebElement isNextButton;

    public void scrollButtomToClickNext(){
        waitForElementToVisible("xpath","//nav[@class='_1ypTlJ']//span");
        scrollToViewElement(isNextButton);
    }

    @FindBy(xpath = "//*[@id='container']//span[@class='_1QZ6fC _3Lgyp8']")
    private List<WebElement> mainCat;

    public void clickMainCategory(int index){
        int size=mainCat.size();
        logger.info(size);
        mainCat.get(index).click();
    }

    @FindBy(xpath = "//div[@class='mCRfo9']//div[@class='Km0IJL col col-3-5']")
    private WebElement loginPopup;

    public boolean isLoginPopupClosed(){
        return loginPopup.isDisplayed();
    }

    @FindBy(xpath = "//div[@class='_1GRhLX _3JslKL required-tracking']//a[contains(text(),'VIEW ALL')]")
    private WebElement viewAll;

    public void clickViewAllButton(){
        scrollDown(1);
        waitForElementToClickable("xpath","//div[@class='_1GRhLX _3JslKL required-tracking']//a[contains(text(),'VIEW ALL')]");
        viewAll.click();
    }

    @FindBy(xpath = "//div[@class='MDGhAp']//img")
    private List<WebElement> dealsPage;

    public void dealsIteam(int index){
        waitForElementToVisible("xpath","//div[@class='MDGhAp']//img");
        int count=dealsPage.size();
        logger.info(count);
        dealsPage.get(index).click();

    }

    @FindBy(xpath = "//div[@class='_3O0U0u _288RSE']//img[@class='_3togXc']")
    private List<WebElement> prodectList;

    public void clickProdectList(int index){
        waitForElementToVisible("xpath","//div[@class='_3O0U0u _288RSE']//img[@class='_3togXc']");
        int count=prodectList.size();
        logger.info(count);
        prodectList.get(index).click();
    }

}
