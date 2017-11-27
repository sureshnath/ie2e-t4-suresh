/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.ie2e.t4.model;

/**
 *
 * @author Suresh
 */
public class FileInfo {

    private String name;
    private String mimeType;
    private Long size;
    private String extension;
    private String fullPath;

    /**
     *
     * @param fullPath
     */
    public void setFullPath(String fullPath) {
        this.fullPath = fullPath;
    }

    /**
     *
     * @return
     */
    public String getFullPath() {
        return fullPath;
    }

    @Override
    public String toString() {
        StringBuilder b = new StringBuilder();
        b.append("Name:");
        b.append(name);
        b.append(",Mime:");
        b.append(mimeType);
        b.append(",size:");
        b.append(size);
        b.append(",ext:");
        b.append(extension);
        return b.toString();
    }
    
    

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * @return the mimeType
     */
    public String getMimeType() {
        return mimeType;
    }

    /**
     * @param mimeType the mimeType to set
     */
    public void setMimeType(String mimeType) {
        this.mimeType = mimeType;
    }

    /**
     * @return the size
     */
    public Long getSize() {
        return size;
    }

    /**
     * @param size the size to set
     */
    public void setSize(Long size) {
        this.size = size;
    }

    /**
     * @return the extension
     */
    public String getExtension() {
        return extension;
    }

    /**
     * @param extension the extension to set
     */
    public void setExtension(String extension) {
        this.extension = extension;
    }

    
}
