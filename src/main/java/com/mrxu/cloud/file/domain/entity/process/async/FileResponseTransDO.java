package com.mrxu.cloud.file.domain.entity.process.async;


import com.mrxu.cloud.common.entity.BaseEntity;
import com.mrxu.cloud.file.domain.entity.trans.TransExtendDTO;

import java.util.List;

/**
 * 转码 process 响应参数
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 */
public class FileResponseTransDO extends BaseEntity {
    private static final long serialVersionUID = -1624690294787697634L;
    //父文件ID
    private String parentId;
    //目标类型
    private String targetType;
    //目标文件名称
    private String targetFileName;
    //目标文件拓展名
    private String targetFileExtension;
    //目标URL
    private String targetUrl;
    //目标文件缩略图
    private String targetThumbnail;
    //目标文件MD5值
    private String targetUnionCode;
    //拓展
    private List<TransExtendDTO> list;

    public String getParentId() {
        return parentId;
    }

    public void setParentId(String parentId) {
        this.parentId = parentId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getTargetFileName() {
        return targetFileName;
    }

    public void setTargetFileName(String targetFileName) {
        this.targetFileName = targetFileName;
    }

    public String getTargetFileExtension() {
        return targetFileExtension;
    }

    public void setTargetFileExtension(String targetFileExtension) {
        this.targetFileExtension = targetFileExtension;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getTargetThumbnail() {
        return targetThumbnail;
    }

    public void setTargetThumbnail(String targetThumbnail) {
        this.targetThumbnail = targetThumbnail;
    }

    public String getTargetUnionCode() {
        return targetUnionCode;
    }

    public void setTargetUnionCode(String targetUnionCode) {
        this.targetUnionCode = targetUnionCode;
    }

    public List<TransExtendDTO> getList() {
        return list;
    }

    public void setList(List<TransExtendDTO> list) {
        this.list = list;
    }
}
