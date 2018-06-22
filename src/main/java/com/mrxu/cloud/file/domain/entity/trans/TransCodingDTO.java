package com.mrxu.cloud.file.domain.entity.trans;

import java.io.Serializable;
import java.util.List;

/**
 * 转码实体
 * @author ifocusing-xuzhiwei
 * @since 2017/11/22
 */
public class TransCodingDTO implements Serializable{
    private static final long serialVersionUID = -6478813021401937750L;
    private String md5;
    private String type;
    private String transCodingUrl;
    private String TransCodingName;
    private List<String[]> transCodingDetails;

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransCodingUrl() {
        return transCodingUrl;
    }

    public void setTransCodingUrl(String transCodingUrl) {
        this.transCodingUrl = transCodingUrl;
    }

    public String getTransCodingName() {
        return TransCodingName;
    }

    public void setTransCodingName(String transCodingName) {
        TransCodingName = transCodingName;
    }

    public List<String[]> getTransCodingDetails() {
        return transCodingDetails;
    }

    public void setTransCodingDetails(List<String[]> transCodingDetails) {
        this.transCodingDetails = transCodingDetails;
    }
}
