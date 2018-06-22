package com.mrxu.cloud.file.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/19
 */
public enum ProcessModeEnum {

    sync("sync"),
    async("async"),
    ;

    private String itemLabel;
    private String itemValue;

    ProcessModeEnum(String itemValue){
        this.itemLabel = this.name();
        this.itemValue = itemValue;
    }

    public String getItemValue() {
        return itemValue;
    }

    public String getItemLabel() {
        return itemLabel;
    }

    @Override
    public String toString() {
        return this.itemValue;
    }

    public static boolean contains(String value){
        for(ProcessModeEnum processModeEnum : ProcessModeEnum.values()){
            if(processModeEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }

    /**
     * 根据类型获取枚举
     * @param type
     * @return
     */
    public static ProcessModeEnum findTransType(String type){
        for(ProcessModeEnum processModeEnum : ProcessModeEnum.values()){
            if(processModeEnum.itemValue.toLowerCase().equals(type.toLowerCase())){
                return processModeEnum;
            }
        }
        return null;
    }
}
