package com.mrxu.cloud.file.service.filesys;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.entity.upload.UploadBaseDTO;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.InputStream;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
public interface IFileSystemService {
    String upload(String filePath) throws MrxuException;

    String upload(UploadBaseDTO updateBase, File file) throws MrxuException;

    String upload(MultipartFile file) throws MrxuException;

    String upload(UploadBaseDTO updateBase, InputStream ins) throws MrxuException;
}
