/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.selenium;

import com.ie2e.t4.service.*;
import com.ie2e.t4.model.FileInfo;
import com.ie2e.t4.repository.ConfigurableFileInfoDao;
import com.ie2e.t4.util.TestConfig;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.concurrent.TimeUnit;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Suresh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-beans.xml")
public class VehicleRegSeleniumTest {

    private static final Logger LOG = Logger.getLogger(VehicleRegSeleniumTest.class);

    @Autowired
    private DirScannerService instance;

    private static ChromeDriverService service;
    private WebDriver driver;
    private String baseUrl;
    private boolean acceptNextAlert = true;
    private final StringBuffer verificationErrors = new StringBuffer();

    @BeforeClass
    public static void createAndStartService() throws Exception {
        if (System.getProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY) == null) {
            System.setProperty(ChromeDriverService.CHROME_DRIVER_EXE_PROPERTY, "D:\\Software\\java\\utils\\chromedriver_win32\\chromedriver.exe");
        };

        service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        service.start();
    }

    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    @Before
    public void setupEnvVar() {
        System.setProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR, TestConfig.FILE_NAME_FILTER_TEST_FOLDER);
    }

    @Test
    public void testFindMimeCsv() throws Exception {
        List<FileInfo> result = instance.findMimeCsv();
        assertEquals(TestConfig.CSV_FILES_COUNT, result.size());
        for (FileInfo info : result) {
            checkFile(info.getFullPath());
        }
    }

    private void checkFile(String filePath) throws Exception {
        File file = new File(filePath);
        BufferedReader reader = new BufferedReader(new FileReader(file));
        String line = reader.readLine(); // skip header - expected format - reg,make,color
        while (line != null && (line = reader.readLine()) != null) {
            String[] vehicle = line.split(",");
            verifyVehicle(filePath, vehicle[0], vehicle[1], vehicle[2]);
        }
    }

    private void verifyVehicle(String filePath, String reg, String make, String color) throws Exception {
        StringBuilder b = new StringBuilder();
        b.append("FilePath:");
        b.append(filePath);
        b.append(",Registration:");
        b.append(reg);
        b.append(",Make:");
        b.append(make);
        b.append(",Color:");
        b.append(color);
        LOG.info(b.toString());

        baseUrl = "https://www.gov.uk/";
        driver.get(baseUrl + "/get-vehicle-information-from-dvla");
        driver.findElement(By.linkText("Start now")).click();
        driver.findElement(By.id("Vrm")).clear();
        driver.findElement(By.id("Vrm")).sendKeys(reg);
        driver.findElement(By.name("Continue")).click();
        assertEquals(make.toUpperCase(), driver.findElement(By.xpath("//div[@id='pr3']/div/ul/li[2]/span[2]/strong")).getText());
        assertEquals(color.toUpperCase(), driver.findElement(By.xpath("//div[@id='pr3']/div/ul/li[3]/span[2]/strong")).getText());

    }

}
