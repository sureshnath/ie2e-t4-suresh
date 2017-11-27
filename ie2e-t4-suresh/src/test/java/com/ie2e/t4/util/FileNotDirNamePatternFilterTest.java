/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.util;

import java.io.File;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Suresh
 */
public class FileNotDirNamePatternFilterTest {

    File baseFolder ;

    /**
     *
     */
    @Before
    public void init(){
        baseFolder = new File(TestConfig.FILE_NAME_FILTER_TEST_FOLDER);
    }
    
    /**
     *
     */
    @Test
    public void testGetAllFilesMatcher() {
        File[] files = baseFolder.listFiles(FileNotDirNamePatternFilter.getAllFilesMatcher());
        assertNotNull(files);
        assertEquals(TestConfig.ALL_FILES_COUNT, files.length);
    }

    /**
     *
     */
    @Test
    public void testGetExcelFilesMatcher() {
        String[] files = baseFolder.list(FileNotDirNamePatternFilter.getExcelFilesMatcher());
        assertNotNull(files);
        assertEquals(TestConfig.EXCEL_FILES_COUNT, files.length);
    }

    /**
     *
     */
    @Test
    public void testGetCsvFilesMatcher() {
        String[] files = baseFolder.list(FileNotDirNamePatternFilter.getCsvFilesMatcher());
        assertNotNull(files);
        assertEquals(TestConfig.CSV_FILES_COUNT, files.length);
    }
    
}
