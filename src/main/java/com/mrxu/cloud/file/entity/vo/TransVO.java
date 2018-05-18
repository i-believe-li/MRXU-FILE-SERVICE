package com.mrxu.cloud.file.entity.vo;

import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public class TransVO extends BaseEntity {
    private static final long serialVersionUID = 4955673008257760537L;
    private String transType;
    private TransDetailVO details;

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public TransDetailVO getDetails() {
        return details;
    }

    public void setDetails(TransDetailVO details) {
        this.details = details;
    }
}
