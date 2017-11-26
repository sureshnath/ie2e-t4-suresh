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

    private final String pattern;
    
    public FileNotDirNamePatternFilter(String pattern){
        this.pattern = pattern;
    }
    @Override
    public boolean accept(File dir, String name) {
        File f = new File(dir, name);
        return f.isFile() && name.matches(pattern);
    }
    
    public static FileNotDirNamePatternFilter getAllFilesMatcher(){
        return new FileNotDirNamePatternFilter(".*");
    }
    public static FileNotDirNamePatternFilter getExcelFilesMatcher(){
        return new FileNotDirNamePatternFilter(".*\\.(xlsx|xls)$");
    }
    public static FileNotDirNamePatternFilter getCsvFilesMatcher(){
        return new FileNotDirNamePatternFilter(".*\\.csv$");
    }
}
