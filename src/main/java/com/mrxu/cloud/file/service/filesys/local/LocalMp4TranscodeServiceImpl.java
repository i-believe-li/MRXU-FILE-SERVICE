package com.mrxu.cloud.file.service.filesys.local;

import com.mrxu.cloud.file.entity.dto.TranscodingDTO;
import com.mrxu.cloud.file.service.filesys.ITranscodeService;
import org.springframework.stereotype.Service;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Service("mp4Transcode")
public class LocalMp4TranscodeServiceImpl implements ITranscodeService {
    @Override
    public TranscodingDTO tanscode() {
        return null;
    }
}
