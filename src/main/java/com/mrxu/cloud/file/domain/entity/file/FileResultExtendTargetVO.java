package com.mrxu.cloud.file.domain.entity.file;

import java.io.Serializable;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/16
 */
public class FileResultExtendTargetVO implements Serializable{
    private static final long serialVersionUID = -597263257434719096L;
    private String targetUrl;
    private String thumbnail;
    private String targetType;
    private Integer order;

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
