package com.mrxu.cloud.file.domain;

import com.mrxu.cloud.common.entity.BaseEntity;

public class FileType extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_type.id
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_type.type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_type.target_type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    private String targetType;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_type.process_instance
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    private String processInstance;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_type.process_mode
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    private String processMode;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_type.id
     *
     * @return the value of file_type.id
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_type.id
     *
     * @param id the value for file_type.id
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_type.type
     *
     * @return the value of file_type.type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_type.type
     *
     * @param type the value for file_type.type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_type.target_type
     *
     * @return the value of file_type.target_type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public String getTargetType() {
        return targetType;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_type.target_type
     *
     * @param targetType the value for file_type.target_type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public void setTargetType(String targetType) {
        this.targetType = targetType == null ? null : targetType.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_type.process_instance
     *
     * @return the value of file_type.process_instance
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public String getProcessInstance() {
        return processInstance;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_type.process_instance
     *
     * @param processInstance the value for file_type.process_instance
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public void setProcessInstance(String processInstance) {
        this.processInstance = processInstance == null ? null : processInstance.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_type.process_mode
     *
     * @return the value of file_type.process_mode
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public String getProcessMode() {
        return processMode;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_type.process_mode
     *
     * @param processMode the value for file_type.process_mode
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    public void setProcessMode(String processMode) {
        this.processMode = processMode == null ? null : processMode.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
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
        FileType other = (FileType) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getTargetType() == null ? other.getTargetType() == null : this.getTargetType().equals(other.getTargetType()))
            && (this.getProcessInstance() == null ? other.getProcessInstance() == null : this.getProcessInstance().equals(other.getProcessInstance()))
            && (this.getProcessMode() == null ? other.getProcessMode() == null : this.getProcessMode().equals(other.getProcessMode()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_type
     *
     * @mbggenerated Thu Jun 28 17:32:02 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getTargetType() == null) ? 0 : getTargetType().hashCode());
        result = prime * result + ((getProcessInstance() == null) ? 0 : getProcessInstance().hashCode());
        result = prime * result + ((getProcessMode() == null) ? 0 : getProcessMode().hashCode());
        return result;
    }
}