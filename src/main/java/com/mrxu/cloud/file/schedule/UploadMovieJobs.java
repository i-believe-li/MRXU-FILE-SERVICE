package com.mrxu.cloud.file.schedule;

import com.mrxu.cloud.file.service.file.IFileService;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.io.File;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/7/3
 */
@Component
public class UploadMovieJobs {
    private static Logger LOG = Logger.getLogger(UploadMovieJobs.class);
    //半小时执行一次
    public final static long HALF_MINUTES =  30 * 60 * 1000;
    @Resource(name = "fdfsService")
    IFileService fileService;
    @Value("${local.video.dir}")
    private String localVideoDir;

    /**
     * 执行完上一次后再执行
     */
    @Scheduled(fixedDelay = HALF_MINUTES)
    public void fixedDelayJob(){
        File file = new File(localVideoDir);
        if(!file.isDirectory()){
            LOG.warn(localVideoDir + "不是一个有效的目录！");
        }
        File[] files = file.listFiles();
        if(files.length > 0){
            for(File movieFile : files){
                VideoServiceThread videoServiceThread = new VideoServiceThread(movieFile);
                videoServiceThread.start();
            }
        }
    }

    class VideoServiceThread extends Thread{
        private File movie;
        public VideoServiceThread(File movie){
            this.movie = movie;
        }

        @Override
        public void run() {
            fileService.uploadLocalMovie(movie);
        }
    }
}
