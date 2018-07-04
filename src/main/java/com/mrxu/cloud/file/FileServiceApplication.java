package com.mrxu.cloud.file;

import com.ctrip.framework.apollo.spring.annotation.EnableApolloConfig;
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
@EnableApolloConfig(value = {"file-service","commconfig"})
public class FileServiceApplication {
    public static void main(String[] args) {
        new SpringApplicationBuilder(FileServiceApplication.class).run(args);
    }
}
