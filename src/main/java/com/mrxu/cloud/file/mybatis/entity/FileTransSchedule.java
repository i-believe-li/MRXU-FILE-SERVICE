package com.mrxu.cloud.file.mybatis.entity;

import com.mrxu.cloud.common.entity.BaseEntity;

public class FileTransSchedule extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.file_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long fileId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.file_url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String fileUrl;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.file_path
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String filePath;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.target_type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String targetType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.schedule_time
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Integer scheduleTime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_trans_schedule.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.id
     *
     * @return the value of file_trans_schedule.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.id
     *
     * @param id the value for file_trans_schedule.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setId(Long id) {
        this.id = id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.file_id
     *
     * @return the value of file_trans_schedule.file_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getFileId() {
        return fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.file_id
     *
     * @param fileId the value for file_trans_schedule.file_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setFileId(Long fileId) {
        this.fileId = fileId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.file_name
     *
     * @return the value of file_trans_schedule.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.file_name
     *
     * @param fileName the value for file_trans_schedule.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.file_url
     *
     * @return the value of file_trans_schedule.file_url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getFileUrl() {
        return fileUrl;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.file_url
     *
     * @param fileUrl the value for file_trans_schedule.file_url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl == null ? null : fileUrl.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.file_path
     *
     * @return the value of file_trans_schedule.file_path
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getFilePath() {
        return filePath;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.file_path
     *
     * @param filePath the value for file_trans_schedule.file_path
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setFilePath(String filePath) {
        this.filePath = filePath == null ? null : filePath.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.target_type
     *
     * @return the value of file_trans_schedule.target_type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.target_type
     *
     * @param targetType the value for file_trans_schedule.target_type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.schedule_time
     *
     * @return the value of file_trans_schedule.schedule_time
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Integer getScheduleTime() {
        return scheduleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.schedule_time
     *
     * @param scheduleTime the value for file_trans_schedule.schedule_time
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setScheduleTime(Integer scheduleTime) {
        this.scheduleTime = scheduleTime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.gmt_create
     *
     * @return the value of file_trans_schedule.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.gmt_create
     *
     * @param gmtCreate the value for file_trans_schedule.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.gmt_modified
     *
     * @return the value of file_trans_schedule.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.gmt_modified
     *
     * @param gmtModified the value for file_trans_schedule.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_trans_schedule.status
     *
     * @return the value of file_trans_schedule.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_trans_schedule.status
     *
     * @param status the value for file_trans_schedule.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    @Override
    public boolean equals(Object that) {
        if (this == that) {
            return true;
        }
        if (that == null) {
            return false;
        }
        if (getClass() != that.getClass()) {
            return false;
        }
        FileTransSchedule other = (FileTransSchedule) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getFileId() == null ? other.getFileId() == null : this.getFileId().equals(other.getFileId()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getFileUrl() == null ? other.getFileUrl() == null : this.getFileUrl().equals(other.getFileUrl()))
            && (this.getFilePath() == null ? other.getFilePath() == null : this.getFilePath().equals(other.getFilePath()))
            && (this.getTargetType() == null ? other.getTargetType() == null : this.getTargetType().equals(other.getTargetType()))
            && (this.getScheduleTime() == null ? other.getScheduleTime() == null : this.getScheduleTime().equals(other.getScheduleTime()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_trans_schedule
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getFileId() == null) ? 0 : getFileId().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getFileUrl() == null) ? 0 : getFileUrl().hashCode());
        result = prime * result + ((getFilePath() == null) ? 0 : getFilePath().hashCode());
        result = prime * result + ((getTargetType() == null) ? 0 : getTargetType().hashCode());
        result = prime * result + ((getScheduleTime() == null) ? 0 : getScheduleTime().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}