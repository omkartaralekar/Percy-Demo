package io.percy.selenium;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;

import java.io.IOException;

public class PercyAssignmentTest {
//    private static final String TEST_URL = "https://www.browserstack.com";
    private static final String TEST_URL = "https://k8s.bsstag.com";
    private static WebDriver driver;
    private static Percy percy;

    @BeforeAll
    public static void testSetup() throws IOException {
        // Disable browser logs from being logged to stdout
        System.setProperty(FirefoxDriver.SystemProperty.BROWSER_LOGFILE,"/dev/null");
        System.setProperty("webdriver.gecko.driver", "/Users/omkartaralekar/Documents/percy/percy-selenium-java-master/driver/geckodriver");
        FirefoxOptions options = new FirefoxOptions();
        options.setHeadless(true);
        driver = new FirefoxDriver(options);
        percy = new Percy(driver);
    }

    @AfterAll
    public static void testTeardown() {
        // Close our test browser.
        driver.quit();
    }

    @Test
    public void takesLocalAppSnapshotWithProvidedName() {
        driver.get(TEST_URL);
        percy.snapshot("Snapshot Home Page");
    }
    @Test
    public void takesPricingSnapshot() {
        driver.get(TEST_URL+"/pricing");
        percy.snapshot("Snapshot pricing");
    }
    @Test
    public void takesIntegrationsSnapshot() {
        driver.get(TEST_URL+"/integrations/automate");
        percy.snapshot("Snapshot Integration");
    }
    @Test
    public void takesDocsSnapshot() {
        driver.get(TEST_URL+"/docs");
        percy.snapshot("Snapshot Docs");
    }
}
