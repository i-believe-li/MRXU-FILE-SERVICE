package com.mrxu.cloud.file.domain.process.sync;


import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/21
 */
public class FileRequestSyncDTO extends BaseEntity {
    private static final long serialVersionUID = 274406395443953380L;
    //上传文件名
    private String fileName;
    //上传文件大小
    private Long fileSize;
    //文件拓展名
    private String extension;
    //文件存放目录
    private String fileDir;
    //文件存放完整路径
    private String filePath;
    //MD5
    private String unionKey;
    //文件类型
    private String fileType;
    //创建人ID
    private String creatorId;
    //上传文件URL
    private String url;
    //

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getExtension() {
        return extension;
    }

    public void setExtension(String extension) {
        this.extension = extension;
    }

    public String getFileDir() {
        return fileDir;
    }

    public void setFileDir(String fileDir) {
        this.fileDir = fileDir;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getCreatorId() {
        return creatorId;
    }

    public void setCreatorId(String creatorId) {
        this.creatorId = creatorId;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
