package com.cloudy.entity;

import java.io.Serializable;

/**
 * Created by ljy_cloudy on 2018/10/6.
 */
public class FileInfo implements Serializable {

    private String path;

    private String filename;

    public FileInfo(String path) {
        this.path = path;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }
}
