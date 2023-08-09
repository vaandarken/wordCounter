package stepdefinitions;

import io.cucumber.java.Before;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.chrome.ChromeDriver;

import java.time.Duration;
import java.util.concurrent.TimeUnit;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static src.WordCounter.return_random_paragraph;


public class wordcounter {

    private String paragraph;
    public static Logger logger = LogManager.getLogger("Foo");

    private final WebDriver driver = new ChromeDriver();
    public wordcounter() {
    }

    @Before
    public void setUp() {
        System.setProperty("webdriver.chrome.driver", "./src/test/resources/chromedriver/chromedriver.exe");
        driver.get("https://wordcounter.net/");
    }

    @Given("^the user access the webpage wordcounter$")
    public void the_user_access_the_webpage_wordcounter() {
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(10, TimeUnit.SECONDS);
        assertEquals("WordCounter - Count Words & Correct Writing", this.driver.getTitle());
        logger.info("Sucessful access to the webpage");
    }

    @When("^the user sends a paragraph of \"(.*)\" words$")
    public void the_user_sends_a_paragraph_of_words(int numWords) {
        paragraph = return_random_paragraph(numWords);
        WebElement wordCounter = driver.findElement(By.id("box"));
        wordCounter.click();
        wordCounter.clear();
        wordCounter.sendKeys(paragraph);
    }

    @Then("^the user will verify the amount of words and characters on the text sent$")
    public void the_user_will_verify_the_amount_of_words_and_characters_on_the_text_sent() {
        //get the amount of words counted by the page
        String nmbrOfWords = driver.findElement(By.id("word_count")).getText();
        //get the amount of chars counted by the page
        String chars = driver.findElement(By.id("character_count")).getText();

        //Split the paragraph sent
        String[] amntOfWords = paragraph.split("\\s+");

        //parse the number received from the page into an int and print it
        int amountOfWords = Integer.parseInt(nmbrOfWords);
        logger.info(amountOfWords);


        //parse the number received from the page into an int and print it
        int amountOfChars = Integer.parseInt(chars);
        logger.info(amountOfChars);

        logger.info("Words sent vs words registered: " + amntOfWords.length + " " + nmbrOfWords);
        System.out.println("Words sent vs words registered: " + amntOfWords.length + " " + nmbrOfWords);
        logger.info("Chars sent vs chars registered: " + paragraph.trim().length() + " "+ amountOfChars);
        System.out.println("Chars sent vs chars registered: " + paragraph.trim().length() + " "+ amountOfChars);

        assertEquals(paragraph.trim().length(),amountOfChars );
        assertEquals(amntOfWords.length, amountOfWords);

        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(10));

        driver.close();
        driver.quit();
    }

    @Then("^the user verifies the top \"(.*)\" words on the paragraph and the number of appearances$")
    public void the_user_verifies_the_top_words_on_the_paragraph_and_the_number_of_appearances(int nmbrWords) {

        for (int i = 1; i<=nmbrWords; i++){
            String topWord = driver.findElement(
                    By.xpath("//*[@id='kwd-accordion-data']/a["+i+"]/span[@class='word']")).getText();
            String appearanceRate = driver.findElement(
                    By.xpath("//*[@id='kwd-accordion-data']/a["+i+"]/span[@class='badge']")).getText();
            logger.info("Top word: " + topWord + ", Number of appearances: " + appearanceRate);
            System.out.println("Top word: " + topWord + ", Number of appearances: " + appearanceRate);
        }

        driver.close();
        driver.quit();
    }

}