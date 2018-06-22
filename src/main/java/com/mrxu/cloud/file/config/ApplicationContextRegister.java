package com.mrxu.cloud.file.config;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

/**
 * 获取上下文
 * @author ifocusing-xuzhiwei
 * @since 2017/10/17
 */
@Component
@Lazy(false)
public class ApplicationContextRegister implements ApplicationContextAware {

    private static ApplicationContext applicationContext;

    /**
     * 设置spring上下文  *  * @param applicationContext spring上下文  * @throws BeansException
     */
    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 获取对象 这里重写了bean方法，起主要作用
     *
     * @param beanId
     * @return  Object
     * @throws BeansException
     */
    public static Object getBean(String beanId) throws BeansException {
        return applicationContext.getBean(beanId);
    }
}

