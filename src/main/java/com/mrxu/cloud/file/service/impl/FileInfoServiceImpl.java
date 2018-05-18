package com.mrxu.cloud.file.service.impl;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.entity.upload.UploadBaseDTO;
import com.mrxu.cloud.file.entity.vo.FileInfoVO;
import com.mrxu.cloud.file.enums.FileServiceEnum;
import com.mrxu.cloud.file.enums.StatusEnum;
import com.mrxu.cloud.file.fastdfs.StringUtils;
import com.mrxu.cloud.file.mybatis.entity.FileInfo;
import com.mrxu.cloud.file.mybatis.entity.FileInfoExample;
import com.mrxu.cloud.file.mybatis.mapper.FileInfoMapper;
import com.mrxu.cloud.file.service.IFileInfoService;
import com.mrxu.cloud.file.service.filesys.IFileSystemService;
import com.mrxu.cloud.file.service.filesys.IMediaService;
import com.mrxu.cloud.file.service.impl.custom.CustomFileInfoService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/18
 */
@Service
public class FileInfoServiceImpl implements IFileInfoService{

    @Value("${current.file.service:local}")
    String currentFileService;
    @Value("${file.temp.save.dir}")
    String fileTempSaveDir;

    @Resource(name = "ossMediaTranscode")
    IMediaService ossMediaTranscode;
    @Resource(name = "localMediaTranscode")
    IMediaService localMediaTranscode;

    @Resource(name = "fastdfsService")
    IFileSystemService fastdfsService;
    @Resource(name = "ossService")
    IFileSystemService ossService;

    @Autowired
    CustomFileInfoService customFileInfoService;
    @Autowired
    FileInfoMapper fileInfoMapper;
    @Autowired
    VestaService vestaService;

    @Override
    public FileInfoVO upload(MultipartFile file) throws MrxuException {
        //获取文件MD5(unionKey)
        String unionKey = this.customFileInfoService.findUnionKey(file);
        //根据MD5判断是否已上传,若已经上传返回数据库信息
        FileInfoVO fileInfoVO = this.findFileInfo(unionKey);
        if(null != fileInfoVO){
            //已上传 - 返回
            return fileInfoVO;
        }
        //获取文件基本信息 -- 源文件上传
        UploadBaseDTO uploadBase = this.findUpdateBaseInfo(file, unionKey);
        //TODO：若后面执行失败，是否需要删除刚刚上传的文件，避免垃圾文件堆积
        //生成缩略图 --> 上传 -->保存
        this.generateThumbnail(uploadBase);
        //保存操作
        return this.saveFileInfo(uploadBase);
    }

    /**
     * 保存文件信息
     * @param uploadBase
     * @return
     */
    private FileInfoVO saveFileInfo(UploadBaseDTO uploadBase) throws MrxuException{
        Long currentTime = System.currentTimeMillis();
        Long fileId = vestaService.genId();
        FileInfo fileInfo = new FileInfo();
        BeanUtils.copyProperties(uploadBase, fileInfo);
        fileInfo.setGmtCreate(currentTime);
        fileInfo.setId(fileId.toString());
        fileInfo.setStatus(StatusEnum.enable.getStatus());
        this.fileInfoMapper.insert(fileInfo);
        if(uploadBase.getType().equalsIgnoreCase("Video")){
            this.joinTransSchedule(fileInfo);
        }
        FileInfoVO fileInfoVO = new FileInfoVO();
        BeanUtils.copyProperties(fileInfo, fileInfoVO);
        fileInfoVO.setFileId(fileInfo.getId());
        return fileInfoVO;
    }

    /**
     * 加入转码任务(视屏)
     * @param fileInfo
     */
    private void joinTransSchedule(FileInfo fileInfo){
        //TODO
    }

    /**
     * 图片与视屏需要缩略图，视屏需要加入任务表自动转码
     * @param uploadBase
     */
    private void generateThumbnail(UploadBaseDTO uploadBase) throws MrxuException{
        if(uploadBase.getType().equalsIgnoreCase("Pic") || uploadBase.getType().equalsIgnoreCase("Video")){
            String thumbnail = null;
            if(uploadBase.getType().equalsIgnoreCase("Pic")){
                String thumbnailPath = fileTempSaveDir + vestaService.genId() + "." + uploadBase.getExtName();
                try {
                    Double scale = this.customFileInfoService.getImageScale(uploadBase.getFile().getInputStream());
                    Thumbnails.of(uploadBase.getFile().getInputStream()).scale(scale).outputQuality(1f).toFile(thumbnailPath);
                    thumbnail = this.findFileSystemService().upload(thumbnailPath);
                } catch (IOException e) {
                    throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, e);
                }
            } else if(uploadBase.getType().equalsIgnoreCase("Video")){

            }
            if(StringUtils.isNotEmpty(thumbnail)){
                uploadBase.setThumbnail(thumbnail);
            }
        }
    }

    /**
     * 通過unionKey获取文件信息
     * @param unionKey
     * @return
     */
    public FileInfoVO findFileInfo(String unionKey){
        FileInfoExample fileInfoExample = new FileInfoExample();
        fileInfoExample.createCriteria().andUnionKeyEqualTo(unionKey);
        List<FileInfo> fileInfoList = fileInfoMapper.selectByExample(fileInfoExample);
        if(null != fileInfoList && !fileInfoList.isEmpty()){
            FileInfo fileInfo = fileInfoList.get(0);
            FileInfoVO fileInfoVO = new FileInfoVO();
            BeanUtils.copyProperties(fileInfo, fileInfoVO);
            fileInfoVO.setFileId(fileInfo.getId());
            return fileInfoVO;
        }
        return null;
    }

    /**
     * 获取文件基本信息
     * @param file
     * @return
     */
    private UploadBaseDTO findUpdateBaseInfo(MultipartFile file, String unionKey) throws MrxuException{
        //文件大小
        long fileSize = file.getSize();
        //上传文件名
        String filename = file.getOriginalFilename();
        //拓展名
        String extName = filename.substring(filename.lastIndexOf(".") + 1);
        //获取文件类型
        String type = FileTypeUtil.findResTypeEnum(filename);

        //上传文件基本信息
        UploadBaseDTO updateBase = new UploadBaseDTO();
        updateBase.setUnionKey(unionKey);
        updateBase.setFileName(filename);
        updateBase.setFileSize(fileSize);
        updateBase.setExtName(extName);
        updateBase.setFile(file);
        updateBase.setType(type);
        try {
            this.findFileSystemService().upload(updateBase, updateBase.getFile().getInputStream());
        } catch (IOException e) {
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR, "文件上传失败");
        }
        return updateBase;
    }

    /**
     * 获取转码服务 -- 本地ffmpeg 、oss云
     * @return
     */
    public IMediaService findMediaService(){
        if(FileServiceEnum.oss.getItemValue().equalsIgnoreCase(currentFileService)){
            return ossMediaTranscode;
        }
        return localMediaTranscode;
    }

    /**
     * 获取文件服务 -- 本地fdfs 、oss云
     * @return
     */
    public IFileSystemService findFileSystemService(){
        if(FileServiceEnum.oss.getItemValue().equalsIgnoreCase(currentFileService)){
            return ossService;
        }
        return fastdfsService;
    }
}
