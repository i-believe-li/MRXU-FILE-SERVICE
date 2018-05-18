package com.mrxu.cloud.file.service.filesys;

import com.mrxu.cloud.file.entity.dto.TranscodingDTO;
import com.mrxu.cloud.file.enums.TranscodingTypeEnum;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public interface IMediaService {
    /**
     * 转码操作
     * @param url 请求转码URL
     * @param transcodingTypeEnum 转码类型
     * @param attname 文件名(url需要自动加上,例：http://xxxx:xxxx/M00/00/s.png?attname=测试.png)
     * @return
     */
    TranscodingDTO transcode(String url, TranscodingTypeEnum transcodingTypeEnum, String attname);
}
