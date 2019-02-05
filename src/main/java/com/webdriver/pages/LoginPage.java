package com.webdriver.pages;

import com.helper.UIProperty;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

/**
 * Created by aaduevsky on 23.04.17.
 * <p>
 * Login page must also be in page object
 */
public class LoginPage {
    private static final Logger log = LogManager.getLogger(LoginPage.class);

    private WebDriver driver;
    @FindBy(id = "login-submit")
    private WebElement loginSubmitButton;
    @FindBy(id = "username")
    private WebElement userNameField;
    @FindBy(id = "password")
    private WebElement passwordField;
    @FindBy(id = "submit")
    private WebElement submit;
    @FindBy(css = ".censored-options>label>input[value='false']")
    private WebElement onlyShowMyUserName;
    @FindBy(id = "join-site-button")
    private WebElement allDoneGoToSiteButton;
    @FindBy(id = "join-site-button")
    private List<WebElement> allDoneGoToSiteButtonCount;


    public LoginPage(WebDriver driver) {
        this.driver = driver;
    }

    public void login(String user) {
        String key = String.format("%s.%s", System.getenv("ENV"), user);
        log.info(String.format("login as %s.%s", System.getenv("ENV"), user));
        userNameField.sendKeys(UIProperty.getUser(key));
        loginSubmitButton.click();
        passwordField.sendKeys(UIProperty.getPasswd(key));
        loginSubmitButton.click();
        //if user login first time
        if (allDoneGoToSiteButtonCount.size() != 0) {
            onlyShowMyUserName.click();
            allDoneGoToSiteButton.click();
        }
    }

}
