package com.mrxu.cloud.file.enums;

/**
 * 转码类型枚举
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public enum TranscodingTypeEnum {
    m3u8Transcode("m3u8Transcode"),//m3u8
    mp4Transcode("mp4Transcode"),//mp4
    flvTranscode("flvTranscode"),//flv
    picTranscode("picTranscode"),//pic
    ;

    private String itemLabel;
    private String itemValue;

    TranscodingTypeEnum(String itemValue){
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
        for(TranscodingTypeEnum transcodingTypeEnum : TranscodingTypeEnum.values()){
            if(transcodingTypeEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
