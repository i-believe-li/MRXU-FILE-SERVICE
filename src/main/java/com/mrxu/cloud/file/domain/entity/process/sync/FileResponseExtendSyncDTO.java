package com.mrxu.cloud.file.domain.entity.process.sync;

import java.io.Serializable;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/14
 */
public class FileResponseExtendSyncDTO implements Serializable{
    private static final long serialVersionUID = -622040334806926923L;
    //拓展文件类型
    private String extendType;
    //拓展文件多个子文件集
    private List<FileResponseExtendTargetSyncDTO> targetList;

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType;
    }

    public List<FileResponseExtendTargetSyncDTO> getTargetList() {
        return targetList;
    }

    public void setTargetList(List<FileResponseExtendTargetSyncDTO> targetList) {
        this.targetList = targetList;
    }
}
