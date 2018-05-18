package com.mrxu.cloud.file.service.filesys.local;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.entity.upload.UploadBaseDTO;
import com.mrxu.cloud.file.service.filesys.IFileSystemService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.csource.common.MyException;
import org.csource.fastdfs.ClientGlobal;
import org.csource.fastdfs.StorageClient1;
import org.csource.fastdfs.UploadCallback;
import org.csource.fastdfs.UploadStream;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.PostConstruct;
import java.io.*;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/15
 */
@Service("fastdfsService")
public class FastdfsSystemServiceImpl implements IFileSystemService {

    private Logger logger = Logger.getLogger(getClass());

    @Value("${IMAGE_ACCESS_URL}")
    private String IMAGE_ACCESS_URL;

    @PostConstruct
    private void initConfig() throws Exception {
        ClientGlobal.initByProperties("fastdfs-client.properties");
        logger.info("ClientGlobal.configInfo(): \n" + ClientGlobal.configInfo());
        logger.info(IMAGE_ACCESS_URL);
    }

    @Override
    public String upload(String filePath) throws MrxuException{
        //返回的上传地址
        String url;
        File file = new File(filePath);
        if(!file.exists()){
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "文件不存在");
        }
        //文件大小
        long fileSize = file.length();
        //上传文件名
        String filename = file.getName();
        //拓展名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        //获取文件类型
        String type = FileTypeUtil.findResTypeEnum(filename);
        UploadBaseDTO uploadBase = new UploadBaseDTO();
        uploadBase.setFileName(file.getName());
        uploadBase.setExtName(extName);
        uploadBase.setType(type);
        uploadBase.setFileSize(fileSize);
        try {
            url = this.upload(uploadBase, new FileInputStream(file));
        } catch (FileNotFoundException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, e.getCause());
        }
        return url;
    }

    @Override
    public String upload(UploadBaseDTO uploadBase, String filePath) throws MrxuException {
        String url;
        try {
            url = this.upload(uploadBase, new FileInputStream(filePath));
        } catch (FileNotFoundException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, e.getCause());
        }
        return url;
    }

    @Override
    public String upload(MultipartFile file) {
        InputStream ins = null;
        long fileSize = file.getSize();
        //上传文件名
        String filename = file.getOriginalFilename();
        //拓展名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        String result = null;
        try {
            ins = file.getInputStream();
            StorageClient1 client = new StorageClient1();
            UploadCallback callback = new UploadStream(ins, fileSize);
            result = client.upload_file1(null, fileSize, callback, extName, null);
        } catch (MyException me){
            logger.error("FastdfsSystemServiceImpl 上传文件失败！", me);
        } catch (IOException e) {
            logger.error("FastdfsController 接收文件失败！", e);
        } finally {
            if (ins != null){
                try {
                    ins.close();
                } catch (IOException e) {
                    logger.error("FastdfsController 接收文件失败！", e);
                }
            }
        }
        if(StringUtils.isNotEmpty(result)){
            return IMAGE_ACCESS_URL + result;
        }
        return null;
    }

    @Override
    public String upload(UploadBaseDTO uploadBase, InputStream ins) throws MrxuException{
        logger.info("文件开始上传...");
        String result;
        try {
            StorageClient1 client = new StorageClient1();
            UploadCallback callback = new UploadStream(ins, uploadBase.getFileSize());
            result = client.upload_file1(null, uploadBase.getFileSize(), callback, uploadBase.getExtName(), null);
        } catch (MyException me){
            logger.error("FastdfsSystemServiceImpl 上传文件失败！", me);
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "上传文件失败！");
        } catch (IOException e) {
            logger.error("FastdfsController 上传文件失败！", e);
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "上传文件失败！");
        }
        if(StringUtils.isNotEmpty(result)){
            uploadBase.setFileKey(result);
            String url = IMAGE_ACCESS_URL + result;
            uploadBase.setUrl(url);
            return url;
        }
        return null;
    }
}
