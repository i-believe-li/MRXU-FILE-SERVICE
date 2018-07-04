package com.mrxu.cloud.file.domain.trans;

import java.io.Serializable;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/15
 */
public class TransCodingResultVO implements Serializable {
    private static final long serialVersionUID = 3494797705587303839L;
    //源文件ID
    private String originId;
    //源文件名称
    private String originFileName;
    //目标类型
    private String targetType;
    //请求URL
    private String requestUrl;
    //转码目标URL
    private String targetUrl;

    public String getOriginFileName() {
        return originFileName;
    }

    public void setOriginFileName(String originFileName) {
        this.originFileName = originFileName;
    }

    public String getOriginId() {
        return originId;
    }

    public void setOriginId(String originId) {
        this.originId = originId;
    }

    public String getTargetType() {
        return targetType;
    }

    public void setTargetType(String targetType) {
        this.targetType = targetType;
    }

    public String getRequestUrl() {
        return requestUrl;
    }

    public void setRequestUrl(String requestUrl) {
        this.requestUrl = requestUrl;
    }

    public String getTargetUrl() {
        return targetUrl;
    }

    public void setTargetUrl(String targetUrl) {
        this.targetUrl = targetUrl;
    }
}
