package com.mrxu.cloud.file.enums;

/**
 * 资源类型枚举
 * @author ifocusing-xujia
 * @since 2017/5/8
 */
public enum ResTypeEnum {
    File("File"),
    Txt("Txt"),
    Pic("Pic"),
    Pdf("Pdf"),
    Zip("Zip"),
    PicAlbum("PicAlbum"),
    Audio("Audio"),
    Video("Video"),
    Fmv("Fmv"),
    WindowModel("WModel"),
    IosModel("IModel"),
    AndriodModel("AModel"),
    HoloModel("HModel"),
    ModelConf("Sdf"),
    Conf("Conf"),
    NotExist("")
    ;

    private String itemLabel;
    private String itemValue;

    ResTypeEnum(String itemValue){
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
        for(ResTypeEnum fileEnum : ResTypeEnum.values()){
            if(fileEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
