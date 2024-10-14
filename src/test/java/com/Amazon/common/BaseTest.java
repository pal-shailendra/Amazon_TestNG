package com.Amazon.common;

import com.Amazon.commonPages.CommonPages;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.support.ThreadGuard;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Optional;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Properties;

public class BaseTest extends CommonPages {


    public static WebDriver driver;
    public static Reusable key;
    public static Properties CONFIG;
    public static Properties AmazonOR;

    @BeforeMethod
    public void createDriver(@Optional("chrome") String browser) throws Throwable {
        key = new Reusable();

        FileInputStream fis = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//com//Amazon//Config//CONFIG.properties");
        CONFIG = new Properties();
        CONFIG.load(fis);

        FileInputStream fis2 = new FileInputStream(System.getProperty("user.dir")+"//src//test//java//com//Amazon//Config//SearchPageOR.properties");
        AmazonOR = new Properties();
        AmazonOR.load(fis2);

        WebDriverManager.chromedriver().setup();
        driver = new ChromeDriver();
        driver.manage().window().maximize();
        String data = CONFIG.getProperty("Amazon_URL");
        key.navigate(data);
    }

    @AfterMethod(alwaysRun = true)
    public void closeDriver() {
      driver.quit();
    }


}
