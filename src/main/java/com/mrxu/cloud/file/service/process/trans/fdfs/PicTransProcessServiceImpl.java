package com.mrxu.cloud.file.service.process.trans.fdfs;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.CMDExecteUtil;
import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.process.sync.FileRequestSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseSyncDTO;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import org.apache.log4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 视频抽帧(同步即可)
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 */
@Service("picTransProcess")
public class PicTransProcessServiceImpl implements IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO> {

    Logger LOG = Logger.getLogger(PicTransProcessServiceImpl.class);

    @Value("${transcode.getpic:'ffmpeg -i #source# -y -f image2 -ss 3 -t 0.001 #target#'}")
    private String transferCMD;
    @Autowired
    VestaService vestaService;
    @Resource(name = "fdfsService")
    IFileService fileService;


    @Override
    public FileResponseSyncDTO process(FileRequestSyncDTO fileRequestSync) throws MrxuException {

        //源文件路径
        String filePath = fileRequestSync.getFilePath();

        //目标文件存放地址
        String thumbnailFilePath =  FileUtil.getFilePath(filePath) + vestaService.genId() + ".jpeg";
        //CMD执行 -- 转码
        String executeCMD = this.transferCMD.replace("#source#", filePath).replace("#target#", thumbnailFilePath);
        LOG.info("ExecuteCMD: " + executeCMD);
        new CMDExecteUtil().exec(executeCMD);

        //文件上传操作，全路径并删除源文件
        String url = fileService.uploadFileToServer(thumbnailFilePath, true, true);

        //这里处理的是缩略图
        FileResponseSyncDTO fileResponseSync = new FileResponseSyncDTO();
        BeanUtils.copyProperties(fileRequestSync, fileResponseSync);
        fileResponseSync.setThumbnail(url);
        return fileResponseSync;
    }
}
