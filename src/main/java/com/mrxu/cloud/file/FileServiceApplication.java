package com.mrxu.cloud.file;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.feign.EnableFeignClients;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
@EnableFeignClients
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.mrxu.cloud.*"})
public class FileServiceApplication {
    public static void main(String[] args) {
        //System.setProperty("org.eclipse.jetty.server.Request.maxFormContentSize", String.valueOf(Integer.MAX_VALUE));
        //System.setProperty("org.eclipse.jetty.server.Request.maxFormKeys", String.valueOf(Integer.MAX_VALUE));
        new SpringApplicationBuilder(FileServiceApplication.class).run(args);
    }
}
