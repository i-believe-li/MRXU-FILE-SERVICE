package com.mrxu.cloud.file.enums;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/20
 */
public enum ModelEnum {

    SDF("sdf"),
    ABI_SRF("abi.srf"),
    ABW_SRF("abw.srf"),
    ABA_SRF("aba.srf"),
    ABH_SRF("abh.srf")
    ;

    private String itemLabel;
    private String itemValue;

    ModelEnum(String itemValue){
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
        for(ModelEnum modelEnum : ModelEnum.values()){
            if(modelEnum.itemValue.equals(value)){
                return true;
            }
        }
        return false;
    }
}
