package com.mrxu.cloud.file.util;

import com.mrxu.cloud.common.enums.AudioFileEnum;
import com.mrxu.cloud.common.enums.PicFileEnum;
import com.mrxu.cloud.common.enums.TxtFileEnum;
import com.mrxu.cloud.common.enums.VideoFileEnum;
import com.mrxu.cloud.file.depend.VestaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.util.Objects;

@Component
public class OSSKeyGenerator {

	@Autowired
	VestaService vestaService;

	public String genKey(String fileSuffix) {
		//生成唯一ID
		String id = String.valueOf(vestaService.genId());

		if (StringUtils.isEmpty(fileSuffix)) {
			return "conf/" + id + ".sdf";
		} else {
			if (TxtFileEnum.contains(fileSuffix)) {
				return "txt/" + id + "." + fileSuffix;
			}
			if (PicFileEnum.contains(fileSuffix)) {
				return "picture/" + id + "." + fileSuffix;
			}
			if (AudioFileEnum.contains(fileSuffix)) {
				return "audio/" + id + "." + fileSuffix;
			}
			if (VideoFileEnum.contains(fileSuffix)) {
				return "video/" + id + "." + fileSuffix;
			}
			if (Objects.equals(fileSuffix, "fmv")) {
				return "fmv/" + id + "." + fileSuffix;
			}
			if (Objects.equals(fileSuffix, "srf")) {
				return "model/" + id + "." + fileSuffix;
			}
			if (Objects.equals(fileSuffix, "sdf")) {
				return "conf/" + id + "." + fileSuffix;
			}
			return "file/" + id + "." + fileSuffix;
		}
	}
}