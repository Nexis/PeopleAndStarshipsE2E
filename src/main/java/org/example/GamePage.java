package org.example;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class GamePage {

    private static final String PAGE_HEADER = ".selenium-page-header";
    private static final String PLAY_BUTTON = ".selenium-play-button";

    private static final String LEFT_CARD = ".selenium-left-card";
    private static final String RIGHT_CARD = ".selenium-right-card";
    private static final String LEFT_CARD_TYPE_SELECT = ".selenium-left-card-type-select";
    private static final String RIGHT_CARD_TYPE_SELECT = ".selenium-right-card-type-select";

    private static final String MAT_OPTION = "//mat-option/span[contains(text(),'%s')]";
    private static final String CARD_HEADER = ".selenium-card-header";


    private WebDriver driver;

    GamePage(WebDriver driver) {
        this.driver = driver;
    }

    public WebElement getGameNameHeader() {
        return driver.findElement(By.cssSelector(PAGE_HEADER));
    }

    public WebElement getPlayButton() {
        return driver.findElement(By.cssSelector(PLAY_BUTTON));
    }

    public WebElement getLeftCard() {
        return driver.findElement(By.cssSelector(LEFT_CARD));
    }

    public WebElement getRightCard() {
        return driver.findElement(By.cssSelector(RIGHT_CARD));
    }

    public WebElement getLeftCardTypeSelect() {
        return driver.findElement(By.cssSelector(LEFT_CARD_TYPE_SELECT));
    }

    public WebElement getRightCardTypeSelect() {
        return driver.findElement(By.cssSelector(RIGHT_CARD_TYPE_SELECT));
    }

    public void selectLeftCardType(String type) {
        selectCardType(type, getLeftCardTypeSelect());
    }

    public void selectRightCardType(String type) {
        selectCardType(type, getRightCardTypeSelect());
    }

    private void selectCardType(String type, WebElement cardTypeSelect) {
        final String TYPE_OPTION = String.format(MAT_OPTION, type);
        cardTypeSelect.click();
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(3));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath(TYPE_OPTION))).click();
    }

    public WebElement getCardHeader(WebElement card) {
        return card.findElement(By.cssSelector(CARD_HEADER));
    }

    public void clickPlayButton() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.elementToBeClickable(By.cssSelector(PLAY_BUTTON)));
        getPlayButton().click();
    }

    public void waitUntilCardsLoaded() {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(5));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(LEFT_CARD)));
        wait.until(ExpectedConditions.presenceOfElementLocated(By.cssSelector(RIGHT_CARD)));
    }

}