package org.example;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class GameTest {
    WebDriver driver;
    GamePage gamePage;

    @BeforeEach
    public void setup() {
        driver = new ChromeDriver();
        driver.manage().timeouts().implicitlyWait(Duration.ofMillis(500));
        driver.get("http://localhost:4200/");
        gamePage = new GamePage(driver);
    }

    @Test
    public void startSetup() {

        String header = gamePage.getGameNameHeader().getText();
        assertEquals("People and Starships", header);

        gamePage.clickPlayButton();
        gamePage.waitUntilCardsLoaded();

        WebElement leftCard = gamePage.getLeftCard();
        WebElement rightCard = gamePage.getRightCard();


        assertTrue(leftCard.isDisplayed());
        assertTrue(rightCard.isDisplayed());

    }

    @Test
    public void playingSelectedStarshipCard() {

        gamePage.selectLeftCardType("Starship");
        gamePage.selectRightCardType("Starship");

        gamePage.clickPlayButton();
        gamePage.waitUntilCardsLoaded();

        WebElement leftCard = gamePage.getLeftCard();
        WebElement rightCard = gamePage.getRightCard();

        assertTrue(leftCard.isDisplayed());
        assertTrue(rightCard.isDisplayed());
        assertEquals(gamePage.getCardHeader(leftCard).getText(), "Starship");
        assertEquals(gamePage.getCardHeader(rightCard).getText(), "Starship");

    }

    @Test
    public void playingSelectedPersonCard() {

        gamePage.selectLeftCardType("Person");
        gamePage.selectRightCardType("Person");

        gamePage.clickPlayButton();
        gamePage.waitUntilCardsLoaded();

        WebElement leftCard = gamePage.getLeftCard();
        WebElement rightCard = gamePage.getRightCard();

        assertTrue(leftCard.isDisplayed());
        assertTrue(rightCard.isDisplayed());
        assertEquals(gamePage.getCardHeader(leftCard).getText(), "Person");
        assertEquals(gamePage.getCardHeader(rightCard).getText(), "Person");
    }

    @Test
    public void playingTwoSelectedDifferentTypeCards() {

        gamePage.selectLeftCardType("Person");
        gamePage.selectRightCardType("Starship");

        gamePage.clickPlayButton();
        gamePage.waitUntilCardsLoaded();

        WebElement leftCard = gamePage.getLeftCard();
        WebElement rightCard = gamePage.getRightCard();

        assertTrue(leftCard.isDisplayed());
        assertTrue(rightCard.isDisplayed());
        assertEquals(gamePage.getCardHeader(leftCard).getText(), "Person");
        assertEquals(gamePage.getCardHeader(rightCard).getText(), "Starship");
    }

    @AfterEach
    public void teardown() {
        driver.quit();
    }

}
