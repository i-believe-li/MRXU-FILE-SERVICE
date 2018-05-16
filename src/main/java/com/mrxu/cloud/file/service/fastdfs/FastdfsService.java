package com.mrxu.cloud.file.service.fastdfs;

import com.mrxu.cloud.file.service.IFileService;
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
import java.io.IOException;
import java.io.InputStream;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/15
 */
@Service("fastdfs")
public class FastdfsService implements IFileService {

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
            logger.error("FastdfsService 上传文件失败！", me);
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
}
