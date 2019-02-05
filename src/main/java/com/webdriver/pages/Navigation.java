package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class Navigation {

    private WebDriver driver;
    @FindBy(id = "quick-create-page-button")
    private WebElement quickCreateButton;
    @FindBy(id = "user-menu-link")
    private WebElement navMenuLink;
    @FindBy(id = "logout-link")
    private WebElement logoutLink;
    @FindBy(id = "logout-submit")
    private WebElement logoutButton;
    @FindBy(id = "admin-menu-link")
    private WebElement adminMenuLink;
    @FindBy(id = "admin-management-link")
    private WebElement userManagementLink;
    @FindBy(css = ".update-item-title>a")
    private List<WebElement> existingPage;

    public Navigation(WebDriver driver) {
        this.driver = driver;
    }

    public void navigateToUserManagement() {
        adminMenuLink.click();
        userManagementLink.click();
    }

    public void selectQuickCreateButton() {
        quickCreateButton.click();
    }

    public void openExistingPage() {
        existingPage.get(1).click();
    }

    public void logOut() {
        navMenuLink.click();
        logoutLink.click();
        logoutButton.click();
    }
}
