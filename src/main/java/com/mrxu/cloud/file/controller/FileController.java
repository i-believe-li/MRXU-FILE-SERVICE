package com.mrxu.cloud.file.controller;

import com.mrxu.cloud.common.View;
import com.mrxu.cloud.common.enums.MessageCodeEnum;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.entity.vo.FileInfoVO;
import com.mrxu.cloud.file.service.IFileInfoService;
import com.mrxu.cloud.file.service.filesys.IFileSystemService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
@RestController
@RequestMapping(value = "/file/")
@Api(value = "文件处理(上传、删除、转码)", description = "文件处理")
public class FileController {

    @Autowired
    IFileInfoService fileInfoService;

    @ApiOperation("文件上传")
    @RequestMapping(value = {"upload"}, method = {RequestMethod.POST})
    public View<FileInfoVO> upload(@RequestParam MultipartFile file) throws MrxuException{
        FileInfoVO data = this.fileInfoService.upload(file);
        return MessageCodeEnum.SUCCESS.toView(data);
    }
}
