package com.mrxu.cloud.file.controller;

import com.mrxu.cloud.common.View;
import com.mrxu.cloud.common.config.Constants;
import com.mrxu.cloud.common.enums.MessageCodeEnum;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.domain.entity.file.FileResultVO;
import com.mrxu.cloud.file.domain.entity.trans.TransCodingResultVO;
import com.mrxu.cloud.file.service.file.IFileService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
@RestController
@RequestMapping(value = "/file/")
@Api(value = "文件处理(上传、删除、转码)", description = "文件处理")
public class FileController {

    @Value("${file.server.prop:fdfs}")
    private String fileServerProp;

    @Resource(name = "fdfsService")
    IFileService fdfsService;
    @Resource(name = "ossService")
    IFileService ossService;

    @ApiOperation(value = "文件上传服务", notes = "根据文件上传,返回文件的url以及首帧的地址")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file")
    @RequestMapping(value = "/upload",method = RequestMethod.POST)
    public View<FileResultVO> upload(@RequestParam("file") MultipartFile file) throws MrxuException {
        if (file == null) MessageCodeEnum.ERROR.toView();
        FileResultVO fileResult = this.getFileService().uploadFile(file);
        View view = MessageCodeEnum.SUCCESS.toView();
        view.setData(fileResult);
        return view;
    }

    @ApiOperation(value = "文件上传服务", notes = "根据文件上传,返回文件的url以及首帧的地址")
    @ApiImplicitParam(name = "file", value = "文件", required = true, dataType = "file")
    @RequestMapping(value = "/multiple/upload",method = RequestMethod.POST)
    public View<ArrayList<FileResultVO>> upload(@RequestParam(value = "files")MultipartFile[] files) throws MrxuException{
        if (files == null || files.length <= 0) MessageCodeEnum.ERROR.toView();
        List<FileResultVO> fileResultList = getFileService().uploadMultipleFile(files);
        if(null == fileResultList){
            return MessageCodeEnum.ERROR.toView();
        }
        View view = MessageCodeEnum.SUCCESS.toView();
        view.setData((ArrayList<FileResultVO>) fileResultList);
        return view;
    }

    @ApiOperation(value = "文件转码服务", notes = "根据提供的文件的URL 和目标文件的类型进行转码")
    @RequestMapping(value = "/transcode",method = RequestMethod.POST)
    public View<TransCodingResultVO> transCode(@RequestParam String fileUrl, @RequestParam String targetType) throws MrxuException{
        TransCodingResultVO transCodingResultVO;
        try{
            transCodingResultVO = this.getFileService().findTransInfo(fileUrl, targetType, fileServerProp);
            if(null == transCodingResultVO) return MessageCodeEnum.ERROR.toView();
        } catch (MrxuException me){
            View view = MessageCodeEnum.ERROR.toView();
            view.setMessage(me.getMessage().replaceAll("50001", "").replaceAll("@", ""));
            return view;
        }
        View view = MessageCodeEnum.SUCCESS.toView();
        view.setData(transCodingResultVO);
        return view;
    }

    @ApiOperation(value = "查询文件是否存在", notes = "根据提供的文件的URL 查询文件是否存在")
    @ApiImplicitParam(name = "fileUrl", value = "文件的地址", required = true, dataType = "string")
    @RequestMapping(value = "/exist",method = RequestMethod.GET)
    public boolean exist(@RequestParam String fileUrl) {
        return getFileService().exist(fileUrl);
    }

    @ApiOperation(value = "文件删除服务", notes = "根据提供的文件的URL 删除文件")
    @ApiImplicitParam(name = "fileUrl", value = "文件的地址", required = true, dataType = "string")
    @RequestMapping(value = "/delete",method = RequestMethod.POST)
    public int delete(@RequestParam String fileUrl) {
        return getFileService().deleteFile(fileUrl);
    }

    /**
     * 根据配置获取文件上传服务
     * @return
     */
    private IFileService getFileService() {
        if (fileServerProp.equalsIgnoreCase(Constants.FILESERVER_OSS)) {
            return ossService;
        }
        return fdfsService;
    }
}
