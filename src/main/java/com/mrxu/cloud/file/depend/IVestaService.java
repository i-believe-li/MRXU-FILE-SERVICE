package com.mrxu.cloud.file.depend;

import io.swagger.annotations.ApiOperation;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/5/17
 */
@FeignClient(value = "vesta-service", fallback = VestaServiceHystix.class)
public interface IVestaService {
    @ApiOperation(value = "生成ID")
    @RequestMapping(value = "/genid", method = {RequestMethod.GET, RequestMethod.POST})
    long genId();
}
