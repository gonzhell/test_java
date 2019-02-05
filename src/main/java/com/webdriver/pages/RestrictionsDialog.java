package com.webdriver.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class RestrictionsDialog {

    private WebDriver driver;
    @FindBy(className = "select2-arrow")
    private WebElement restrictionArrow;
    @FindBy(css = "button#page-restrictions-dialog-save-button.aui-button")
    private WebElement applyButton;
    @FindBy(id = "page-restrictions-dialog-close-button")
    private WebElement cancelLink;
    @FindBy(xpath = "//li[@class='select2-search-field']/input[@type='text']")
    private WebElement searchTextField;
    @FindBy(id = "page-restrictions-add-button")
    private WebElement addButton;
    @FindBy(css = "div.select2-result-label")
    private WebElement userDropDown;

    public RestrictionsDialog(WebDriver driver) {
        this.driver = driver;
    }

    public void setNoPermissions() throws InterruptedException {
        Thread.sleep(1000);
        restrictionArrow.click();
        driver.findElements(By.className("restrictions-dialog-option")).get(1).click();
    }

    public void setEditPermissions() throws InterruptedException {
        Thread.sleep(2000);
        restrictionArrow.click();
        Thread.sleep(1000);
        driver.findElements(By.className("restrictions-dialog-option")).get(2).click();
    }

    public void setViewEditPermissions() throws InterruptedException {
        Thread.sleep(2000);
        restrictionArrow.click();
        Thread.sleep(1000);
        driver.findElements(By.className("restrictions-dialog-option")).get(3).click();
    }

    public void searchUser(String userName) throws InterruptedException {
        try {
            searchTextField.clear();
            searchTextField.sendKeys(userName);
            Thread.sleep(2000);
            userDropDown.click();
            Thread.sleep(1000);
            addButton.click();
        } catch (Exception e) {
            System.out.println("Failed to find user. Error: " + e.getMessage());
        }
    }

    public void selectApplyButton() {
        applyButton.click();
    }

    public void selectCancelButton() {
        cancelLink.click();
    }
}
