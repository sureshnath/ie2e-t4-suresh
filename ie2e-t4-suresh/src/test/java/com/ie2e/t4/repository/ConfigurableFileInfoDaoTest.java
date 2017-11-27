/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.repository;

import com.ie2e.t4.model.FileInfo;
import com.ie2e.t4.util.TestConfig;
import java.util.List;
import org.apache.log4j.Logger;
import org.apache.log4j.spi.LoggerFactory;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

/**
 *
 * @author Suresh
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/spring-beans.xml")
public class ConfigurableFileInfoDaoTest {
    
    private static final Logger LOG = Logger.getLogger(ConfigurableFileInfoDaoTest.class);
    
    @Autowired
    private IFileInfoDao instance;
    
    @Before
    public void setupEnvVar(){
        System.setProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR, TestConfig.FILE_NAME_FILTER_TEST_FOLDER);
    }

    @Test
    public void testNonExistingConfigDirectory() {
        LOG.info("Starting Negative test - non existing config directory");
        System.setProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR, TestConfig.NON_EXISTING_FOLDER);
        List<FileInfo> result = instance.findAll();
        assertNotNull(result);
        assertEquals(0, result.size());
        
    }

    @Test
    public void testNoEnvVar() {
        LOG.info("Starting Negative test - without config directory environment variable");
        System.clearProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR);
        List<FileInfo> result = instance.findAll();
        assertNotNull(result);
        assertEquals(0, result.size());
    }

    @Test
    public void testFindAll() {
        List<FileInfo> result = instance.findAll();
        assertEquals(4, result.size());
    }

    @Test
    public void testFindMimeExcel() {
        List<FileInfo> result = instance.findMimeExcel();
        assertEquals(2, result.size());
    }

    @Test
    public void testFindMimeCsv() {
        List<FileInfo> result = instance.findMimeCsv();
        assertEquals(1, result.size());
    }

}
