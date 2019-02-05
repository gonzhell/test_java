package com.webdriver.pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class PublishedPage {

    private WebDriver driver;
    @FindBy(id = "content-metadata-page-restrictions")
    private WebElement restrictionsIcon;
    @FindBy(css = "div.ia-secondary-content")
    private WebElement pageTree;
    @FindBy(css = "div.quick-comment-prompt")
    private WebElement commentBoxPrompt;
    @FindBy(id = "wysiwygTextarea_ifr")
    private WebElement commentBoxTextFrame;
    @FindBy(id = "tinymce")
    private WebElement commentBoxTextArea;
    @FindBy(css = "span.icon.aui-icon.aui-icon-small.aui-iconfont-editor-italic")
    private WebElement commentBoxTextItalic;
    @FindBy(id = "_mce_caret")
    private WebElement commentBoxText;
    @FindBy(id = "rte-button-publish")
    private WebElement commentSaveButton;
    @FindBy(css = "div.comment-content.wiki-content")
    private WebElement commentText;
    @FindBy(id = "action-menu-link")
    private WebElement menuItemMore;
    @FindBy(id = "action-remove-content-link")
    private WebElement menuItemDeletePage;
    @FindBy(id = "confirm")
    private WebElement confirmDelete;

    public PublishedPage(WebDriver driver) {
        this.driver = driver;
    }

    public void selectRestrictionIcon() {
        restrictionsIcon.click();
    }

    private void validatePageShownInTreeView(String PageTitle) {
        Assert.assertTrue(driver.getCurrentUrl().contains("atlassian.net/wiki/display/"));
        for (WebElement pageNode : pageTree.findElements(By.tagName("a"))) {
            if (PageTitle == pageNode.getText()) {
                Assert.assertTrue(pageNode.getText().contains(PageTitle));
            }
        }
    }

    public void validatePageCreated(String[] PageTitle) {
        if (!driver.getTitle().startsWith(PageTitle[0])) {
            throw new IllegalStateException("Page title does not match expected title");
        } else {
            validatePageShownInTreeView(PageTitle[0]);
        }
    }

    public void enterEmphasisedCommentInCommentBox(String comment) throws InterruptedException {
        commentBoxPrompt.click();
        Thread.sleep(7000);
        commentBoxTextItalic.click();
        driver.switchTo().frame("wysiwygTextarea_ifr");
        commentBoxTextArea.sendKeys(comment);
        driver.switchTo().defaultContent();
        commentSaveButton.click();
        Thread.sleep(3000);
    }

    public void validateCommentBoxTextIsEmphasised(String comment) {
        Assert.assertTrue(commentText.getAttribute("innerHTML").contains("<em>" + comment + "</em>"));
    }

    public void deletePage() {
        menuItemMore.click();
        menuItemDeletePage.click();
        confirmDelete.click();
    }
}
