package com.mrxu.cloud.file.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public enum FileServiceEnum {
    local("local"),//local(fastdfs文件服务器、ffmpeg转码)
    oss("oss"),//oss云服务器，阿里云全家桶
    ;

    private String itemLabel;
    private String itemValue;

    FileServiceEnum(String itemValue){
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
        for(FileServiceEnum fileServiceEnum : FileServiceEnum.values()){
            if(fileServiceEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
