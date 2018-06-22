package com.mrxu.cloud.file.domain.entity.process.sync;


import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/21
 */
public class FileResponseSyncDTO extends BaseEntity {
    private static final long serialVersionUID = -5211801266226519053L;

    //**********与FileResultVO 保持一致的参数 S
    //上传文件地址
    private String url;
    //上传缩略图
    private String thumbnail;
    //上传文件类型
    private String fileType;
    //上传文件名
    private String fileName;
    //拓展参数，用于返回图文集、视屏转码一类的
    private FileResponseExtendSyncDTO extend;
    //**********与FileResultVO 保持一致的参数 E

    //创建人ID
    private String creatorId;
    //上传文件唯一码(MD5)
    private String unionKey;
    //上传文件拓展名
    private String extension;
    //上传文件地址
    private String filePath;
    //文件存放目录
    private String fileDir;

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

    public FileResponseExtendSyncDTO getExtend() {
        return extend;
    }

    public void setExtend(FileResponseExtendSyncDTO extend) {
        this.extend = extend;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }
}
