package com.mrxu.cloud.file.mybatis.entity;

import com.mrxu.cloud.common.entity.BaseEntity;

public class FileInfo extends BaseEntity {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String id;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.parent_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String parentId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.union_key
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String unionKey;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String fileName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String type;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.extension
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String extension;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String url;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.thumbnail
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String thumbnail;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.description
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String description;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.creator
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private String creator;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long gmtCreate;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Long gmtModified;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column file_info.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    private Byte status;

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.id
     *
     * @return the value of file_info.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getId() {
        return id;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.id
     *
     * @param id the value for file_info.id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setId(String id) {
        this.id = id == null ? null : id.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.parent_id
     *
     * @return the value of file_info.parent_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getParentId() {
        return parentId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.parent_id
     *
     * @param parentId the value for file_info.parent_id
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setParentId(String parentId) {
        this.parentId = parentId == null ? null : parentId.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.union_key
     *
     * @return the value of file_info.union_key
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getUnionKey() {
        return unionKey;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.union_key
     *
     * @param unionKey the value for file_info.union_key
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setUnionKey(String unionKey) {
        this.unionKey = unionKey == null ? null : unionKey.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.file_name
     *
     * @return the value of file_info.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getFileName() {
        return fileName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.file_name
     *
     * @param fileName the value for file_info.file_name
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setFileName(String fileName) {
        this.fileName = fileName == null ? null : fileName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.type
     *
     * @return the value of file_info.type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getType() {
        return type;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.type
     *
     * @param type the value for file_info.type
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setType(String type) {
        this.type = type == null ? null : type.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.extension
     *
     * @return the value of file_info.extension
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getExtension() {
        return extension;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.extension
     *
     * @param extension the value for file_info.extension
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setExtension(String extension) {
        this.extension = extension == null ? null : extension.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.url
     *
     * @return the value of file_info.url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getUrl() {
        return url;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.url
     *
     * @param url the value for file_info.url
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setUrl(String url) {
        this.url = url == null ? null : url.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.thumbnail
     *
     * @return the value of file_info.thumbnail
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getThumbnail() {
        return thumbnail;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.thumbnail
     *
     * @param thumbnail the value for file_info.thumbnail
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setThumbnail(String thumbnail) {
        this.thumbnail = thumbnail == null ? null : thumbnail.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.description
     *
     * @return the value of file_info.description
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getDescription() {
        return description;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.description
     *
     * @param description the value for file_info.description
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setDescription(String description) {
        this.description = description == null ? null : description.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.creator
     *
     * @return the value of file_info.creator
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public String getCreator() {
        return creator;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.creator
     *
     * @param creator the value for file_info.creator
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setCreator(String creator) {
        this.creator = creator == null ? null : creator.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.gmt_create
     *
     * @return the value of file_info.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getGmtCreate() {
        return gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.gmt_create
     *
     * @param gmtCreate the value for file_info.gmt_create
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setGmtCreate(Long gmtCreate) {
        this.gmtCreate = gmtCreate;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.gmt_modified
     *
     * @return the value of file_info.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Long getGmtModified() {
        return gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.gmt_modified
     *
     * @param gmtModified the value for file_info.gmt_modified
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setGmtModified(Long gmtModified) {
        this.gmtModified = gmtModified;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column file_info.status
     *
     * @return the value of file_info.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public Byte getStatus() {
        return status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column file_info.status
     *
     * @param status the value for file_info.status
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    public void setStatus(Byte status) {
        this.status = status;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
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
        FileInfo other = (FileInfo) that;
        return (this.getId() == null ? other.getId() == null : this.getId().equals(other.getId()))
            && (this.getParentId() == null ? other.getParentId() == null : this.getParentId().equals(other.getParentId()))
            && (this.getUnionKey() == null ? other.getUnionKey() == null : this.getUnionKey().equals(other.getUnionKey()))
            && (this.getFileName() == null ? other.getFileName() == null : this.getFileName().equals(other.getFileName()))
            && (this.getType() == null ? other.getType() == null : this.getType().equals(other.getType()))
            && (this.getExtension() == null ? other.getExtension() == null : this.getExtension().equals(other.getExtension()))
            && (this.getUrl() == null ? other.getUrl() == null : this.getUrl().equals(other.getUrl()))
            && (this.getThumbnail() == null ? other.getThumbnail() == null : this.getThumbnail().equals(other.getThumbnail()))
            && (this.getDescription() == null ? other.getDescription() == null : this.getDescription().equals(other.getDescription()))
            && (this.getCreator() == null ? other.getCreator() == null : this.getCreator().equals(other.getCreator()))
            && (this.getGmtCreate() == null ? other.getGmtCreate() == null : this.getGmtCreate().equals(other.getGmtCreate()))
            && (this.getGmtModified() == null ? other.getGmtModified() == null : this.getGmtModified().equals(other.getGmtModified()))
            && (this.getStatus() == null ? other.getStatus() == null : this.getStatus().equals(other.getStatus()));
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table file_info
     *
     * @mbggenerated Tue May 15 16:44:04 CST 2018
     */
    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((getId() == null) ? 0 : getId().hashCode());
        result = prime * result + ((getParentId() == null) ? 0 : getParentId().hashCode());
        result = prime * result + ((getUnionKey() == null) ? 0 : getUnionKey().hashCode());
        result = prime * result + ((getFileName() == null) ? 0 : getFileName().hashCode());
        result = prime * result + ((getType() == null) ? 0 : getType().hashCode());
        result = prime * result + ((getExtension() == null) ? 0 : getExtension().hashCode());
        result = prime * result + ((getUrl() == null) ? 0 : getUrl().hashCode());
        result = prime * result + ((getThumbnail() == null) ? 0 : getThumbnail().hashCode());
        result = prime * result + ((getDescription() == null) ? 0 : getDescription().hashCode());
        result = prime * result + ((getCreator() == null) ? 0 : getCreator().hashCode());
        result = prime * result + ((getGmtCreate() == null) ? 0 : getGmtCreate().hashCode());
        result = prime * result + ((getGmtModified() == null) ? 0 : getGmtModified().hashCode());
        result = prime * result + ((getStatus() == null) ? 0 : getStatus().hashCode());
        return result;
    }
}