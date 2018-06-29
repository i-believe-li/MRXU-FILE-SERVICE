package com.mrxu.cloud.file.service.process.model;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.domain.process.sync.FileRequestSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseExtendDetailSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseExtendSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseSyncDTO;
import com.mrxu.cloud.file.enums.ResTypeEnum;
import com.mrxu.cloud.file.enums.TransTypeEnum;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/20
 */
@Service("modelZipResolveProcess")
public class ModelZipResolveProcessServiceImpl implements IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO> {

    private static final Logger LOG = Logger.getLogger(ModelZipResolveProcessServiceImpl.class);

    @Resource(name = "fdfsService")
    private IFileService fdfsService;

    @Override
    public FileResponseSyncDTO process(FileRequestSyncDTO fileRequestSync) throws MrxuException {
        FileResponseExtendSyncDTO modelExtend = new FileResponseExtendSyncDTO();
        //源文件本地存放绝对路径
        String originFilePath = fileRequestSync.getFilePath();
        String tempFileDir = originFilePath.substring(0, originFilePath.lastIndexOf(".")) + "/tmp";
        try {
            FileUtil.unZipFiles(originFilePath, tempFileDir);
        } catch (IOException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
        }
        //目录文件集
        File tmpFileDir = new File(tempFileDir);
        if (tmpFileDir.isDirectory()) {

            modelExtend.setExtendType(TransTypeEnum.Model.getItemValue());
            List<FileResponseExtendDetailSyncDTO> targetExtendList = new ArrayList<>();
            String[] fileList = tmpFileDir.list();
            for (int i = 0; i < fileList.length; i++) {
                String modelFilePath = tmpFileDir + "/" + fileList[i];
                String unionKey = FileUtil.calculateFileMD5(new File(modelFilePath), false);
                String modelUrl = this.fdfsService.uploadFileToServer(modelFilePath, true, true);
                //拓展详情
                FileResponseExtendDetailSyncDTO extendTarget = new FileResponseExtendDetailSyncDTO();
                extendTarget.setUnionKey(unionKey);
                extendTarget.setOrder(i+1);
                extendTarget.setTransType(modelUrl);
                if (FileTypeUtil.findResTypeEnum(modelFilePath).equalsIgnoreCase(ResTypeEnum.AndriodModel.getItemValue())) {
                    extendTarget.setTransType(ResTypeEnum.AndriodModel.getItemValue());
                } else if (FileTypeUtil.findResTypeEnum(modelFilePath).equalsIgnoreCase(ResTypeEnum.IosModel.getItemValue())) {
                    extendTarget.setTransType(ResTypeEnum.IosModel.getItemValue());
                } else if (FileTypeUtil.findResTypeEnum(modelFilePath).equalsIgnoreCase(ResTypeEnum.WindowModel.getItemValue())) {
                    extendTarget.setTransType(ResTypeEnum.WindowModel.getItemValue());
                } else if (FileTypeUtil.findResTypeEnum(modelFilePath).equalsIgnoreCase(ResTypeEnum.HoloModel.getItemValue())) {
                    extendTarget.setTransType(ResTypeEnum.HoloModel.getItemValue());
                } else if (FileTypeUtil.findResTypeEnum(modelFilePath).equalsIgnoreCase(ResTypeEnum.ModelConf.getItemValue())) {
                    extendTarget.setTransType(ResTypeEnum.ModelConf.getItemValue());
                }
                targetExtendList.add(extendTarget);
            }
            modelExtend.setExtendList(targetExtendList);
        } else {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
        }

        try {
            //删除临时目录
            //FileUtils.deleteRecursive(Paths.get(tempFileDir));
        } catch (Exception e) {
            LOG.warn("临时目录删除失败！");
        }

        FileResponseSyncDTO fileResponseSync = new FileResponseSyncDTO();
        BeanUtils.copyProperties(fileRequestSync, fileResponseSync);
        fileResponseSync.setExtend(modelExtend);
        return fileResponseSync;
    }
}
