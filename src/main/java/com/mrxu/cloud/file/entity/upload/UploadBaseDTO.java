package com.mrxu.cloud.file.entity.upload;

import com.mrxu.cloud.common.entity.BaseEntity;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public class UploadBaseDTO extends BaseEntity{
    private static final long serialVersionUID = 3223727533616848009L;
    private String type;
    private String fileName;
    private String extName;
    private String unionKey;
    private Long fileSize;
    private String tempPath;
    private String fileKey;
    private String url;
    private String thumbnail;
    private MultipartFile file;

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getExtName() {
        return extName;
    }

    public void setExtName(String extName) {
        this.extName = extName;
    }

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public Long getFileSize() {
        return fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public String getTempPath() {
        return tempPath;
    }

    public void setTempPath(String tempPath) {
        this.tempPath = tempPath;
    }

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

    public MultipartFile getFile() {
        return file;
    }

    public void setFile(MultipartFile file) {
        this.file = file;
    }

    public String getFileKey() {
        return fileKey;
    }

    public void setFileKey(String fileKey) {
        this.fileKey = fileKey;
    }
}
