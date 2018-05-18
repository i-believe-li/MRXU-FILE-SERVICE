package com.mrxu.cloud.file.service;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.entity.vo.FileInfoVO;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
public interface IFileInfoService {
    /**
     * 文件上传
     * @param file
     * @return
     */
    FileInfoVO upload(MultipartFile file) throws MrxuException;
}
