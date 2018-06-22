package com.mrxu.cloud.file.domain.entity.process.sync;


import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/21
 */
public class FileResponseExtendTargetSyncDTO extends BaseEntity {
    private static final long serialVersionUID = -2168644029530788855L;
    private String targetUrl;
    private String thumbnail;
    private String unionKey;
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

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
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
