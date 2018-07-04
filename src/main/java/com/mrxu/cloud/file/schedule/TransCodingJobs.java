package com.mrxu.cloud.file.schedule;

import com.mrxu.cloud.common.util.FileUtil;
import com.mrxu.cloud.file.config.ApplicationContextRegister;
import com.mrxu.cloud.file.depend.VestaService;
import com.mrxu.cloud.file.domain.FileTransSchedule;
import com.mrxu.cloud.file.domain.FileTransScheduleExample;
import com.mrxu.cloud.file.domain.Files;
import com.mrxu.cloud.file.domain.process.async.FileRequestTransDO;
import com.mrxu.cloud.file.domain.process.async.FileResponseTransDO;
import com.mrxu.cloud.file.domain.trans.TransExtendDTO;
import com.mrxu.cloud.file.enums.StatusEnum;
import com.mrxu.cloud.file.mapper.FileTransScheduleMapper;
import com.mrxu.cloud.file.mapper.FilesMapper;
import com.mrxu.cloud.file.service.file.custom.FileAuxiliaryService;
import com.mrxu.cloud.file.service.process.IFileProcessService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author ifocusing-xuzhiwei
 * @since 2017/11/22
 */
//@Component
public class TransCodingJobs {

    private static Logger LOG = Logger.getLogger(TransCodingJobs.class);

    //30秒执行一次
    public final static long HALF_MINUTES =  30 * 1000;
    @Autowired
    FileTransScheduleMapper fileTransScheduleMapper;
    @Autowired
    FileAuxiliaryService fileAuxiliaryService;
    @Autowired
    FilesMapper filesMapper;
    @Autowired
    VestaService vestaService;

    /**
     * 执行完上一次后再执行
     */
    @Scheduled(fixedDelay = HALF_MINUTES)
    public void fixedDelayJob(){
        //查询优先排除已经失败的
        FileTransScheduleExample example = new FileTransScheduleExample();
        example.createCriteria().andScheduleNumLessThanOrEqualTo(2);
        List<FileTransSchedule> fileTransScheduleList = fileTransScheduleMapper.selectByExample(example);
        if(null != fileTransScheduleList && !fileTransScheduleList.isEmpty()){
            for(FileTransSchedule fileTransSchedule : fileTransScheduleList){
                //判断是否需要执行当前任务
                if(!this.isNext(fileTransSchedule)){
                    continue;
                }

                /*
                    @Description 封装入参，传入线程异步处理
                    @Create By xuzw
                 */
                FileRequestTransDO request = genRequestParam(fileTransSchedule);
                Thread transThread = new Thread(new TransRunnable(request));
                transThread.start();

                //记录任务执行次数(做次数加一操作)
                this.addScheduleNum(fileTransSchedule);
            }
        } else {
            LOG.warn("提示：当前没有需要转码的任务！");
        }
    }

    /**
     * 强制规定时间重复执行
     */
    @Scheduled(fixedRate = HALF_MINUTES)
    public void fixedRateJob(){
        //System.out.println(Dates.format_yyyyMMddHHmmss(new Date())+" >>fixedRate执行....");
    }

    /**
     * 定时任务
     */
    @Scheduled(cron="0 15 3 * * ?")
    public void cronJob(){
        //System.out.println(Dates.format_yyyyMMddHHmmss(new Date())+" >>cron执行....");
    }

    /**
     * 内部类
     *      * final 不能被继承 -- 安全与效率
     * @author ifocusing-xuzhiwei
     * @since 2017/11/22
     */
    final class TransRunnable implements Runnable{
        /*
          入参
         */
        private FileRequestTransDO request;

        public TransRunnable(FileRequestTransDO request){
            this.request = request;
        }

        @Override
        public void run() {
            //源文件本地存放位置
            String originFilePath = request.getFilePath();
            //目标转码类型
            String transType = request.getTransType();
            //任务ID
            String scheduleId = request.getScheduleId();

            if(FileUtil.getFileExtName(originFilePath).equalsIgnoreCase(transType)){
                LOG.warn(">>>>>请求转码的目标文件就是源文件,该任务直接删除！");
                this.deleteSchedule(scheduleId, originFilePath);
                return;
            }

            //通过JavaBean和准备的入参执行转码操作  --  并返回包装结果
            IFileProcessService<FileRequestTransDO, FileResponseTransDO> transProcess =
                    (IFileProcessService<FileRequestTransDO, FileResponseTransDO>)
                            ApplicationContextRegister.getBean(request.getProcessInstance());
            FileResponseTransDO responseTrans = transProcess.process(request);

            //保存转码结果
            this.saveTransResult(responseTrans);
            //执行完毕删除任务
            this.deleteSchedule(scheduleId, originFilePath);

        }

