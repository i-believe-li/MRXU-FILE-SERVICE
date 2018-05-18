package com.mrxu.cloud.file.service.filesys.oss;

import com.mrxu.cloud.file.entity.dto.TranscodingDTO;
import com.mrxu.cloud.file.enums.TranscodingTypeEnum;
import com.mrxu.cloud.file.service.filesys.IMediaService;
import org.springframework.stereotype.Service;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Service("ossMediaTranscode")
public class OssMediaServiceImpl implements IMediaService {
    @Override
    public TranscodingDTO transcode(String url, TranscodingTypeEnum transcodingTypeEnum, String attname) {
        return null;
    }
}
