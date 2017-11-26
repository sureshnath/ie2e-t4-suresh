/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.repository;

import com.ie2e.t4.model.FileInfo;
import java.util.List;

/**
 *
 * @author Suresh
 */
public interface IFileInfoDao {

    public List<FileInfo> findAll();
    public List<FileInfo> findMimeExcel();
    public List<FileInfo> findMimeCsv();
}
