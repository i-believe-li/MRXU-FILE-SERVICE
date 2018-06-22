package com.mrxu.cloud.file.enums;

/**
 * 状态枚举
 * @author ifocusing-xujia
 * @since 2017/5/10
 */
public enum StatusEnum {
    on(1),
    off(0),
    ;

    private String itemLabel;
    private Integer itemValue;

    StatusEnum(Integer itemValue){
        this.itemLabel = this.name();
        this.itemValue = itemValue;
    }

    public Integer getItemValue() {
        return itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    @Override
    public String toString() {
        return Integer.toString(this.itemValue);
    }
}
