package com.mrxu.cloud.file.entity.vo;

import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * 文件信息
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public class FileInfoVO extends BaseEntity{
    private static final long serialVersionUID = -6037037162680969314L;
    private String fileId;
    private String unionKey;
    private String fileName;
    private String extName;
    private String url;
    private String thumbnail;
    private TransVO trans;

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
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

    public TransVO getTrans() {
        return trans;
    }

    public void setTrans(TransVO trans) {
        this.trans = trans;
    }


}
