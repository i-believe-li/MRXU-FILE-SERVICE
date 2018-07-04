package com.mrxu.cloud.file.domain.file;

import java.io.Serializable;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/14
 */
public class FileResultExtendVO implements Serializable{
    private static final long serialVersionUID = -622040334806926923L;
    //拓展文件类型
    private String extendType;
    //拓展文件多个子文件集
    private List<FileResultExtendDetailVO> extendList;

    public String getExtendType() {
        return extendType;
    }

    public void setExtendType(String extendType) {
        this.extendType = extendType;
    }

    public List<FileResultExtendDetailVO> getExtendList() {
        return extendList;
    }

    public void setExtendList(List<FileResultExtendDetailVO> extendList) {
        this.extendList = extendList;
    }
}
