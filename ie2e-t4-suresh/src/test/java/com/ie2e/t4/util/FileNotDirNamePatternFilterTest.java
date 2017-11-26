/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.util;

import java.io.File;
import java.util.List;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.Before;

/**
 *
 * @author Suresh
 */
public class FileNotDirNamePatternFilterTest {

    File baseFolder ;
    @Before
    public void init(){
        baseFolder = new File("src/test/resources/fileNameFilterTestResourceFolder");
    }
    
    @Test
    public void testGetAllFilesMatcher() {
        File[] files = baseFolder.listFiles(FileNotDirNamePatternFilter.getAllFilesMatcher());
        assertNotNull(files);
        assertEquals(4, files.length);
    }

    @Test
    public void testGetExcelFilesMatcher() {
        String[] files = baseFolder.list(FileNotDirNamePatternFilter.getExcelFilesMatcher());
        assertNotNull(files);
        assertEquals(2, files.length);
    }

    @Test
    public void testGetCsvFilesMatcher() {
        String[] files = baseFolder.list(FileNotDirNamePatternFilter.getCsvFilesMatcher());
        assertNotNull(files);
        assertEquals(1, files.length);
    }
    
}
