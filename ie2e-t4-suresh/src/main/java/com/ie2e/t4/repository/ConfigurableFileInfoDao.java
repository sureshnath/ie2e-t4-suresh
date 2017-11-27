/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.repository;

import com.ie2e.t4.model.FileInfo;
import com.ie2e.t4.util.FileNotDirNamePatternFilter;
import java.io.File;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;
import org.apache.commons.io.FilenameUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

/**
 *
 * @author Suresh
 */
@Repository
public class ConfigurableFileInfoDao implements IFileInfoDao {

    private static final Logger LOG = Logger.getLogger(ConfigurableFileInfoDao.class);
    public static final String SYS_PROP_CONF_DIR = "com.ie2e.t4.conf.dir";

    @Autowired
    private Environment env;

    public Environment getEnv() {
        return env;
    }

    public void setEnv(Environment env) {
        this.env = env;
    }

    private List<FileInfo> listFileInfos(FileNotDirNamePatternFilter filter) {
        List<FileInfo> fileInfos = new ArrayList<>();
        String configDirPath = env.getProperty(SYS_PROP_CONF_DIR);
        File configuredDirectory = new File(configDirPath==null?"":configDirPath);
        if (configuredDirectory.exists()) {
            for (File file : configuredDirectory.listFiles(filter)) {
                FileInfo fileInfo = new FileInfo();
                fileInfo.setName(file.getName());
                fileInfo.setSize(file.length());
                fileInfo.setExtension(FilenameUtils.getExtension(file.getName()));
                fileInfo.setMimeType(URLConnection.guessContentTypeFromName(file.getName()));
                fileInfo.setFullPath(file.getAbsolutePath());
                fileInfos.add(fileInfo);
            }
        } else {
            LOG.warn("DAO configured to use directory, but does NOT exists: " + configuredDirectory.getAbsolutePath());
        }
        return fileInfos;
    }

    @Override
    public List<FileInfo> findAll() {
       return listFileInfos(FileNotDirNamePatternFilter.getAllFilesMatcher());
    }

    @Override
    public List<FileInfo> findMimeExcel() {
       return listFileInfos(FileNotDirNamePatternFilter.getExcelFilesMatcher());
    }

    @Override
    public List<FileInfo> findMimeCsv() {
       return listFileInfos(FileNotDirNamePatternFilter.getCsvFilesMatcher());
    }

}
