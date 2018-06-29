package com.mrxu.cloud.file.service.process.thumbnail;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.MathUtil;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.process.sync.FileRequestSyncDTO;
import com.mrxu.cloud.file.domain.process.sync.FileResponseSyncDTO;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.io.IOException;

/**
 * 获取图片缩略图
 * @author ifocusing-xuzhiwei
 * @since 2018/6/19
 */
@Service("picThumbnailProcess")
public class PicThumbnailProcessServiceImpl implements IFileProcessService<FileRequestSyncDTO, FileResponseSyncDTO> {

    @Autowired
    VestaService vestaService;
    @Resource(name = "fdfsService")
    IFileService fileService;

    @Override
    public FileResponseSyncDTO process(FileRequestSyncDTO fileRequestSync) throws MrxuException {
        //源文件大小
        long fileSize = fileRequestSync.getFileSize();
        //源文件绝对路径
        String filePath = fileRequestSync.getFilePath();
        //临时文件存放目录
        String fileDir = fileRequestSync.getFileDir();
        //缩略图
        String thumbnailUrl;

        //当上传文件小于60KB,缩略图就是原图，而且不需要生成缩略图信息
        if(fileSize > 1024 * 60){
            String thumbnailPath = fileDir + vestaService.genId() + ".jpeg";
            try {
                Double scale = MathUtil.getImageScale(filePath);
                Thumbnails.of(filePath).scale(scale).outputQuality(1f).toFile(thumbnailPath);
            } catch (IOException e) {
                throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
            }
            //缩略图上传操作
            thumbnailUrl = this.fileService.uploadFileToServer(thumbnailPath, true, true);
        } else {
            thumbnailUrl =  fileRequestSync.getUrl();
        }

        //这里处理的是缩略图
        FileResponseSyncDTO fileResponseSync = new FileResponseSyncDTO();
        BeanUtils.copyProperties(fileRequestSync, fileResponseSync);
        fileResponseSync.setThumbnail(thumbnailUrl);
        return fileResponseSync;
    }
}
