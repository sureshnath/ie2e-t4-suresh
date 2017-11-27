/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.service;

import com.ie2e.t4.model.FileInfo;
import com.ie2e.t4.repository.ConfigurableFileInfoDao;
import com.ie2e.t4.util.TestConfig;
import java.util.List;
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
public class DirScannerServiceTest {
    
    @Autowired
    private DirScannerService instance;

    /**
     *
     */
    @Before
    public void setupEnvVar(){
        System.setProperty(ConfigurableFileInfoDao.SYS_PROP_CONF_DIR, TestConfig.FILE_NAME_FILTER_TEST_FOLDER);
    }

    /**
     *
     */
    @Test
    public void testFindAll() {
        List<FileInfo> result = instance.findAll();
        assertEquals(TestConfig.ALL_FILES_COUNT, result.size());
    }

    /**
     *
     */
    @Test
    public void testFindMimeExcel() {
        List<FileInfo> result = instance.findMimeExcel();
        assertEquals(TestConfig.EXCEL_FILES_COUNT, result.size());
    }

    /**
     *
     */
    @Test
    public void testFindMimeCsv() {
        List<FileInfo> result = instance.findMimeCsv();
        assertEquals(TestConfig.CSV_FILES_COUNT, result.size());
    }
    
}
