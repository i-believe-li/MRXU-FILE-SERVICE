package com.mrxu.cloud.file.util;

import com.mrxu.cloud.common.enums.AudioFileEnum;
import com.mrxu.cloud.common.enums.PicFileEnum;
import com.mrxu.cloud.common.enums.TxtFileEnum;
import com.mrxu.cloud.common.enums.VideoFileEnum;
import com.mrxu.cloud.file.enums.ResTypeEnum;
import org.springframework.util.StringUtils;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/22
 */
public class FileTypeUtil {
	/**
	 * 根据拓展名获取资源类型
	 *
	 * @param fileName
	 * @return
	 */
	public static String findResTypeEnum(String fileName) {
		if (StringUtils.isEmpty(fileName)) {
			return ResTypeEnum.File.getItemValue();
		}

		String suffix;
		if(fileName.indexOf(".") == -1){
			suffix = fileName;
		} else {
			suffix = getFileExtName(fileName);
			if(StringUtils.isEmpty(suffix)){
				return ResTypeEnum.File.getItemValue();
			}
		}

		if (StringUtils.isEmpty(suffix)) {
			return ResTypeEnum.NotExist.getItemValue();
		} else if (VideoFileEnum.contains(suffix)) {
			return ResTypeEnum.Video.getItemValue();
		} else if (AudioFileEnum.contains(suffix)) {
			return ResTypeEnum.Audio.getItemValue();
		} else if (PicFileEnum.contains(suffix)) {
			return ResTypeEnum.Pic.getItemValue();
		} else if (TxtFileEnum.contains(suffix)) {
			return ResTypeEnum.Txt.getItemValue();
		} else if (suffix.toLowerCase().equals("gls")) {
			return ResTypeEnum.Pdf.getItemValue();
		} else if (suffix.toLowerCase().equals("zip")) {
			return ResTypeEnum.Zip.getItemValue();
		} else if (suffix.toLowerCase().equals("sdf")) {
			return ResTypeEnum.ModelConf.getItemValue();
		} else if (suffix.toLowerCase().equals("abi.srf")) {
			return ResTypeEnum.IosModel.getItemValue();
		} else if (suffix.toLowerCase().equals("abw.srf")) {
			return ResTypeEnum.WindowModel.getItemValue();
		} else if (suffix.toLowerCase().equals("aba.srf")) {
			return ResTypeEnum.AndriodModel.getItemValue();
		} else if (suffix.toLowerCase().equals("abh.srf")) {
			return ResTypeEnum.HoloModel.getItemValue();
		}
		return ResTypeEnum.File.getItemValue();
	}

	/**
	 * 获取文件后缀名
	 * @param fileName
	 * @return
	 */
	public static String getFileExtName(String fileName) {
		String suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase();
		if (suffix.equalsIgnoreCase("srf")) {
			fileName = fileName.substring(0, fileName.lastIndexOf("."));
			if(fileName.indexOf(".") == -1){
				suffix = fileName + "." + suffix;
			} else {
				suffix = fileName.substring(fileName.lastIndexOf(".") + 1).toLowerCase() + "." + suffix;
			}
		}
		return suffix;
	}
}
