package com.mrxu.cloud.file.service.impl.custom;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.entity.upload.UploadBaseDTO;
import com.mrxu.cloud.file.util.md5.MD5;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.*;
import java.text.DecimalFormat;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Component
public class CustomFileInfoService {
    /**
     * 获取文件基本信息
     * @param file
     * @return
     */
    /*public UploadBaseDTO findUpdateBaseInfo(MultipartFile file){
        //文件大小
        long fileSize = file.getSize();
        //上传文件名
        String filename = file.getOriginalFilename();
        //拓展名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        //获取文件类型
        UploadBaseDTO updateBase = new UploadBaseDTO();
        updateBase.setExtName(extName);
        updateBase.setFileName(filename);
        updateBase.setFileSize(fileSize);
        updateBase.setFile(file);
        return updateBase;
    }*/

    /**
     * 获取文件MD5
     * @param file
     * @return
     */
    public String findUnionKey(MultipartFile file) throws MrxuException{
        //计算MD5
        String md5;
        try {
            md5 = MD5.asHex(MD5.getHash(file));
        } catch (IOException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "无法计算文件MD5值，请核对！");
        }
        return md5;
    }

    /**
     * 整数相除 保留一位小数
     * @param a
     * @param b
     * @return
     */
    public Double division(int a ,int b){
        float num =(float)a/b;
        DecimalFormat df = new DecimalFormat("0.0");
        return Double.valueOf(df.format(num));
    }

    /**
     * 获取图片缩放比例
     * @param ins
     * @return
     */
    public Double getImageScale(InputStream ins) throws MrxuException{
        BufferedImage bufferedImg = null;
        try {
            bufferedImg = ImageIO.read(ins);
        } catch (IOException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "无法解析图片信息");
        }
        int imgWidth = bufferedImg.getWidth();
        int imgHeight = bufferedImg.getHeight();
        int maxEdge = imgWidth > imgHeight ? imgWidth : imgHeight;
        return maxEdge > 200 ? this.division(200, maxEdge) : 1D;
    }
}
