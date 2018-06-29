package com.mrxu.cloud.file.service.file.custom;

import com.mrxu.cloud.common.enums.ResTypeEnum;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.domain.FileTransScheduleExample;
import com.mrxu.cloud.file.domain.Files;
import com.mrxu.cloud.file.domain.FilesExample;
import com.mrxu.cloud.file.domain.file.FileResultExtendDetailVO;
import com.mrxu.cloud.file.domain.file.FileResultExtendVO;
import com.mrxu.cloud.file.domain.file.FileResultVO;
import com.mrxu.cloud.file.enums.TransTypeEnum;
import com.mrxu.cloud.file.mapper.FileTransScheduleMapper;
import com.mrxu.cloud.file.mapper.FilesMapper;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件辅助服务
 *     files、fileTransSchedule 增删改查
 * @author ifocusing-xuzhiwei
 * @since 2017/11/21
 */
@Component
public class FileAuxiliaryService {

    @Autowired
    FilesMapper filesMapper;
    @Autowired
    FileTransScheduleMapper fileTransScheduleMapper;

    /**
     * 根据文件MD5获取文件结果
     * @return
     */
    public FileResultVO findFileResultByUnionKey(String unionKey) throws MrxuException {
        Files files = this.findFileInfo(unionKey);
        if(null == files){
            return null;
        }
        //文件主信息
        FileResultVO fileResult = new FileResultVO();
        fileResult.setFileType(files.getType());
        fileResult.setThumbnail(files.getThumbnail());
        fileResult.setUrl(files.getUrl());
        fileResult.setFileName(files.getFileName());

        //判断是否有拓展信息
        FilesExample extendExample = new FilesExample();
        extendExample.createCriteria().andParentIdEqualTo(files.getId());
        List<Files> extendFileList = this.filesMapper.selectByExample(extendExample);
        //没有拓展信息, 直接返回
        if(null == extendFileList || extendFileList.isEmpty()) return fileResult;

        //有拓展信息，需返回  --> 这里暂时默认拓展只有一个类型
        FileResultExtendVO extend = new FileResultExtendVO();
        if(files.getType().equalsIgnoreCase(ResTypeEnum.Pdf.getItemValue())){
            //图文集
            extend.setExtendType(TransTypeEnum.PicAlbum.getItemValue());
        } else if(files.getType().equalsIgnoreCase(ResTypeEnum.Zip.getItemValue())){
            //模型文件集
            extend.setExtendType(TransTypeEnum.Model.getItemValue());
        } else if(files.getType().equalsIgnoreCase(ResTypeEnum.Video.getItemValue())){
            //模型文件集
            extend.setExtendType("VideoTrans");
        } else {
            //默认的拓展类型
            extend.setExtendType("default");
        }
        //拓展目标详情
        List<FileResultExtendDetailVO> extendTargetList = new ArrayList<>();
        int i = 1;
        for(Files extendFile : extendFileList){
            //子文件类型
            String childType = extendFile.getType();
            //子文件URL
            String childUrl = extendFile.getUrl();
            if (StringUtils.isEmpty(childType)) continue;
            //单个拓展详情
            FileResultExtendDetailVO target = new FileResultExtendDetailVO();
            target.setOrder(i++);
            target.setTransUrl(childUrl);
            target.setTransType(extendFile.getTransType());
            target.setThumbnail(extendFile.getThumbnail());
            extendTargetList.add(target);
        }
        extend.setExtendList(extendTargetList);
        fileResult.setExtend(extend);
        return fileResult;
    }

    /**
     * 保存文件信息
     * @param files
     * @return
     */
    public int saveFileInfo(Files files){
        return filesMapper.insert(files);
    }

    /**
     * 通过MD5获取文件信息
     * @param md5
     * @return
     */
    public Files findFileInfo(String md5){
        FilesExample filesExample = new FilesExample();
        filesExample.createCriteria().andUnionKeyEqualTo(md5);
        List<Files> filesList = filesMapper.selectByExample(filesExample);
        if(null != filesList && !filesList.isEmpty()){
            return filesList.get(0);
        }
        return null;
    }

    /**
     * 根据fileId获取转码信息
     * @param parentId
     * @return
     */
    public List<Files> findTransFileInfo(String parentId){
        FilesExample filesExample = new FilesExample();
        filesExample.createCriteria().andParentIdEqualTo(parentId);
        return filesMapper.selectByExample(filesExample);
    }

    /**
     * 获取转码信息
     * @return
     */
    public Files findTransFiles(String fileId, TransTypeEnum transTypeEnum){
        FilesExample filesExample = new FilesExample();
        filesExample.createCriteria().andIdEqualTo(fileId).andTypeEqualTo(transTypeEnum.getItemValue());
        List<Files> filesList = filesMapper.selectByExample(filesExample);
        if(null != filesList && !filesList.isEmpty()){
            return filesList.get(0);
        }
        filesExample.createCriteria().andParentIdEqualTo(fileId).andTypeEqualTo(transTypeEnum.getItemValue());
        filesList = filesMapper.selectByExample(filesExample);
        if(null != filesList && !filesList.isEmpty()){
            return filesList.get(0);
        }
        return null;
    }

    /**
     * 删除任务表
     * @param scheduleId
     * @return
     */
    public int deleteFileTransSchedule(String scheduleId){
        return fileTransScheduleMapper.deleteByPrimaryKey(scheduleId);
    }

    /**
     * 判断进度表是否还有数据，如果转码完成，删除临时存放文件
     * @param fileId
     * @param filePath
     */
    public void deleteTempFile(String fileId, String filePath){
        FileTransScheduleExample fileTransScheduleExample = new FileTransScheduleExample();
        fileTransScheduleExample.createCriteria().andFileIdEqualTo(fileId);
        if(0 == fileTransScheduleMapper.countByExample(fileTransScheduleExample)){
            //删除临时文件
            new File(filePath).delete();
        }
    }
    
    /**
     * 
    * @Title: countFileTransScheduleByFilePath  
    * @Description: 验证临时文件是否也为其他转码任务提供支持
    * @param filePath 临时文件路径
    * @return long
    * @author ifocusing-wangjiafei
    * @throws
     */
    public long countFileTransScheduleByFilePath(String filePath) {
    	FileTransScheduleExample fileTransScheduleExample = new FileTransScheduleExample();
    	fileTransScheduleExample.createCriteria().andFilePathEqualTo(filePath);
    	return fileTransScheduleMapper.countByExample(fileTransScheduleExample);
    }
}
