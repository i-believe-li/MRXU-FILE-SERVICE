package com.mrxu.cloud.file.domain.file;

import java.io.Serializable;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/16
 */
public class FileResultExtendDetailVO implements Serializable{
    private static final long serialVersionUID = -597263257434719096L;
    private String transUrl;
    private String thumbnail;
    private String transType;
    private Integer order;


    public String getTransUrl() {
        return transUrl;
    }

    public void setTransUrl(String transUrl) {
        this.transUrl = transUrl;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public Integer getOrder() {
        return order;
    }

    public void setOrder(Integer order) {
        this.order = order;
    }
}
