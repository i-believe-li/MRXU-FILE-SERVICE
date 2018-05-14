package com.mrxu.cloud.file;

import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.context.annotation.ComponentScan;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/14
 */
@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.mrxu.cloud.*"})
public class FileServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FileServiceApplication.class).run(args);
    }
}
