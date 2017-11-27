/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.model;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 * Test model bean FileInfo
 * @author Suresh
 */
public class FileInfoTest {
    
    /**
     * test set/get on name
     */
    @Test
    public void testSetGetName() {
        FileInfo instance = new FileInfo();
        String expResult = "setName";
        instance.setName(expResult);
        String result = instance.getName();
        assertEquals(expResult, result);
    }

    /**
     * test set/get on mime
     */
    @Test
    public void testSetGetMimeType() {
        FileInfo instance = new FileInfo();
        String expResult = "mimeType";
        instance.setMimeType(expResult);
        String result = instance.getMimeType();
        assertEquals(expResult, result);
    }

    /**
     * test set/get on size
     */
    @Test
    public void testSetGetSize() {
        FileInfo instance = new FileInfo();
        Long expResult = 123L;
        instance.setSize(expResult);
        Long result = instance.getSize();
        assertEquals(expResult, result);
    }

    /**
     * test set/get on extension
     */
    @Test
    public void testSetGetExtension() {
        FileInfo instance = new FileInfo();
        String expResult = "ext";
        instance.setExtension(expResult);
        String result = instance.getExtension();
        assertEquals(expResult, result);
    }
    
    /**
     * test set/get on fullpath
     */
    public void testSetGetFullPath() {
        FileInfo instance = new FileInfo();
        String expResult = "path";
        instance.setFullPath(expResult);
        String result = instance.getFullPath();
        assertEquals(expResult, result);
    }
    
}
