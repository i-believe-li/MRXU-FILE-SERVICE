package com.mrxu.cloud.file.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * 阿里云oss配置
 * @author ifocusing-xujia
 * @since 2017/4/28
 */
@Component
public class AliyunOSSConfig {
    @Value("${oss.accessKey:''}")
    public String oss_accessKey;
    @Value("${oss.secretKey:''}")
    public String oss_secretKey;
    @Value("${oss.endpoint:''}")
    public String oss_endpoint;
    @Value("${oss.bucket:''}")
    public String oss_bucket;
    @Value("${oss.bucket.temp:''}")
    public String oss_bucket_temp;
    @Value("${oss.location:''}")
    public String oss_location;
   
    @Value("${msts.url:''}")
    public String mstsUrl;
    

	@Value("${mts.pipelineId}")
    private String pipelineId;
    @Value("${mts.templateId}")
    
    
    private String templateId;
    @Value("${callback.url:''}")
    public String callback_url;
    @Value("${callback.host:''}")
    public String callback_host;
    @Value("${callback.bodyType:''}")
    public String callback_bodyType;
    

    public String getOss_accessKey() {
        return oss_accessKey;
    }

    public void setOss_accessKey(String oss_accessKey) {
        this.oss_accessKey = oss_accessKey;
    }

    public String getOss_secretKey() {
        return oss_secretKey;
    }

    public void setOss_secretKey(String oss_secretKey) {
        this.oss_secretKey = oss_secretKey;
    }

    public String getOss_endpoint() {
        return oss_endpoint;
    }

    public void setOss_endpoint(String oss_endpoint) {
        this.oss_endpoint = oss_endpoint;
    }

    public String getOss_bucket() {
        return oss_bucket;
    }

    public void setOss_bucket(String oss_bucket) {
        this.oss_bucket = oss_bucket;
    }

    public String getOss_bucket_temp() {
        return oss_bucket_temp;
    }

    public void setOss_bucket_temp(String oss_bucket_temp) {
        this.oss_bucket_temp = oss_bucket_temp;
    }

    public String getOss_location() {return oss_location;}

    public void setOss_location(String oss_location) {this.oss_location = oss_location;}

    public String getCallback_url() {
        return callback_url;
    }

    public void setCallback_url(String callback_url) {
        this.callback_url = callback_url;
    }

    public String getCallback_host() {
        return callback_host;
    }

    public void setCallback_host(String callback_host) {
        this.callback_host = callback_host;
    }

    public String getCallback_bodyType() {
        return callback_bodyType;
    }

    public void setCallback_bodyType(String callback_bodyType) {
        this.callback_bodyType = callback_bodyType;
    }
    
    public String getMstsUrl() {
		return mstsUrl;
	}

	public void setMstsUrl(String mstsUrl) {
		this.mstsUrl = mstsUrl;
	}

	public String getPipelineId() {
		return pipelineId;
	}

	public void setPipelineId(String pipelineId) {
		this.pipelineId = pipelineId;
	}

	public String getTemplateId() {
		return templateId;
	}

	public void setTemplateId(String templateId) {
		this.templateId = templateId;
	}
}
