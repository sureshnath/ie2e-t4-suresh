/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.service;

import com.ie2e.t4.model.FileInfo;
import com.ie2e.t4.repository.IFileInfoDao;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 *
 * @author Suresh
 */
@Service
public class DirScannerService {

    @Autowired
    private IFileInfoDao dao;

    public List<FileInfo> findAll() {
        return dao.findAll();
    }

    public List<FileInfo> findMimeExcel() {
        return dao.findMimeExcel();

    }

    public List<FileInfo> findMimeCsv(){
        return dao.findMimeCsv();
    }
}
