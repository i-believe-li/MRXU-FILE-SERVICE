package com.mrxu.cloud.file.controller;

import com.mrxu.cloud.common.View;
import com.mrxu.cloud.file.service.IFileService;
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

    @Resource(name = "fastdfs")
    IFileService fileService;

    @ApiOperation("上传图片")
    @RequestMapping(value = {"upload"}, method = {RequestMethod.POST})
    public View<String> upload(@RequestParam MultipartFile file){
        String url = fileService.upload(file);
        View view = new View();
        view.setCode(200);
        view.setData(url);
        return view ;
    }
}
