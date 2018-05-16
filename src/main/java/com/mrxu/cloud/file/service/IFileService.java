package com.mrxu.cloud.file.service;

import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
public interface IFileService {
    String upload(MultipartFile file);
}
