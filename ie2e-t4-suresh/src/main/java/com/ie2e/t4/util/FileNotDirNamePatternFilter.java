/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.util;

import java.io.File;
import java.io.FilenameFilter;

/**
 *
 * @author Suresh
 */
public class FileNotDirNamePatternFilter implements FilenameFilter{

    private static final String ALL_FILES_PATTERN = ".*";
    private static final String EXCEL_FILES_PATTERN = ".*\\.(xlsx|xls)$";
    private static final String CSV_FILES_PATTERN = ".*\\.csv$";
    
    private final String pattern;
    
    /**
     *
     * @param pattern
     */
    public FileNotDirNamePatternFilter(String pattern){
        this.pattern = pattern;
    }
    @Override
    public boolean accept(File dir, String name) {
        File f = new File(dir, name);
        return f.isFile() && name.matches(pattern);
    }
    
    /**
     *
     * @return
     */
    public static FileNotDirNamePatternFilter getAllFilesMatcher(){
        return new FileNotDirNamePatternFilter(ALL_FILES_PATTERN);
    }

    /**
     *
     * @return
     */
    public static FileNotDirNamePatternFilter getExcelFilesMatcher(){
        return new FileNotDirNamePatternFilter(EXCEL_FILES_PATTERN);
    }

    /**
     *
     * @return
     */
    public static FileNotDirNamePatternFilter getCsvFilesMatcher(){
        return new FileNotDirNamePatternFilter(CSV_FILES_PATTERN);
    }
}
