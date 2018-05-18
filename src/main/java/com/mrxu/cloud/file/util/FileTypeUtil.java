package com.mrxu.cloud.file.util;

import com.mrxu.cloud.common.enums.*;
import org.springframework.util.StringUtils;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/22
 */
public class FileTypeUtil {
    /**
     * 根据拓展名获取资源类型
     * @param fileName
     * @return
     */
    public static String findResTypeEnum(String fileName){
        //不包含"."
    	if(fileName.indexOf(".") == -1){
    		return ResTypeEnum.File.getItemValue();
    	}
    	String suffix = getFileExtName(fileName);
        if(StringUtils.isEmpty(suffix)){
            return ResTypeEnum.NotExist.getItemValue();
        } else if(VideoFileEnum.contains(suffix)){
            return ResTypeEnum.Video.getItemValue();
        } else if(AudioFileEnum.contains(suffix)){
            return ResTypeEnum.Audio.getItemValue();
        } else if(PicFileEnum.contains(suffix)){
            return ResTypeEnum.Pic.getItemValue();
        } else if(TxtFileEnum.contains(suffix)){
            return ResTypeEnum.Txt.getItemValue();
        } else if(suffix.toLowerCase().equals("pdf")){
            return ResTypeEnum.Pdf.getItemValue();
        } else if(suffix.toLowerCase().equals("zip")){
        	return ResTypeEnum.Zip.getItemValue();
        }
        return ResTypeEnum.File.getItemValue();
    }

    /**
     * fileExt
     * @param fileExt
     * @return
     */
    public static TransTypeEnum findTransType(String fileExt){
        if(TransTypeEnum.contains(fileExt)){
            return TransTypeEnum.valueOf(fileExt);
        }
        return null;
    }
    
    public static String getFileExtName(String fileName){
    	String suffix= fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
    	if(suffix.equalsIgnoreCase("srf")){
    		 fileName = fileName.substring(0, fileName.lastIndexOf("."));
    		 suffix =fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase()+"."+suffix;
    	}
    	return suffix;
    }
    
    public static void main(String[] args) {
		System.out.println(FileTypeUtil.findResTypeEnum("/root/puwang/111111111.mp4"));
	}
}

