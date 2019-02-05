package com.webdriver.tests;

import com.webdriver.pages.*;
import org.junit.*;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;
import org.openqa.selenium.support.PageFactory;

import java.util.Collection;
import java.util.Arrays;

@RunWith(value = Parameterized.class)
public class RestrictionsTest extends TestBase {

    String user;
    String userRole;

    CreatePageTest cpt = new CreatePageTest();

    public RestrictionsTest(String corUrl, String tstUrl){
        this.user = corUrl;
        this.userRole = tstUrl;
    }

    @Before
    public void createTestPage() throws InterruptedException {
        cpt.loginPage=this.loginPage;
        cpt.blankPage=this.blankPage;
        cpt.newPage=this.newPage;
        cpt.navPage=this.navPage;
        cpt.driver=driver;
        cpt.testCreatePage(user);
        navPage.logOut();
    }

    @After
    public void destroyPage(){
        newPage.deletePage();
    }

    @Parameters
    public static Collection<Object[]> data() {
        Object[][] data = new Object[][] {
                {"test",""},
                {"test2","confluence-users"},
                {"test1","System Administrator"}
        };
        return Arrays.asList(data);
    }

    // Tests that restricting a page works when set to the current user
    @Test
    public void testRestriction() throws InterruptedException {

        loginPage.login(user);

        // Open Existing Page
        navPage.openExistingPage();
        Thread.sleep(2000);

        // Open Restrictions Dialog
        newPage.selectRestrictionIcon();
        Thread.sleep(3000);

        // Select Restriction Type
        RestrictionsDialog dialog = PageFactory.initElements(driver, RestrictionsDialog.class);
        if (!userRole.equals("")) {
            dialogPage.setViewEditPermissions();
            dialogPage.searchUser(userRole);
        } else {
            dialog.setEditPermissions();
        }
        dialog.selectCancelButton();
    }
}

