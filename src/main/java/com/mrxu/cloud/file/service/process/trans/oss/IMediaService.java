package com.mrxu.cloud.file.service.process.trans.oss;


import com.mrxu.cloud.common.enums.MTSTranscodingEnum;
import com.mrxu.cloud.file.domain.entity.trans.TransCodingDTO;

/**
 * 系统媒体服务
 * 提供各种格式转mp4的功能
 * 以及mp4转m3u8的功能
 * 提供抽首帧的服务
 * 提供
 * @author zhangdejun
 *
 */
public interface IMediaService {
	/**
	 * 提供各种视频转码服务，
	 * 包括 mp4 \ flv \ m3u8 \ mp3
	 * @param file 转码后的服务的
	 * @return
	 */
	TransCodingDTO transCode(String file, MTSTranscodingEnum trancodeEnum, String attName);
}
