package com.mrxu.cloud.file.service.filesys.oss;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.entity.upload.UploadBaseDTO;
import com.mrxu.cloud.file.service.filesys.IFileSystemService;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Service("ossService")
public class OssSystemServiceImpl implements IFileSystemService {
    @Override
    public String upload(String filePath) throws MrxuException {
        return null;
    }

    @Override
    public String upload(UploadBaseDTO updateBase, String filePath) throws MrxuException {
        return null;
    }

    @Override
    public String upload(MultipartFile file) {
        return null;
    }

    @Override
    public String upload(UploadBaseDTO updateBase, InputStream ins) {
        return null;
    }
}
