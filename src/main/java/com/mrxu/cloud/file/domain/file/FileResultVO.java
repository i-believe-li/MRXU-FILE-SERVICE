package com.mrxu.cloud.file.domain.file;

import java.io.Serializable;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/14
 */
public class FileResultVO implements Serializable {
    private static final long serialVersionUID = 1109845183922898949L;
    //上传文件地址
    private String url;
    //上传缩略图
    private String thumbnail;
    //上传文件类型
    private String fileType;
    //上传文件名
    private String fileName;

    //拓展参数，用于返回图文集、视屏转码一类的
    private FileResultExtendVO extend;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public FileResultExtendVO getExtend() {
        return extend;
    }

    public void setExtend(FileResultExtendVO extend) {
        this.extend = extend;
    }
}
