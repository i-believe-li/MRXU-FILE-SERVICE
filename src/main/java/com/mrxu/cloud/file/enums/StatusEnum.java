package com.mrxu.cloud.file.enums;

/**
 * 状态枚举
 * @author ifocusing-xujia
 * @since 2017/5/10
 */
public enum StatusEnum {

    delete((byte)0, "delete"),//删除
    disable((byte)1, "disable"),//未启用 -- 备用状态
    enable((byte)2, "enable"),//启用/激活状态
    forbid((byte)3, "forbid"), //禁用
    expire((byte)4, "expire"),//启用状态下变过期
    forbidExpire((byte)5,"forbidExpire"),//禁用状态下过期
    ;

    private String itemLabel;
    private Byte status;
    private String statusName;

    StatusEnum(Byte status, String statusName){
        this.itemLabel = this.name();
        this.status = status;
        this.statusName = statusName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    public void setItemLabel(String itemLabel) {
        this.itemLabel = itemLabel;
    }

    public String getStatusName() {
        return statusName;
    }

    public void setStatusName(String statusName) {
        this.statusName = statusName;
    }

    public static StatusEnum findStatusEnum(String statusName){
        for(StatusEnum statusEnum : StatusEnum.values()){
            if(statusEnum.getStatusName().equalsIgnoreCase(statusName)){
                return statusEnum;
            }
        }
        return null;
    }
}