        /**
         * 保存转码结果
         * @param responseTrans
         */
        private void saveTransResult(FileResponseTransDO responseTrans){
            //当前时间戳
            Long currentTime = System.currentTimeMillis();
            String fileId = String.valueOf(vestaService.genId());
            Files transFile = new Files();
            transFile.setId(fileId);
            transFile.setParentId(responseTrans.getParentId());
            transFile.setUnionKey(responseTrans.getTargetUnionCode());
            transFile.setCreatorId("0");
            transFile.setGmtCreate(currentTime);
            transFile.setFileName(responseTrans.getTargetFileName());
            transFile.setUrl(responseTrans.getTargetUrl());
            transFile.setTransType(responseTrans.getTargetTransType());
            transFile.setType(responseTrans.getTargetType());
            transFile.setThumbnail(responseTrans.getTargetThumbnail());
            transFile.setStatus(StatusEnum.on.getItemValue());
            fileAuxiliaryService.saveFileInfo(transFile);
            List<TransExtendDTO> list = responseTrans.getList();
            if (null != list && !list.isEmpty()) {
                int index = 1;
                for (TransExtendDTO transExtend : list) {
                    Files fileExtend = new Files();
                    fileExtend.setId(fileId + (index++));
                    fileExtend.setParentId(fileId);
                    fileExtend.setCreatorId("0");
                    fileExtend.setGmtCreate(currentTime);
                    fileExtend.setUnionKey("0");
                    fileExtend.setUrl(transExtend.getTargetUrl());
                    fileExtend.setFileName(transExtend.getFileName());
                    fileExtend.setTransType(transExtend.getTransType());
                    fileExtend.setType(transExtend.getType());
                    fileExtend.setStatus(StatusEnum.on.getItemValue());
                    fileAuxiliaryService.saveFileInfo(fileExtend);
                }
            }
        }

        /**
         * 执行完毕删除任务
         * @param scheduleId
         */
        private void deleteSchedule(String scheduleId, String originFilePath){
            fileAuxiliaryService.deleteFileTransSchedule(scheduleId);
            //临时文件删除控制(任务表不存在其他对应的转码任务才能删除，否则删除之后转码将会失败)
            if(0 == fileAuxiliaryService.countFileTransScheduleByFilePath(originFilePath)) {
                fileAuxiliaryService.deleteTempFile(scheduleId, originFilePath);
            }
        }

        public FileRequestTransDO getRequest() {
            return request;
        }

        public void setRequest(FileRequestTransDO request) {
            this.request = request;
        }
    }

    /**
     * 增加任务执行次数
     */
    private void addScheduleNum(FileTransSchedule fileTransSchedule){
        FileTransSchedule update = new FileTransSchedule();
        update.setId(fileTransSchedule.getId());
        update.setScheduleNum(fileTransSchedule.getScheduleNum()+1);
        update.setGmtModified(System.currentTimeMillis());
        fileTransScheduleMapper.updateByPrimaryKeySelective(update);
    }

    /**
     * 判断是否需要执行当前任务
     *     * 处于转码中
     *     * 如果发生错误，每一次需要间隔10分钟(不要跟30秒的定时任务混淆)
     *     true: 通过校验需要执行 false: 未通过校验无需执行
     * @param fileTransSchedule
     */
    private boolean isNext(FileTransSchedule fileTransSchedule){
        //上一次修改时间
        long modifyTime = fileTransSchedule.getGmtModified();
        //当前时间
        long currentTime = System.currentTimeMillis();
        //已执行次数
        int scheduleTime = fileTransSchedule.getScheduleNum();
        //如果错误后第二次及以上，需要间隔10分钟
        if(scheduleTime > 0 && (((currentTime - modifyTime)/1000)/60) < 10){
            //中断此次循环，继续下次循环
            return false;
        }
        return true;
    }

    /**
     * 封装请求转码数据（入参）
     * @param fileTransSchedule
     * @return
     */
    private FileRequestTransDO genRequestParam(FileTransSchedule fileTransSchedule){
        String transType = fileTransSchedule.getTransType();
        String fileId = fileTransSchedule.getFileId();
        Files fileInfo = filesMapper.selectByPrimaryKey(fileId);
        FileRequestTransDO request = new FileRequestTransDO();
        request.setFileId(fileId);
        request.setFileName(fileTransSchedule.getFileName());
        request.setFilePath(fileTransSchedule.getFilePath());
        request.setFileUrl(fileTransSchedule.getOriginUrl());
        request.setTransType(transType);
        request.setScheduleId(fileTransSchedule.getId());
        request.setThumbnail(fileInfo.getThumbnail());
        request.setProcessInstance(fileTransSchedule.getProcessInstance());
        return request;
    }
}
