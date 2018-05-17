package com.mrxu.cloud.file.config;

import com.mrxu.cloud.common.sequence.Sequence;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.Serializable;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/16
 */
@Component
public class SequenceConfig implements Serializable{
    private static final long serialVersionUID = -2229471357156454673L;
    //分布式高效有序ID
    private final Sequence sequence;

    /**
     * 构造函数(注入机器ID、数据中心ID)
     * @param workerId
     * @param dataCenterId
     */
    public SequenceConfig(@Value("${snowflake.worker_id}") Long workerId, @Value("${snowflake.data_center_id}") Long dataCenterId){
        super();
        workerId = workerId == null ? 10L : workerId;
        dataCenterId = dataCenterId == null ? 10L : dataCenterId;
        sequence = new Sequence(workerId, dataCenterId);
    }

    /**
     * 生成唯一ID
     * @return
     */
    public Long getNextId(){
        return sequence.nextId();
    }
}
