package com.mrxu.cloud.file.service.process.trans.fdfs;

import com.mrxu.cloud.common.enums.MrxuExceptionEnums;
import com.mrxu.cloud.common.exception.MrxuException;
import com.mrxu.cloud.common.util.CMDExecteUtil;
import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.config.FDSFConfig;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.process.async.FileRequestTransDO;
import com.mrxu.cloud.file.domain.process.async.FileResponseTransDO;
import com.mrxu.cloud.file.domain.trans.TransExtendDTO;
import com.mrxu.cloud.file.service.file.IFileService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import com.mrxu.cloud.file.util.FileTypeUtil;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import javax.annotation.Resource;
import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 */
@Service("m3u8TransProcess")
public class M3u8TransProcessServiceImpl implements IFileProcessService<FileRequestTransDO, FileResponseTransDO> {

    private Logger LOG = Logger.getLogger(M3u8TransProcessServiceImpl.class);

    @Value("${transcode.tom3u8:'ffmpeg -y -i #source# -c:v libx264 -c:a aac -strict -2 -f hls -hls_list_size 0 -hls_time 10 #target#'}")
    private String transferCMD;
    @Autowired
    private VestaService vestaService;

    @Resource(name = "fdfsService")
    private IFileService fileService;

    @Autowired
    FDSFConfig config;

    @Override
    public FileResponseTransDO process(FileRequestTransDO request) throws MrxuException {
        //临时文件目录
        String tempDir = config.getLocalPath();
        //源文件路径
        String filePath = request.getFilePath();
        //源文件名
        String fileName = request.getFileName();

        if(org.apache.commons.lang.StringUtils.isNotEmpty(fileName)){
            //获取不带后缀的文件名
            fileName = request.getFileName().substring(0, request.getFileName().indexOf("."));
        }
        if(!new File(filePath).exists()){
            //TODO 文件不存在需要下载到本地再处理
            String originUrl = request.getFileUrl();
            //下载
            FileUtil.downLoadFromUrl(originUrl, filePath);
        }
        //转码拓展
        List<TransExtendDTO> tsList = new ArrayList<>();

        String uuid = String.valueOf(vestaService.genId());
        String m3u8FullPath = tempDir + uuid + ".m3u8";
        String m3u8NewFullPath = tempDir + uuid + "_1.m3u8";

        //转码操作
        String tmpCMD = transferCMD.replace("#source#", filePath).replace("#target#", m3u8FullPath);
        LOG.info("tmpCMD: " + tmpCMD);
        CMDExecteUtil execute = new CMDExecteUtil();
        execute.exec(tmpCMD);

        FileReader fr = null;
        BufferedReader br = null;
        FileWriter fw = null;
        BufferedWriter bw = null;
        try {
            fr = new FileReader(m3u8FullPath);
            br = new BufferedReader(fr); // 将流整体读取。
            fw = new FileWriter(m3u8NewFullPath);
            bw = new BufferedWriter(fw); // 将流整体写入。
            String ts;
            String tsUrl;
            while ((ts = br.readLine()) != null) {// 判断是否是最后一行
                if (StringUtils.isEmpty(ts)) {
                    continue;
                }
                if (ts.startsWith("#")) {
                    bw.write(ts + "\n");
                    continue;
                }
                tsUrl = this.fileService.uploadFileToServer(tempDir + "/" + ts,true,true);
                bw.write(tsUrl.substring(tsUrl.lastIndexOf("/") + 1) + "\n");
                TransExtendDTO transExtend = new TransExtendDTO();
                transExtend.setFileName(request.getFileName());
                transExtend.setTargetUrl(tsUrl);
                transExtend.setTransType("ts");
                transExtend.setType("ts");
                tsList.add(transExtend);
            }
            bw.flush();
            bw.close();
            fw.close();
            br.close();
            fr.close();
            String m3u8Url = this.fileService.uploadFileToServer(m3u8NewFullPath,true,false);
            //计算MD5
            String unionCode = FileUtil.calculateFileMD5(new File(m3u8NewFullPath), false);

            //返回对象封装
            FileResponseTransDO fileResponse = new FileResponseTransDO();
            fileResponse.setParentId(request.getFileId());
            fileResponse.setTargetFileExtension("m3u8");
            fileResponse.setTargetFileName(fileName + ".m3u8");
            //视频缩略图可以沿用父类抽帧
            fileResponse.setTargetThumbnail(request.getThumbnail());
            fileResponse.setTargetType(FileTypeUtil.findResTypeEnum(request.getTransType()));
            fileResponse.setTargetTransType(request.getTransType());
            fileResponse.setTargetUnionCode(unionCode);
            fileResponse.setTargetUrl(m3u8Url);
            //ts集合
            fileResponse.setList(tsList);
            return fileResponse;
        } catch (FileNotFoundException e) {
            LOG.error(e);
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
        } // 获取文件流
        catch (IOException e1) {
            e1.printStackTrace();
            LOG.error(e1);
            throw new MrxuException(MrxuExceptionEnums.RC_COMMON_ERROR);
        } finally {
            if (bw != null) {
                try {
                    bw.close();
                } catch (IOException e) {
                    LOG.error(e);
                }

            }
            if (fw != null) {
                try {
                    fw.close();
                } catch (IOException e) {
                    LOG.error(e);
                }

            }
            if (br != null) {
                try {
                    br.close();
                } catch (IOException e) {
                    LOG.error(e);
                }

            }
            if (fr != null) {
                try {
                    fr.close();
                } catch (IOException e) {
                    LOG.error(e);
                }

            }
            File m3u8File = new File(m3u8FullPath);
            if(m3u8File.exists()){
                m3u8File.delete();
            }
            File m3u8FileNew = new File(m3u8NewFullPath);
            if(m3u8FileNew.exists()){
                m3u8FileNew.delete();
            }
        }
    }
}
