package com.mrxu.cloud.file.domain.process.sync;

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
    private List<FileResponseExtendDetailSyncDTO> extendList;

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType;
    }

    public List<FileResponseExtendDetailSyncDTO> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<FileResponseExtendDetailSyncDTO> extendList) {
        this.extendList = extendList;
    }
}
