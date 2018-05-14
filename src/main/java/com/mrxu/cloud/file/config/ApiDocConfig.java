package com.mrxu.cloud.file.config;

import com.mrxu.cloud.common.config.Swagger2Config;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import springfox.documentation.builders.PathSelectors;
import springfox.documentation.builders.RequestHandlerSelectors;
import springfox.documentation.spi.DocumentationType;
import springfox.documentation.spring.web.plugins.Docket;
import springfox.documentation.swagger2.annotations.EnableSwagger2;

/**
 * @author ifocusing-xuzhiwei
 * @since 2018/3/7
 */
@Configuration
@EnableSwagger2
public class ApiDocConfig extends Swagger2Config{
    @Bean
    public Docket createRestApi() {

        /*ParameterBuilder tokenPar = new ParameterBuilder();
        List<Parameter> pars = new ArrayList<Parameter>();
        tokenPar.name(SecurityHeader.TOKEN).description("令牌").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(tokenPar.build());

        ParameterBuilder clientPar = new ParameterBuilder();
        clientPar.name(SecurityHeader.CLI_INFO).description("设备信息").modelRef(new ModelRef("string")).parameterType("header").required(false).build();
        pars.add(clientPar.build());*/

        return new Docket(DocumentationType.SWAGGER_2)
                .apiInfo(apiInfo())
                //.ignoredParameterTypes(CurrentUser.class, CurrentClient.class)
                .select()
                .apis(RequestHandlerSelectors.basePackage("com.mrxu.cloud"))
                .paths(PathSelectors.any())
                .build();
                //.globalOperationParameters(pars);
    }
}
