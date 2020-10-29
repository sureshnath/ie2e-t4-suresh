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

import io.github.bonigarcia.wdm.WebDriverManager;
import org.apache.log4j.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Test;

import static io.github.bonigarcia.wdm.config.DriverManagerType.CHROME;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriverService;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 * This is a functional test using Selenium that scans the directory pointed by 
 * {@link TestConfig#FILE_NAME_FILTER_TEST_FOLDER} for all CSV files and
 * check the vehicle registrations, their make and model in against DVLA website
 * <p>
 * CSV File format <BR>
 * Line 1 : header - registration,make, color<BR>
 * Line 2-n: data 
 * </p>
 * 
 * <p>
 * Selenium tests runs against Chrome and can be configured using {@link ChromeDriverService#CHROME_DRIVER_EXE_PROPERTY}
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

    /**
     *
     * @throws Exception
     */
    @BeforeClass
    public static void createAndStartService() throws Exception {
        WebDriverManager.getInstance(CHROME).setup();

        service = new ChromeDriverService.Builder()
                .usingAnyFreePort()
                .build();
        service.start();
    }

    /**
     *
     */
    @AfterClass
    public static void createAndStopService() {
        service.stop();
    }

    /**
     *
     */
    @Before
    public void createDriver() {
        driver = new RemoteWebDriver(service.getUrl(),
                DesiredCapabilities.chrome());
        driver.manage().timeouts().implicitlyWait(30, TimeUnit.SECONDS);
    }

    /**
     *
     * @throws Exception
     */
    @After
    public void tearDown() throws Exception {
        driver.quit();
        String verificationErrorString = verificationErrors.toString();
        if (!"".equals(verificationErrorString)) {
            fail(verificationErrorString);
        }
    }

    /**
     *
     */
    @Before
    public void setupEnvVar() {
        System.setProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR, TestConfig.FILE_NAME_FILTER_TEST_FOLDER);
    }

    /**
     *
     * @throws Exception
     */
    @Test
    public void testVehicleRegCSVs() throws Exception {
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
        driver.findElement(By.id("wizard_vehicle_enquiry_capture_vrn_vrn")).clear();
        driver.findElement(By.id("wizard_vehicle_enquiry_capture_vrn_vrn")).sendKeys(reg);
        driver.findElement(By.id("submit_vrn_button")).click();
        assertEquals(make.toUpperCase(), driver.findElement(By.xpath("//dt[text()='Make']/../dd")).getText());
        assertEquals(color.toUpperCase(), driver.findElement(By.xpath("//dt[text()='Colour']/../dd")).getText());

    }

}
