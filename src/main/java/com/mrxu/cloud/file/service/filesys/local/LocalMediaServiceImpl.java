package com.mrxu.cloud.file.service.filesys.local;

import com.mrxu.cloud.file.entity.dto.TranscodingDTO;
import com.mrxu.cloud.file.enums.TranscodingTypeEnum;
import com.mrxu.cloud.file.service.filesys.IMediaService;
import com.mrxu.cloud.file.service.filesys.ITranscodeService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Service("localMediaTranscode")
public class LocalMediaServiceImpl implements IMediaService{

    @Resource(name = "flvTranscode")
    ITranscodeService flvTranscode;

    @Resource(name = "m3u8Transcode")
    ITranscodeService m3u8Transcode;

    @Resource(name = "mp4Transcode")
    ITranscodeService mp4Transcode;

    @Resource(name = "picTranscode")
    ITranscodeService picTranscode;

    @Override
    public TranscodingDTO transcode(String url, TranscodingTypeEnum transcodingTypeEnum, String attname) {
        return null;
    }
}
