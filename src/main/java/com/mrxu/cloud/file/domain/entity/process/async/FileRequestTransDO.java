package com.mrxu.cloud.file.domain.entity.process.async;


import com.mrxu.cloud.common.entity.BaseEntity;

/**
 * 转码 process 请求参数
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 */
public class FileRequestTransDO extends BaseEntity {
    private static final long serialVersionUID = -2602865030638301130L;
    //任务ID
    private String scheduleId;
    //源文件ID
    private String fileId;
    //文件名称
    private String fileName;
    //源文件本地存放绝对路径
    private String filePath;
    //文件URL
    private String fileUrl;
    //目标类型
    private String transType;
    //缩略图 --如果是视屏的话可以沿用父类的缩略图，无需重新抽帧
    private String thumbnail;
    //转码实例name
    private String processInstance;

    
    public String getProcessInstance() {
		return processInstance;
	}

	public void setProcessInstance(String processInstance) {
		this.processInstance = processInstance;
	}

	public String getScheduleId() {
        return scheduleId;
    }

    public void setScheduleId(String scheduleId) {
        this.scheduleId = scheduleId;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFilePath() {
        return filePath;
    }

    public void setFilePath(String filePath) {
        this.filePath = filePath;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getTransType() {
        return transType;
    }

    public void setTransType(String transType) {
        this.transType = transType;
    }

    public String getThumbnail() {
        return thumbnail;
    }

    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail;
    }
}
