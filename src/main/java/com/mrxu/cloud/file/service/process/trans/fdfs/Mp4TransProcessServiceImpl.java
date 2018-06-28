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
@Service("mp4TransProcess")
public class Mp4TransProcessServiceImpl implements IFileProcessService<FileRequestTransDO, FileResponseTransDO> {

    private static Logger LOG = Logger.getLogger(Mp4TransProcessServiceImpl.class);

    @Value("${transcode.tomp4:'ffmpeg -y -i #source# -f mp4 -vcodec mpeg4 -profile:v high -pre medium  -b:v 720k  #target#'}")
    private String transferCMD;
    @Autowired
    private VestaService vestaService;
    @Resource(name = "fdfsService")
    private IFileService fileService;

    @Override
    public FileResponseTransDO process(FileRequestTransDO request) throws MrxuException {
        //源文件路径
        String filePath = request.getFilePath();
        String fileName = request.getFileName();

        if(StringUtils.isNotEmpty(fileName)){
            //获取不带后缀的文件名
            fileName = request.getFileName().substring(0, request.getFileName().indexOf("."));
        }
        if(!new File(filePath).exists()){
            //TODO 文件不存在需要下载到本地再处理
            String originUrl = request.getFileUrl();
            //下载
            FileUtil.downLoadFromUrl(originUrl, fileName, filePath);
        }
        //目标文件存放地址
        String mp4FilePath = filePath + vestaService.genId() + ".mp4";

        //转码操作
        String tmpCMD = transferCMD.replace("#source#", filePath).replace("#target#", String.valueOf(mp4FilePath));
        LOG.info("tmpCMD: " + tmpCMD);
        CMDExecteUtil executor = new CMDExecteUtil();
        executor.exec(tmpCMD);

        //mp4上传地址
        String mp4Url = fileService.uploadFileToServer(mp4FilePath,true,false);
        //唯一码
        String unionCode = FileUtil.calculateFileMD5(new File(mp4FilePath), true);
        //返回对象封装
        FileResponseTransDO fileResponse = new FileResponseTransDO();
        fileResponse.setParentId(request.getFileId());
        fileResponse.setTargetFileExtension("mp4");
        fileResponse.setTargetFileName(fileName + ".mp4");
        //视频缩略图可以沿用父类抽帧
        fileResponse.setTargetThumbnail(request.getThumbnail());
        fileResponse.setTargetType(FileTypeUtil.findResTypeEnum(request.getTransType()));
        fileResponse.setTargetTransType(request.getTransType());
        fileResponse.setTargetUnionCode(unionCode);
        fileResponse.setTargetUrl(mp4Url);
        return fileResponse;
    }
}
