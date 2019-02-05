package com.webdriver.tests;

import com.webdriver.pages.BlankPage;
import com.webdriver.pages.LoginPage;
import com.webdriver.pages.Navigation;
import com.webdriver.pages.PublishedPage;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import junitparams.JUnitParamsRunner;
import junitparams.Parameters;
import org.junit.runner.RunWith;
import org.openqa.selenium.support.PageFactory;

@RunWith(JUnitParamsRunner.class)
public class CreatePageTest extends TestBase {


    @Before
    public void initPageObjects(){
        loginPage = PageFactory.initElements(driver,LoginPage.class);
        blankPage = PageFactory.initElements(driver, BlankPage.class);
        newPage = PageFactory.initElements(driver, PublishedPage.class);
        navPage = PageFactory.initElements(driver, Navigation.class);
    }

    @After
    public void deleteCreatedPages() {
        newPage.deletePage();
    }
    // Tests that a page is created using the quick create function in Confluence
    // It's better to parametrize test if test might be run from different users

    @Test
    @Parameters({"test"})
    public void testCreatePage(String user) throws InterruptedException {

        // Log In
        loginPage.login(user);

        // Go to Create Page
        navPage.selectQuickCreateButton();

        // Add content to the page & save
        String[] testData = {"test page title", "{status:colour=Green|title=Testing Create Page|subtle=true}"};
        blankPage.fillTitleBar(testData[0]);
        driver.switchTo().frame("wysiwygTextarea_ifr");
        blankPage.fillBody(testData[1]);
        driver.switchTo().defaultContent();
        blankPage.selectSaveButton();

        //it's better to replace all sleep to waiting specific element
        Thread.sleep(5000);

        // Check creating the page worked

        newPage.validatePageCreated(testData);

        // Check the comment box functionality
        newPage.enterEmphasisedCommentInCommentBox("test comment $@%");
        newPage.validateCommentBoxTextIsEmphasised("test comment $@%");

    }


}

