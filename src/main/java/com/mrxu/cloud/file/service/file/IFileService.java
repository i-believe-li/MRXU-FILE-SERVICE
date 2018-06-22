package com.mrxu.cloud.file.service.file;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.domain.entity.file.FileResultVO;
import com.mrxu.cloud.file.domain.entity.trans.TransCodingResultVO;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * 提供文件的服务，包含上传下载和删除
 * @author ifocusing-xuzhiwei
 * @since 2017/8/24
 */
public interface IFileService {
    
    /**
     * 文件上传
     * @param file
     * @return
     * @throws MrxuException
     */
    FileResultVO uploadFile(MultipartFile file) throws MrxuException;

    /**
     * 上传多个文件
     * @return
     * @throws MrxuException
     */
    List<FileResultVO> uploadMultipleFile(MultipartFile[] files) throws MrxuException;

    /**
     * 上传文件FDFS
     * @param file
     * @param isFullPath
     * @param isDelete
     * @return
     * @throws MrxuException
     */
    String uploadFileToServer(String file, boolean isFullPath, boolean isDelete) throws MrxuException;

    /**
     * 下载文件
     * @param url 文件存储服务器的url地址
     * @return 本地文件的地址
     * @throws Exception 
     */
    String downloadFile(String url);
    
    
    /**
     * 删除文件（放入回收站，一个月后自动删除）
     */
    int deleteFile(String key);
    
    /**
     * 文件是否存在
     */
    boolean exist(String key);

    /**
     * 获取转码信息
     * @param fileUrl 转码源对象
     * @param targetType 转码目标对象
     * @return
     */
    TransCodingResultVO findTransInfo(String fileUrl, String targetType, String mediaPlatform) throws MrxuException;
 }
