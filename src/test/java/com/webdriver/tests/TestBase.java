package com.webdriver.tests;

// *********************************************************
//  Atlassian automation exercise webdriver tests
// *********************************************************

import com.helper.UIProperty;
import com.webdriver.pages.*;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import org.apache.log4j.LogManager;
import org.apache.log4j.Logger;
import org.junit.*;
import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.PageFactory;

import java.util.concurrent.TimeUnit;

public abstract class TestBase {
    private static final Logger log = LogManager.getLogger(TestBase.class);

    WebDriver driver;
    LoginPage loginPage;
    BlankPage blankPage;
    PublishedPage newPage;
    Navigation navPage;
    RestrictionsDialog dialogPage;

    @BeforeClass
    public static void setupClass() {
        ChromeDriverManager.getInstance().setup();
    }

    @Before
    public void setupTest() {
        log.info("loading property for env:"+System.getenv("ENV"));
        UIProperty.loadProperty("login.properties");
        String siteUrl = "https://alexaduevsky.atlassian.net/wiki";
        driver = new ChromeDriver();
        // it's better to parametrize dimension to test page scaling
        driver.manage().window().setSize(new Dimension(1200, 850));
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        driver.navigate().to(siteUrl);

        //page object init
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        newPage = PageFactory.initElements(driver, PublishedPage.class);
        navPage = PageFactory.initElements(driver, Navigation.class);
        dialogPage = PageFactory.initElements(driver, RestrictionsDialog.class);
        blankPage = PageFactory.initElements(driver, BlankPage.class);
    }

    @After
    public void tearDownTest() {
        driver.quit();
    }

}
