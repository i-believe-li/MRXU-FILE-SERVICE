package com.mrxu.cloud.file.service.process.trans.fdfs;

import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.CMDExecteUtil;
import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.entity.process.async.FileRequestTransDO;
import com.mrxu.cloud.file.domain.entity.process.async.FileResponseTransDO;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 */
@Service("flvTransProcess")
public class FlvTransProcessServiceImpl implements IFileProcessService<FileRequestTransDO, FileResponseTransDO> {

    private Logger LOG = Logger.getLogger(FlvTransProcessServiceImpl.class);

    @Value("${transcode.toflv:'ffmpeg -y -i #source# -acodec aac -ab 56  -ar 44100 -b 500 -r 15 #target#'}")
    private String transferCMD;

    @Autowired
    private VestaService vestaService;
    @Resource(name = "fdfsService")
    private IFileService fileService;

    @Override
    public FileResponseTransDO process(FileRequestTransDO request) throws MrxuException {
        //源文件路径
        String filePath = request.getFilePath();
        //文件名
        String fileName = request.getFileName();

        if(StringUtils.isNotEmpty(fileName)){
            //获取不带后缀的文件名
            fileName = request.getFileName().substring(0, request.getFileName().indexOf("."));
        }
        if(!new File(filePath).exists()){
            //TODO 文件不存在需要下载到本地再处理
            String originUrl = request.getFileUrl();
            //下载成功即可保证与之前文件一致不用在更新任务表中字段
            FileUtil.downLoadFromUrl(originUrl, fileName, filePath);
        }

        //目标文件存放地址
        String flvFilePath =  FileUtil.getFilePath(filePath) + vestaService.genId() + ".flv";;

        //CMD执行 -- 转码
        String executeCMD = transferCMD.replace("#source#", filePath).replace("#target#", flvFilePath);
        LOG.info("ExecuteCMD: " + executeCMD);
        new CMDExecteUtil().exec(executeCMD);

        //文件上传操作，全路径并删除源文件
        String flvUrl = fileService.uploadFileToServer(flvFilePath, true,false);
        //获取文件MD5
        String unionCode = FileUtil.calculateFileMD5(new File(flvFilePath), false);
        
        FileResponseTransDO fileResponse = new FileResponseTransDO();
        fileResponse.setParentId(request.getFileId());
        fileResponse.setTargetFileExtension("flv");
        fileResponse.setTargetFileName(fileName + "flv");
        //视频缩略图可以沿用父类抽帧
        fileResponse.setTargetThumbnail(request.getThumbnail());
        fileResponse.setTargetType(FileTypeUtil.findResTypeEnum(request.getTransType()));
        fileResponse.setTargetTransType(request.getTransType());
        fileResponse.setTargetUnionCode(unionCode);
        fileResponse.setTargetUrl(flvUrl);
        return fileResponse;
    }
}
