package com.mrxu.cloud.file.service.process;

import com.mrxu.cloud.common.exception.MrxuException;

import java.io.IOException;

/**
 * 文件处理
 * @author ifocusing-xuzhiwei
 * @since 2018/6/15
 * @param <K> 入参1
 * @param <T> 出参
 */
public interface IFileProcessService<K, T> {

    /**
     * 文件处理
     * @param k
     * @return
     * @throws IOException 
     */
     T process(K k) throws MrxuException;
}
