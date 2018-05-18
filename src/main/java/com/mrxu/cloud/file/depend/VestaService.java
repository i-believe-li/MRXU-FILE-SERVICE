package com.mrxu.cloud.file.depend;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/17
 */
@FeignClient(value = "vesta-service")
public interface VestaService {
    @GetMapping(value = "/vesta/genid")
    long genId();
}
