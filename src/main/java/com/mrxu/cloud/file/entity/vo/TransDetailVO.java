package com.mrxu.cloud.file.entity.vo;

import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public class TransDetailVO extends BaseEntity {
    private static final long serialVersionUID = -8706439087900884841L;
    private String unionKey;
    private String targetUrl;
    private String thumbnail;

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

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
}
