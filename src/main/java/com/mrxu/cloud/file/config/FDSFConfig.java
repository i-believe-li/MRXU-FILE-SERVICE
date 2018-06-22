package com.mrxu.cloud.file.config;

import org.csource.fastdfs.ClientGlobal;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Properties;

/**
 * FastDFS配置
 * @author Administrator
 */
@Component
public class FDSFConfig {
	
	/**链接超时时间*/
	@Value("${fastdfs.connect_timeout_in_seconds:5}")
	private String connectTime;
	
	@Value("${fastdfs.network_timeout_in_seconds:30}")
	private String networkTimeout;
	
	@Value("${fastdfs.charset:UTF-8}")
	private String charset;
	
	
	@Value("${fastdfs.http_anti_steal_token:false}")
	private String hasToken;
	
	@Value("${fastdfs.http_secret_key:FastDFS1234567890}")
	private String secretKey;
	
	@Value("${fastdfs.http_tracker_http_port:80}")
	private String port;
	
	@Value("${fastdfs.tracker_servers:'localhost:22122'}")
	private String trackerServers;

	@Value("${fastdfs.ftp_server:'http://localhost/'}")
	private String ftpServerUrl;
	@Value("${fastdfs.local_dir:'/usr/local/tmp/'}")
	private String localPath;
	
	public String getLocalPath() {
		return localPath;
	}

	public void setLocalPath(String localPath) {
		this.localPath = localPath;
	}

	public String getFtpServerUrl() {
		return ftpServerUrl;
	}

	public void setFtpServerUrl(String ftpServerUrl) {
		this.ftpServerUrl = ftpServerUrl;
	}

	/**
	 * 获取配置转换为properties文件
	 * @return 转换后的properties
	 */
	public Properties getProperties() {
		Properties pro = new Properties();
		pro.setProperty(ClientGlobal.PROP_KEY_CONNECT_TIMEOUT_IN_SECONDS, connectTime);
		pro.setProperty(ClientGlobal.PROP_KEY_NETWORK_TIMEOUT_IN_SECONDS, networkTimeout);
		pro.setProperty(ClientGlobal.PROP_KEY_CHARSET, charset);
		pro.setProperty(ClientGlobal.PROP_KEY_HTTP_ANTI_STEAL_TOKEN, hasToken);
		pro.setProperty(ClientGlobal.PROP_KEY_HTTP_SECRET_KEY, secretKey);
		pro.setProperty(ClientGlobal.PROP_KEY_HTTP_TRACKER_HTTP_PORT, port);
		pro.setProperty(ClientGlobal.PROP_KEY_TRACKER_SERVERS, trackerServers);
		
		return pro;
	}
	
	public String getConnectTime() {
		return connectTime;
	}

	public void setConnectTime(String connectTime) {
		this.connectTime = connectTime;
	}

	public String getNetworkTimeout() {
		return networkTimeout;
	}

	public void setNetworkTimeout(String networkTimeout) {
		this.networkTimeout = networkTimeout;
	}

	public String getCharset() {
		return charset;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getHasToken() {
		return hasToken;
	}

	public void setHasToken(String hasToken) {
		this.hasToken = hasToken;
	}

	public String getSecretKey() {
		return secretKey;
	}

	public void setSecretKey(String secretKey) {
		this.secretKey = secretKey;
	}

	public String getPort() {
		return port;
	}

	public void setPort(String port) {
		this.port = port;
	}

	public String getTrackerServers() {
		return trackerServers;
	}

	public void setTrackerServers(String trackerServers) {
		this.trackerServers = trackerServers;
	}
	
	
	
}
