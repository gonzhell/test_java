package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UserManagement {

    private WebDriver driver;
    @FindBy(css = "a.aui-button.back-to-product")
    private WebElement backToProductButton;
    @FindBy(name = "names")
    private WebElement namesField;
    @FindBy(name = "emails")
    private WebElement emailsField;
    @FindBy(id = "new-user-quick-add-submit")
    private WebElement createUsersButton;
    @FindBy(css = "span.aui-icon.icon-close")
    private WebElement popupMessageClose;
    @FindBy(css = "table.aui.umtable")
    private WebElement userTable;
    @FindBy(id = "login-as-button")
    private WebElement loginAsUserButton;
    @FindBy(css = "button.aui-button.aui-button-link.skip-onboarding")
    private WebElement stopNewUserOnboardingTips;
    @FindBy(id = "impersonation-dismiss-trigger")
    private WebElement stopImpersonatingButton;
    @FindBy(id = "delete-user-button-dropdown")
    private WebElement deleteUserDropdown;
    @FindBy(id = "delete-user-button")
    private WebElement deleteUserButton;
    @FindBy(id = "user-delete-submit")
    private WebElement deleteUserConfirmButton;

    public UserManagement(WebDriver driver) {
        this.driver = driver;
    }

    // only works if there are less than 5 users in the userbase
    public void addNewUser(String username) throws InterruptedException {
        namesField.sendKeys(username);
        emailsField.sendKeys(username + "@example.com");
        createUsersButton.click();
        popupMessageClose.click();
    }

    public void openUserFromUserList(String username) {
        WebElement tableRow = userTable.findElement(By.xpath("//tr[@data-username='" + username + "']"));
        WebElement tableCell = tableRow.findElement(By.tagName("td"));
        WebElement userLink = tableCell.findElement(By.tagName("a"));
        userLink.click();
    }

    public void impersonateUser() throws InterruptedException {
        loginAsUserButton.click();
        stopNewUserOnboardingTips.click();
    }

    public void stopImpersonatingUser() {
        stopImpersonatingButton.click();
    }

    public void removeUser() {
        deleteUserDropdown.click();
        deleteUserButton.click();
        deleteUserConfirmButton.click();
    }

    public void returnToProduct() throws InterruptedException {
        driver.navigate().to(backToProductButton.getAttribute("href"));
    }

}
