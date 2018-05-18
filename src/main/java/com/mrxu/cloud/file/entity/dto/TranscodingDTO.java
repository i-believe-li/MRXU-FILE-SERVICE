package com.mrxu.cloud.file.entity.dto;

import com.mrxu.cloud.common.entity.BaseEntity;
import com.mrxu.cloud.file.entity.vo.TransDetailVO;
import com.mrxu.cloud.file.entity.vo.TransVO;

import java.io.Serializable;
import java.util.List;

/**
 * 转码实体
 * @author ifocusing-xuzhiwei
 * @since 2017/11/22
 */
public class TranscodingDTO extends BaseEntity{
    private static final long serialVersionUID = -6478813021401937750L;
    private String unionKey;
    private String type;
    private String transType;
    private String url;
    private String fileName;
    private List<TransDetailVO> transDetails;

    public String getUnionKey() {
        return unionKey;
    }

    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public List<TransDetailVO> getTransDetails() {
        return transDetails;
    }

    public void setTransDetails(List<TransDetailVO> transDetails) {
        this.transDetails = transDetails;
    }
}
